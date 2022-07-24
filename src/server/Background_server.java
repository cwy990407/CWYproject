package server;
	
	import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.net.ServerSocket;
	import java.net.Socket;

	public class Background_server { 

	       private ServerSocket ss;
	       private Socket cs;
	       private DataInputStream in;
	       private DataOutputStream out;
	       private MainServer sg; //클라이언트에게 받은 메시지를 서버 GUI에게 넘기기 위함.

	       public void setSg(MainServer sg) {  //클라이언트에게 받은 메시지를 서버
	                                                     // GUI에게 넘기기 위하여, 서버GUI 의 객체주소 받고, 
	             this.sg = sg;
	       }

	       public void setting() {
	            try {
	                  ss = new ServerSocket(8888);
	                  cs = ss.accept();
	   
	                  in = new DataInputStream(cs.getInputStream()); 
	                                     //클라이언트 메시지 받음
	                  out = new DataOutputStream(cs.getOutputStream()); 
	                                    //서버쪽 메시지 클라이언트에 넘김
	 
	                  String msg = in.readUTF();  // 클라이언트 연결 확인용  클라이언트 메시지 읽기
	   
	                   sg.appendMsg(msg); //클라이언트에게 받은 메시지 서버GUI에 넘기고, 
	 
	                    while(in != null) {
	                                 msg = in.readUTF(); //클라이언트 메시지 읽기
	                                 sg.appendMsg(msg); // 클라이언트에게 받은 메시지 서버GUI에 넘기고, 
	                     }
	 
	                } catch (IOException e) {
	                      e.printStackTrace();
	                }
	         }

	       public void sendMessage(String msg) {
	          try {
	              out.writeUTF("서버 : "+msg); 
	                      //서버GUI에게 받은 메시지 클라이언트백그라운드에 넘김
	          } catch (IOException e) {
	                  e.printStackTrace();
	          }
	      }
	}
	