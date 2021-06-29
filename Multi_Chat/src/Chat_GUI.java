import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class Chat_GUI extends JFrame  {

   // �α��� �г�
   private JPanel loginPanel;
   // �α��� ��ư
   protected JButton loginButton;
   
   // ��ȭ�� ��
   private JLabel inLabel;
   // ��ȭ�� ��� ��
   protected JLabel outLabel;
   // ��ȭ�� �Է� �ؽ�Ʈ�ʵ�
   protected JTextField idInput;
   
   // �г��� ��
   private JLabel nickLabel;
   // �г��� �Է� �ؽ�Ʈ �ʵ�
   protected JTextField nick_input;
   
   // �α׾ƿ� �г�
   private JPanel logoutPanel;
   // �α׾ƿ� ��ư
   protected JButton logoutButton;
   
   // �޽��� �Է� �г� ����
   private JPanel msgPanel;
   // �޽��� �Է� �ؽ�Ʈ�ʵ�
   protected JTextField msgInput;
   // ���� ��ư
   

   // ȭ�� ���� ��ȯ�� ���� ī�� ���̾ƿ�
   protected Container tab;
   protected CardLayout cardLayout;
   
   // ä�� ���� ���â 
   protected JTextArea msgOut;
   
   protected String id;           
   protected String nickname;
   protected JButton exitButton;
   protected JButton saveButton;
     

   // ������
   public Chat_GUI()
   {
      // ���� ������ ����
      super("::��Ƽê::");
         
         // �α��� �г� ȭ�� ����
         loginPanel = new JPanel();
         loginPanel.setBackground(new Color(230, 230, 250));
         
         // �α��� �г� ���̾ƿ� ����
         loginPanel.setLayout(new FlowLayout());
         
         // �α��� �Է��ʵ�/��ư ����
         idInput = new JTextField(15);
         loginButton = new JButton("�α���");
         loginButton.setBackground(new Color(216, 191, 216));
         loginButton.setFont(new Font("���ʷҵ���", Font.BOLD, 16));
         nick_input = new JTextField(15);
         
         inLabel = new JLabel("�̸� ");
         inLabel.setFont(new Font("���ʷҵ���", Font.BOLD, 15));
         nickLabel = new JLabel("�г���");
         nickLabel.setFont(new Font("���ʷҵ���", Font.BOLD, 15));
         
         loginPanel.add(inLabel);
         loginPanel.add(idInput);
         loginPanel.add(nickLabel);
         loginPanel.add(nick_input);
         loginPanel.add(loginButton);
         
         // �α׾ƿ� �г� ����
         logoutPanel = new JPanel();
         logoutPanel.setBackground(new Color(230, 230, 250));
         
         // �α׾ƿ� �г� ���̾ƿ� ����
         logoutPanel.setLayout(new BorderLayout());
         outLabel = new JLabel();
         logoutButton = new JButton("�α׾ƿ�");
         logoutButton.setBackground(new Color(216, 191, 216));
         
         // �α׾ƿ� �гο� ���� ����
         logoutPanel.add(outLabel, BorderLayout.CENTER);
         logoutPanel.add(logoutButton, BorderLayout.EAST);
         
         // �޽��� �Է� �г� ����
         msgPanel = new JPanel();
         // �޽��� �Է� �׽�Ʈ�ʵ�
         msgPanel.setLayout(new BorderLayout());
         msgInput = new JTextField(30);
         msgInput.setBackground(new Color(255, 255, 255));
         // ���� ��ư
         
         
         // �޽��� �Է� �гο� ���� ����
         exitButton = new JButton("����");
         exitButton.setBackground(new Color(216, 191, 216));
         saveButton = new JButton("����");
         saveButton.setBackground(new Color(216, 191, 216));
         
         msgPanel.add(msgInput, BorderLayout.WEST);
         msgPanel.add(exitButton, BorderLayout.CENTER);
         msgPanel.add(saveButton, BorderLayout.EAST);
         
            // �α���/�α׾ƿ� �г� �� �ϳ��� �����ϴ� CardLayout �г�
            tab = new JPanel();
            tab.setBackground(new Color(230, 230, 250));
            cardLayout = new CardLayout();
            tab.setLayout(cardLayout);
            tab.add(loginPanel, "login");
            tab.add(logoutPanel, "logout");
         
         // �޽��� ��� ���� �ʱ�ȭ
         msgOut = new JTextArea("", 10, 30);
         msgOut.setBackground(new Color(248, 248, 255));
         // JTextArea�� ������ �������� ���ϵ��� �Ѵ�. ��, ��� �������� ����Ѵ�.
         msgOut.setEditable(false);
         
         // �޽��� ��� ���� ��ũ�� �ٸ� �����Ѵ�
         // ���� ��ũ�� �ٴ� �׻� ��Ÿ���� ���� ��ũ�� �ٴ� �ʿ��� �� ��Ÿ������ ���α׷����Ѵ�.
         JScrollPane jsp = new JScrollPane(msgOut, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
         jsp.setBackground(new Color(221, 160, 221));
         getContentPane().add(tab, BorderLayout.NORTH);
         getContentPane().add(jsp, BorderLayout.CENTER);
         getContentPane().add(msgPanel, BorderLayout.SOUTH);
         cardLayout.show(tab, "login");
         
         pack();
         setSize(600,800);
         setVisible(true);
   }
   
   public void addButtonActionListener(ActionListener listener)
   {
      // �̺�Ʈ ������ ���
      loginButton.addActionListener(listener);
      logoutButton.addActionListener(listener);
      exitButton.addActionListener(listener);
      msgInput.addActionListener(listener);
      saveButton.addActionListener(listener);
      
   }

}