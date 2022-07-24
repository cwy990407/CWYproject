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
				System.out.println("***���� ���� ����***");

				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());

				out.writeUTF(name); // �г��� �����ϸ� ������ �̰� �г������� �ν��� �ϰ� �ʿ� �������
				System.out.println("***�޽��� ���� ����***");

				while (in != null) {
					msg = in.readUTF();
					gui.appendMsg(msg);
					jta.setCaretPosition(jta.getText().length()); // �ڵ����� ��ũ���� �ǹ����� ������

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
		setTitle("��� ����");
		setBounds(500, 100, 300, 450);
		jp1 = new JPanel();
		jp1.setLayout(null);// ���� ��ġ ���
		jp2 = new JPanel();
		jp3 = new JPanel();

		jta = new JTextArea(150, 100);
		jtf = new JTextField(20);
		sc = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		// ���� ��ũ�� ���, ���� ��ũ�� ��� �� ��
		sc.setBounds(7, 0, 275, 330);
		jbsend = new JButton("send");

		jb1 = new JButton("���");
		jb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new wd1();

				Object jbb1 = e.getSource(); // �׼��̺�Ʈ e�� �޾� jbb1�� �Ҵ�
				if ((JButton) jbb1 == jb1) {
					jta.append(" ///��� ��ȸ ����Դϴ�./// \n");
				}
			}

			class wd1 extends JFrame {
				wd1() {
					setTitle(" ��� ��ȸ ���� ");

					jpwd1 = new JPanel();
					setContentPane(jpwd1);

					jlwd1 = new JLabel(" ��� ��ȣ :");
					jfwd1 = new JTextField(20);
					jbwd1 = new JButton(" ��ȸ ");
					jlwd2 = new JLabel(" ");

					jpwd1.add(jlwd1);
					jpwd1.add(jfwd1);
					jpwd1.add(jbwd1);
					jpwd1.add(jlwd2);

					jta.setEditable(false); // ä�� ȭ�� ��� �Ұ���
					setSize(300, 150);
					setResizable(false);
					setVisible(true);

					jbwd1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub

							if (jfwd1.getText().equals("12345")) { // ���ڿ� ����
								String msg = "*** ��� ��ȣ ���� ����***\n";
								client.sendMessage(msg);
								jlwd2.setText(" ��� ��ȣ�� ��ġ�մϴ�. ");

							} else if (!jfwd1.getText().equals("12345")) {
								String msg = "***��� ��ȣ ���� ����***\n";
								client.sendMessage(msg);
								jlwd2.setText(" �ٽ� �Է��� �ּ���  ");

							}

						}
					});
				}
			}

		});

		jb2 = new JButton("ȯ��");
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new wd2();

				Object jbb2 = e.getSource();
				if ((JButton) jbb2 == jb2) {
					jta.append(" ///��ǰ ��ȸ ����Դϴ�./// \n");
				}
			}

			class wd2 extends JFrame {
				wd2() {
					setTitle(" ��ǰ ��ȸ ���� ");

					jpwd2 = new JPanel();
					setContentPane(jpwd2);

					jlwd2 = new JLabel(" ��ǰ ��ȣ :");
					jfwd2 = new JTextField(20);
					jbwd2 = new JButton("��ȸ");
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

							if (jfwd2.getText().equals("67890")) { // ���ڿ� ����
								String msg = "***��ǰ ��ȣ ���� ����***\n";
								client.sendMessage(msg);
								jlwd3.setText(" ��ǰ ��ȣ�� ��ġ�մϴ�. ");

							}

							else if (!jfwd2.getText().equals("67890")) {
								String msg = "***��ǰ ��ȣ ���� ����***\n";
								client.sendMessage(msg);
								jlwd3.setText(" �ٽ� �Է��� �ּ���.  ");

							}
						}
					});
				}
			}
		});

		jb3 = new JButton("��Ÿ");
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jta.append(" ///��Ÿ ���� ����Դϴ�./// \n");
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
		System.out.print(" �̸� : ");
		name = scanner.nextLine();
		scanner.close();
		new MainClient();

	}

	@Override
	// ��ġ�� ������ �κ�
	public void actionPerformed(ActionEvent e) {
		String msg = name + " : " + jtf.getText() + "\n";
		client.sendMessage(msg);
		jtf.setText("");
	}

	public void appendMsg(String msg) {
		jta.append(msg);
	}

}
