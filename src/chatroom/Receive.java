package chatroom;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	private DataInputStream dis;
	private boolean isRunning=true;
	private FrameChat fc;
	
	public Receive(Socket socket,FrameChat fc) {
		this.fc=fc;
		try {
			dis=new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			isRunning=false;
			CloseUtil.closeAll(dis);
		}
	}
	
	public String receive() {
		String msg="";
		try {
			msg= dis.readUTF();
		} catch (IOException e) {
			isRunning=false;
			CloseUtil.closeAll(dis);
		}
		return msg;
		
	}
	
	
	@Override
	public void run() {
		while(isRunning) {
			fc.receive(receive());
		}
		
	}
	
}
