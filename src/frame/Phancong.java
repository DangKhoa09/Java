package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import Crud.crudChitietCT;
import Crud.crudCongtrinh;
import Crud.crudLaodong;
import Crud.crudChuyenmon;
import Entity.Chitietct;
import Entity.Chuyenmon;
import Entity.Congtrinh;
import Entity.Laodong;


//import Crub.crubChitiet;
//import Crub.crubCongtrinh;
//import Crub.crubPhongban;

public class Phancong extends JPanel implements ActionListener, MouseListener{
	private javax.swing.JButton btn;
    private javax.swing.JButton btnPhancong;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> jComboBox1,jComboBox2;
    private javax.swing.JLabel lblSoluongcan;
    private javax.swing.JLabel lblSoluong,lblCM,lblMaCT;
    private javax.swing.JLabel lblTenCT,lblSL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtSL,txtTen;
    private  DefaultTableModel model;
    private JMenuItem mnQL;
    private crudChuyenmon qlcm;
    private crudCongtrinh qlct;
    private crudChitietCT qlctct;
    private crudLaodong qlld;
    private DefaultComboBoxModel cmBoxModel,cmBoxModel2;
	 public Phancong() {
		// TODO Auto-generated constructor stub
		setLayout(null);
		jPanel1 = new JPanel();
		jPanel1.setLayout(null);
		jPanel1.setPreferredSize(new Dimension(0,70));
		jPanel1.setBackground(Color.WHITE);
		
		jPanel1.add(lblTenCT = new JLabel("Tên công trình: "));
		jPanel1.add(txtTen = new JTextField());
		jPanel1.add(lblSoluongcan = new JLabel("Số lượng cần: "));
		jPanel1.add(lblSL = new JLabel(""));
		jPanel1.add(lblSoluong = new JLabel("Số lượng: "));
		jPanel1.add(txtSL =new JTextField());
		jPanel1.add(lblCM = new JLabel("Chuyên môn"));
		jComboBox2 = new JComboBox<String>();
		cmBoxModel2 = new DefaultComboBoxModel<>(XuatTTCombobox1());
		jComboBox2.setModel(cmBoxModel2);
		jPanel1.add(jComboBox2);
		
		
		
		lblTenCT.setBounds(20,35,100,20);
		txtTen.setBounds(115,35,150,20);
		lblSoluongcan.setBounds(275,35,100,20);
		lblSL.setBounds(375,35,100,20);
		lblSoluong.setBounds(500,35,100,20);
		txtSL.setBounds(600,35,100,20);
		lblCM.setBounds(750,35,100,20);
		jComboBox2.setBounds(890,35,180,20);
		
		jPanel2 = new JPanel();
		jPanel2.setLayout(null);
		jPanel2.setBackground(Color.WHITE);
		String[] a ="Mã lao động;Tên lao động;Chuyên môn;Số điện thoại;Địa chỉ".split(";");
		model = new DefaultTableModel(a,0);
		jScrollPane1 = new JScrollPane(jTable1 = new JTable(model));
		JTableHeader header = jTable1.getTableHeader();
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.PLAIN, 14));
		header.setBackground(new Color(0,51,51));
		jScrollPane1.setBounds(20,5,1285,470);
		jPanel2.add(jScrollPane1);
		showData();
		jTable1.setBackground(new Color(255,255,204));
		jPanel3 = new JPanel();
		jPanel3.setLayout(null);
		jPanel3.setPreferredSize(new Dimension(0,50));
		jPanel3.setBackground(Color.WHITE);
		jPanel3.add(lblMaCT = new JLabel("Mã công trình"));
		jComboBox1 = new JComboBox<String>();
		cmBoxModel = new DefaultComboBoxModel<>(XuatTTCombobox());
		jPanel3.add(jComboBox1);
		jComboBox1.addItem("Select");
		jComboBox1.setModel(cmBoxModel);
	
		
		jPanel3.add(btnPhancong = new JButton("Phân công"));
		jPanel3.add(btnReset = new JButton("Refresh"));
		
		lblMaCT.setBounds(20,12,100,20);
		jComboBox1.setBounds(115,10,120,25);
		btnPhancong.setBounds(550,10,100,25);
		btnReset.setBounds(700,10,100,25);		
		jPanel1.setBounds(0,0,1337,70);
		jPanel2.setBounds(0,70,1337,500);
		jPanel3.setBounds(0,570,1337,50);
		add(jPanel1);
		add(jPanel2);
		add(jPanel3);
		
		
		btnPhancong.addActionListener(this);
		btnReset.addActionListener(this);
		jTable1.addMouseListener(this);
		jComboBox1.addActionListener(this);
		jComboBox2.addActionListener(this);
		
		
	}
	 public static void main(String[] args) {
		new Phancong().setVisible(true);
	}
	 @Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Object o = e.getSource();
			
			if(o.equals(jComboBox1)) {
				
				String mact = (String)jComboBox1.getSelectedItem();
				List<Congtrinh> listCT = new crudCongtrinh().FindByID(mact);
				txtTen.setText(qlct.TenCT(mact));
				txtSL.setText(qlct.soluong(mact)+"");
				if(mact.equals("Select")) {
					lblSL.setText("");
				}
				else {
					for(Congtrinh ct: listCT) {
						lblSL.setText(ct.getSoluong()+"");
					
				}
				}
				
				
			}
			else if(o.equals(btnPhancong)) {
				qlctct = new crudChitietCT();
				qlld = new crudLaodong();
				int row = jTable1.getSelectedRow();
				String ma = jTable1.getValueAt(row,0).toString();
				Laodong mald = new Laodong(ma);
				String mact = jComboBox1.getSelectedItem().toString();
				Congtrinh mact1 = new Congtrinh(mact);
				java.sql.Date ngaystart = qlct.ngaybatdau(mact);
				java.sql.Date ngayend = qlct.ngaykethuc(mact);
				Chitietct ctct = new Chitietct(mact1, mald, ngaystart, ngayend);
				List<Congtrinh> listCT = new crudCongtrinh().FindByID(mact);
				if (qlct.soluong(mact)==qlct.soluongcan(mact)) {
					JOptionPane.showMessageDialog(this, "Số lượng vượt giới hạn!");
				}
				else {
					if(qlld.CHIHUY(ma, mact)) {
						if(qlctct.insert(ctct)) {
							JOptionPane.showMessageDialog(this,"Phan cong thanh cong");
							showData();
							txtSL.setText(qlct.soluong(mact)+"");
	
						}
					}
					else {
						{
							JOptionPane.showMessageDialog(this,"Công trình này đã có chỉ huy");
						}
					}
				}
				
			
			}
			else if(o.equals(jComboBox2)) {
				String macm = LayMaCM1((String) jComboBox2.getSelectedItem());
				if(jComboBox2.getSelectedItem().equals("-----------")) {
					showData();
				}
				else {
					showData2(macm);
				}
				
				
			}
			else if(o.equals(btnReset)) {
				showData();
				newCombobox();
				newCombobox1();
				lblSL.setText("");
				txtSL.setText("");
				txtTen.setText("");
			}
			
		}
		private void showData() {
			List<Laodong> listNV = crudLaodong.find();
			model.setRowCount(0);
			listNV.forEach((Laodong)->{model.addRow(new Object[] {Laodong.getMald(),Laodong.getTenld(),Laodong.getMacm().getMacm(),Laodong.getDiachi(),Laodong.getSdt()});
			});
			
		}
		private void showData1(String ma) {
			List<Congtrinh> listCT = new crudCongtrinh().FindByID(ma);
			model.setRowCount(0);
			listCT.forEach((Congtrinh)->{model.addRow(new Object[] {Congtrinh.getTenct(),Congtrinh.isTrangthai(),Congtrinh.getSoluong()});});
			
		}
		private void showData2(String ma) {
			List<Laodong> list = new crudLaodong().FindByPB(ma);
			model.setRowCount(0);
			list.forEach((Laodong)->{model.addRow(new Object[] {Laodong.getMald(),Laodong.getTenld(),Laodong.getMacm().getMacm(),Laodong.getDiachi(),Laodong.getSdt()});});
			
		}
		private void newCombobox1() {
			cmBoxModel = new DefaultComboBoxModel<>(XuatTTCombobox1());
			jComboBox2.setModel(cmBoxModel);
		}
		private String[] XuatTTCombobox1() {
			
			qlcm = new crudChuyenmon();
			List<Chuyenmon> listPB = qlcm.findAll();
			String[] result = new String[listPB.size()];
			for(int i=0;i<listPB.size();i++) {
				result[i]=listPB.get(i).getTencm();
			}
			return result;
			
		}
		private void newCombobox() {
			cmBoxModel = new DefaultComboBoxModel<>(XuatTTCombobox());
			jComboBox1.setModel(cmBoxModel);
		}
		private String[] XuatTTCombobox() {
			
			qlct = new crudCongtrinh();
			List<Congtrinh> listPB = qlct.findPhancong();
			String[] result = new String[listPB.size()];
			for(int i=0;i<listPB.size();i++) {
				result[i]=listPB.get(i).getMact();
			}
			return result;
			
		}
		private String LayMaCM1(String TenCT) {
			qlcm = new crudChuyenmon();
			List<Chuyenmon> listPB = qlcm.findAll();
			for (Chuyenmon cm : listPB) {
				if(cm.getTencm().equals(TenCT))
					return cm.getMacm();
			}
			return null;
		}
//	
		private String LayMaCM(String TenCT) {
			qlct = new crudCongtrinh();
			List<Congtrinh> listPB = qlct.findAll();
			for (Congtrinh cm : listPB) {
				if(cm.getTenct().equals(TenCT))
					return cm.getMact();
			}
			return null;
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
