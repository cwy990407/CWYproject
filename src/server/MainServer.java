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
 
    private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>(); // 사용자들의 정보를 저장하는 맵
 
    public final void setGui(MainServer gui) {
        this.gui = gui;
    }
 
    public void setting() throws IOException {
        Collections.synchronizedMap(clientsMap); 
        // 해쉬맵은 스레드의 안전성이 떨어져 Collections.synchronizedMap(); 메소드로 안전하게 만들어 줌
        serverSocket = new ServerSocket(7777);
        
        while (true) { 
           
            System.out.println(" ///연결 기다리는 중입니다/// ");
            socket = serverSocket.accept(); // 먼저 서버가 계속 반복해 사용자를 받음
           
            // 여기서 새로운 사용자 스레드 클래스 생성해서 소켓정보를 넣음
            Receiver receiver = new Receiver(socket);
            receiver.start();
        }
    }
 
    public void main(String[] args) throws IOException {
       Background_server serverBackground = new Background_server();
        serverBackground.setting();
    }
 
    
    public void addClient(String name, DataOutputStream out) throws IOException { // 맵의 내용(클라이언트) 저장과 삭제
        sendMessage(name + " 님이 접속하셨습니다.\n");
        jta.append("<<<<<"+ name + " 고객님이 접속되었습니다.>>>>>\n");
        clientsMap.put(name, out);
    }
 
    public void removeClient(String name) {
        sendMessage(name + " 님이 나가셨습니다.\n");
        jta.append(">>>>>" + name + " 고객님이 종료하였습니다.<<<<<\n");
        clientsMap.remove(name);
    }
 
   
    public void sendMessage(String msg) { // 메시지 내용 전송
        Iterator<String> it = clientsMap.keySet().iterator(); 
        // 이터레이터 객체 it이 클라이언트맵 값들을 가지고 옴
        // keySet(): 맵 계열의 전체 키를 반환 
        // iterator(): 저장된 요소를 읽어오는 역할
        String key = ""; //?
        
        while (it.hasNext()) { // hasNext()에 반환할 값이 있으면 true, 없으면 false
            key = it.next(); // next() : iterator 객체의 값 얻기
            
            try {
                clientsMap.get(key).writeUTF(msg);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
 

    class Receiver extends Thread { // 메세지가 들어오면 서버에게 전파 부탁
       
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
                    jta.setCaretPosition(jta.getText().length()); // 자동으로 스크롤이 맨밑으로 맞춰짐

                  }
                
            } catch (IOException e) {
               removeClient(name); // 사용 접속 종료 시 리무브 클라이언트 처리 
           }
        }
    }
}

 
    public MainServer() throws IOException {
        setTitle(" 상담 채팅 ");
         setBounds(170, 120, 300, 450);
         jp1 = new JPanel();
         jp1.setLayout(null);// 절대 위치 사용
         jp2 = new JPanel();
         jtf = new JTextField(20);
         jta = new JTextArea(150, 360);
         sc = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         // 세로 스크롤 사용, 가로 스크롤 사용 안 함
         sc.setBounds(7, 7, 275, 360);
         jbsend = new JButton("send");

         
         jp1.add(sc);
         add(jp1);
         jp2.add(jtf);
         jp2.add(jbsend);
         add("South", jp2);
         
         jta.setEditable(false); // 편집 불가능
        
         
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
        String msg = "상담원 : " + jtf.getText() + "\n";
        server.sendMessage(msg);
        String msg2 = jtf.getText() + "\n"; 
        jta.append("서버 : " + msg2);

        jtf.setText("");
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
    }
 
}