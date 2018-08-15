package web;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;


/**
 * Created by Tomek on 2016-11-09.
 */
public class RestClient{

    private String SERVER;
    private String responseString;
    private CloseableHttpClient httpclient;
    private CloseableHttpResponse response = null;

    private static final int timeout = 45 * 1000;
    private static final String RESTURL = "";

    public RestClient() {
        createHttpClient();
    }
    public RestClient(Boolean ssl) {

        if(ssl){createHhttpsClient();}
        else createHttpClient();
    }




    public void loginSuser(){
        sendPostRequest("/rest/login?login=suser&password=suser","");
        //sendPostRequest("/rest/login?login=suser&password=kit2000","");
    }

    public void loginAs(String login , String pass){
        sendPostRequest("/rest/login?login="+login+"&password="+pass+"","");
    }

    public void sendPostRequest(String requestUrl, String payload) {

        String url = this.SERVER + requestUrl;
        System.out.println("RequestUrl: "+url);

        try {

            this.response = this.httpclient.execute(createPost(payload,url));

            this.responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(response.getStatusLine().toString());
            System.out.println(responseString);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public void sendPostFileRequest(String requestUrl, String payload) {
        System.out.println("RequestUrl: "+requestUrl);


        String url = this.SERVER + requestUrl;
        System.out.println("RequestUrl: "+url);
        try {


            HttpEntity entity= createMultiPartEntity(payload);
            System.out.println("Entity: "+entity.getContent());
            this.response = this.httpclient.execute(createPost(entity,url));

            this.responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(response.getStatusLine().toString());
            System.out.println(responseString);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }


    public void sendPutRequest(String requestUrl, String payload) {

        String url = this.SERVER + requestUrl;
        System.out.println("RequestUrl: "+url);
        try {

            this.response = this.httpclient.execute(createPut(payload,url));

            this.responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(response.getStatusLine().toString());
            System.out.println(responseString);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    public String sendGetRequest(String requestUrl) {
        System.out.println("RequestUrl: "+requestUrl);
        String  returnString = "";
        try {
            String url = this.SERVER + requestUrl;
            HttpGet httpGet = new HttpGet(url);

            this.response = this.httpclient.execute(httpGet);
            returnString = EntityUtils.toString(response.getEntity(), "UTF-8");

            System.out.println(getResponseStatus());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return  returnString;
    }

    public String sendDeleteRequest(String requestUrl) {
        System.out.println("RequestUrl: "+requestUrl);
        String  returnString = "";
        try {
            String url = this.SERVER + requestUrl;
            HttpDelete httpDelete = new HttpDelete(url);

            this.response = this.httpclient.execute(httpDelete);
            System.out.println(getResponseStatus());

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return  returnString;
    }
    public String getResponseStatus(){

        return  response.getStatusLine().toString();
    }

    public String getResponse(){

        return responseString;
    }


    private static HttpEntity createStringEntity(String params) {
        StringEntity se;
        se = new StringEntity(params, "UTF-8");
        se.setContentType("application/json; charset=UTF-8");
        return se;
    }

    private static HttpEntity createMultiPartEntity(String params) throws IOException {

        MultipartEntityBuilder  builder  = MultipartEntityBuilder.create();
        builder .setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        File tempFile = createTempJson(params);
        builder .addBinaryBody("file", tempFile, ContentType.create("Content-Type: application/octet-stream"),tempFile.getName());
        return builder.build() ;
    }

    private static HttpUriRequest createPost(String params, String url) {
        HttpPost post = new HttpPost(url);
        post.setEntity(createStringEntity(params));
        return post;
    }
    private static HttpUriRequest createPost(HttpEntity  params, String url) {
        HttpPost post = new HttpPost(url);
        post.setEntity(params);
        return post;
    }

    private static HttpUriRequest createPut(String params, String url) {
        HttpPut put = new HttpPut(url);
        put.setEntity(createStringEntity(params));
        return put;
    }

    public void close (){
        try {
            this.response.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            this.httpclient.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createHttpClient(){

        try {

            System.out.println("Rest url : "+RESTURL);
            this.SERVER = RESTURL;
            this.httpclient = getDefaultHttpClientBuilder()
                    .build();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    private void createHhttpsClient(){

        SSLContextBuilder builder = new SSLContextBuilder();

        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory  sslsf = new SSLConnectionSocketFactory(builder.build());

            System.out.println("Rest url : "+RESTURL);
            this.SERVER = RESTURL;
            this.httpclient = getDefaultHttpClientBuilder()
                    .setSSLSocketFactory(sslsf)
                    .build();


        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

    }

    private static RequestConfig getRequestConfig(){
        return   RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .setSocketTimeout(timeout)
                .build();
    }
    private static HttpClientBuilder getDefaultHttpClientBuilder(){
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(getRequestConfig())
                .setRetryHandler(getRetryHandler(2))
                .setMaxConnTotal(5)
                .setMaxConnPerRoute(5)
                .setConnectionTimeToLive(30, TimeUnit.SECONDS);
    }
    private static HttpRequestRetryHandler getRetryHandler(int trays){
        return new DefaultHttpRequestRetryHandler(trays, true);

    }


    private static File createTempJson(String json) throws IOException {

        InputStream in = new ByteArrayInputStream(json.getBytes());
        final File tempFile = File.createTempFile("Temp", ".json");
        tempFile.deleteOnExit();
        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(in, out);
        }
        return tempFile;
    }



}
