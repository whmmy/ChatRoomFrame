package chatroom;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Send{
	private DataOutputStream dos;
	private boolean isRunning=true;
	
	public Send(Socket socket) {
		try {
			dos=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			isRunning=false;
			CloseUtil.closeAll(dos);
		}
	}
	
	public String getMsg() {
		return"";
	}
	
	public void send(String msg) {
		if(msg!=null||!msg.equals("")) {
			try {
				dos.writeUTF(msg);
				dos.flush();			
				//System.out.println("“‘∑¢ÀÕ"+msg);
			} catch (IOException e) {
				isRunning=false;
				CloseUtil.closeAll(dos);
			}
		}
	}
		
	
	
}
