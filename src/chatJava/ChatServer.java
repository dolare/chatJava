package chatJava;
import java.awt.*;
import java.awt.List;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;

public class ChatServer {
	ServerSocket ss = null;
	Socket soc = null;
	
	ArrayList<Client> clients = new ArrayList<Client>();
	
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
				    
				    clients.add(c);
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
		private DataOutputStream dos = null;
		public Client(Socket soc){
			this.soc = soc;
			try {
				this.dis = new DataInputStream(soc.getInputStream());
				this.dos = new  DataOutputStream(soc.getOutputStream());
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
					for(int i = 0; i < clients.size();i++){
						Client c = clients.get(i);
						c.send(str);
					}
					}
				} catch (EOFException e) {			
					System.out.print("clinet has disconnected");
				}
				catch (IOException e) {					
					e.printStackTrace();
				}finally{
					try{
						
						if(dis!=null)dis.close();
						if(dos!=null)dos.close();
					}catch (IOException e) {					
						e.printStackTrace();
					}	
					
				}

			}
		
		
		public void send(String str){
			
			try {
				
				dos.writeUTF(str);
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
	}
		
		
	

}
