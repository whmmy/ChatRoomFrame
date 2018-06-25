package chatroom;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class FrameChat {
	private JTextArea sendText;
	private JTextArea chatText;
	private JButton btn;
	private JFrame f;
	private Send send;
	private JTextArea name;
	private StringBuffer sb=new StringBuffer();
	private String userN;
	private String msg;
	public FrameChat(Send send) {
		this.send=send;
		this.sendText=new JTextArea();
		this.chatText=new JTextArea();
		this.btn=new JButton("send");
		this.name=new JTextArea(" Mystery Men");
		initFrame();
		initView();
	}
	
	private void initFrame() {
		f =new JFrame("HM_Chat");
		f.setSize(620, 800);
		f.setLocation(200, 200);
		f.setVisible(true);				
		f.setLayout(null);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	private void initView() {
		JLabel lab=new JLabel("User Name:", SwingConstants.CENTER);
		lab.setBounds(50,30, 100, 30);
		name.setBounds(160, 30, 100, 30);
		chatText.setBounds(50, 70, 500, 450);
		chatText.setEditable(false);
		sendText.setBounds(50, 530, 500, 80);
		btn.setBounds(50, 650, 500, 50);
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!(sendText.getText()).equals("")) {
					setMsg();
					send.send(setOtherMsg());
					sendMsg(setSelfMsg());					
				}
			}
		});
		f.add(lab);
		f.add(name);
		f.add(chatText);
		f.add(sendText);
		f.add(btn);	
	}
	
	private String setSelfMsg() {
		return "Me:"+msg;
	}
	
	private String setOtherMsg() {
		return userN+":"+msg;
	}
	private void setMsg() {
		userN=name.getText();
		SimpleDateFormat df = 
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		msg= sendText.getText()+"\r\n-------------date:"+df.format(new Date());
	}
	
	private void sendMsg(String msg) {
		sb.append(msg+"\r\n");
		chatText.setText(sb.toString());
		sendText.setText("");
	}
	
	public void receive(String msg) {
		sb.append(msg);
		chatText.setText(sb.toString());
	}
	
	
	
}
