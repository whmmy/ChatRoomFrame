package chatroom;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {
	
	public static void closeAll(Closeable... io) {
		for(Closeable t:io) {
			try {
				t.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
