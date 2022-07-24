import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Font;
public class rate_calculate {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame f=new JFrame("환율 계산기");
		JLabel l1=new JLabel("환율할 금액(KRW)");
		l1.setBounds(103, 42, 159, 30);
		l1.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		JTextField text=new JTextField();
		text.setBounds(285, 42, 100, 30);
		JLabel l2=new JLabel("환율된 금액");
		l2.setBounds(121, 98, 128, 30);
		l2.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		JTextField texts=new JTextField();
		texts.setBounds(285, 102, 100, 30);
		
		JButton btn1=new JButton("미국");
		btn1.setBounds(0, 155, 100, 30);
		btn1.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn2=new JButton("일본");
		btn2.setBounds(114, 155, 100, 30);
		btn2.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn3=new JButton("유럽");
		btn3.setBounds(226, 155, 100, 30);
		btn3.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn4=new JButton("중국");
		btn4.setBounds(338, 155, 100, 30);
		btn4.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn5=new JButton("영국");
		btn5.setBounds(450, 155, 100, 30);
		btn5.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn6=new JButton("호주");
		btn6.setBounds(0, 212, 100, 30);
		btn6.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn7=new JButton("캐나다");
		btn7.setBounds(114, 212, 100, 30);
		btn7.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn8=new JButton("뉴질랜드");
		btn8.setBounds(226, 212, 100, 30);
		btn8.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn9=new JButton("덴마크");
		btn9.setBounds(338, 212, 100, 30);
		btn9.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
		
		JButton btn10=new JButton("태국");
		btn10.setBounds(450, 212, 100, 30);
		btn10.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
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
lblNewLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
f.getContentPane().add(lblNewLabel);

		
		f.setSize(564,315);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		btn1.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double usd=1204.70; //미국
	    	double result=kr/usd; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"달러");
			}
		});
		
		btn2.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double jpy=11.0074; //일본
	    	double result=kr/jpy; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"엔");
			}
		});
		
		btn3.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double eur=1359.32; //유럽
	    	double result=kr/eur; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"유로");
			}
		});
		
		btn4.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double cny=170.06; //중국
	    	double result=kr/cny; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"위안");
			}
		});
		
		btn5.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double gbp=1529.79; //영국
	    	double result=kr/gbp; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"파운드");
			}
		});
		
		btn6.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double aud=839.01; //호주
	    	double result=kr/aud; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"달러");
			}
		});
		
		btn7.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double cad=896.82; //캐나다
	    	double result=kr/cad; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"달러");
			}
		});
		
		btn8.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double nzd=784.56; //뉴질랜드
	    	double result=kr/nzd; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"달러");
			}
		});
		
		btn9.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double dkk=182.34; //덴마크
	    	double result=kr/dkk; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"크로네");
			}
		});
		
		btn10.addActionListener(new ActionListener() {
			@Override
	     	public void actionPerformed(ActionEvent e) {
		
	    	double kr=Double.parseDouble(text.getText()); //환율할 금액 	
	    	double thb=38.28; //태국
	    	double result=kr/thb; //환율된 금액
	    	texts.setText((Math.round(result*100)/100.0)+"바트");
			}
		});
	}
}	
