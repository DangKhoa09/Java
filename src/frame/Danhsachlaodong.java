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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import Crud.crudLaodong;
import Entity.Laodong;


public class Danhsachlaodong extends JPanel implements ActionListener, MouseListener {
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tablemodelCT;
	private JButton btnXemChiTiet, btnTimKiem, btnTroVe,btnQuanly;
	private String tenNhanVien;
	private String tenPhongBan;
	private JMenuItem mnQL;
	public Danhsachlaodong() {
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		contentPane.setLayout(null);
		String[] colNameCT = "Mã;Họ Tên;Giới tính;Chuyên môn;CMND;Số điện thoại;Địa chỉ;Trạng thái".split(";");
		tablemodelCT = new DefaultTableModel(colNameCT, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tablemodelCT),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20,25,1285,500);
		contentPane.add(scrollPane);
		JTableHeader header = table.getTableHeader();
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.PLAIN, 14));
		header.setBackground(new Color(0,51,51));
		table.setBackground(new Color(255,255,204));
		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setFont(new Font("Arial", Font.PLAIN, 14));
		btnXemChiTiet.setBounds(300, 570, 120, 30);
		contentPane.add(btnXemChiTiet);
		

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Arial", Font.PLAIN, 14));
		btnTimKiem.setBounds(480, 570, 120, 30);
		contentPane.add(btnTimKiem);


		btnQuanly = new JButton("Refresh");
		btnQuanly.setFont(new Font("Arial", Font.PLAIN, 14));
		btnQuanly.setBounds(660, 570, 120, 30);
		contentPane.add(btnQuanly);
		contentPane.setBounds(0,0,1337,690);
		add(contentPane);
		
		showData();
		btnTimKiem.addActionListener(this);
		btnXemChiTiet.addActionListener(this);
		btnQuanly.addActionListener(this);
		table.addMouseListener(this);
	}
	public static void main(String[] args) {
		new Danhsachlaodong().setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnTroVe)) {
			setVisible(false);
		}
		else if(o.equals(btnQuanly)) {
			showData();
		}
		else if(o.equals(btnTimKiem)) {
			String input = JOptionPane.showInputDialog(this,"Nhập mã");
			if(input.length()>0) {
				
				if(input.equalsIgnoreCase("all")) {
					showData();
				}
				else {
					showbyid(input);
				}
			}
			else if(input.trim().equals("") || input==null) {
				JOptionPane.showMessageDialog(this,"Nhập mã lao động");
			}
		
		}
		else if(o.equals(btnXemChiTiet)) {
			int row = table.getSelectedRow();
			if (row!=-1) {
				
				String ma = table.getValueAt(row,0).toString();
				ChitietLaodong ctLd = new  ChitietLaodong(ma);
				ctLd.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this,"Chọn người lao động cần xem");
			}
		}
		
	}

	private void showData() {
		List<Laodong> listNV = crudLaodong.findAll();
		tablemodelCT.setRowCount(0);
		listNV.forEach((Laodong)->{tablemodelCT.addRow(new Object[] {Laodong.getMald(),Laodong.getTenld(),Laodong.isPhai()?"Nữ":"Nam",Laodong.getMacm().getMacm(),Laodong.getCmnd(),Laodong.getSdt(),Laodong.getDiachi(),Laodong.isTrangthai()?"Hoạt động":"Tạm ngưng"});
		});
		
	}
	private void showbyid(String matim) {
		if(matim.length()>0) {
			tablemodelCT.setRowCount(0);
			List<Laodong> listNV = crudLaodong.FindByID(matim);
			listNV.forEach((Laodong)->{tablemodelCT.addRow(new Object[] {Laodong.getMald(),Laodong.getTenld(),Laodong.isPhai()?"Nữ":"Nam",Laodong.getMacm().getMacm(),Laodong.getCmnd(),Laodong.getSdt(),Laodong.getDiachi(),Laodong.isTrangthai()?"Hoạt động":"Tạm ngưng"});
			});
		}
		else {
			JOptionPane.showMessageDialog(this,"Nhập");
			showData();
		}
		
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
