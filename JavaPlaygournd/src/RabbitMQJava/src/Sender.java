package RabbitMQJava.src;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.Channel;

public class Sender {
	
	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	
	public Sender(){
		this.factory = new ConnectionFactory();
	    this.factory.setHost(ConnectionInfo.getHost());
	    this.factory.setUsername(ConnectionInfo.getUserName());
	    this.factory.setPassword(ConnectionInfo.getPassword());
	    this.factory.setPort(ConnectionInfo.getPort());
	    this.factory.setVirtualHost(ConnectionInfo.getVirtualHost());
	    
	}
	
	
	public void SendMessage(String message,	Channel channel){
		
		try{
		channel.basicPublish("", ConnectionInfo.getPythonQueue(), null, message.getBytes());
		System.out.println("Message Sent: '" + message + "'");
		}
		catch (IOException e) {
			System.out.println("RabbitMQ server is Down !");
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
	}
	
	
	public void closeQuee(Channel channel){
		try{
			channel.close();
			connection.close();
		}
		catch (IOException e) {
			System.out.println("RabbitMQ server is Down !");
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	public void openQuee(){
		try {
			connection = factory.newConnection();

			this.channel = connection.createChannel();			
			channel.queueDeclare(ConnectionInfo.getPythonQueue(), false, false, false, null);
				
			
		} catch (IOException e) {
			System.out.println("RabbitMQ server is Down !");
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
			}
	
	public Channel getChannel(){
		return channel;
	}
	
}
