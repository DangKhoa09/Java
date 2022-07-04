package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Crud.crudChuyenmon;
import Crud.crudCongtrinh;
import Crud.crudLaodong;
import Entity.Chuyenmon;
import Entity.Congtrinh;
import Entity.Laodong;


//import Crub.crubLaodong;

public class ChitietLaodong extends JFrame implements ActionListener {
	private JLabel lblImage,lblMa,lbltxtMa,lblTen,lbltxtTen,lblPhai,lbltxtPhai,lblTT,lbltxtTT,lblCM,lbltxtCM,lblSDT,lbltxtSDT,lblCTQ,lblCT,lblTuoi,lbltxtTuoi;
	private JComboBox<String> cmBox1,cmBox2;
	private JButton btnTrove,btnCTPC;
//	private crubLaodong qlld;
	private crudChuyenmon qlcm;
	private String ma;
	public ChitietLaodong(String ma) {
		// TODO Auto-generated constructor stub
		this.ma=ma;
		setTitle("Chi tiết lao động");
		setSize(700,430);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel jpn,jpn1;
		jpn = new JPanel();
		jpn.setLayout(null);
		jpn.add(lblImage = new JLabel(""));
		jpn.add(lblMa = new JLabel("Mã lao động:"));
		jpn.add(lbltxtMa = new JLabel(""));
		jpn.add(lblTen = new JLabel("Tên lao động:"));
		jpn.add(lbltxtTen = new JLabel(""));
		jpn.add(lblPhai = new JLabel("Giới tính:"));
		jpn.add(lbltxtPhai = new JLabel(""));
		jpn.add(lblTuoi = new JLabel("Tuổi:"));
		jpn.add(lbltxtTuoi = new JLabel(""));
		jpn.add(lblTT = new JLabel("Trạng thái:"));
		jpn.add(lbltxtTT = new JLabel(""));
		jpn.add(lblCM = new JLabel("Chuyên môn:"));
		jpn.add(lbltxtCM = new JLabel(""));
		jpn.add(lblSDT = new JLabel("Liên hệ:"));
		jpn.add(lbltxtSDT = new JLabel(""));
		jpn.add(lblCTQ = new JLabel("Các công trình đã thực hiện:"));
		jpn.add(cmBox1 = new JComboBox<String>());
		jpn.add(lblCT = new JLabel("Các công trình đang thực hiện:"));
		jpn.add(cmBox2 = new JComboBox<String>());
		ImageIcon icon1 = new ImageIcon("src/images/avatar.png");
		
		//lblImage.setBorder(BorderFactory.createLineBorder(Color.black));
		lblImage.setBackground(Color.cyan);
		lblImage.setIcon(icon1);
		lblImage.setBounds(20,10,160,180);
		lblMa.setBounds(220,10,100,20);
		lbltxtMa.setBounds(320,10,100,20);
		lbltxtMa.setForeground(Color.blue);
		lblTen.setBounds(220,40,100,20);
		lbltxtTen.setBounds(320,40,180,20);
		lblPhai.setBounds(220,70,100,20);
		lbltxtPhai.setBounds(320,70,100,20);
		lblTuoi.setBounds(220,100,100,20);
		lbltxtTuoi.setBounds(320,100,150,20);
		lblCM.setBounds(220,130,100,20);
		lbltxtCM.setBounds(320,130,180,20);
		lblSDT.setBounds(220,160,100,20);
		lbltxtSDT.setBounds(320,160,100,20);
		lblTT.setBounds(220,190,100,20);
		lbltxtTT.setBounds(320,190,100,20);
		lblCTQ.setBounds(220,220,180,20);
		cmBox1.setBounds(400,220,100,20);
		lblCT.setBounds(220,250,180,20);
		cmBox2.setBounds(400,250,100,20);
		jpn.setBackground(Color.white);
		
		
		
		
		
		jpn1 = new JPanel();
		jpn1.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		jpn1.add(btnTrove = new JButton("Thoát"));
		jpn1.setBounds(220,290,300,70);
		jpn1.setBackground(Color.white);
		jpn.add(jpn1,BorderLayout.CENTER);
		add(jpn,BorderLayout.CENTER);
		xuatt(ma);
		xuattCT(ma);
		xuattCT1(ma);
		btnTrove.addActionListener(this);
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

	private void xuatt(String ma) {
		List<Laodong> listNV = crudLaodong.FindByID(ma);
		for (Laodong cm : listNV) {
			lbltxtMa.setText(cm.getMald());
			lbltxtTen.setText(cm.getTenld());
			lbltxtPhai.setText(cm.isPhai()?"Nữ":"Nam");
			lbltxtTuoi.setText((-(cm.getNgaysinh().getYear()+1900) + java.time.LocalDateTime.now().getYear())+"");
			lbltxtTT.setText(cm.isTrangthai()?"Hoạt động":"Tạm ngưng");
			lbltxtCM.setText(LayTenCM(cm.getMacm().getMacm()));
			lbltxtSDT.setText(cm.getSdt());
			
		}
	}
	private void xuattCT(String ma) {
		List<Congtrinh> listCT = crudCongtrinh.FindByCT(ma);
		for (Congtrinh cm : listCT) {
			cmBox2.addItem(cm.getMact());
		}
	}
	private void xuattCT1(String ma) {
		List<Congtrinh> listCT = crudCongtrinh.FindByCTQ(ma);
		for (Congtrinh cm : listCT) {
			cmBox1.addItem(cm.getMact());
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object object = e.getSource();
		if(object.equals(btnTrove)) {
			setVisible(false);
		}
		if(object.equals(btnCTPC)) {
			try {
				String mald = lbltxtMa.getText();
				String mact = cmBox2.getSelectedItem().toString();
				if(mact.length()>0) {
					ChitietPhancong ctct = new ChitietPhancong(mact,mald);
					ctct.setVisible(true);
				}
				else if(mact.equals("") || mact==null) {
					JOptionPane.showMessageDialog(this,"Hiện chưa có thông tin!");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this,"Hiện chưa có thông tin!");
			}
			
		}
		
	}
}
