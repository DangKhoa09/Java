package frame;

import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Crud.crudChuyenmon;
import Crud.dangnhap;
import Entity.Chuyenmon;
import Entity.taikhoan;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Giaodien_Home extends JFrame implements ActionListener {
	private JButton btnQLLD,btnQLCT,btnCTCT,btnPC,btnThoat,btnDSLD,btnDSCT,btnDangxuat;
	private JLabel lblTua,lbltitJLabel,lbladd,timeSystem,calendarBD,lblAdmin,lblTen,lblsdt;
	private JPanel jpn1,jpn2,jpn3;
	private JTabbedPane tabbedPane;
	private String ma;
	private dangnhap quanly;
	public Giaodien_Home(String ma) {
		this.ma=ma;
		setSize(1540,830);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		jpn1 = new JPanel();
		jpn1.setLayout(null);
		jpn1.setBackground(new Color(51,51,51));
		jpn1.add(lbladd = new JLabel(""));
		lbladd.setOpaque(true);
		lbladd.setBackground(Color.black);
		lbladd.setBounds(0,0,300,30);
		jpn1.add(lblAdmin = new JLabel(""));
		jpn1.add(lblTen = new JLabel("Admin"));
		jpn1.add(lblsdt = new JLabel("SĐT: "));
		jpn1.add(btnThoat = new JButton("Thoát"));
		jpn1.add(btnDangxuat = new JButton("Đăng xuất"));
		ImageIcon icon = new ImageIcon(Giaodien_Home.class.getResource("/images/acc.png"));
		lblAdmin.setIcon(icon);
		lblAdmin.setBounds(60,10,150,150);
		lblTen.setBounds(18,160,180,25);
		lblTen.setForeground(Color.WHITE);
		lblsdt.setForeground(Color.WHITE);
		lblsdt.setBounds(18,190,180,25);
		btnDangxuat.setBounds(25,240,140,30);
		btnThoat.setBounds(25,285,140,30);
		
		
		
		
		
		
		jpn2 = new JPanel();
		jpn2.setLayout(null);
		jpn2.setBounds(300,0,300,200);
		jpn2.setPreferredSize(new Dimension(0,102));
		jpn2.setBackground(new Color(51,51,51));
		jpn2.add(lblTua = new JLabel(""));
		jpn2.add(lbltitJLabel = new JLabel("HỆ THỐNG QUẢN LÝ LAO ĐỘNG"));
		lbltitJLabel.setBounds(600,10,700,50);
		lbltitJLabel.setForeground(Color.white);
		lbltitJLabel.setFont(new Font("Arials",Font.BOLD,35));
		lblTua.add(timeSystem = new JLabel(""));
		lblTua.add(calendarBD = new JLabel(""));
		timeSystem.setBounds(15,20,100,25);
		calendarBD.setBounds(15,50,200,25);
		timeSystem.setForeground(Color.WHITE);
		calendarBD.setForeground(Color.WHITE);
		timeSystem.setFont(new Font("arials",Font.PLAIN,18));
		calendarBD.setFont(new Font("arials",Font.PLAIN,12));

		
		lblTua.setBackground(new Color(51,51,51));
		lblTua.setOpaque(true);
		lblTua.setBounds(0,0,200,110);
		
		jpn3 = new JPanel();
		jpn3.setLayout(null);
		jpn3.setBackground(new Color(51,51,51));
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		jpn3.add(tabbedPane);
		tabbedPane.setBackground(new Color(255,255,204));
		tabbedPane.setBounds(0,0,1337,690);
		tabbedPane.addTab("Home",new ImageIcon(Giaodien_Home.class.getResource("/images/Home.png")),home(),null);
		tabbedPane.addTab("Quản lý công trình",new ImageIcon(Giaodien_Home.class.getResource("/images/cong_trinh.png")),new Quanlycongtrinh(),null);
		tabbedPane.addTab("Quản lý lao động",new ImageIcon(Giaodien_Home.class.getResource("/images/Workers.png")),new Giaodienlaodong(),null);
		tabbedPane.addTab("Danh sách lao động",new ImageIcon(Giaodien_Home.class.getResource("/images/listnv1.png")),new Danhsachlaodong(),null);
		tabbedPane.addTab("Phân công",new ImageIcon(Giaodien_Home.class.getResource("/images/phan_cong.png")),new Phancong(),null);
		tabbedPane.addTab("Chuyên môn",new ImageIcon(Giaodien_Home.class.getResource("/images/job_1.png")),new Giaodienchuyenmon(),null);
		tabbedPane.addTab("Chi tiết công trình",new ImageIcon(Giaodien_Home.class.getResource("/images/chi_tiet.png")),new Chitietcongtrinh(),null);
		
		//tabbedPane.addTab("Danh sách công trình",new ImageIcon(Giaodien_Home.class.getResource("/images/listct.png")),new Danhsachcongtrinh(),null);
		add(jpn3,BorderLayout.CENTER);
		add(jpn2,BorderLayout.PAGE_START);
		
		
		
		
		jpn1.setPreferredSize(new Dimension(200,820));
		add(jpn1,BorderLayout.WEST);
		clock();
		showquanly(ma);
		btnDangxuat.addActionListener(this);
		btnThoat.addActionListener(this);
		
		
		
		
	}
//	public static void main(String[] args) {
//		new Giaodien_Home().setVisible(true);
//	}
	private JPanel home() {
		JPanel jpn = new JPanel();
		ImageIcon icon1 = new ImageIcon(new ImageIcon("src/images/imghome1.jpg").getImage().getScaledInstance(1350, 800, Image.SCALE_DEFAULT));
		JLabel lbLabel  = new JLabel();
		lbLabel.setIcon(icon1);
		jpn.add(lbLabel);
		
		return jpn;
	}
	public void clock() {
	    Thread clock = new Thread() {
	        public void run() {
	            try {
	                while (true) {
	                    Calendar cal = new GregorianCalendar();
	                    int second = cal.get(Calendar.SECOND);
	                    int minute = cal.get(Calendar.MINUTE);
	                    int hour = cal.get(Calendar.HOUR_OF_DAY);
	                    String thu;
	                    int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	                    if (dayOfWeek == 1) {
	                        thu = "Chủ nhật";
	                    } else {
	                        thu = "Thứ " + Integer.toString(dayOfWeek);
	                    }
	                    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
	                    int month = cal.get(Calendar.MONTH);
	                    int year = cal.get(Calendar.YEAR);

	                    timeSystem.setText(hour + ":" + minute + ":" + second);
//	                    timeSystemBD.setText(hour + ":" + minute + ":" + second);
//	                    timeSystemTK.setText(hour + ":" + minute + ":" + second);
	                    calendarBD.setText(thu + " Ngày " + dayOfMonth + " tháng " + (month + 1) + " năm " + year);
//	                    calendarTK.setText(thu + dayOfWeek + " ngÃ y " + dayOfMonth + " thÃ¡ng " + (month + 1) + " nÄƒm " + year);
	                    sleep(1000);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    };
	    clock.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThoat)) {
			int loinhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn thoát ?","Lưu ý",JOptionPane.YES_NO_OPTION);
			if(loinhac==JOptionPane.YES_OPTION) {
				setVisible(false);
			}
		}
		else if(o.equals(btnDangxuat)) {
			int loinhac = JOptionPane.showConfirmDialog(this,"Bạn có muốn đăng xuất ?","Lưu ý",JOptionPane.YES_NO_OPTION);
			if(loinhac==JOptionPane.YES_OPTION) {
				setVisible(false);
				new frameDangnhap().setVisible(true);
				
			}
		}
		
		
	}
	private void showquanly(String matk) {
		List<taikhoan> listNV = quanly.findAll(matk);
		for(taikhoan tk : listNV) {
			lblTen.setText("Admin: "+tk.getTenquanly());
			lblsdt.setText("Phone: "+tk.getSdt());
		}
	}
	
	
}
