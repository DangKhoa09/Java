package Crud;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Connection.Connect;
import Entity.Congtrinh;
import Entity.Laodong;
import Entity.Chitietct;


public class crudChitietCT {
	private static ArrayList<Chitietct> qlct;

	public crudChitietCT() {
		qlct = new ArrayList<Chitietct>();
	}
	public static List<Chitietct> findAll() {
		List<Chitietct> listNV = new ArrayList<Chitietct>();
		Statement stmt = null;
		try {
				Connection con =Connect.getInstance().getConnection();
				String sql = "SELECT * FROM CHITIETCT";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String mact = rs.getString("MACT");
					Congtrinh ct = new Congtrinh(mact);
					String mald = rs.getString("MALD");
					Laodong ld = new Laodong(mald);
					Date startDate = rs.getDate("NGAYBATDAY");
					Date enDate = rs.getDate("NGAYKETTHUC");
					Chitietct ctct = new Chitietct(ct, ld, startDate, enDate);
					listNV.add(ctct);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV ;
	

	}
	public static List<Chitietct> findMaLD() {
		List<Chitietct> listNV = new ArrayList<Chitietct>();
		Statement stmt = null;
		try {
				Connection con =Connect.getInstance().getConnection();
				String sql = "SELECT MaLD FROM CHITIETCT GROUP BY MALD";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String mald = rs.getString("MALD");
					Laodong ld = new Laodong(mald);
					Chitietct ctct = new Chitietct(null, ld, null, null);
					listNV.add(ctct);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV ;
	

	}

	public boolean insert(Chitietct ct) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = ("INSERT INTO [dbo].[CHITIETCT](MACT,MALD,NGAYBATDAY,NGAYKETTHUC) VALUES (?,?,?,?)");
			stmt = con.prepareCall(sql);
			stmt.setString(1, ct.getMact().getMact());
			stmt.setString(2, ct.getMald().getMald());
			stmt.setDate(3, ct.getNgaybatdau());
			stmt.setDate(4,ct.getNgayketthuc());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
		
	}

	public boolean update(Chitietct ct) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = ("UPDATE CHITIETCT SET NGAYBATDAY=?,NGAYKETTHUC=? WHERE MACT =? AND MALD =?");
			stmt = con.prepareCall(sql);
			
			stmt.setDate(1, ct.getNgaybatdau());
			stmt.setDate(2,ct.getNgayketthuc());
			stmt.setString(3, ct.getMact().getMact());
			stmt.setString(4, ct.getMald().getMald());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
		
	}
	public static List<Chitietct> FindByID(String mact) {
		List<Chitietct> dstimNV = new ArrayList<Chitietct>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			if(!(mact.equals("")&& mact.length()>0)){
				sql = "SELECT * FROM CHITIETCT WHERE MACT LIKE '"+mact+"'";
			}
			else {
				sql = "select * from [dbo].[CHITIETCT]";
			}
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String mact1 = rs.getString("MACT");
				Congtrinh ct = new Congtrinh(mact1);
				String mald = rs.getString("MALD");
				Laodong ld = new Laodong(mald);
				Date startDate = rs.getDate("NGAYBATDAY");
				Date enDate = rs.getDate("NGAYKETTHUC");
				Chitietct ctct = new Chitietct(ct, ld, startDate, enDate);
				dstimNV.add(ctct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static List<Chitietct> FindByIDLD(String mact) {
		List<Chitietct> dstimNV = new ArrayList<Chitietct>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			if(!(mact.equals("")&& mact.length()>0)){
				sql = "SELECT * FROM CHITIETCT WHERE MALD = '"+mact+"'";
			}
			else {
				sql = "select * from [dbo].[CHITIETCT]";
			}
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String mact1 = rs.getString("MACT");
				Congtrinh ct = new Congtrinh(mact1);
				String mald = rs.getString("MALD");
				Laodong ld = new Laodong(mald);
				Date startDate = rs.getDate("NGAYBATDAY");
				Date enDate = rs.getDate("NGAYKETTHUC");
				Chitietct ctct = new Chitietct(ct, ld, startDate, enDate);
				dstimNV.add(ctct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static List<Chitietct> FindByIDCT(String mact) {
		List<Chitietct> dstimNV = new ArrayList<Chitietct>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			if(!(mact.equals("")&& mact.length()>0)){
				sql = "SELECT * FROM CHITIETCT WHERE MACT LIKE '"+mact+"'";
			}
			else {
				sql = "select * from [dbo].[CHITIETCT]";
			}
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String mact1 = rs.getString("MACT");
				Congtrinh ct = new Congtrinh(mact1);
				String mald = rs.getString("MALD");
				Laodong ld = new Laodong(mald);
				Date startDate = rs.getDate("NGAYBATDAY");
				Date enDate = rs.getDate("NGAYKETTHUC");
				Chitietct ctct = new Chitietct(ct, ld, startDate, enDate);
				dstimNV.add(ctct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static boolean delete(String mact, String mald) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM CHITIETCT WHERE MACT LIKE '"+mact+"' AND MALD LIKE '"+mald+"'");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public static boolean deleteAll(String mact) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM CHITIETCT WHERE MACT LIKE '"+mact+"'");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public static List<Chitietct> findCTPhancong(String mact1,String mald1) {
		List<Chitietct> listNV = new ArrayList<Chitietct>();
		Statement stmt = null;
		try {
				Connection con =Connect.getInstance().getConnection();
				String sql = "SELECT * FROM CHITIETCT WHERE MACT = '"+mact1+"' AND MALD = '"+mald1+"'";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String mact = rs.getString("MACT");
					Congtrinh ct = new Congtrinh(mact);
					String mald = rs.getString("MALD");
					Laodong ld = new Laodong(mald);
					Date startDate = rs.getDate("NGAYBATDAY");
					Date enDate = rs.getDate("NGAYKETTHUC");
					Chitietct ctct = new Chitietct(ct, ld, startDate, enDate);
					listNV.add(ctct);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV ;
	

	}
	
	
}
