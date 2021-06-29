import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.google.gson.Gson;

public class MultiChatController implements Runnable {
	
	private String ip = "127.0.0.1";
	private Socket socket;
	private BufferedReader inMsg = null;
	private PrintWriter outMsg = null;
	Gson gson = new Gson();
	
	MultiChat_Server mc;
	
	Message m;
	
	Thread thread;
	boolean status;
	
	static Chat_GUI mcui;
	
	Logger logger;
	
	File savefile = new File("C:/Member/msgsave.txt");
	
	// �� Ŭ���� ���� ��ü
			private final Chat_GUI v;
			// ������ Ŭ���� ���� ��ü
			private final MultiChatData chatData;
			public MultiChatController(MultiChatData chatData, Chat_GUI v)
			{
				// �ΰ� ��ü �ʱ�ȭ
				logger = Logger.getLogger(this.getClass().getName());
				
				// �𵨰� �� Ŭ���� ����
				this.chatData = chatData;
				this.v = v;
			}
			
			// ������ ��ü���� ������ ��ȭ�� ó���� UI ��ü �߰�       
			public void appMain()        
			{
				chatData.addObj(v.msgOut);
			
				v.addButtonActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						Object obj = e.getSource();
					
						
						if(obj == v.loginButton)
						{
							v.id = v.idInput.getText();
							v.nickname = v.nick_input.getText();						
							
							v.outLabel.setText(" �̸�: " + v.id + "     �г��� : " + v.nickname);
							v.cardLayout.show(v.tab, "logout");
							connectServer();
						}
						else if(obj == v.logoutButton)
						{
							// �α׾ƿ� �޽��� ����
							outMsg.println(gson.toJson(new Message(v.id, v.nickname, "", "logout", "")));
							// ��ȭ â Ŭ����
							v.msgOut.setText("");
							// �α��� �гη� ��ȯ
							v.cardLayout.show(v.tab, "login");
							outMsg.close();
						
							try
							{
								inMsg.close();
								socket.close();
							}
							catch (IOException ex)
							{
								ex.printStackTrace();
							}
							status = false;
						}
						else if(obj == v.msgInput)
						{
							// �޽��� ����
							outMsg.println(gson.toJson(new Message("", v.nickname, v.msgInput.getText(), "msg", "")));
							// �Է� â Ŭ����
							v.msgInput.setText("");
						}
						else if(obj == v.exitButton)
						{
							try { 
	                             BufferedWriter msg_save = new BufferedWriter(new FileWriter(savefile, true));  
	                             msg_save.write("\r\n" + "�ٸ� ������ ���� : " + "\r" );
	                             
	                             msg_save.close();
	                          }
	                          catch (Exception ee)
	                          {
	                             ee.printStackTrace();
	                          }
							System.exit(0);
						}
						else if(obj == v.saveButton)
						{
							 try {
	                             
	                             BufferedWriter msg_save = new BufferedWriter(new FileWriter(savefile, true));  
	                             msg_save.write(v.msgOut.getText());
	                             msg_save.close();
	                             JOptionPane.showMessageDialog(v, "��ȭ ������ ����Ǿ����ϴ�. ���� ��ư�� ��������.");
	                          }
	                          catch (Exception ee)
	                          {
	                             ee.printStackTrace();
	                          }
						}
					}
				});
				
			}
			
		public void connectServer()
		{
			try
			{
				// ���� ����
				socket = new Socket(ip, 7898);
				System.out.println("[client]server ���� ����!!");
				
				// ����� ��Ʈ�� ����
				inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				outMsg = new PrintWriter(socket.getOutputStream(), true);
				
				//������ �α��� �޽��� ����
				m = new Message(v.id, v.nickname, "", "login", "");
				outMsg.println(gson.toJson(m));
				
				// �޽��� ������ ���� ������ ����
				thread = new Thread(this);
				thread.start();
			}
			catch (Exception e)
			{
				
				e.printStackTrace();
			}
		}
			
		public void run()
		{
			// ���� �޽����� ó���ϴ� �� �ʿ��� ���� ����
			String msg;
			status = true;
			
			while(true)
			{
				try
				{
					// �޽��� ���� �� �Ľ�
					msg = inMsg.readLine();
					m = gson.fromJson(msg, Message.class);
					
					//MultiChatData ��ü�� ������ ����
					chatData.refreshData(m.getId() + "   << " + m.getNickname() + ">>" + "  : " + m.getMsg() + "\n");
					
					// Ŀ���� ���� ��ȭ �޽����� ǥ��
					v.msgOut.setCaretPosition(v.msgOut.getDocument().getLength());
				}
				catch (IOException e)
				{
					System.out.println("[MultiChatUI]�޽��� ��Ʈ�� ����!!");
				}
			}
			//logger.info("[CHATUI]"+thread.getName());
		}
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			Login_GUI log_gui = new Login_GUI();
			
		}

}
	
