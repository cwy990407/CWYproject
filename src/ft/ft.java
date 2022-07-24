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
	public static Connection con = null; // 연결
	public PreparedStatement pre = null; // 명령
	public static ResultSet res = null;
	public static Statement st = null;

	public static void main(String[] args) {
		new ft();
	}

	public void getCon() throws SQLException { // db 연결 메소드
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // 드라이버 로딩
			String URL = "jdbc:mysql://localhost:3306/ft";
			con = DriverManager.getConnection(URL, "root", "gkstjstn02"); // 드라이버 연결

		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> 로드 실패");
		}
	}

	public ft() {
		setTitle("관리자 로그인");

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
						l2.setText("로그인 성공");
						new FT2();

					} else {
						l2.setText("다시 입력해 주세요.");
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
		super("피트니스 회원 관리");

		ftDAO dao = new ftDAO(); // 데베 연결, 회원 추가, 수정, 삭제 등에 사용
		v = dao.getft2list();
		System.out.println("v=" + v);
		c = getColumn();

		dt = new DefaultTableModel(v, c);

		tb = new JTable(dt);
		dt.fireTableDataChanged();
		sc = new JScrollPane(tb);
		add(sc);

		p1 = new JPanel();
		b1 = new JButton("회원 등록");
		t1 = new JTextField(15);
		b2 = new JButton("검색");
		b3 = new JButton("새로 고침");

		p1.add(t1);
		p1.add(b2);
		p1.add(b1);
		p1.add(b3);

		add(p1, BorderLayout.SOUTH);
		p1.setBackground(new Color(0, 50, 150, 40));

		tb.getTableHeader().setReorderingAllowed(false);// 테이블 이동 불가
		tb.getTableHeader().setResizingAllowed(false); // 테이블 크기 조절 불가

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
		col.add("회원 번호");
		col.add("회원명");
		col.add("나이");
		col.add("전화번호");
		col.add("등록 일자");
		col.add("등록 기간");
		col.add("종목");
		col.add("담당자");

		return col;
	}

	public void re() { // 테이블 정보 갱신 메소드
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
				JOptionPane.showMessageDialog(this, "검색할 회원명을 입력해 주세요.");
			
			} else {
				ftDAO.getSearch(dt, t1.getText());
				t1.setText(" ");

				if (dt.getRowCount() > 0) {
					tb.setRowSelectionInterval(0, 0);
				} else {
					JOptionPane.showMessageDialog(this, "검색한 회원을 찾을 수 없습니다.");
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