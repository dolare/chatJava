package chatJava;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.io.*;


public class ChatServer {
	public static void main(String args[]){
		boolean started = false;
		
		DataInputStream dis = null; 
		try {
			ServerSocket ss = new ServerSocket(8888);
			started = true;
			Socket soc = ss.accept();
			System.out.println("a client has connected");
			
			while(started){	
				dis = new DataInputStream(soc.getInputStream());
				String str = dis.readUTF();
				System.out.println(str);	
			}dis.close();
			
		} catch (IOException e) {	
			e.printStackTrace();
		}
	}
}
