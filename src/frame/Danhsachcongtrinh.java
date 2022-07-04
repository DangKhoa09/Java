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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Crud.crudCongtrinh;
import Entity.Congtrinh;


public class Danhsachcongtrinh extends JPanel implements ActionListener, MouseListener {
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tablemodelCT;
	private JButton btnXemChiTiet, btnTimKiem, btnTroVe,btnQuanly;
	private String tenNhanVien;
	private String tenPhongBan;
	private JMenuItem mnQL;
	public Danhsachcongtrinh() {
		setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		String[] colNameCT = "Mã công trình;Tên công trình;Ngày cấp phép;Ngày khởi công;Ngày dự kiến hoàn thành;Địa điểm;Trạng thái".split(";");
		tablemodelCT = new DefaultTableModel(colNameCT, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tablemodelCT),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20,35,1200,500);
		contentPane.add(scrollPane);
		

		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setFont(new Font("Arial", Font.PLAIN, 14));
		btnXemChiTiet.setBounds(300, 570, 120, 30);
		contentPane.add(btnXemChiTiet);
		

		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setFont(new Font("Arial", Font.PLAIN, 14));
		btnTimKiem.setBounds(480, 570, 120, 30);
		contentPane.add(btnTimKiem);


		btnQuanly = new JButton("Quản lý");
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
		new Danhsachcongtrinh().setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			String input =JOptionPane.showInputDialog("Nháº­p mÃ£ cÃ´ng trÃ¬nh");
			if(input.length()>0) {
				if(input.equalsIgnoreCase("all")) {
					showData();
				}
				else {
					showbyid(input);
				}
			}
		}
		
		
		

	}
	//MÃ£ CÃ´ng TrÃ¬nh;TÃªn CÃ´ng TrÃ¬nh;Trang thai;Ä�á»‹a Ä�iá»ƒm;NgÃ y Cáº¥p PhÃ©p;NgÃ y Dá»± Kiáº¿n;NgÃ y Khá»Ÿi CÃ´ng
	private void showData() {
		List<Congtrinh> list = crudCongtrinh.findAll();
		tablemodelCT.setRowCount(0);
		list.forEach((Congtrinh)->{tablemodelCT.addRow(new Object[] {Congtrinh.getMact(),Congtrinh.getTenct(),Congtrinh.getNgaycap(),Congtrinh.getNgaykhoicong(),
				Congtrinh.getNgaydukien(),Congtrinh.getDiadiem(),Congtrinh.isTrangthai()?"HoÃ n thÃ nh":"ChÆ°a hoÃ n thÃ nh"});
		});
		
	}
	private void showbyid(String matim) {
		if(matim.length()>0) {
			tablemodelCT.setRowCount(0);
			List<Congtrinh> list = crudCongtrinh.FindByID(matim);
			list.forEach((Congtrinh)->{tablemodelCT.addRow(new Object[] {Congtrinh.getMact(),Congtrinh.getTenct(),Congtrinh.getNgaycap(),Congtrinh.getNgaykhoicong(),
					Congtrinh.getNgaydukien(),Congtrinh.getDiadiem(),Congtrinh.isTrangthai()?"HoÃ n thÃ nh":"ChÆ°a hoÃ n thÃ nh"});
			});
		}
		else {
			JOptionPane.showMessageDialog(this,"Nháº­p ten cáº§n tÃ¬m");
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
