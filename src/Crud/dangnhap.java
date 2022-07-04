package Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.Connect;
import Entity.Chuyenmon;
import Entity.taikhoan;

public class dangnhap {
	public boolean ktQuanLy(String maQuanLy, String matKhau) {
		Connection con= Connect.getInstance().getConnection();
		try {
			PreparedStatement p=con.prepareStatement("select MaQL from QuanLy where taikhoan=? and matkhau=?");
			p.setString(1, maQuanLy);
			p.setString(2, matKhau);
			ResultSet r=p.executeQuery();
			while(r.next()) {
				if(!r.getString("MaQL").equalsIgnoreCase(""))
					return true;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	public static List<taikhoan> findAll(String ma1) {
		List<taikhoan> listCM = new ArrayList<taikhoan>();
		Statement stmt = null;
		try {
				Connection con = Connect.getInstance().getConnection();
				String sql = "SELECT * FROM QUANLY where taikhoan = '"+ma1+"'";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ma = rs.getString("MAQL");
					String ten = rs.getString("TENQL");
					String sdt = rs.getString("SDT");
					String user = rs.getString("taikhoan");
					String pass = rs.getString("matkhau");
					taikhoan tk = new taikhoan(ma, ten, sdt, user, pass);
					listCM.add(tk);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCM ;
	

	}
}
