package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import Crud.crudChitietCT;
import DatePicker.DateLabelFormatter;
import Entity.Chitietct;
import Entity.Congtrinh;
import Entity.Laodong;

public class ChitietPhancong extends JFrame implements ActionListener, MouseListener{
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel panel;
	private JLabel lblthongTinCTCT;
	private JLabel blbIDCongTrinh;
	private JLabel lblIDNhanVien;
	private JPanel panel_1;
	private JButton btnthem;
	private JButton btncapNhat;
	private JButton btnxoaRong;
	private JButton btntroVe;
	private JTextField txtIDCongTrinh;
	private JTextField txtIDNhanVien;
	private JTextField txtngayBD;
	private JTextField txtngayKT;
	private Properties p;
	private UtilDateModel model1, model2;
	private JDatePanelImpl datePanel1, datePanel2;
	private JDatePickerImpl datePicker1, datePicker2;
	private static String mact;
	private static String mald;
	private crudChitietCT qlctct;
	public ChitietPhancong(String mact,String mald) {
		// TODO Auto-generated constructor stub
		this.mact=mact;
		this.mald=mald;
		setTitle("Chi tiết");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 520, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(20, 10, 460, 345);
		contentPane.add(panel);
		panel.setLayout(null);

		lblthongTinCTCT = new JLabel("Thông tin");
		lblthongTinCTCT.setForeground(Color.BLUE);
		lblthongTinCTCT.setFont(new Font("Arial", Font.BOLD, 19));
		lblthongTinCTCT.setBounds(180, 10, 400, 30);
		panel.add(lblthongTinCTCT);

		blbIDCongTrinh = new JLabel("Mã công trình:");
		blbIDCongTrinh.setFont(new Font("Arial", Font.PLAIN, 14));
		blbIDCongTrinh.setBounds(10, 50, 120, 20);
		panel.add(blbIDCongTrinh);

		lblIDNhanVien = new JLabel("Mã lao động:");
		lblIDNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIDNhanVien.setBounds(10, 80, 120, 20);
		panel.add(lblIDNhanVien);
		
		lblIDNhanVien = new JLabel("Ngày bắt đầu:");
		lblIDNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIDNhanVien.setBounds(10, 110, 120, 20);
		panel.add(lblIDNhanVien);
		
		lblIDNhanVien = new JLabel("Ngày kết thúc:");
		lblIDNhanVien.setFont(new Font("Arial", Font.PLAIN, 14));
		lblIDNhanVien.setBounds(10, 150, 120, 20);
		panel.add(lblIDNhanVien);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Tác vụ", TitledBorder.LEADING, TitledBorder.TOP,

				null, Color.BLUE));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 200, 430, 140);
		panel.add(panel_1);


		btncapNhat = new JButton("Cập nhật");
		btncapNhat.setFont(new Font("Arial", Font.PLAIN, 14));
		btncapNhat.setBackground(Color.WHITE);
		btncapNhat.setBounds(25, 80, 170, 40);
		panel_1.add(btncapNhat);

		btnxoaRong = new JButton("Xóa");
		btnxoaRong.setFont(new Font("Arial", Font.PLAIN, 14));
		btnxoaRong.setBackground(Color.WHITE);
		btnxoaRong.setBounds(130, 25, 170, 40);
		panel_1.add(btnxoaRong);

		btntroVe = new JButton("Thoát");
		btntroVe.setFont(new Font("Arial", Font.PLAIN, 14));
		btntroVe.setBackground(Color.WHITE);
		btntroVe.setBounds(230, 80, 170, 40);
		panel_1.add(btntroVe);

		txtIDCongTrinh = new JTextField();
		txtIDCongTrinh.setEditable(false);
		txtIDCongTrinh.setColumns(10);
		txtIDCongTrinh.setBounds(140, 50, 300, 20);
		panel.add(txtIDCongTrinh);

		txtIDNhanVien = new JTextField();
		txtIDNhanVien.setEditable(false);
		txtIDNhanVien.setColumns(10);
		txtIDNhanVien.setBounds(140, 80, 300, 20);
		panel.add(txtIDNhanVien);
		
//		txtngayBD = new JTextField();
//		txtngayBD.setColumns(10);
//		txtngayBD.setBounds(140, 110, 300, 20);
//		panel.add(txtngayBD);
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model1 = new UtilDateModel();
		datePanel1 = new JDatePanelImpl(model1, p);
		datePicker1 = new JDatePickerImpl(datePanel1,new DateLabelFormatter());
		panel.add(datePicker1);
		datePicker1.setBounds(140, 110, 300, 26);
		
		
//		txtngayKT = new JTextField();
//		txtngayKT.setColumns(10);
//		txtngayKT.setBounds(140, 140, 300, 20);
//		panel.add(txtngayKT);
		p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		model2 = new UtilDateModel();
		datePanel2 = new JDatePanelImpl(model2, p);
		datePicker2 = new JDatePickerImpl(datePanel2,new DateLabelFormatter());
		panel.add(datePicker2);
		datePicker2.setBounds(140, 150, 300, 26);
		
		btntroVe.addActionListener(this);
		btncapNhat.addActionListener(this);
		btnxoaRong.addActionListener(this);
		showData(mact, mald);
	}
//	public static void main(String[] args) {
//		new ChitietPhancong(mact, mald).setVisible(true);
//	}
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

	public static void main(String[] args) {
		new ChitietPhancong(mact,mald).setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		int row = table.getSelectedRow();
//		if (row != -1) {
////			btnthem.setVisible(false);
//			String ma = (String) table.getValueAt(row,0);
//			String mald = (String)table.getValueAt(row,1);
//			qlctct = new crubChitiet();
//			List<Chitietct> list = qlctct.findCTPhancong(ma, mald);
//			for (Chitietct ctct : list) {
//				txtIDCongTrinh.setText(ctct.getMact().getMact());
//				txtIDNhanVien.setText(ctct.getMald().getMald());
//				model1.setValue(ctct.getNgaybatdau());
//				model2.setValue(ctct.getNgayketthuc());
//			}
//		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btntroVe)) {
			setVisible(false);
		}
		else if(o.equals(btncapNhat)) {
			int loinhac = JOptionPane.showConfirmDialog(this,"Cập nhật lại ?","Lưu ý",JOptionPane.YES_NO_OPTION);
			if(loinhac==JOptionPane.YES_OPTION) {
				String ma = txtIDCongTrinh.getText();
				Congtrinh ct = new Congtrinh(ma);
				String mald = txtIDNhanVien.getText();
				Laodong ld = new Laodong(mald);
				Date ngaybatdau = Date.valueOf(LocalDate.of(model1.getYear(), model1.getMonth() + 1, model1.getDay()));
				Date ngayketthuc= Date.valueOf(LocalDate.of(model2.getYear(), model2.getMonth() + 1, model2.getDay()));
				Chitietct ctct = new Chitietct(ct, ld, ngaybatdau, ngayketthuc);
				qlctct = new crudChitietCT();
				if(qlctct.update(ctct)) {
					JOptionPane.showMessageDialog(this,"Cập nhật thành công!");
					setVisible(false);
				}
				
				
			}
		}
		else if(o.equals(btnxoaRong)) {
			String ma = txtIDCongTrinh.getText();
			String mald = txtIDNhanVien.getText();
			int loinhac = JOptionPane.showConfirmDialog(this,"Chắc chắn xóa không?","Cảnh báo",JOptionPane.YES_NO_OPTION);
			if(loinhac==JOptionPane.YES_OPTION) {
				if(qlctct.delete(ma, mald)) {
					JOptionPane.showMessageDialog(this,"Xóa thành công!");
					setVisible(false);
				}
			}
			
		}
		
	}
	private void showData(String mact,String mald) {
		List<Chitietct> list = crudChitietCT.findCTPhancong(mact, mald);
		for(Chitietct ct : list) {
			txtIDCongTrinh.setText(ct.getMact().getMact());
			txtIDNhanVien.setText(ct.getMald().getMald());
			model1.setValue(ct.getNgaybatdau());
			model2.setValue(ct.getNgayketthuc());
		}
		
	}
}
