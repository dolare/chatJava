package chatJava;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;
import java.io.*;


public class ChatClient extends Frame{
	Socket soc = null;
	DataOutputStream dos = null;
	
	TextField tf1 = new TextField ();
	
	TextArea ta1 =  new TextArea();
	
	public static void main(String args[]){
		
			new ChatClient().launchFrame();
		
		
	}
	
	public void connect(){
		try {
		    soc =new Socket("127.0.0.1",8888);
		    dos = new DataOutputStream(soc.getOutputStream());
			System.out.println("Clinet has been connected");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void disconnect(){
		try {
			dos.close();
			soc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void launchFrame(){
		setLocation(400,300);
		this.setSize(300 ,300);
		add(tf1 , BorderLayout.SOUTH);
		add(ta1,BorderLayout.NORTH);
		pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg) {
				disconnect();
				System.exit(0);
			}
			
		});
		tf1.addActionListener(new TFListener());
		setVisible(true);
		
		connect();
	}
	private class TFListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String str = tf1.getText().trim();
			ta1.setText(str);
			tf1.setText("");
			try {

				dos.writeUTF(str);
				dos.flush();
				//dos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
