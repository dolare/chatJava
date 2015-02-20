package chatJava;

import java.awt.*;
import java.awt.event.*;

public class ChatClient extends Frame{
	
	TextField tf1 = new TextField ();
	
	TextArea ta1 =  new TextArea();
	
	public static void main(String args[]){
		new ChatClient().launchFrame();
	}
	
	public void launchFrame(){
		setLocation(400,300);
		this.setSize(300 ,300);
		add(tf1 , BorderLayout.SOUTH);
		add(ta1,BorderLayout.NORTH);
		pack();
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
			
		});
		tf1.addActionListener(new TFListener());
		setVisible(true);
		
		;
	}
	private class TFListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String s = tf1.getText().trim();
			ta1.setText(s);
			tf1.setText("");
		}
	}
}
