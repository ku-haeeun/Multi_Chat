import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Login_GUI extends JFrame {
	
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField IdTextField;
	private ButtonGroup bg = new ButtonGroup();
	protected JPanel LoginPanel;
	protected JLabel ChatLabel;
	
	File file = new File("C:/Member/member.txt");
	
	public Login_GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel LoginPanel = new JPanel();
		LoginPanel.setBackground(new Color(230, 230, 250));
		LoginPanel.setBounds(0, 0, 578, 744);
		contentPane.add(LoginPanel);
		LoginPanel.setLayout(null);
		
		JLabel IdLable = new JLabel("ID");
	      IdLable.setBounds(98, 302, 79, 31);
	      IdLable.setFont(new Font("���ʷҵ���", Font.BOLD, 25));
	      LoginPanel.add(IdLable);
	      
	      JLabel PwdLabel = new JLabel("Pwd");
	      PwdLabel.setBounds(98, 417, 82, 31);
	      PwdLabel.setFont(new Font("���ʷҵ���", Font.BOLD, 25));
	      LoginPanel.add(PwdLabel);
	      
	      passwordField = new JPasswordField();
	      passwordField.setBounds(228, 410, 199, 46);
	      passwordField.setFont(new Font("���ʷҵ���", Font.BOLD, 20));
	      LoginPanel.add(passwordField);
	      
	      IdTextField = new JTextField();
	      IdTextField.setBounds(228, 295, 199, 46);
	      IdTextField.setFont(new Font("���ʷҵ���", Font.BOLD, 23));
	      IdTextField.setColumns(10);
	      LoginPanel.add(IdTextField);
	      
	      JButton Joinbutton = new JButton("Join");
	      Joinbutton.setBounds(382, 598, 143, 73);
	      Joinbutton.setForeground(Color.BLACK);
	      Joinbutton.setFont(new Font("���ʷҵ���", Font.BOLD, 25));
	      Joinbutton.setBackground(new Color(216, 191, 216));
	      LoginPanel.add(Joinbutton);
	      
	      JLabel ChatLabel = new JLabel("");
	      ChatLabel.setIcon(new ImageIcon(Login_GUI.class.getResource("/Chatt.png")));
	      ChatLabel.setBounds(236, 86, 191, 144);
	      LoginPanel.add(ChatLabel);
	    
	      JButton Loginbutton = new JButton("Login");
	      Loginbutton.setForeground(Color.BLACK);
	      Loginbutton.setFont(new Font("���ʷҵ���", Font.BOLD, 25));
	      Loginbutton.setBackground(new Color(216, 191, 216));
	      Loginbutton.setBounds(210, 598, 143, 73);
	      LoginPanel.add(Loginbutton);
	      
	      
	         JCheckBox B = new JCheckBox("B");
	         B.setFont(new Font("���ʷҵ���", Font.BOLD, 15));
	         B.setBackground(new Color(255, 255, 255));
	         B.setBounds(10, 709, 39, 27);
	         LoginPanel.add(B);
	         bg.add(B);
	         
	         JCheckBox G = new JCheckBox("G\r\n");
	         G.setFont(new Font("���ʷҵ���", Font.BOLD, 15));
	         G.setBackground(new Color(255, 255, 255));
	         G.setBounds(47, 709, 39, 27);
	         LoginPanel.add(G);
	         bg.add(G);
	         
	         JCheckBox O = new JCheckBox("O");
	         O.setFont(new Font("���ʷҵ���", Font.BOLD, 15));
	         O.setBackground(new Color(255, 255, 255));
	         O.setBounds(86, 709, 45, 27);
	         LoginPanel.add(O);
	         bg.add(O);
	         
	      setVisible(true);
	      
	      Loginbutton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent arg0) {
	             
	        	  if(IdTextField.getText().isEmpty() || passwordField.getText().isEmpty())
	              {
	                 JOptionPane.showMessageDialog(contentPane, "���̵�� ��й�ȣ�� Ȯ�����ּ���.");
	              }
	              else
	              {
	             	 try
	             	 {
	             		 String data;  
	             		 String[] array;
	             		 BufferedReader id_save_read = new BufferedReader(new FileReader(file));
	             		 
	             		 
	             		 while((data=id_save_read.readLine()) != null)  
	             		 {
	             			 array = data.split("/");
	             			 if(IdTextField.getText().equals(array[0]))    // ���̵� �ִ�!
	             				 
	             			 {
	             				 if(passwordField.getText().equals(array[1]))  // ��й�ȣ ������ �ݾ��ְ� chat�� ������
	             				 {
	             					 dispose();
	             					MultiChatController app = new MultiChatController(new MultiChatData(), new Chat_GUI());
	             					app.appMain();
	             				 }
	             				 else                                          // ��й�ȣ�� Ʋ���� Ȯ���ش޶�
	             				 {
	             					JOptionPane.showMessageDialog(contentPane, "��й�ȣ�� Ȯ�����ּ���.");
	             				 }
	             			 }
	             		 }
	             		 id_save_read.close();
	             	 }
	             	 catch (IOException e)
	             	 {
	             		 e.printStackTrace();
	             	 }                
	              }
	           }  
	       });
	      
	      Joinbutton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent arg0) {
	             dispose();
	             Join_GUI join_gui = new Join_GUI();
	          }   
	       });
	      
	      B.addActionListener(new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent arg0) {
	                LoginPanel.setBackground(new Color(224, 255, 255));
	             }   
	          });
	         
	         G.addActionListener(new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent arg0) {
	                LoginPanel.setBackground(new Color(240, 255, 240));
	             }   
	          });
	         
	         O.addActionListener(new ActionListener() {
	             @Override
	             public void actionPerformed(ActionEvent arg0) {
	                LoginPanel.setBackground(new Color(255, 228, 196));
	             }   
	          });
	        
		
	}
}
