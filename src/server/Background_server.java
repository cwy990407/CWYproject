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
	       private MainServer sg; //Ŭ���̾�Ʈ���� ���� �޽����� ���� GUI���� �ѱ�� ����.

	       public void setSg(MainServer sg) {  //Ŭ���̾�Ʈ���� ���� �޽����� ����
	                                                     // GUI���� �ѱ�� ���Ͽ�, ����GUI �� ��ü�ּ� �ް�, 
	             this.sg = sg;
	       }

	       public void setting() {
	            try {
	                  ss = new ServerSocket(8888);
	                  cs = ss.accept();
	   
	                  in = new DataInputStream(cs.getInputStream()); 
	                                     //Ŭ���̾�Ʈ �޽��� ����
	                  out = new DataOutputStream(cs.getOutputStream()); 
	                                    //������ �޽��� Ŭ���̾�Ʈ�� �ѱ�
	 
	                  String msg = in.readUTF();  // Ŭ���̾�Ʈ ���� Ȯ�ο�  Ŭ���̾�Ʈ �޽��� �б�
	   
	                   sg.appendMsg(msg); //Ŭ���̾�Ʈ���� ���� �޽��� ����GUI�� �ѱ��, 
	 
	                    while(in != null) {
	                                 msg = in.readUTF(); //Ŭ���̾�Ʈ �޽��� �б�
	                                 sg.appendMsg(msg); // Ŭ���̾�Ʈ���� ���� �޽��� ����GUI�� �ѱ��, 
	                     }
	 
	                } catch (IOException e) {
	                      e.printStackTrace();
	                }
	         }

	       public void sendMessage(String msg) {
	          try {
	              out.writeUTF("���� : "+msg); 
	                      //����GUI���� ���� �޽��� Ŭ���̾�Ʈ��׶��忡 �ѱ�
	          } catch (IOException e) {
	                  e.printStackTrace();
	          }
	      }
	}
	