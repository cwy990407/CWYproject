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
                        //서버백그라운드 메시지 받을 것
                out = new DataOutputStream(cs.getOutputStream());
                        //클라이언트메시지 서버백그라운드에 보낼 것

                out.writeUTF("안녕 난 클라이언트야! 전송완료 \n"); 
                        //서버백그라운드에게 보내면, readUTF() 로 읽어서, 서버GUI로 다시 보낸다.

               while(in != null) {
                        String msg = in.readUTF(); //서버쪽 메시지 읽기
                        cgui.appendMsg(msg); 
                               //서버백그라운드에게 받은 메시지 클라이언트GUI에게 넘김
                }
          } catch(IOException e) {
                    e.printStackTrace();
          }
     }
    public void sendMessage(String msg2) {
         try {
             out.writeUTF("클라이언트 : " + msg2); 
                        //서버백그라운드에게 보내면, readUTF() 로 읽어서,
                       // sg.appendMsg(msg)로 서버GUI에게 다시 보내면,
                           
         } catch (IOException e) {
                 e.printStackTrace();
        }
    }
}
