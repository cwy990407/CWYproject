import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Font;
public class rate_calculate {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame f=new JFrame("ȯ�� ����");
		JLabel l1=new JLabel("ȯ���� �ݾ�(KRW)");
		l1.setBounds(103, 42, 159, 30);
		l1.setFont(new Font("���� ���", Font.PLAIN, 16));
		JTextField text=new JTextField();
		text.setBounds(285, 42, 100, 30);
		JLabel l2=new JLabel("ȯ���� �ݾ�");
		l2.setBounds(121, 98, 128, 30);
		l2.setFont(new Font("���� ���", Font.PLAIN, 16));
		JTextField texts=new JTextField();
		texts.setBounds(285, 102, 100, 30);
		
		JButton btn1=new JButton("�̱�");
		btn1.setBounds(0, 155, 100, 30);
		btn1.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn2=new JButton("�Ϻ�");
		btn2.setBounds(114, 155, 100, 30);
		btn2.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn3=new JButton("����");
		btn3.setBounds(226, 155, 100, 30);
		btn3.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn4=new JButton("�߱�");
		btn4.setBounds(338, 155, 100, 30);
		btn4.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn5=new JButton("����");
		btn5.setBounds(450, 155, 100, 30);
		btn5.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn6=new JButton("ȣ��");
		btn6.setBounds(0, 212, 100, 30);
		btn6.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn7=new JButton("ĳ����");
		btn7.setBounds(114, 212, 100, 30);
		btn7.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn8=new JButton("��������");
		btn8.setBounds(226, 212, 100, 30);
		btn8.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn9=new JButton("����ũ");
		btn9.setBounds(338, 212, 100, 30);
		btn9.setFont(new Font("���� ���", Font.PLAIN, 14));
		
		JButton btn10=new JButton("�±�");
		btn10.setBounds(450, 212, 100, 30);
		btn10.setFont(new Font("���� ���", Font.PLAIN, 14));
		f.getContentPane().setLayout(null);
	

		f.getContentPane().add(l1); f.getContentPane().add(text);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn1);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn2);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn3);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn4);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn5);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn6);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn7);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn8);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn9);
		f.getContentPane().add(l2); f.getContentPane().add(texts);f.getContentPane().add(btn10);
JLabel lblNewLabel = new JLabel("\uC6D0");
lblNewLabel.setBounds(397, 42, 80, 29);
lblNewLabel.setFont(new Font("���� ���", Font.PLAIN, 16));
f.getContentPane().add(lblNewLabel);

		
		f.setSize(564,315);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn1.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double usd=1204.70; //�̱�
	    	double result=kr/usd; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"�޷�");
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double jpy=11.0074; //�Ϻ�
	    	double result=kr/jpy; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"��");
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double eur=1359.32; //����
	    	double result=kr/eur; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"����");
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double cny=170.06; //�߱�
	    	double result=kr/cny; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"����");
			}
		});
		
		btn5.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double gbp=1529.79; //����
	    	double result=kr/gbp; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"�Ŀ��");
			}
		});
		
		btn6.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double aud=839.01; //ȣ��
	    	double result=kr/aud; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"�޷�");
			}
		});
		
		btn7.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double cad=896.82; //ĳ����
	    	double result=kr/cad; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"�޷�");
			}
		});
		
		btn8.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double nzd=784.56; //��������
	    	double result=kr/nzd; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"�޷�");
			}
		});
		
		btn9.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double dkk=182.34; //����ũ
	    	double result=kr/dkk; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"ũ�γ�");
			}
		});
		
		btn10.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //ȯ���� �ݾ� 	
	    	double thb=38.28; //�±�
	    	double result=kr/thb; //ȯ���� �ݾ�
	    	texts.setText((Math.round(result*100)/100.0)+"��Ʈ");
			}
		});
	}
}	
