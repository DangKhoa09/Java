package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Crud.dangnhap;


public class frameDangnhap extends JFrame implements ActionListener {
	private JPanel jpn;
	private JLabel lblTua,lbltk,lblmk,lblava;
	private JTextField txtTK;
	private JPasswordField passMK;
	private JButton btnDN,btnThoat;
	public frameDangnhap() {
		// TODO Auto-generated constructor stub
		setSize(600,350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		jpn = new JPanel();
		jpn.setLayout(null);
		jpn.add(lblTua = new JLabel("ĐĂNG NHẬP"));
		jpn.add(lblava = new JLabel());
		lblTua.setFont(new Font("Times New Roman",Font.BOLD,30));
		lblTua.setForeground(Color.BLUE);
		jpn.add(lbltk = new JLabel("Tài khoản"));
		jpn.add(lblmk = new JLabel("Mật khẩu"));
		jpn.add(txtTK = new JTextField());
		jpn.add(passMK = new JPasswordField());
		jpn.add(btnDN = new JButton("Đăng nhập"));
		jpn.add(btnThoat = new JButton("Thoát"));
		jpn.setBackground(new Color(0,51,51));
		//lblTua.setBounds(190,15,200,35);
		lblava.setBounds(10,5,150,150);
		lbltk.setFont(new Font("arials",Font.PLAIN,20));
		lbltk.setForeground(Color.WHITE);
		lblmk.setForeground(Color.WHITE);
		lblmk.setFont(new Font("arials",Font.PLAIN,20));
		ImageIcon icon = new ImageIcon(frameDangnhap.class.getResource("/images/man.png"));
		lblava.setIcon(icon);
		lbltk.setBounds(175,30,100,30);
		lblmk.setBounds(175,100,100,30);
		txtTK.setBounds(290,30,200,30);
		passMK.setBounds(290,100,200,30);
		btnDN.setBounds(150,220,120,30);
		btnThoat.setBounds(300,220,120,30);
		
		
		add(jpn);
		btnDN.addActionListener(this);
		btnThoat.addActionListener(this);
	}
//	public static void main(String[] args) {
//		new frameDangnhap().setVisible(true);
//	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		String tk = txtTK.getText();
		String matKhau = new String(passMK.getPassword());
		dangnhap dn = new dangnhap();
		if (o.equals(btnDN)) {
				if(dn.ktQuanLy(tk, matKhau)) {
					Giaodien_Home home = new Giaodien_Home(tk);
					home.setVisible(true);
					setVisible(false);
				
			}
				else {
					JOptionPane.showMessageDialog(this,"Tài khoản hoặc mật khẩu không đúng");
					txtTK.requestFocus();
					txtTK.selectAll();
				}
			
		}
		if(o.equals(btnThoat)) {
			System.exit(0);
		}
	}
}
