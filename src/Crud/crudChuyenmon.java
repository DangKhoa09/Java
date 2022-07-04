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

public class crudChuyenmon {
	private static ArrayList<Chuyenmon> qlcm;

	public crudChuyenmon() {
		qlcm = new ArrayList<Chuyenmon>();
	}
	public static List<Chuyenmon> findAll() {
		List<Chuyenmon> listCM = new ArrayList<Chuyenmon>();
		Statement stmt = null;
		try {
				Connection con = Connect.getInstance().getConnection();
				String sql = "SELECT * FROM CHUYENMON";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ma = rs.getString("MACM");
					String ten = rs.getString("TENCM");
					Chuyenmon cm = new Chuyenmon(ma, ten);
					listCM.add(cm);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCM ;
	

	}

	public boolean insert(Chuyenmon cm) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = ("INSERT INTO [dbo].[CHUYENMON](MACM,TENCM) VALUES (?,?)");
			stmt = con.prepareCall(sql);
			stmt.setString(1, cm.getMacm());
			stmt.setString(2, cm.getTencm());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;	
	}

	public boolean update(Chuyenmon cm) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("UPDATE [dbo].[CHUYENMON] SET TENCM=? WHERE MACM = ?");
			stmt.setString(1,cm.getTencm());
			stmt.setString(2, cm.getMacm());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	
	public static boolean delete(String macm) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM CHUYENMON WHERE MACM LIKE '"+macm+"'");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	public String tuDongLayMa() {
		String maNV = "";
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "DECLARE @MACM NCHAR(5),@SOCM INT,@TONGCM INT\r\n"
					+ "SET @TONGCM = (SELECT COUNT(*) FROM CHUYENMON)\r\n"
					+ "IF @TONGCM =0\r\n"
					+ "	BEGIN\r\n"
					+ "		SET @MACM = 'CM01'\r\n"
					+ "	END\r\n"
					+ "ELSE\r\n"
					+ "	BEGIN\r\n"
					+ "		SET @MACM = RIGHT((SELECT MAX(MACM) FROM CHUYENMON),2)\r\n"
					+ "		SET @SOCM = CAST(@MACM AS INT)+1\r\n"
					+ "		IF @SOCM<10\r\n"
					+ "			SET @MACM = 'CM0'+CAST(@SOCM AS nvarchar(2))\r\n"
					+ "		ELSE IF @SOCM>=10 AND @SOCM <100\r\n"
					+ "			SET @MACM = 'CM'+CAST(@SOCM AS nvarchar(2))\r\n"
					+ "	END\r\n"
					+ "SELECT @MACM";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				maNV = ma;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maNV;
	}
}
