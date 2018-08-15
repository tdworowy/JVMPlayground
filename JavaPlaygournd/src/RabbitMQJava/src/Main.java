import RabbitMQJava.src.Receiver;
import RabbitMQJava.src.Sender;

public class Main {
	
	
 public static int index = 1;
 public static Object obj = new Object();
 
	public static void main(String [ ] args)
	{
		Receiver receiver = new Receiver();
		Sender sender = new Sender();
	
		sender.openQuee();
		receiver.OpenQuee();
		
		
		
Runnable task1 =() -> {
		 try {
		
			while(true){
				sender.SendMessage("JAVA_Message"+index + " From Thread: "+Thread.currentThread().getName(), sender.getChannel());
				synchronized (obj) {
					index++;	
				}
				Thread.sleep(2000);
			
			}
		 } 
		 catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
							
			}
		};
		
Runnable task2 =() ->{
	      try {	   
			while(true){
				receiver.reciveMessage(receiver.getChannel());
				Thread.sleep(2000);
				}
			}
	      catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
	      }
			};
	
	
		
	
			Thread thread1 = new Thread(task1);
			thread1.setName("Thread1");
			Thread thread2 = new Thread(task2);
			thread2.setName("Thread2");
			
			thread1.start();
			thread2.start();
	}
	
	
	
	}

