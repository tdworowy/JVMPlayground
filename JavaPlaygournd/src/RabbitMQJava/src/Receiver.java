package RabbitMQJava.src;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Receiver {
	

	private ConnectionFactory factory;
	private Connection connection;
	private ConnectionInfo info ;
	private Channel channel;
	
	public Receiver(){
		this.factory = new ConnectionFactory();
	    this.factory.setHost(ConnectionInfo.getHost());
	    this.factory.setUsername(ConnectionInfo.getUserName());
	    this.factory.setPassword(ConnectionInfo.getPassword());
	    this.factory.setPort(ConnectionInfo.getPort());
	    this.factory.setVirtualHost(ConnectionInfo.getVirtualHost());
	    
	}
	
	
	public Channel getChannel(){
		return channel;
	}
	
	public void OpenQuee(){
		try {
		this.connection = factory.newConnection();	
		this.channel = connection.createChannel();
		this.channel.queueDeclare(ConnectionInfo.getJavaQueue(), false, false, false, null);
		
		
		} catch (IOException e) {
			System.out.println("RabbitMQ server is Down !");
			System.out.println(e.getMessage());
		
			
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
	
	
	public void reciveMessage(Channel channel){
		try {
						
			System.out.println("{N|T} Waiting for messages.");
			
			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println("Message Received '" + message + "'");
				}
			};
			// loop that waits for message 		
			channel.basicConsume(ConnectionInfo.getJavaQueue(), true, consumer);
		} catch (IOException e) {
			System.out.println("RabbitMQ server is Down !");
		
		}
}
		
	
		}