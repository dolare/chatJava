package chatJava;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.io.*;


public class ChatServer {
	ServerSocket ss = null;
	Socket soc = null;
	
	
	public void start(){
		boolean started = false;

		try {
			ss = new ServerSocket(8888);
			started = true;
		    
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			try {
				while(started){	
					soc = ss.accept();
				    
				    Client c = new Client(soc);
				    
				    System.out.println("a client has connected");
				    
				    new Thread(c).start();
				}
				
			}
			catch (IOException e) {					
				e.printStackTrace();
			}finally{
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	
	public static void main(String args[]){
		new ChatServer().start();
		
	}



	//class Client extends Thread{
	class Client implements Runnable{
		boolean hasConnect ;
		private Socket soc = null;
		private DataInputStream dis = null;
		public Client(Socket soc){
			this.soc = soc;
			try {
				this.dis = new DataInputStream(soc.getInputStream());
				hasConnect = true;
			} catch (IOException e) {
	
				e.printStackTrace();
			}
		}
		public void run() {
				try {
					while(hasConnect){
					String str = dis.readUTF();
					System.out.println(str);	
					}
				} catch (EOFException e) {			
					System.out.print("clinet has disconnected");
				}
				catch (IOException e) {					
					e.printStackTrace();
				}finally{
					try{
						if(dis!=null)dis.close();
					}catch (IOException e) {					
						e.printStackTrace();
					}	
				}

			}
		}
		
		
	

}
