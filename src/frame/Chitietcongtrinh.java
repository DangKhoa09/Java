package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Crud.crudChitietCT;
import Crud.crudCongtrinh;
import Entity.Chitietct;
import Entity.Congtrinh;

public class Chitietcongtrinh extends JPanel implements ActionListener {
	private JScrollPane scroll;
	private JTable table;
	private DefaultTableModel model;
	private String mact;
	private JButton btnSua,btnTroVe,btnTim,btnReset,btnXoa;
	private JPanel contentPanel;
	private JComboBox<String> cmBox,cmBox2;
	private crudChitietCT qlctct;
	private crudCongtrinh qlct;
	private JMenuItem mnQL;
	private DefaultComboBoxModel<String> cmbModel1,cmbModel2;
	public Chitietcongtrinh() {
		setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		contentPanel.setBackground(Color.WHITE);
		String[] a = "Mã công trình;Mã lao động;Ngày khởi công;Ngày dự kiến hoàn thành".split(";");
		model = new DefaultTableModel(a,0);
		scroll = new JScrollPane(table= new JTable(model));
		scroll.setBounds(20,35,1020,600);
		contentPanel.add(scroll);
		
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.PLAIN, 14));
		header.setBackground(new Color(0,51,51));
		table.setBackground(new Color(255,255,204));
		JPanel jpn1 = new JPanel();
		jpn1.setLayout(null);
		cmBox = new JComboBox<>();
		cmbModel1 = new DefaultComboBoxModel<>(XuatTTCombobox2());
		cmBox.setModel(cmbModel1);
		cmBox2 = new JComboBox<>();
		cmbModel2 = new DefaultComboBoxModel<>(XuatTTCombobox1());
		cmBox2.setModel(cmbModel2);
		jpn1.add(cmBox);
		jpn1.add(cmBox2);
		jpn1.setBorder(BorderFactory.createTitledBorder("Tìm kiếm"));
		cmBox.setBounds(15,25,120,30);
		cmBox2.setBounds(15,65,120,30);
		JPanel jpn2 = new JPanel();
		jpn2.setLayout(null);
		jpn2.add(btnSua = new JButton("Chỉnh sửa"));
		jpn2.add(btnReset = new JButton("Refresh"));
		jpn2.add(btnXoa = new JButton("Xóa"));
		btnSua.setBounds(15,25,120,30);
		btnReset.setBounds(15,65,120,30);
		btnXoa.setBounds(15,105,120,30);
		jpn2.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		jpn1.setBounds(1100,35,150,120);
		jpn2.setBounds(1100,170,150,160);
		jpn1.setBackground(Color.WHITE);
		jpn2.setBackground(Color.WHITE);
		contentPanel.add(jpn2);
		contentPanel.add(jpn1);
		contentPanel.setBounds(0,0,1337,690);
		add(contentPanel);
		
		btnSua.addActionListener(this);
		cmBox2.addActionListener(this);
		cmBox.addActionListener(this);
		btnReset.addActionListener(this);
		btnXoa.addActionListener(this);
		showData();
		
	}
	public static void main(String[] args) {
		new Chitietcongtrinh().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(cmBox2)) {
			String input = (String)cmBox2.getSelectedItem();
			if(input.length()>0) {
				if(input.equalsIgnoreCase("all")) {
					showData();
				}
				else {
					showDataidld(input);
//					if(!showDataidld(input)) {
//						JOptionPane.showMessageDialog(this,"Không có nhân công này!");
//					}
						
				}
				
			}
			else {
				JOptionPane.showMessageDialog(this,"Nhập mã để tìm");
			}
			
		}
		else if(o.equals(btnSua)) {
			int row = table.getSelectedRow();
			
			if(row!=-1) {
				String mact = table.getValueAt(row,0).toString();
				String mald = table.getValueAt(row,1).toString();
				ChitietPhancong ctPc = new ChitietPhancong(mact,mald);
				ctPc.setVisible(true);
			}
			else {
			JOptionPane.showMessageDialog(this,"Chọn dòng cần chỉnh sửa !");
				
			}
			
		}
		else if(o.equals(btnReset)) {
			showData();
			newCombobox1();
			newCombobox2();
		}
		else if(o.equals(btnXoa)) {
			String input = JOptionPane.showInputDialog("Nhập mã công trình cần xóa:");
			int loinhac = JOptionPane.showConfirmDialog(this,"Xóa toàn bộ chi tiết lao động của công trình","Cảnh báo",JOptionPane.YES_NO_OPTION);
			if(loinhac==JOptionPane.YES_OPTION) {
				if(qlctct.deleteAll(input)) {
					JOptionPane.showMessageDialog(this,"Xóa thành công!");
					showData();
				}
			}
		}
		else if(o.equals(cmBox)) {
			String ma = cmBox.getSelectedItem().toString();
			if(ma.equalsIgnoreCase("all")){
				showData();
			}
			else {
				showDataidct(ma);
			}
			
			
		}
		
	}
	private void showData() {
		List<Chitietct> list = crudChitietCT.findAll();
		model.setRowCount(0);
		list.forEach((Chitietct)->{model.addRow(new Object[] {Chitietct.getMact().getMact(),Chitietct.getMald().getMald(),Chitietct.getNgaybatdau(),Chitietct.getNgayketthuc()});
		});
		
	}
	private void showDataidld(String ma) {
		List<Chitietct> list = crudChitietCT.FindByIDLD(ma);
		model.setRowCount(0);
//		list.forEach((Chitietct)->{model.addRow(new Object[] {Chitietct.getMact().getMact(),Chitietct.getMald().getMald(),Chitietct.getNgaybatdau(),Chitietct.getNgayketthuc()});
//		
//		});
		for(Chitietct Chitietct : list) {
			model.addRow(new Object[] {Chitietct.getMact().getMact(),Chitietct.getMald().getMald(),Chitietct.getNgaybatdau(),Chitietct.getNgayketthuc()});
			
		}
		
		
		
	}
	private void showDataidct(String ma) {
		List<Chitietct> list = crudChitietCT.FindByIDCT(ma);
		model.setRowCount(0);
		list.forEach((Chitietct)->{model.addRow(new Object[] {Chitietct.getMact().getMact(),Chitietct.getMald().getMald(),Chitietct.getNgaybatdau(),Chitietct.getNgayketthuc()});
		});
		
	}
	private void newCombobox1() {
		cmbModel1 = new DefaultComboBoxModel<>(XuatTTCombobox1());
		cmBox.setModel(cmbModel1);
	}
	private String[] XuatTTCombobox1() {
		
		qlctct = new crudChitietCT();
		List<Chitietct> listPB = qlctct.findMaLD();
		String[] result = new String[listPB.size()];
		for(int i=0;i<listPB.size();i++) {
			result[i]=listPB.get(i).getMald().getMald();
		}
		return result;
		
	}
	private void newCombobox2() {
		cmbModel2 = new DefaultComboBoxModel<>(XuatTTCombobox2());
		cmBox.setModel(cmbModel2);
	}
	private String[] XuatTTCombobox2() {
		
		qlct = new crudCongtrinh();
		List<Congtrinh> listPB = qlct.findAll();
		String[] result = new String[listPB.size()];
		for(int i=0;i<listPB.size();i++) {
			result[i]=listPB.get(i).getMact();
		}
		return result;
		
	}
}
