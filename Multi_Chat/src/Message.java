
public class Message {

	private String id;        // ���̵�
	private String passwd;    // ��й�ȣ
	private String msg;		  // ä�ø޽���
	private String type;	  // �޽��� ����(�α���, �α׾ƿ�, �޽��� ����)
	private String nickname;  // �г���
	
	
	public Message() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Message(String id, String nickname, String msg ,String type, String passwd) 
	{
		this.id = id;
		this.nickname = nickname;
		this.msg =msg;
		this.type = type;
		this.passwd = passwd;
	}

	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getPasswd()
	{
		return passwd;
	}
	public void setPasswd(String passwd)
	{
		this.passwd = passwd;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	public String getNickname() 
	{
		return nickname;
	}
	public void setNickname(String nickname) 
	{
		this.nickname = nickname;
	}
	

}
