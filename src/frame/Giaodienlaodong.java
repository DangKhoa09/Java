package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Crud.crudLaodong;
import Crud.crudChuyenmon;
import Crud.crudCongtrinh;
import DatePicker.DateLabelFormatter;
import Entity.Chuyenmon;
import Entity.Congtrinh;
import Entity.Laodong;
import validata.Validate;
public class Giaodienlaodong extends JPanel implements MouseListener, ActionListener {
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtMa,txtTen,txtNgaysinh,txtSDT,txtDiachi,txtNgaylamviec,txtTim,txtCMND;
	private JLabel lblMa,lblTen,lblNgaysinh,lblSDT,lblDiachi,lblNgaylamviec,lblTTLD,lblGioitinh,lblTim,lblTrangthai,lblCMND,lblChuyenmon;
	private JScrollPane scroll;
	private JCheckBox chkPhai;
	private JPanel jpnW,jpnC,jpnW1,jpnN,jpnWC;
	private JButton btnThem,btnXoa,btnCapnhat,btnXoarong,btnTim,btnReset;
	private JRadioButton rad1;
	private JComboBox<String> cmBox;
	private Properties p;
	private crudChuyenmon qlcm;
	private crudLaodong qlld;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JDatePickerImpl datePicker1, datePicker2;
	private JPanel jpn,jpn1;
	private JMenuItem mnQL,mnDS;
	private Validate regex;
	private DefaultComboBoxModel cmBoxModel;
	public Giaodienlaodong() {
		setLayout(null);
		
		jpn = new JPanel();
		jpn.setLayout(null);
		String[] a = "Mã lao động;Họ và Tên;Chuyên môn;Giới tính;Ngày sinh;Ngày làm việc;Địa chỉ;Số điện thoại".split(";");
		model = new DefaultTableModel(a,0);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		scroll.setBounds(20,25,1285,200);
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.PLAIN, 14));
		header.setBackground(new Color(0,51,51));
		jpn.add(scroll);
		jpn.setBackground(Color.white);
		table.setBackground(new Color(255,255,204));
		jpn.add(lblMa = new JLabel("Mã lao động"));
		jpn.add(lblTen = new JLabel("Tên lao động"));
		jpn.add(lblGioitinh = new JLabel("Giới tính"));
		jpn.add(lblNgaysinh = new JLabel("Ngày sinh"));
		jpn.add(lblNgaylamviec = new JLabel("Ngày làm việc"));
		jpn.add(lblDiachi = new JLabel("Địa chỉ"));
		jpn.add(lblSDT = new JLabel("Số điện thoại"));
		jpn.add(lblTrangthai = new JLabel("Trạng thái"));
		jpn.add(lblCMND = new JLabel("CMND"));
		jpn.add(lblChuyenmon = new JLabel("Chuyên môn"));
		jpn.add(lblTim = new JLabel("Nhập tên cần tìm:"));
		
		jpn.add(txtMa = new JTextField());
		jpn.add(txtTen = new JTextField());
		jpn.add(txtNgaysinh = new JTextField());
		jpn.add(txtTim = new JTextField());
		jpn.add(btnTim = new JButton("Tìm"));
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		jpn.add(datePicker1);
		datePicker1.setTextEditable(true);
		jpn.add(txtNgaylamviec = new JTextField());
		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		jpn.add(datePicker2);
		model2.setValue(Date.valueOf(LocalDate.now()));
		datePicker2.setTextEditable(true);
		jpn.add(txtDiachi = new JTextField());
		jpn.add(txtSDT = new JTextField());
		jpn.add(chkPhai = new JCheckBox("Nữ"));
		chkPhai.setBackground(Color.WHITE);
		jpn.add(rad1 = new JRadioButton("Hoạt động"));
		jpn.add(txtCMND = new JTextField());
		cmBox = new JComboBox<String>();
		cmBoxModel = new DefaultComboBoxModel<>(XuatTTCombobox());
		cmBox.setModel(cmBoxModel);
		jpn.add(cmBox);
		rad1.setBackground(Color.WHITE);
		chkPhai.setForeground(Color.BLACK);
		rad1.setSelected(true);

		
		int x=25,y=300,w1=100,w2=300,h=30;
		lblMa.setBounds(x,y,w1,h);
		txtMa.setForeground(Color.blue);
		txtMa.setBounds(x+110,y,100,h);
		txtMa.setEditable(false);
		lblTen.setBounds(x,y+50,w1,h);
		txtTen.setBounds(x+110,y+50,w2,h);
		lblGioitinh.setBounds(x+600,y+50,w1,h);
		chkPhai.setBounds(x+700,y+50,100,h);
		lblChuyenmon.setBounds(x+600,y+200,w1,h);
		cmBox.setBounds(x+700,y+200,200,h);
		lblNgaysinh.setBounds(x,y+100,w1,h);
		datePicker1.setBounds(x+110,y+100,200,h);
		lblNgaylamviec.setBounds(x,y+150,w1,h);
		datePicker2.setBounds(x+110,y+150,200,h);
		lblCMND.setBounds(x,y+200,w1,h);
		txtCMND.setBounds(x+110,y+200,w2,h);
		lblDiachi.setBounds(x+600,y+100,w1,h);
		txtDiachi.setBounds(x+700,y+100,w2,h);
		lblSDT.setBounds(x+600,y+150,w1,h);
		txtSDT.setBounds(x+700,y+150,w2,h);
		lblTrangthai.setBounds(x+600,y,w1,h);
		rad1.setBounds(x+700,y,200,h);
		lblTim.setBounds(800,240,130,20);
		txtTim.setBounds(910,240,200,20);
		btnTim.setBounds(1120,240,70,25);
		
		jpn1 = new JPanel();
		jpn1.setLayout(null);
	
		jpn1.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		jpn1.add(btnThem = new JButton("Thêm mới"));
		jpn1.add(btnXoa = new JButton("Xóa"));
		jpn1.add(btnXoarong = new JButton("Xóa rỗng"));
		jpn1.add(btnCapnhat = new JButton("Cập nhật"));
		jpn1.add(btnReset = new JButton("Refresh"));

		jpn1.setBackground(Color.WHITE);
		jpn1.setBounds(300,550,700,70);
		btnThem.setBounds(110,25,100,30);
		btnXoa.setBounds(215,25,100,30);
		btnCapnhat.setBounds(320,25,100,30);
		btnXoarong.setBounds(425,25,100,30);
		btnReset.setBounds(530,25,100,30);
		jpn.add(jpn1);
		showData();
		jpn.setBounds(0,0,1337,690);
		add(jpn);
		btnCapnhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoarong.addActionListener(this);
		txtTim.addActionListener(this);
		table.addMouseListener(this);
		btnTim.addActionListener(this);
		btnReset.addActionListener(this);
		
	}
	public static void main(String[] args) {
		new Giaodienlaodong().setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		regex = new Validate();
		if(o.equals(btnThem)) {
			if(regex.rThemLaoDong(txtTen, model1, model2, txtDiachi, txtSDT, txtCMND)) {
				qlld = new crudLaodong();
				String ma = qlld.tuDongLayMa();
				String ten = txtTen.getText();
				String cm= LayMaCM((String)cmBox.getSelectedItem());
				Chuyenmon cmon = new Chuyenmon(cm);
				boolean phai = chkPhai.isSelected()?true:false;
				Date ngaySinh = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngayLam = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
				String dchi = txtDiachi.getText();
				String sdt =txtSDT.getText();
				String cmnd = txtCMND.getText();
				boolean tt = rad1.isSelected()?true:false;
				Laodong nv = new Laodong(ma, ten, cmon, phai, ngaySinh, ngayLam, dchi, cmnd, sdt, tt);
				if(qlld.insert(nv)) {
					JOptionPane.showMessageDialog(this,"Thêm thành công!");
					showData();
					cleardata();
				}
				
					
			}
		}
		else if(o.equals(btnReset)) {
			showData();
			newCombobox();
		}
		else if(o.equals(btnXoarong)) {
			cleardata();
			
		}
		else if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			String mald = table.getValueAt(row,0).toString();
			if(row>=0) {
			int loinhac = JOptionPane.showConfirmDialog(this,"Chắc chắn xóa ?","Lưu ý",JOptionPane.YES_NO_OPTION);
				if(loinhac==JOptionPane.YES_OPTION) {
					crudLaodong.delete(mald);
					JOptionPane.showMessageDialog(this,"Xóa thành công!");
					showData();
				}
			}
		}
		else if(o.equals(btnCapnhat)) {
			int row = table.getSelectedRow();
			String mald = table.getValueAt(row,0).toString();
			if(regex.rThemLaoDong(txtTen, model1, model2, txtDiachi, txtSDT, txtCMND)) {
				String ma = mald;
				String ten = txtTen.getText();
				String cm= LayMaCM((String)cmBox.getSelectedItem());
				Chuyenmon cmon = new Chuyenmon(cm);
				boolean phai = chkPhai.isSelected()?true:false;
				Date ngaySinh = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngayLam = Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
				String dchi = txtDiachi.getText();
				String sdt =txtSDT.getText();
				String cmnd = txtCMND.getText();
				boolean tt = rad1.isSelected()?true:false;
				Laodong nv = new Laodong(mald, ten, cmon, phai, ngaySinh, ngayLam, dchi, cmnd, sdt, tt);
				qlld = new crudLaodong();
				if(qlld.update(nv)) {
					JOptionPane.showMessageDialog(this,"Cập nhật thành công");
					showData();
					cleardata();
				}
				
					
			}
			
		}
		else if(o.equals(btnTim) || o.equals(txtTim)) {
			String tim = txtTim.getText();
			showbyname(tim);
		}
		
	}
	private void cleardata() {
		txtMa.setText("");
		txtTen.setText("");
		txtCMND.setText("");
		txtDiachi.setText("");
		txtSDT.setText("");
		cmBox.setSelectedIndex(0);
		chkPhai.setSelected(false);
		model1.setValue(Date.valueOf(LocalDate.now()));
		model2.setValue(Date.valueOf(LocalDate.now()));
	}
	//MÃ£ lao Ä‘á»™ng;TÃªn lao Ä‘á»™ng;ChuyÃªn mÃ´n;Giá»›i tÃ­nh;Tuá»•i;NgÃ y lÃ m viá»‡c;Ä�á»‹a chá»‰;Sá»‘ diá»‡n thoáº¡i/
	private void showData() {
		List<Laodong> listNV = crudLaodong.findAll();
		model.setRowCount(0);
		listNV.forEach((Laodong)->{model.addRow(new Object[] {Laodong.getMald(),Laodong.getTenld(),Laodong.getMacm().getMacm(),Laodong.isPhai()?"Nữ":"Nam",Laodong.getNgaysinh(),
				Laodong.getNgaylamviec(),Laodong.getDiachi(),Laodong.getSdt()});
		});
		
	}
	private void showbyname(String matim) {
		if(matim.length()>0) {
			model.setRowCount(0);
			List<Laodong> listNV = crudLaodong.FindByName(matim);
			listNV.forEach((Laodong)->{model.addRow(new Object[] {Laodong.getMald(),Laodong.getTenld(),Laodong.getMacm().getMacm(),Laodong.isPhai()?"Nữ":"Nam",Laodong.getNgaysinh(),
					Laodong.getNgaylamviec(),Laodong.getDiachi(),Laodong.getSdt()});
			});
		}
		else {
			JOptionPane.showMessageDialog(this,"Nháº­p ten cáº§n tÃ¬m");
			showData();
		}
		
	}
	private void newCombobox() {
		cmBoxModel = new DefaultComboBoxModel<>(XuatTTCombobox());
		cmBox.setModel(cmBoxModel);
	}
	private String[] XuatTTCombobox() {
		
		qlcm = new crudChuyenmon();
		List<Chuyenmon> listPB = qlcm.findAll();
		String[] result = new String[listPB.size()];
		for(int i=0;i<listPB.size();i++) {
			result[i]=listPB.get(i).getTencm();
		}
		return result;
		
	}
	
	private String LayMaCM(String TenCM) {
		qlcm = new crudChuyenmon();
		List<Chuyenmon> listPB = qlcm.findAll();
		for (Chuyenmon cm : listPB) {
			if(cm.getTencm().equals(TenCM))
				return cm.getMacm();
		}
		return null;
	}
	private String LayTenCM(String Ma) {
		qlcm = new crudChuyenmon();
		List<Chuyenmon> listPB = qlcm.findAll();
		for (Chuyenmon cm : listPB) {
			if(cm.getMacm().equals(Ma))
				return cm.getTencm();
		}
		return null;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		String ma = (String) table.getValueAt(row, 0);
		List<Laodong> list = qlld.findAll();
		for (Laodong nv : list) {
			if (ma.trim().equals(nv.getMald().trim())) {
				// txtmaNV.setEditable(false);
				txtMa.setText(ma);
				txtTen.setText(nv.getTenld());
				cmBox.setSelectedItem(LayTenCM(nv.getMacm().getMacm()));
				if (nv.isPhai())
					chkPhai.setSelected(true);
				else
					chkPhai.setSelected(false);
				model1.setValue(nv.getNgaysinh());
				model2.setValue(nv.getNgaylamviec());
				txtCMND.setText(nv.getCmnd());
				txtSDT.setText(nv.getSdt());
				txtDiachi.setText(nv.getDiachi());
				boolean i = nv.isTrangthai();
				rad1.setSelected(false);
				if (i == true)
					rad1.setSelected(true);
				
			}
		}

			
		
		
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
