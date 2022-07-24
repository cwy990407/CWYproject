package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainServer extends JFrame implements ActionListener {
  
    private JTextArea jta;
    private JTextField jtf;
    private Background_server server = new Background_server();
    private JScrollPane sc;
    private JPanel jp1;
    private JPanel jp2;
    private JButton jbsend;

public class Background_server {

   private ServerSocket serverSocket;
    private Socket socket;
    private MainServer gui;
    private String msg;
 
    private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>(); // ����ڵ��� ������ �����ϴ� ��
 
    public final void setGui(MainServer gui) {
        this.gui = gui;
    }
 
    public void setting() throws IOException {
        Collections.synchronizedMap(clientsMap); 
        // �ؽ����� �������� �������� ������ Collections.synchronizedMap(); �޼ҵ�� �����ϰ� ����� ��
        serverSocket = new ServerSocket(7777);
        
        while (true) { 
           
            System.out.println(" ///���� ��ٸ��� ���Դϴ�/// ");
            socket = serverSocket.accept(); // ���� ������ ��� �ݺ��� ����ڸ� ����
           
            // ���⼭ ���ο� ����� ������ Ŭ���� �����ؼ� ���������� ����
            Receiver receiver = new Receiver(socket);
            receiver.start();
        }
    }
 
    public void main(String[] args) throws IOException {
       Background_server serverBackground = new Background_server();
        serverBackground.setting();
    }
 
    
    public void addClient(String name, DataOutputStream out) throws IOException { // ���� ����(Ŭ���̾�Ʈ) ����� ����
        sendMessage(name + " ���� �����ϼ̽��ϴ�.\n");
        jta.append("<<<<<"+ name + " ������ ���ӵǾ����ϴ�.>>>>>\n");
        clientsMap.put(name, out);
    }
 
    public void removeClient(String name) {
        sendMessage(name + " ���� �����̽��ϴ�.\n");
        jta.append(">>>>>" + name + " ������ �����Ͽ����ϴ�.<<<<<\n");
        clientsMap.remove(name);
    }
 
   
    public void sendMessage(String msg) { // �޽��� ���� ����
        Iterator<String> it = clientsMap.keySet().iterator(); 
        // ���ͷ����� ��ü it�� Ŭ���̾�Ʈ�� ������ ������ ��
        // keySet(): �� �迭�� ��ü Ű�� ��ȯ 
        // iterator(): ����� ��Ҹ� �о���� ����
        String key = ""; //?
        
        while (it.hasNext()) { // hasNext()�� ��ȯ�� ���� ������ true, ������ false
            key = it.next(); // next() : iterator ��ü�� �� ���
            
            try {
                clientsMap.get(key).writeUTF(msg);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 

    class Receiver extends Thread { // �޼����� ������ �������� ���� ��Ź
       
        private DataInputStream in;
        private DataOutputStream out;
        private String name;
 
        
        public Receiver(Socket socket) throws IOException { 
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
            name = in.readUTF();
            addClient(name, out);
        }
 
        public void run() {
            try {
                
               while (in != null) {
                    msg = in.readUTF();
                    sendMessage(msg);
                    gui.appendMsg(msg);
                    jta.setCaretPosition(jta.getText().length()); // �ڵ����� ��ũ���� �ǹ����� ������

                  }
                
            } catch (IOException e) {
               removeClient(name); // ��� ���� ���� �� ������ Ŭ���̾�Ʈ ó�� 
           }
        }
    }
}

 
    public MainServer() throws IOException {
        setTitle(" ��� ä�� ");
         setBounds(170, 120, 300, 450);
         jp1 = new JPanel();
         jp1.setLayout(null);// ���� ��ġ ���
         jp2 = new JPanel();
         jtf = new JTextField(20);
         jta = new JTextArea(150, 360);
         sc = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         // ���� ��ũ�� ���, ���� ��ũ�� ��� �� ��
         sc.setBounds(7, 7, 275, 360);
         jbsend = new JButton("send");

         
         jp1.add(sc);
         add(jp1);
         jp2.add(jtf);
         jp2.add(jbsend);
         add("South", jp2);
         
         jta.setEditable(false); // ���� �Ұ���
        
         
         setDefaultCloseOperation(EXIT_ON_CLOSE);
        
          setVisible(true);
         
         jbsend.addActionListener(this); 
         jtf.addActionListener(this);
         server.setGui(this);
         server.setting();

    }
 
    public static void main(String[] args) throws IOException {
        new MainServer();
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = "���� : " + jtf.getText() + "\n";
        server.sendMessage(msg);
        String msg2 = jtf.getText() + "\n"; 
        jta.append("���� : " + msg2);

        jtf.setText("");
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
    }
 
}