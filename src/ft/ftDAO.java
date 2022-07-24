package ft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class ftDAO { // ȸ�� ���̺��� ������ ó�� ��� Ŭ����
	private static Connection con = null; // ����
	private PreparedStatement pre = null; // ���
	private static ResultSet res = null; // ���
	private static Statement st = null; // like ������ ����� �� statement

	FT2 ft2;

	public ftDAO() {

	}

	public ftDAO(FT2 ft2) {
		this.ft2 = ft2;
		System.out.println("DAO->" + ft2);
	}

	public Connection getCon() throws SQLException { // db ���� �޼ҵ�
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // ����̹� �ε�
			String URL = "jdbc:mysql://localhost:3306/ft";
			con = DriverManager.getConnection(URL, "root", "gkstjstn02"); // ����̹� ����
			return con;
		} catch (ClassNotFoundException e) {
			System.out.println(e + "=> �ε� ����");
		}
		return null;
	}

	public ftDTO getftDTO(String number) {
		ftDTO dto = new ftDTO();

		try {
			con = getCon();
			String sql = "SELECT * FROM `ft`.`ft` WHERE number=?";
			pre = con.prepareStatement(sql);
			pre.setString(1, number);

			res = pre.executeQuery();

			if (res.next()) {
				dto.setNumber(res.getString("number"));
				dto.setName(res.getString("name"));
				dto.setAge(res.getString("age"));
				dto.setTel(res.getString("tel"));
				dto.setSt(res.getString("st"));
				dto.setFi(res.getString("fi"));
				dto.setFts(res.getString("fts"));
				dto.setPeo(res.getString("peo"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public Vector getft2list() { // ȸ�� ����Ʈ ���
		// TODO Auto-generated method stub

		Vector da = new Vector();

		try {
			con = getCon();
			String sql = "SELECT * FROM `ft`.`ft` ORDER BY number";
			pre = con.prepareStatement(sql);
			res = pre.executeQuery();

			while (res.next()) {
				String number = res.getString("number");
				String name = res.getString("name");
				String age = res.getString("age");
				String tel = res.getString("tel");
				String st = res.getString("st");
				String fi = res.getString("fi");
				String fts = res.getString("fts");
				String peo = res.getString("peo");

				Vector row = new Vector();
				row.add(number);
				row.add(name);
				row.add(age);
				row.add(tel);
				row.add(st);
				row.add(fi);
				row.add(fts);
				row.add(peo);

				da.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return da;
	}

	public static void getSearch(DefaultTableModel dt, String text) { //�˻�
		String sql = "SELECT * FROM `ft`.`ft` WHERE name LIKE'%" + text.trim() +"%'";

		try {
			st = con.createStatement();
			res = st.executeQuery(sql);

			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
				
			}
			while (res.next()) {
				String data[] = { res.getString(1), res.getString(2), res.getString(3), res.getString(4),
						res.getString(5), res.getString(6), res.getString(7), res.getString(8) };

				dt.addRow(data);
			}
		} catch (SQLException e) {
			System.out.println(e + "-> �˻� ����");
		}
	}

	public boolean insertMember(ftDTO dto) { // ȸ�� ���
		try {
			con = getCon();
			String sql = "INSERT INTO `ft`.`ft` (`number`, `name`, `age`, `tel`, `st`, `fi`, `fts`, `peo`) "
					+ "VALUES (?,?,?,?,?,?,?,?)";

			pre = con.prepareStatement(sql);
			pre.setString(1, dto.getNumber());
			pre.setString(2, dto.getName());
			pre.setString(3, dto.getAge());
			pre.setString(4, dto.getTel());
			pre.setString(5, dto.getSt());
			pre.setString(6, dto.getFi());
			pre.setString(7, dto.getFts());
			pre.setString(8, dto.getPeo());

			int r = pre.executeUpdate(); // ���� > ����
			if (r > 0)
				return true; // ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateMember(ftDTO udto) { // ȸ�� ���� ����

		System.out.println("dto=" + udto.toString());
		try {
			con = getCon();
			String sql = "UPDATE `ft`.`ft` SET `name`=?, `age`=?, `tel`=?, `st`=?, `fi`=?, `fts`=?, `peo`=? WHERE `number`=?";

			pre = con.prepareStatement(sql);
			pre.setString(1, udto.getName());
			pre.setString(2, udto.getAge());
			pre.setString(3, udto.getTel());
			pre.setString(4, udto.getSt());
			pre.setString(5, udto.getFi());
			pre.setString(6, udto.getFts());
			pre.setString(7, udto.getPeo());
			pre.setString(8, udto.getNumber());

			int r = pre.executeUpdate(); // ���� > ����
			// 1~n=����, 0=����
			if (r > 0)
				return true; // ����
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteMember(String number) { // ȸ�� ���� ����
		try {
			con = getCon();
			String sql = "DELETE FROM `ft`.`ft` WHERE `number`=?";

			pre = con.prepareStatement(sql);
			pre.setString(1, number);

			int r = pre.executeUpdate(); // ���� > ����
			if (r > 0)
				return true; // ����
		} catch (Exception e) {
			System.out.println(e + "����");
		}
		return false;
	}
}