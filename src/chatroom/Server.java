package chatroom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private ArrayList<MyChannel> allClients=new ArrayList<MyChannel>();
	public static void main(String[] args) throws IOException {
		Server server=new Server();
		server.serverStart();
	}
	
	public void serverStart() throws IOException {
		ServerSocket server=new ServerSocket(10086);
		System.out.print("server stated ---prot:10086");
		while(true) {
			Socket client=server.accept();
			MyChannel channel=new MyChannel(client);
			channel.sendGetIn();
			allClients.add(channel);
			new Thread(channel).start();
		}
	}
	
	private class MyChannel	implements Runnable{
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning=true;
		public MyChannel(Socket socket) {
			try {
				dis=new DataInputStream(socket.getInputStream());
				dos=new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				whenExc();
			}
		}
		
		
		public String receive() {
			String msg="";
			try {
				msg=dis.readUTF();
			} catch (IOException e) {
				whenExc();
			}
			return msg;
		}
		public void sendGetIn() {
			for(MyChannel client:allClients) {	
				if(client==this) {
					client.send("welcome join chat room");
				}
				client.send("------Someone joined the chat room-------\r\n");
			}
		}
		
		public void sendMsgAll() {
			String msg=receive();
			if(msg.equals("")) {
				return;
			}
			for(MyChannel client:allClients) {	
				if(client==this) {
					continue;
				}
				client.send(msg+"----from client"+allClients.indexOf(this)+"\r\n");
			}
						
		}
		public void send(String msg) {
			if(msg.equals("")) {
				return;
			}
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				whenExc();
			}
		}
		
		public void whenExc() {
			CloseUtil.closeAll(dis,dos);
			allClients.remove(this);
			isRunning=false;
		}
		
		
		@Override
		public void run() {
			while(isRunning) {
				sendMsgAll();
			}
			
		}
		
	}
}
