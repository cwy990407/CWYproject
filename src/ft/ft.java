package ft;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.NullPointerException;

public class ft extends JFrame {
	public static Connection con = null; // ����
	public PreparedStatement pre = null; // ���
	public static ResultSet res = null;
	public static Statement st = null;

	public static void main(String[] args) {
		new ft();
	}

	public void getCon() throws SQLException { // db ���� �޼ҵ�
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // ����̹� �ε�
			String URL = "jdbc:mysql://localhost:3306/ft";
			con = DriverManager.getConnection(URL, "root", "gkstjstn02"); // ����̹� ����

		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> �ε� ����");
		}
	}

	public ft() {
		setTitle("������ �α���");

		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("Password ");
		JLabel l2 = new JLabel(" ");
		JTextField t1 = new JTextField(10);
		JButton b1 = new JButton("Log in");

		p1.add(l1);
		p1.add(t1);
		p1.add(b1);
		p1.add(l2);
		add(p1);

		b1.addActionListener(new ActionListener() {
			public void setLogin() {
				try {
					getCon();
					String pass = t1.getText();
					String sql = "SELECT * FROM login" + " WHERE password=" + pass + "";
					st = con.createStatement();
					res = st.executeQuery(sql);

					if (res.next()) {
						String password = res.getString("password");
						System.out.printf("%s", password);
						l2.setText("�α��� ����");
						new FT2();

					} else {
						l2.setText("�ٽ� �Է��� �ּ���.");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			@Override

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setLogin();
			}
		});

		setSize(300, 150);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

}

class FT2 extends JFrame implements MouseListener, ActionListener {

	Vector v;
	Vector c;
	DefaultTableModel dt;
	JTable tb;
	JScrollPane sc;
	JPanel p1;
	JButton b1, b2, b3;
	JTextField t1;

	public FT2() {
		super("��Ʈ�Ͻ� ȸ�� ����");

		ftDAO dao = new ftDAO(); // ���� ����, ȸ�� �߰�, ����, ���� � ���
		v = dao.getft2list();
		System.out.println("v=" + v);
		c = getColumn();

		dt = new DefaultTableModel(v, c);

		tb = new JTable(dt);
		dt.fireTableDataChanged();
		sc = new JScrollPane(tb);
		add(sc);

		p1 = new JPanel();
		b1 = new JButton("ȸ�� ���");
		t1 = new JTextField(15);
		b2 = new JButton("�˻�");
		b3 = new JButton("���� ��ħ");

		p1.add(t1);
		p1.add(b2);
		p1.add(b1);
		p1.add(b3);

		add(p1, BorderLayout.SOUTH);
		p1.setBackground(new Color(0, 50, 150, 40));

		tb.getTableHeader().setReorderingAllowed(false);// ���̺� �̵� �Ұ�
		tb.getTableHeader().setResizingAllowed(false); // ���̺� ũ�� ���� �Ұ�

		tb.addMouseListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);

		setSize(750, 360);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private Vector getColumn() {
		// TODO Auto-generated method stub

		Vector col = new Vector();
		col.add("ȸ�� ��ȣ");
		col.add("ȸ����");
		col.add("����");
		col.add("��ȭ��ȣ");
		col.add("��� ����");
		col.add("��� �Ⱓ");
		col.add("����");
		col.add("�����");

		return col;
	}

	public void re() { // ���̺� ���� ���� �޼ҵ�
		ftDAO dao = new ftDAO();
		DefaultTableModel dt = new DefaultTableModel(dao.getft2list(), getColumn());
		tb.setModel(dt);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == b1) {
			new ftProc(this);

		}

		if (e.getSource() == b2) {

			if (t1.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "�˻��� ȸ������ �Է��� �ּ���.");
			
			} else {
				ftDAO.getSearch(dt, t1.getText());
				t1.setText(" ");

				if (dt.getRowCount() > 0) {
					tb.setRowSelectionInterval(0, 0);
				} else {
					JOptionPane.showMessageDialog(this, "�˻��� ȸ���� ã�� �� �����ϴ�.");
				}
			}
		} else if (e.getSource() == b3) {

			ftDAO dao = new ftDAO();
			dt = new DefaultTableModel(dao.getft2list(), getColumn());
			tb.setModel(dt);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		int r = tb.getSelectedRow();
		String number = (String) tb.getValueAt(r, 0); //
		ftProc me = new ftProc(number, this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}