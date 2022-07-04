package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Iterator;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Crud.crudCongtrinh;
import Crud.crudLaodong;
import Crud.crudChuyenmon;
import DatePicker.DateLabelFormatter;
import Entity.Chuyenmon;
import Entity.Congtrinh;
import Entity.Laodong;
import validata.Validate;


public class Quanlycongtrinh extends JPanel implements MouseListener, ActionListener {
	private JTable table;
	private DefaultTableModel model;
	private JTextField txtMa,txtTen,txtNgaycapphep,txtTim,txtNgayKhoicong,txtNgayDuKien,txtDiadiem,txtSoluong;
	private JLabel lblMa,lblTen,lblNgaycapphep,lblNgayKhoicong,lblTim,lblNgayDuKien,lblDiadiem,lblTrangthai,lblTTDA,lblSoluong;
	private JScrollPane scroll;
	private JCheckBox chkHoanthanh;
	private JPanel jpnW,jpnC,jpnW1,jpnN,jpnWC;
	private JButton btnThem,btnXoa,btnCapnhat,btnXoarong,btnTim,btnHome;
	private JRadioButton rad1,rad2;
	private Properties p;
	private UtilDateModel model1, model2,model3;
	private JDatePanelImpl datePanel1, datePanel2,datePanel3;
	private JDatePickerImpl datePicker1, datePicker2,datePicker3;
	private JComboBox<String> cmBox;
	private JPanel jpn,jpn1;
	private JMenuItem mnQL,mnDS;
	private crudCongtrinh qlct;
	private DefaultComboBoxModel cmBoxModel;
	private Validate regex;
	public Quanlycongtrinh() {
//		setBounds(300,132,1237,687);
////		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		setResizable(false);
		setLayout(null);
		jpn = new JPanel();
		jpn.setLayout(null);
		
		String[] a = "Mã công trình;Tên công trình;Ngày cấp phép;Ngày khởi công;Ngày dự kiến hoàn thành;Địa điểm;Trạng thái".split(";");
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
		jpn.add(lblMa = new JLabel("Mã công trình"));
		jpn.add(lblTen = new JLabel("Tên công trình"));
		jpn.add(lblNgaycapphep = new JLabel("Ngày cấp phép"));
		jpn.add(lblNgayKhoicong = new JLabel("Ngày khởi công"));
		jpn.add(lblNgayDuKien = new JLabel("Ngày dự kiến"));
		jpn.add(lblDiadiem = new JLabel("Địa điểm"));
		jpn.add(lblTrangthai = new JLabel("Trạng thái"));
		jpn.add(lblSoluong = new JLabel("Số lượng nhân công"));
		jpn.add(txtMa = new JTextField());
		jpn.add(txtTen = new JTextField());
		jpn.add(txtSoluong = new JTextField());
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1, new DateLabelFormatter());
		jpn.add(datePicker1);
		datePicker1.setTextEditable(true);
		
	
		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());
		jpn.add(datePicker2);
		datePicker2.setTextEditable(true);
		
		
		model3 = new UtilDateModel();
		datePanel3 = new JDatePanelImpl(model3, p);
		datePicker3 = new JDatePickerImpl(datePanel3, new DateLabelFormatter());
		jpn.add(datePicker3);
		datePicker3.setTextEditable(true);
		jpn.add(txtDiadiem = new JTextField());
		jpn.add(chkHoanthanh = new JCheckBox("Hoàn thành"));
		chkHoanthanh.setBackground(Color.white);
		jpn.add(lblTim = new JLabel("Tìm theo mã:"));
		
		cmBox = new JComboBox<String>();
		cmBoxModel = new DefaultComboBoxModel<>(XuatTTCombobox());
		cmBox.setModel(cmBoxModel);
		jpn.add(cmBox);
		int x=25,y=280,w1=100,w2=300,h=30;
		lblMa.setBounds(x,y,w1,h);
		txtMa.setBounds(x+110,y,100,h);
		lblTen.setBounds(x,y+50,w1,h);
		txtTen.setBounds(x+110,y+50,w2+300,h);
		lblSoluong.setBounds(x+270,y,w1+80,h);
		txtSoluong.setBounds(x+420,y,w2-200,h);
		lblTrangthai.setBounds(x+570,y,w1,h);
		chkHoanthanh.setBounds(x+650,y,100,h);
		
		lblNgaycapphep.setBounds(x,y+100,w1,h);
		datePicker1.setBounds(x+110,y+100,200,h);
		lblNgayKhoicong.setBounds(x,y+150,w1,h);
		datePicker2.setBounds(x+110,y+150,200,h);
		lblNgayDuKien.setBounds(x,y+200,w1,h);
		datePicker3.setBounds(x+110,y+200,200,h);
		lblDiadiem.setBounds(x,y+250,w1,h);
		txtMa.setForeground(Color.blue);
		txtDiadiem.setBounds(x+110,y+250,w2+300,h);
		
		lblTim.setBounds(980,230,120,20);
		cmBox.setBounds(1082,230,120,20);
		jpn1 = new JPanel();
		jpn1.setLayout(null);
		
		cmBox.addItem("Select");
		jpn1.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		jpn1.add(btnThem = new JButton("Thêm mới"));
		jpn1.add(btnXoa = new JButton("Xóa"));
		jpn1.add(btnXoarong = new JButton("Xóa rỗng"));
		jpn1.add(btnCapnhat = new JButton("Cập nhật"));
		txtMa.setEditable(false);	
		jpn1.setBounds(300,570,600,70);
		btnThem.setBounds(110,25,100,30);
		btnXoa.setBounds(215,25,100,30);
		btnCapnhat.setBounds(320,25,100,30);
		btnXoarong.setBounds(425,25,100,30);
		
		jpn1.setBackground(Color.WHITE);
		jpn.add(jpn1);
		showData();
		jpn.setBounds(0,0,1337,690);
		add(jpn);
		btnCapnhat.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoarong.addActionListener(this);
		
		cmBox.addActionListener(this);
		table.addMouseListener(this);
		XuatTTCombobox();
		table.setBackground(new Color(255,255,204));
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		regex = new Validate();
		if(o.equals(btnThem)) {
			if(regex.rThemCongTrinh(txtTen, model1, model2, model3, txtDiadiem, txtSoluong)) {
				qlct = new crudCongtrinh();
				String ma = qlct.tuDongLayMa();
				String ten = txtTen.getText();
				boolean tt = chkHoanthanh.isSelected()?true:false;
				Date ngaycap = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngaystart= Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
				Date ngayend = Date.valueOf(LocalDate.of(model3.getYear(), model3.getMonth() +1, model3.getDay()));
				String dchi = txtDiadiem.getText();
				int so = Integer.parseInt(txtSoluong.getText());
				Congtrinh ct = new Congtrinh(ma, ten, tt, so, ngaycap, ngaystart, ngayend, dchi);
				if(qlct.insert(ct)) {
					JOptionPane.showMessageDialog(this,"Thêm thành công");
					showData();
					newCombobox();
					cleardata();
				}
				
					
			}
		}
		if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			String mact = table.getValueAt(row,0).toString();
			if(row>=0) {
			int loinhac = JOptionPane.showConfirmDialog(this,"Chắc chắn xóa?","Lưu ý",JOptionPane.YES_NO_OPTION);
				if(loinhac==JOptionPane.YES_OPTION) {
					crudCongtrinh.delete(mact);
					JOptionPane.showMessageDialog(this,"Xóa thành công!");
					showData();
					newCombobox();
				}
			}
		}
		if(o.equals(btnXoarong)) {
			
			cleardata();
		}
		if(o.equals(btnCapnhat)) {
			if(regex.rThemCongTrinh(txtTen, model1, model2, model3, txtDiadiem, txtSoluong)) {
				String ma = txtMa.getText();
				String ten = txtTen.getText();
				boolean tt = chkHoanthanh.isSelected()?true:false;
				Date ngaycap = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngaystart= Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
				Date ngayend = Date.valueOf(LocalDate.of(model3.getYear(), model3.getMonth() +1, model3.getDay()));
				String dchi = txtDiadiem.getText();
				int so = Integer.parseInt(txtSoluong.getText());
				Congtrinh ct = new Congtrinh(ma, ten, tt, so, ngaycap, ngaystart, ngayend, dchi);
				qlct = new crudCongtrinh();
				if(qlct.update(ct)) {
					JOptionPane.showMessageDialog(this,"Cập nhật thành công !");
					showData();
					newCombobox();
					cleardata();
				}
				
					
			}
		}
		if(o.equals(cmBox)) {
			String ma = cmBox.getSelectedItem().toString();
			
			if(ma.equalsIgnoreCase("Select")) {
				showData();
			}
			else {
				showbyid(ma);
			}
		}
		
		
		
	}
	
	private void cleardata() {
		txtMa.setText("");
		txtTen.setText("");
		txtDiadiem.setText("");
		txtSoluong.setText("");
		chkHoanthanh.setSelected(false);
		model1.setValue(Date.valueOf(LocalDate.now()));
		model2.setValue(Date.valueOf(LocalDate.now()));
		model3.setValue(Date.valueOf(LocalDate.now()));
		
	}
	//MÃ£ cÃ´ng trÃ¬nh;TÃªn cÃ´ng trÃ¬nh;NgÃ y cáº¥p phÃ©p;NgÃ y khá»Ÿi cÃ´ng;NgÃ y dá»± kiáº¿n hoÃ n thÃ nh;Ä�á»‹a Ä‘iá»ƒm;Tráº¡ng thÃ¡i
	private void showData() {
		List<Congtrinh> list = crudCongtrinh.findAll();
		model.setRowCount(0);
		list.forEach((Congtrinh)->{model.addRow(new Object[] {Congtrinh.getMact(),Congtrinh.getTenct(),Congtrinh.getNgaycap(),Congtrinh.getNgaykhoicong(),
				Congtrinh.getNgaydukien(),Congtrinh.getDiadiem(),Congtrinh.isTrangthai()?"Hoàn thành":"Chưa hoàn thành"});
		});
		
	}
	private void showbyid(String matim) {
		if(matim.length()>0) {
			model.setRowCount(0);
			List<Congtrinh> listNV = crudCongtrinh.FindByID(matim);
			listNV.forEach((Congtrinh)->{model.addRow(new Object[] {Congtrinh.getMact(),Congtrinh.getTenct(),Congtrinh.getNgaycap(),Congtrinh.getNgaykhoicong(),
					Congtrinh.getNgaydukien(),Congtrinh.getDiadiem(),Congtrinh.isTrangthai()?"Hoàn thành":"Chưa hoàn thành"});
			});
		}
		else {
			JOptionPane.showMessageDialog(this,"Nhập tên cần tìm");
			showData();
		}
		
	}
	private void newCombobox() {
		cmBoxModel = new DefaultComboBoxModel<>(XuatTTCombobox());
		cmBox.setModel(cmBoxModel);
	}
	private String[] XuatTTCombobox() {
		
		qlct = new crudCongtrinh();
		List<Congtrinh> listPB = qlct.findAll();
		String[] result = new String[listPB.size()];
		for(int i=0;i<listPB.size();i++) {
			result[i]=listPB.get(i).getMact();
		}
		return result;
		
	}
	



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		String ma = (String) table.getValueAt(row, 0);
		List<Congtrinh> list = qlct.findAll();
		for (Congtrinh nv : list) {
			if (ma.trim().equals(nv.getMact().trim())) {
				// txtmaNV.setEditable(false);
				txtMa.setText(ma);
				txtTen.setText(nv.getTenct());
				if (nv.isTrangthai())
					chkHoanthanh.setSelected(true);
				else
					chkHoanthanh.setSelected(false);
				model1.setValue(nv.getNgaycap());
				model2.setValue(nv.getNgaykhoicong());
				model3.setValue(nv.getNgaydukien());
				txtSoluong.setText(nv.getSoluong()+"");
				txtDiadiem.setText(nv.getDiadiem());
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
