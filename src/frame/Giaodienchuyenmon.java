package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Crud.crudChuyenmon;
import Crud.crudCongtrinh;
import Entity.Chuyenmon;
import validata.Validate;
import Crud.crudLaodong;


public class Giaodienchuyenmon extends JPanel implements ActionListener, MouseListener {
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel panel;
	private JLabel lblthongTinPB;
	private JLabel lblmaPB;
	private JLabel lbltenPB;
	private JPanel panel_1;
	private JButton btnthem;
	private JButton btncapNhat;
	private JButton btnxoaRong;
	private JButton btntroVe,btnXoa;
	private JTextField txtmaPB;
	private JTextField txttenPB;
	private JMenuItem mnQL;
	private crudChuyenmon qlpb;
	private Validate regex;
	private crudLaodong qlld;
	private static List<Giaodienchuyenmon> listCM;
	public Giaodienchuyenmon() {
		// TODO Auto-generated constructor stub
		taoGiaoDien();
	}
	public void taoGiaoDien() {
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		String[] header = "Mã chuyên môn;Tên chuyên môn;Số lượng".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.addMouseListener(this);
		scrollPane.setBounds(20, 35,700, 600);
		contentPane.add(scrollPane);
		JTableHeader header1 = table.getTableHeader();
		header1.setForeground(Color.WHITE);
		header1.setFont(new Font("Arial", Font.PLAIN, 14));
		header1.setBackground(new Color(0,51,51));
		table.setBackground(new Color(255,255,204));
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(800,100, 500, 317);
		contentPane.add(panel);
		panel.setLayout(null);

		lblthongTinPB = new JLabel("");
		lblthongTinPB.setForeground(Color.BLUE);
		lblthongTinPB.setFont(new Font("Arial", Font.BOLD, 19));
		lblthongTinPB.setBounds(61, 0, 232, 31);
		panel.add(lblthongTinPB);

		lblmaPB = new JLabel("Mã chuyên môn:");
		lblmaPB.setFont(new Font("Arial", Font.PLAIN, 14));
		lblmaPB.setBounds(10, 51, 121, 31);
		panel.add(lblmaPB);

		lbltenPB = new JLabel("Tên chuyên môn:");
		lbltenPB.setFont(new Font("Arial", Font.PLAIN, 14));
		lbltenPB.setBounds(10, 111, 121, 31);
		panel.add(lbltenPB);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(null, "Tác vụ", TitledBorder.LEADING, TitledBorder.TOP,

				null, Color.BLUE));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 175,320, 132);
		panel.add(panel_1);

		btnthem = new JButton("Thêm mới");
		btnthem.setFont(new Font("Arial", Font.PLAIN, 14));
		btnthem.setBounds(24, 25, 99, 31);
		panel_1.add(btnthem);

		btncapNhat = new JButton("Cập nhật");
		btncapNhat.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btncapNhat.setBounds(24, 89, 99, 31);
		panel_1.add(btncapNhat);

		btnxoaRong = new JButton("Refresh");
		btnxoaRong.setFont(new Font("Arial", Font.PLAIN, 14));

		btnxoaRong.setBounds(179, 25, 99, 31);
		panel_1.add(btnxoaRong);

		btnXoa = new JButton("Xóa");
		btnXoa.setFont(new Font("Arial", Font.PLAIN, 14));
		
		btnXoa.setBounds(179, 89, 99, 31);
		panel_1.add(btnXoa);

		

		txtmaPB = new JTextField();
		txtmaPB.setColumns(10);
		txtmaPB.setBounds(130, 51, 200, 27);
		panel.add(txtmaPB);
		showData();
		contentPane.setBounds(0,0,1337,690);
		add(contentPane);

		txttenPB = new JTextField();
		txttenPB.setColumns(10);
		txttenPB.setBounds(130, 111,200, 27);
		panel.add(txttenPB);
		btnthem.addActionListener(this);
		btncapNhat.addActionListener(this);
		btnxoaRong.addActionListener(this);
		btnXoa.addActionListener(this);
		txtmaPB.setEditable(false);
		table.addMouseListener(this);
		
	}
	public static void main(String[] args) {
		new Giaodienchuyenmon().setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		regex = new Validate();
		if (o.equals(btntroVe)) {
			setVisible(false);
		}
		if(o.equals(btnthem)) {
			if(regex.rChuyenMon(txttenPB)) {
				qlpb = new crudChuyenmon();
				String ma = qlpb.tuDongLayMa();
				String ten = txttenPB.getText();
				Chuyenmon cm = new Chuyenmon(ma, ten);
				if(qlpb.insert(cm)) {
					JOptionPane.showMessageDialog(this,"Thêm thành công");
					showData();
					
				}
				
						
			}
			
		}
		else if(o.equals(btncapNhat)) {
			if(regex.rChuyenMon(txttenPB)) {
				String ma = txtmaPB.getText();
				String ho = txttenPB.getText();
				Chuyenmon cm= new Chuyenmon(ma, ho);
				qlpb = new crudChuyenmon();
				if(qlpb.update(cm)) {
					JOptionPane.showMessageDialog(this,"Cap nhat thanh cong");
					showData();
				}			
			}
		}
		else if(o.equals(btnxoaRong)) {
			showData();
			
		}
		else if(o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			String ma = table.getValueAt(row,0).toString();
			int soluong = (int)table.getValueAt(row,2);
			if(row>=0) {
			int loinhac = JOptionPane.showConfirmDialog(this,"Chắc chắn xóa?","Lưu ý",JOptionPane.YES_NO_OPTION);
				if(loinhac==JOptionPane.YES_OPTION) {
					if(soluong==0) {
						crudChuyenmon.delete(ma);
						JOptionPane.showMessageDialog(this,"Xóa thành công!");
						showData();
					}
					else {
						JOptionPane.showMessageDialog(this,"Có người lao động, không thể xóa!");
					}
				}
			}
		}
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row=table.getSelectedRow();
		txtmaPB.setText(table.getValueAt(row, 0).toString());
		txttenPB.setText(table.getValueAt(row, 1).toString());
		
	}
	private void showData() {
		qlld = new crudLaodong();
		List<Chuyenmon> listNV = crudChuyenmon.findAll();
		tableModel.setRowCount(0);
		listNV.forEach((Chuyenmon)->{tableModel.addRow(new Object[] {Chuyenmon.getMacm(),Chuyenmon.getTencm(),qlld.soluongchuyenmon(Chuyenmon.getMacm())});
		});
		
	}
	private boolean ValidData() {
		String ten=txttenPB.getText().trim();
		if(!(ten.length()>0 && ten.matches("([a-zA-Z\\s*])+"))){
			
			JOptionPane.showMessageDialog(this, "Error: Ten khong chua ky tu dac biet va so ");
			txttenPB.requestFocus();
			return false;
		}                                      
		return true;
	}

}
