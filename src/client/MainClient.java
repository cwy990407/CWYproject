package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainClient extends JFrame implements ActionListener {

	private JTextArea jta;
	private JTextField jtf, jfwd1, jfwd2;
	private JScrollPane sc;
	private JPanel jp1, jp2, jp3, jpwd1, jpwd2;
	private JButton jbsend, jb1, jb2, jb3, jbwd1, jbwd2;
	private JLabel jlwd1, jlwd2, jlwd3;
	private BackgroundClient client = new BackgroundClient();
	private static String name;

	public class BackgroundClient {

		private Socket socket;
		private DataInputStream in;
		private DataOutputStream out;
		private MainClient gui;
		private String msg;
		private String name;

		public void setGui(MainClient gui) {
			this.gui = gui;
		}

		public void connet() {
			try {
				socket = new Socket("192.168.219.102", 7777);
				System.out.println("***서버 연결 성공***");

				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				out.writeUTF(name); // 닉네임 전송하면 서버가 이걸 닉네임으로 인식을 하고 맵에 집어넣음
				System.out.println("***메시지 전송 성공***");

				while (in != null) {
					msg = in.readUTF();
					gui.appendMsg(msg);
					jta.setCaretPosition(jta.getText().length()); // 자동으로 스크롤이 맨밑으로 맞춰짐

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void main(String[] args) {
			BackgroundClient clientBackground = new BackgroundClient();
			clientBackground.connet();
		}

		public void sendMessage(String msg2) {
			try {
				out.writeUTF(msg2);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void setNickname(String name) {
			this.name = name;
		}

	}

	public MainClient() {
		setTitle("상담 문의");
		setBounds(500, 100, 300, 450);
		jp1 = new JPanel();
		jp1.setLayout(null);// 절대 위치 사용
		jp2 = new JPanel();
		jp3 = new JPanel();

		jta = new JTextArea(150, 100);
		jtf = new JTextField(20);
		sc = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// 세로 스크롤 사용, 가로 스크롤 사용 안 함
		sc.setBounds(7, 0, 275, 330);
		jbsend = new JButton("send");

		jb1 = new JButton("배송");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new wd1();

				Object jbb1 = e.getSource(); // 액션이벤트 e를 받아 jbb1에 할당
				if ((JButton) jbb1 == jb1) {
					jta.append(" ///배송 조회 상담입니다./// \n");
				}
			}

			class wd1 extends JFrame {
				wd1() {
					setTitle(" 배송 조회 문의 ");

					jpwd1 = new JPanel();
					setContentPane(jpwd1);

					jlwd1 = new JLabel(" 배송 번호 :");
					jfwd1 = new JTextField(20);
					jbwd1 = new JButton(" 조회 ");
					jlwd2 = new JLabel(" ");

					jpwd1.add(jlwd1);
					jpwd1.add(jfwd1);
					jpwd1.add(jbwd1);
					jpwd1.add(jlwd2);

					jta.setEditable(false); // 채팅 화면 사용 불가능
					setSize(300, 150);
					setResizable(false);
					setVisible(true);

					jbwd1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub

							if (jfwd1.getText().equals("12345")) { // 문자열 필터
								String msg = "*** 배송 번호 인증 성공***\n";
								client.sendMessage(msg);
								jlwd2.setText(" 배송 번호가 일치합니다. ");

							} else if (!jfwd1.getText().equals("12345")) {
								String msg = "***배송 번호 인증 실패***\n";
								client.sendMessage(msg);
								jlwd2.setText(" 다시 입력해 주세요  ");

							}

						}
					});
				}
			}

		});

		jb2 = new JButton("환불");
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new wd2();

				Object jbb2 = e.getSource();
				if ((JButton) jbb2 == jb2) {
					jta.append(" ///상품 조회 상담입니다./// \n");
				}
			}

			class wd2 extends JFrame {
				wd2() {
					setTitle(" 상품 조회 문의 ");

					jpwd2 = new JPanel();
					setContentPane(jpwd2);

					jlwd2 = new JLabel(" 상품 번호 :");
					jfwd2 = new JTextField(20);
					jbwd2 = new JButton("조회");
					jlwd3 = new JLabel(" ");

					jpwd2.add(jlwd2);
					jpwd2.add(jfwd2);
					jpwd2.add(jbwd2);
					jpwd2.add(jlwd3);

					setSize(300, 150);
					setResizable(false);
					setVisible(true);

					jbwd2.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub

							if (jfwd2.getText().equals("67890")) { // 문자열 필터
								String msg = "***상품 번호 인증 성공***\n";
								client.sendMessage(msg);
								jlwd3.setText(" 상품 번호가 일치합니다. ");

							}

							else if (!jfwd2.getText().equals("67890")) {
								String msg = "***상품 번호 인증 실패***\n";
								client.sendMessage(msg);
								jlwd3.setText(" 다시 입력해 주세요.  ");

							}
						}
					});
				}
			}
		});

		jb3 = new JButton("기타");
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jta.append(" ///기타 문의 상담입니다./// \n");
			}
		});

		jp1.add(sc);
		add(jp1);
		jp2.add(jtf);
		jp2.add(jbsend);
		add("South", jp2);
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);
		add("North", jp3);

		jbsend.addActionListener(this);
		jtf.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		jta.setEditable(false);

		client.setGui(this);
		client.setNickname(name);
		client.connet();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print(" 이름 : ");
		name = scanner.nextLine();
		scanner.close();
		new MainClient();

	}

	@Override
	// 말치면 보내는 부분
	public void actionPerformed(ActionEvent e) {
		String msg = name + " : " + jtf.getText() + "\n";
		client.sendMessage(msg);
		jtf.setText("");
	}

	public void appendMsg(String msg) {
		jta.append(msg);
	}

}
