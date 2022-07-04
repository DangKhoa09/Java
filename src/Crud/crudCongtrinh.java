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


public class crudCongtrinh {
	private static ArrayList<Congtrinh> qlct;

	public crudCongtrinh() {
		qlct = new ArrayList<Congtrinh>();
	}
	public static List<Congtrinh> findAll() {
		List<Congtrinh> listNV = new ArrayList<Congtrinh>();
		Statement stmt = null;
		try {
				Connection con =Connect.getInstance().getConnection();
				String sql = "SELECT * FROM CONGTRINH";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ma = rs.getString("MACT");
					String ten = rs.getString("TENCT");
					Boolean tt = rs.getBoolean("TRANGTHAI");
					Date ngcap = rs.getDate("NGAYCAP");
					Date ngcong = rs.getDate("NGAYKHOICONG");
					Date ngdukien = rs.getDate("NGAYDUKIEN");
					String diadiem = rs.getString("DIADIEM");	
					int soluong = rs.getInt("SOLUONGLD");
					Congtrinh nv = new Congtrinh(ma, ten, tt, soluong, ngcap, ngcong, ngdukien, diadiem);
					listNV.add(nv);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV ;
	

	}

	public boolean insert(Congtrinh ct) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = ("INSERT INTO [dbo].[CONGTRINH](MACT,TENCT,TRANGTHAI,NGAYCAP,NGAYKHOICONG,NGAYDUKIEN,DIADIEM,SOLUONGLD) VALUES (?,?,?,?,?,?,?,?)");
			stmt = con.prepareCall(sql);
			stmt.setString(1, ct.getMact());
			stmt.setString(2, ct.getTenct());
			stmt.setBoolean(3, ct.isTrangthai());
			stmt.setDate(4, ct.getNgaycap());
			stmt.setDate(5, ct.getNgaykhoicong());
			stmt.setDate(6, ct.getNgaydukien());
			stmt.setString(7,ct.getDiadiem());
			stmt.setInt(8,ct.getSoluong());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
		
	}

	public boolean update(Congtrinh ct) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = ("UPDATE CONGTRINH SET TENCT=?,TRANGTHAI=?,NGAYCAP=?,NGAYKHOICONG=?,NGAYDUKIEN=?,DIADIEM=?,SOLUONGLD=? WHERE MACT =?");
			stmt = con.prepareCall(sql);
			
			stmt.setString(1, ct.getTenct());
			stmt.setBoolean(2, ct.isTrangthai());
			stmt.setDate(3, ct.getNgaycap());
			stmt.setDate(4, ct.getNgaykhoicong());
			stmt.setDate(5, ct.getNgaydukien());
			stmt.setString(6,ct.getDiadiem());
			stmt.setInt(7,ct.getSoluong());
			stmt.setString(8, ct.getMact());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
		
	}

	public static List<Congtrinh> FindByID(String mact) {
		List<Congtrinh> dstimNV = new ArrayList<Congtrinh>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			if(!(mact.equals("")&& mact.length()>0)){
				sql = "SELECT * FROM CONGTRINH WHERE MaCT = '"+mact+"'";
			}
			else {
				sql = "select * from [dbo].[CONGTRINH]";
			}
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString("MACT");
				String ten = rs.getString("TENCT");
				Boolean tt = rs.getBoolean("TRANGTHAI");
				Date ngcap = rs.getDate("NGAYCAP");
				Date ngcong = rs.getDate("NGAYKHOICONG");
				Date ngdukien = rs.getDate("NGAYDUKIEN");
				String diadiem = rs.getString("DIADIEM");	
				int soluong = rs.getInt("SOLUONGLD");
				Congtrinh ct = new Congtrinh(ma, ten, tt, soluong, ngcap, ngcong, ngdukien, diadiem);
				dstimNV.add(ct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static boolean delete(String mact) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM CONGTRINH WHERE MACT LIKE '"+mact+"'");
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}
	

	public String tuDongLayMa() {
		String maCT = "";
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "DECLARE @MACT NCHAR(5),@SOCT INT,@TONGCT INT\r\n"
					+ "SET @TONGCT = (SELECT COUNT(*) FROM CONGTRINH)\r\n"
					+ "IF @TONGCT =0\r\n"
					+ "	BEGIN\r\n"
					+ "		SET @MACT = 'CT001'\r\n"
					+ "	END\r\n"
					+ "ELSE\r\n"
					+ "	BEGIN\r\n"
					+ "		SET @MACT = RIGHT((SELECT MAX(MACT) FROM CONGTRINH),3)\r\n"
					+ "		SET @SOCT = CAST(@MACT AS INT)+1\r\n"
					+ "		IF @SOCT<10\r\n"
					+ "			SET @MACT = 'CT00'+CAST(@SOCT AS nvarchar(3))\r\n"
					+ "		ELSE IF @SOCT>=10 AND @SOCT <100\r\n"
					+ "			SET @MACT = 'CT0'+CAST(@SOCT AS nvarchar(3))\r\n"
					+ "		ELSE IF @SOCT>=100\r\n"
					+ "			SET  @MACT = 'CT'+CAST(@SOCT AS nvarchar(3))\r\n"
					+ "	END\r\n"
					+ "SELECT @MACT";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				maCT = ma;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maCT;
	}
	public String TenCT(String ma) {
		String tenCT = "";
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "SELECT TENCT FROM CONGTRINH WHERE MACT = '"+ma+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ten = rs.getString(1);
				tenCT = ten;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tenCT;
	}
	public int soluong(String ma) {
		int soluong1 = 0;
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "select count(*) from chitietct where mact ='"+ma+"' group by mact";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int soluong = rs.getInt(1);
				soluong1 = soluong;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soluong1;
	}
	public int soluongcan(String ma) {
		int soluong1 = 0;
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "select SOLUONGLD from CONGTRINH where mact ='"+ma+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int soluong = rs.getInt(1);
				soluong1 = soluong;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soluong1;
	}
	public boolean trangthai(String ma) {
		Boolean tt = false;
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "select trangthai from congtrinh where mact = '"+ma+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Boolean tt1 = rs.getBoolean(1);
				tt = tt1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tt;
	}
	public Date ngaybatdau(String ma) {
		Date ngayDate=null;
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "select NGAYKHOICONG FROM CONGTRINH WHERE MACT = '"+ma+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Date ngay = rs.getDate(1);
				ngayDate = ngay;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ngayDate;
	}
	public Date ngaykethuc(String ma) {
		Date ngayDate=null;
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "select NGAYDUKIEN FROM CONGTRINH WHERE MACT = '"+ma+"'";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Date ngay = rs.getDate(1);
				ngayDate = ngay;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ngayDate;
	}
	public static List<Congtrinh> FindByCT(String mald) {
		List<Congtrinh> dstimNV = new ArrayList<Congtrinh>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			
			sql = "SELECT A.MACT FROM CONGTRINH A JOIN CHITIETCT B ON A.MACT=B.MACT JOIN LAODONG C ON B.MALD=C.MALD  WHERE A.TRANGTHAI = 0 and C.MALD= '"+mald+"'";
		
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				
				Congtrinh nv = new Congtrinh(ma);
				dstimNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static List<Congtrinh> FindByCTQ(String mald) {
		List<Congtrinh> dstimNV = new ArrayList<Congtrinh>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			
			sql = "SELECT A.MACT FROM CONGTRINH A JOIN CHITIETCT B ON A.MACT=B.MACT JOIN LAODONG C ON B.MALD=C.MALD  WHERE A.TRANGTHAI = 1 and C.MALD= '"+mald+"'";
		
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				
				Congtrinh nv = new Congtrinh(ma);
				dstimNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static List<Congtrinh> findPhancong() {
		List<Congtrinh> listNV = new ArrayList<Congtrinh>();
		Statement stmt = null;
		try {
				Connection con =Connect.getInstance().getConnection();
				String sql = "SELECT * FROM CONGTRINH WHERE TRANGTHAI =0";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ma = rs.getString("MACT");
					String ten = rs.getString("TENCT");
					Boolean tt = rs.getBoolean("TRANGTHAI");
					Date ngcap = rs.getDate("NGAYCAP");
					Date ngcong = rs.getDate("NGAYKHOICONG");
					Date ngdukien = rs.getDate("NGAYDUKIEN");
					String diadiem = rs.getString("DIADIEM");	
					int soluong = rs.getInt("SOLUONGLD");
					Congtrinh nv = new Congtrinh(ma, ten, tt, soluong, ngcap, ngcong, ngdukien, diadiem);
					listNV.add(nv);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV ;
	

	}
	
	
	
}
