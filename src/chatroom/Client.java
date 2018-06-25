package chatroom;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket client=new Socket("localhost",10086);			
		Send send=new Send(client);
		FrameChat fc=new FrameChat(send);		
		new Thread(new Receive(client,fc)).start();
	}
	
	
}
