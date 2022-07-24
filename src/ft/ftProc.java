package ft;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ftProc extends JFrame implements ActionListener {

	JTextField tnumber, tname, tage, ttel1, ttel2, ttel3;
	JTextField tyear1, tmon1, tdate1, tyear2, tmon2, tdate2;
	JRadioButton rPT, rH, rGH;
	JComboBox cpeo;
	JButton btAdd, btCan, btUpdat, btDel;

	GridBagLayout grid; // ��ġ�� ���� ���� �� �ִ� gridlayout
	GridBagConstraints gridc;
	FT2 ft2;

	public ftProc() {
		createUI(); // UI �ۼ��� �ִ� �޼ҵ�
		btUpdat.setEnabled(false);
		btUpdat.setVisible(false);
		btDel.setEnabled(false);
		btDel.setVisible(false);
	}

	public ftProc(FT2 ft2) { // ���Կ� ������
		createUI();
		btUpdat.setEnabled(false);
		btUpdat.setVisible(false);
		btDel.setEnabled(false);
		btDel.setVisible(false);
		this.ft2 = ft2;
	}

	public ftProc(String number, FT2 ft2) { // ������ ���� ������
		// TODO Auto-generated constructor stub

		createUI();
		btAdd.setEnabled(false);
		btAdd.setVisible(false);
		this.ft2 = ft2;

		ftDAO dao = new ftDAO();
		ftDTO udto = dao.getftDTO(number);
		viewData(udto);
	} // ȸ�� ��ȣ�� ������ ����

	private void viewData(ftDTO udto) {
		// DTO�� ȸ�� ������ ������ ȭ�鿡 ����
		// TODO Auto-generated method stub

		String number = udto.getNumber();
		String name = udto.getName();
		String age = udto.getAge();
		String tel = udto.getTel();
		String st = udto.getSt();
		String fi = udto.getFi();
		String fts = udto.getFts();
		String peo = udto.getPeo();

		tnumber.setText(number);

		tname.setText(name);
		tage.setText(age);

		ttel1.setText(tel.substring(0, 3));
		ttel2.setText(tel.substring(4, 8));
		ttel3.setText(tel.substring(9, 13));

		tyear1.setText(st.substring(0, 4));
		tmon1.setText(st.substring(5, 7));
		tdate1.setText(st.substring(8, 10));

		tyear2.setText(fi.substring(0, 4));
		tmon2.setText(fi.substring(5, 7));
		tdate2.setText(fi.substring(8, 10));

		if (fts.equals("PT")) {
			rPT.setSelected(true);
		} else if (fts.equals("�ｺ")) {
			rH.setSelected(true);
		} else if (fts.equals("�ｺ+GX")) {
			rGH.setSelected(true);
		}

		cpeo.setSelectedItem(peo);
	}

	private void createUI() {
		// TODO Auto-generated method stub

		this.setTitle("ȸ�� ����");
		grid = new GridBagLayout();
		setLayout(grid);
		gridc = new GridBagConstraints();
		gridc.fill = GridBagConstraints.BOTH;

		JLabel lnumber = new JLabel("ȸ�� ��ȣ : ");
		tnumber = new JTextField(15);
		gridAdd(lnumber, 0, 0, 1, 1);
		gridAdd(tnumber, 1, 0, 3, 1);

		JLabel lname = new JLabel("ȸ���� : ");
		tname = new JTextField(15);
		gridAdd(lname, 0, 1, 1, 1);
		gridAdd(tname, 1, 1, 3, 1);

		JLabel lage = new JLabel("���� : ");
		tage = new JTextField(15);
		gridAdd(lage, 0, 2, 1, 1);
		gridAdd(tage, 1, 2, 3, 1);

		JLabel ltel = new JLabel("TEL : ");
		JPanel ptel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ttel1 = new JTextField(4);
		ttel2 = new JTextField(4);
		ttel3 = new JTextField(4);
		ptel.add(ttel1);
		ptel.add(new JLabel(" - "));
		ptel.add(ttel2);
		ptel.add(new JLabel(" - "));
		ptel.add(ttel3);
		gridAdd(ltel, 0, 3, 1, 1);
		gridAdd(ptel, 1, 3, 3, 1);

		JLabel ldate1 = new JLabel("��� ���� : ");
		tyear1 = new JTextField(4);
		tmon1 = new JTextField(4);
		tdate1 = new JTextField(4);
		JPanel pdate1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pdate1.add(tyear1);
		pdate1.add(new JLabel(" / "));
		pdate1.add(tmon1);
		pdate1.add(new JLabel(" / "));
		pdate1.add(tdate1);
		gridAdd(ldate1, 0, 4, 1, 1);
		gridAdd(pdate1, 1, 4, 3, 1);

		JLabel ldate2 = new JLabel("��� �Ⱓ : ");
		tyear2 = new JTextField(4);
		tmon2 = new JTextField(4);
		tdate2 = new JTextField(4);
		JPanel pdate2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pdate2.add(tyear2);
		pdate2.add(new JLabel(" / "));
		pdate2.add(tmon2);
		pdate2.add(new JLabel(" / "));
		pdate2.add(tdate2);
		gridAdd(ldate2, 0, 5, 1, 1);
		gridAdd(pdate2, 1, 5, 3, 1);

		JLabel lfts = new JLabel("��� ���� : ");
		JPanel pfts = new JPanel(new FlowLayout(FlowLayout.LEFT));
		rPT = new JRadioButton("PT", true);
		rH = new JRadioButton("�ｺ", true);
		rGH = new JRadioButton("�ｺ+GX", true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rPT);
		bg.add(rH);
		bg.add(rGH);
		pfts.add(rPT);
		pfts.add(rH);
		pfts.add(rGH);
		gridAdd(lfts, 0, 6, 1, 1);
		gridAdd(pfts, 1, 6, 3, 1);

		JLabel lpeo = new JLabel("����� : ");
		String[] speo = { "---", "����ѹ�", "�ź���", "�η��" };
		cpeo = new JComboBox(speo);
		JPanel ppeo = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ppeo.add(cpeo);
		gridAdd(lpeo, 0, 7, 1, 1);
		gridAdd(ppeo, 1, 7, 3, 1);

		JPanel pbtn = new JPanel();
		btAdd = new JButton("���");
		btUpdat = new JButton("����");
		btDel = new JButton("����");
		btCan = new JButton("���");
		pbtn.add(btAdd);
		pbtn.add(btUpdat);
		pbtn.add(btDel);
		pbtn.add(btCan);
		gridAdd(pbtn, 0, 10, 10, 10);

		btAdd.addActionListener(this);
		btUpdat.addActionListener(this);
		btDel.addActionListener(this);
		btCan.addActionListener(this);

		setSize(350, 450);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private void gridAdd(JComponent jc, int x, int y, int w, int h) {
		// TODO Auto-generated method stub
		gridc.gridx = x;
		gridc.gridy = y;
		gridc.gridwidth = w;
		gridc.gridheight = h;
		grid.setConstraints(jc, gridc);
		gridc.insets = new Insets(2, 2, 2, 2); // ����
		add(jc, gridc);
	}

	public static void main(String[] args) {
		new ftProc();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub

		if (ae.getSource() == btAdd) { // ȸ�� ���

			if (tnumber.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "ȸ�� ��ȣ�� �Է��ϼ���.");
				tnumber.requestFocus();

			} else if (tname.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "ȸ������ �Է��ϼ���.");
				tname.requestFocus();

			} else if (tage.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "���̸� �Է��ϼ���.");
				tage.requestFocus();

			} else if (ttel1.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �Է��ϼ���.");
				ttel1.requestFocus();

			} else if (ttel2.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �Է��ϼ���.");
				ttel2.requestFocus();

			} else if (ttel3.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��ȭ��ȣ�� �Է��ϼ���.");
				ttel3.requestFocus();

			} else if (tyear1.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��� ���ڸ� �Է��ϼ���.");
				tyear1.requestFocus();

			} else if (tmon1.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��� ���ڸ� �Է��ϼ���.");
				tmon1.requestFocus();

			} else if (tdate1.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��� ���ڸ� �Է��ϼ���.");
				tdate1.requestFocus();

			} else if (tyear2.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��� �Ⱓ�� �Է��ϼ���.");
				tyear2.requestFocus();

			} else if (tmon2.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��� �Ⱓ���Է��ϼ���.");
				tmon2.requestFocus();

			} else if (tdate2.getText().trim().length() == 0) {
				JOptionPane.showMessageDialog(this, "��� �Ⱓ�� �Է��ϼ���.");
				tdate2.requestFocus();

			} else {
				insertMember();
			}

		} else if (ae.getSource() == btCan) {
			this.dispose(); // ���� â�� �ݱ�

		}
		if (ae.getSource() == btUpdat) {
			updateMember();
			this.dispose();

		} else if (ae.getSource() == btDel) {
			int x = JOptionPane.showConfirmDialog(this, "�����Ͻðڽ��ϱ�?", "����", JOptionPane.YES_NO_OPTION);
			this.dispose();

			if (x == JOptionPane.OK_OPTION) {
				deleteMember();
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "��ҵǾ����ϴ�.");
			}
		}
	}

	private void deleteMember() {
		// TODO Auto-generated method stub

		String number = tnumber.getText();
		ftDAO dao = new ftDAO();
		boolean bo = dao.deleteMember(number);

		if (bo) {
			JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "ȸ�� ��ȣ�� ��ġ���� �ʽ��ϴ�.");
		}
	}

	private void updateMember() {
		// TODO Auto-generated method stub

		ftDTO dto = getViewData(); // ȸ���� ������ ���
		ftDAO dao = new ftDAO(); // ������ ���̽� ����

		boolean bo = dao.updateMember(dto);

		if (bo) {
			JOptionPane.showMessageDialog(this, "�����Ǿ����ϴ�.");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "�ٽ� Ȯ���� �ּ���.");
		}
	}

	private void insertMember() {
		// TODO Auto-generated method stub

		ftDTO dto = getViewData(); // ����ڰ� �Է��� ������ ����
		ftDAO dao = new ftDAO();

		boolean bo = dao.insertMember(dto);

		if (bo) {
			JOptionPane.showMessageDialog(this, "��ϵǾ����ϴ�.");
			this.dispose();

		} else {
			JOptionPane.showMessageDialog(this, "�ߺ��� ȸ�� ��ȣ�Դϴ�.");
			tnumber.setText(" ");
			this.dispose();
		}
	}

	private ftDTO getViewData() {
		// TODO Auto-generated method stub

		// ȭ�鿡�� ����ڰ� �Է��� ������ ��´�
		ftDTO dto = new ftDTO();
		String number = tnumber.getText();
		String name = tname.getText();
		String age = tage.getText();
		String tel1 = ttel1.getText();
		String tel2 = ttel2.getText();
		String tel3 = ttel3.getText();
		String tel = tel1 + "-" + tel2 + "-" + tel3;
		String date1 = tyear1.getText();
		String date2 = tmon1.getText();
		String date3 = tdate1.getText();
		String date4 = tyear2.getText();
		String date5 = tmon2.getText();
		String date6 = tdate2.getText();
		String st = date1 + "/" + date2 + "/" + date3;
		String fi = date4 + "/" + date5 + "/" + date6;
		String fts = "";
		if (rPT.isSelected()) {
			fts = "PT";
		} else if (rH.isSelected()) {
			fts = "�ｺ";
		} else if (rGH.isSelected()) {
			fts = "�ｺ+GX";
		}

		String peo = (String) cpeo.getSelectedItem();

		dto.setNumber(number);
		dto.setName(name);
		dto.setAge(age);
		dto.setTel(tel);
		dto.setSt(st);
		dto.setFi(fi);
		dto.setFts(fts);
		dto.setPeo(peo);

		return dto;

	}
}