package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Background_Client {

       private Socket cs;
       private DataInputStream in;
       private DataOutputStream out;
       private MainClient cgui;

       public void setCgui(MainClient cgui) {
             this.cgui = cgui;
       }
       
       public void connect() {
            try {
                cs = new Socket(" 192.168.219.106", 8888);
                in = new DataInputStream(cs.getInputStream()); 
                        //������׶��� �޽��� ���� ��
                out = new DataOutputStream(cs.getOutputStream());
                        //Ŭ���̾�Ʈ�޽��� ������׶��忡 ���� ��

                out.writeUTF("�ȳ� �� Ŭ���̾�Ʈ��! ���ۿϷ� \n"); 
                        //������׶��忡�� ������, readUTF() �� �о, ����GUI�� �ٽ� ������.

               while(in != null) {
                        String msg = in.readUTF(); //������ �޽��� �б�
                        cgui.appendMsg(msg); 
                               //������׶��忡�� ���� �޽��� Ŭ���̾�ƮGUI���� �ѱ�
                }
          } catch(IOException e) {
                    e.printStackTrace();
          }
     }
    public void sendMessage(String msg2) {
         try {
             out.writeUTF("Ŭ���̾�Ʈ : " + msg2); 
                        //������׶��忡�� ������, readUTF() �� �о,
                       // sg.appendMsg(msg)�� ����GUI���� �ٽ� ������,
                           
         } catch (IOException e) {
                 e.printStackTrace();
        }
    }
}
