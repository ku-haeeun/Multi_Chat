import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.Gson;

public class MultiChat_Server {

	boolean status;
	// ���� ���� �� Ŭ���̾�Ʈ ���� ����
	private ServerSocket ss = null;
	private Socket s = null;
	
	// ����� Ŭ���̾�Ʈ �����带 �����ϴ� ArrayList
	ArrayList<ChatThread> chatThreads = new ArrayList<ChatThread>();
	
	// �ΰ� ��ü
	Logger logger;
	
	// ��Ƽ ä�� ���� ���α׷� �κ�
	public void start()
	{
		logger = Logger.getLogger(this.getClass().getName());
		
		try
		{
			// ���� ���� ����
			ss = new ServerSocket(7898);
			logger.info("MultiChatServer start");
			
			// ���� ������ ���鼭 Ŭ���̾�Ʈ ������ ��ٸ���.
			while(true)
			{
				s = ss.accept();
				// ����� Ŭ���̾�Ʈ�� ���� ������ Ŭ���� ����
				ChatThread chat = new ChatThread();
				// Ŭ���̾�Ʈ ����Ʈ �߰�
		 		chatThreads.add(chat);         // chatThreads : arrayList
				// ������ ����
				chat.start();
			}
		}
		catch (Exception e)
		{
			logger.info("[MultiChatServer]start() Exception �߻�!!");
				e.printStackTrace();
		}
	}
	
	// ����� ��� Ŭ���̾�Ʈ�� �޽��� �߰�
	void msgSendAll(String msg)          
	{
		for(ChatThread ct : chatThreads)
		{
			ct.outMsg.println(msg);
		}
	}
	
	// Ư�� Ŭ���̾�Ʈ�� �޽��� �߰�
	void msgSendOne(String msg)
	{
		
	}
	
	class ChatThread extends Thread
	{	
		// ���� �޼��� �� �Ľ� �޽��� ó���� ���� ���� ����
		String msg;
		
		// �޽��� ��ü ����
		Message m = new Message();
		//Message n = new Message();
		
		// JSON �ļ� �ʱ�ȭ
		Gson gson = new Gson();
		
		// ����� ��Ʈ��
		private BufferedReader inMsg = null;
		private PrintWriter outMsg = null;
		
		public void run()
		{
			status = true;
			// ���� ������ true�̸� ������ ���鼭 ����ڿ��Լ� ���ŵ� �޽��� ó��
			logger.info("ChatThread start");
			
			try
			{
				inMsg = new BufferedReader(new InputStreamReader(s.getInputStream()));
				outMsg = new PrintWriter(s.getOutputStream(),true);
				
				status = true;
				
				while(status)
				{
					// ���ŵ� �޽����� msg ������ ����
					msg = inMsg.readLine();
					//nickname = innick.readLine();
					
					// JSON �޽����� Message ��ü�� ����
					m = gson.fromJson(msg, Message.class);
					
					// �Ľ̵� ���ڿ� �迭�� �� ���� ��Ұ��� ���� ó��
					// �α׾ƿ� �޽����� ��
					if(m.getType().equals("logout"))
					{
						chatThreads.remove(this);
						msgSendAll(gson.toJson(new Message(m.getId(), m.getNickname(), "���� �����߽��ϴ�.", "sevrer", "")));
						// �ش� Ŭ���̾�Ʈ ������ ����� status�� false�� ����       => arraylist���� ���� chatthread�� ����
						status = false;
					}
					//�α��� �޽����� ��
					else if(m.getType().equals("login"))
					{
						msgSendAll(gson.toJson(new Message(m.getId(), "�г��� : " + m.getNickname(), "���� �α����߽��ϴ�.", "server", "")));
					}
					else
					{
						msgSendAll(msg);
					}
				}
				// ������ ����� Ŭ���̾�Ʈ ������ ����ǹǷ� ������ ���ͷ�Ʈ
				this.interrupt();
				logger.info(this.getName() + " �����!!");          
			}
			catch (IOException e) 
			{
				// TODO: handle exception
				chatThreads.remove(this);
				logger.info("[ChatThread]run() ");
				e.printStackTrace();
			}		
		}		
	}
	
	public static void main(String[] args) {
		MultiChat_Server server = new MultiChat_Server();
		server.start();
	}
}
	///
