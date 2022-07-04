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
import Entity.Chuyenmon;
import Entity.Laodong;


public class crudLaodong {
	private static ArrayList<Laodong> qlld;

	public crudLaodong() {
		qlld = new ArrayList<Laodong>();
	}
	public static List<Laodong> findAll() {
		List<Laodong> listNV = new ArrayList<Laodong>();
		Statement stmt = null;
		try {
				Connection con =Connect.getInstance().getConnection();
				String sql = "SELECT * FROM LAODONG";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ma = rs.getString("MaLD");
					String ten = rs.getString("TENLAODONG");
					String macm = rs.getString("MaCM");
					Chuyenmon cm = new Chuyenmon(macm);
					Boolean phai = rs.getBoolean("Gioitinh");
					Date ngsinh = rs.getDate("Ngaysinh");
					Date nglv = rs.getDate("Ngaylamviec");
					String Diachi = rs.getString("Diachi");
					String sdt = rs.getString("SDT");	
					String cmnd = rs.getString("CMND");
					Boolean tt = rs.getBoolean("Trangthai");
					Laodong nv = new Laodong(ma, ten, cm, phai, ngsinh, nglv, Diachi, cmnd, sdt, tt);
					listNV.add(nv);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV ;
	

	}
	public static List<Laodong> find() {
		List<Laodong> listNV = new ArrayList<Laodong>();
		Statement stmt = null;
		try {
				Connection con =Connect.getInstance().getConnection();
				String sql = "SELECT * FROM LAODONG WHERE TRANGTHAI=1 AND MALD NOT IN (SELECT A.MALD FROM LAODONG A JOIN CHITIETCT B ON A.MALD=B.MALD JOIN CONGTRINH C ON C.MACT=B.MACT WHERE A.TRANGTHAI=1 AND C.TRANGTHAI=0)";
				stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String ma = rs.getString("MaLD");
					String ten = rs.getString("TENLAODONG");
					String macm = rs.getString("MaCM");
					Chuyenmon cm = new Chuyenmon(macm);
					Boolean phai = rs.getBoolean("Gioitinh");
					Date ngsinh = rs.getDate("Ngaysinh");
					Date nglv = rs.getDate("Ngaylamviec");
					String Diachi = rs.getString("Diachi");
					String sdt = rs.getString("SDT");	
					String cmnd = rs.getString("CMND");
					Boolean tt = rs.getBoolean("Trangthai");
					Laodong nv = new Laodong(ma, ten, cm, phai, ngsinh, nglv, Diachi, cmnd, sdt, tt);
					listNV.add(nv);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNV ;
	

	}

	public boolean insert(Laodong nv) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		
		try {
			String sql = ("INSERT INTO [dbo].[LAODONG](MALD,TENLAODONG,GIOITINH,NGAYSINH,NGAYLAMVIEC,TRANGTHAI,DIACHI,SDT,CMND,MACM) VALUES (?,?,?,?,?,?,?,?,?,?)");
			stmt = con.prepareCall(sql);
			stmt.setString(1, nv.getMald());
			stmt.setString(2, nv.getTenld());
			stmt.setBoolean(3, nv.isPhai());
			stmt.setDate(4, nv.getNgaysinh());
			stmt.setDate(5, nv.getNgaylamviec());;
			stmt.setBoolean(6, nv.isTrangthai());
			stmt.setString(7,nv.getDiachi());
			stmt.setString(8, nv.getSdt());
			stmt.setString(9, nv.getCmnd());
			stmt.setString(10,nv.getMacm().getMacm());
			n= stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n>0;
		
	}

	public boolean update(Laodong nv) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = ("update [dbo].[LAODONG] SET TENLAODONG=?,GIOITINH=?,NGAYSINH=?,NGAYLAMVIEC=?,TRANGTHAI=?,DIACHI=?,SDT=?,CMND=?,MACM=? WHERE MALD =?");
			stmt = con.prepareCall(sql);
			
			stmt.setString(1, nv.getTenld());
			stmt.setBoolean(2, nv.isPhai());
			stmt.setDate(3, nv.getNgaysinh());
			stmt.setDate(4, nv.getNgaylamviec());;
			stmt.setBoolean(5, nv.isTrangthai());
			stmt.setString(6,nv.getDiachi());
			stmt.setString(7, nv.getSdt());
			stmt.setString(8, nv.getCmnd());
			stmt.setString(9,nv.getMacm().getMacm());
			stmt.setString(10, nv.getMald());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;
	}

	public static List<Laodong> FindByName(String ten) {
		List<Laodong> dstimNV = new ArrayList<Laodong>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			if(!(ten.equals("")&& ten.length()>0)){
				sql = "SELECT * FROM LAODONG WHERE TENLAODONG LIKE N'%"+ten+"%'";
			}
			else {
				sql = "select * from [dbo].[LAODONG]";
			}
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString("MALD");
				String ten1 = rs.getString("TENLAODONG");
				String macm = rs.getString("MACM");
				Chuyenmon cm = new Chuyenmon(macm);
				Boolean phai = rs.getBoolean("GIOITINH");
				Date ngsinh = rs.getDate("NGAYSINH");
				Date nglv = rs.getDate("NGAYLAMVIEC");
				String Diachi = rs.getString("DIACHI");
				String sdt = rs.getString("SDT");	
				Boolean tt = rs.getBoolean("TRANGTHAI");
				Laodong nv = new Laodong(ma, ten1, cm, phai, ngsinh, nglv, Diachi, Diachi, sdt, tt);
				dstimNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static List<Laodong> FindByID(String ma1) {
		List<Laodong> dstimNV = new ArrayList<Laodong>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			if(!(ma1.equals("")&& ma1.length()>0)){
				sql = "SELECT * FROM LAODONG WHERE MALD = '"+ma1+"'";
			}
			else {
				sql = "select * from [dbo].[LAODONG]";
			}
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString("MALD");
				String ten1 = rs.getString("TENLAODONG");
				String macm = rs.getString("MACM");
				Chuyenmon cm = new Chuyenmon(macm);
				Boolean phai = rs.getBoolean("GIOITINH");
				Date ngsinh = rs.getDate("NGAYSINH");
				Date nglv = rs.getDate("NGAYLAMVIEC");
				String Diachi = rs.getString("DIACHI");
				String sdt = rs.getString("SDT");	
				Boolean tt = rs.getBoolean("TRANGTHAI");
				Laodong nv = new Laodong(ma, ten1, cm, phai, ngsinh, nglv, Diachi, Diachi, sdt, tt);
				dstimNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static List<Laodong> FindByPB(String ma1) {
		List<Laodong> dstimNV = new ArrayList<Laodong>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			if(!(ma1.equals("")&& ma1.length()>0)){
				sql = "SELECT * FROM LAODONG WHERE MACM = '"+ma1+"'";
			}
			else {
				sql = "select * from [dbo].[LAODONG]";
			}
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString("MALD");
				String ten1 = rs.getString("TENLAODONG");
				String macm = rs.getString("MACM");
				Chuyenmon cm = new Chuyenmon(macm);
				Boolean phai = rs.getBoolean("GIOITINH");
				Date ngsinh = rs.getDate("NGAYSINH");
				Date nglv = rs.getDate("NGAYLAMVIEC");
				String Diachi = rs.getString("DIACHI");
				String sdt = rs.getString("SDT");	
				Boolean tt = rs.getBoolean("TRANGTHAI");
				Laodong nv = new Laodong(ma, ten1, cm, phai, ngsinh, nglv, Diachi, Diachi, sdt, tt);
				dstimNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	
	public static List<Laodong> FindByCTQ() {
		List<Laodong> dstimNV = new ArrayList<Laodong>();
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql;
			sql = "SELECT MACT FROM CONGTRINH A JOIN CHITIETCT B ON A.MACT=B.MACT JOIN LAODONG C ON B.MALD=C.MALD  WHERE A.TRANGTHAI = 1";
		
			
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				String ma = rs.getString(1);
				
				Laodong nv = new Laodong(ma);
				dstimNV.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dstimNV;
	}
	public static boolean delete(String manv) {
		Connection con = Connect.getInstance().getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("DELETE FROM LAODONG WHERE MaLD LIKE '"+manv+"'");
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
			String sql = "DECLARE @MANV NCHAR(5),@SO INT,@TONG INT\r\n"
					+ "SET @TONG = (SELECT COUNT(*) FROM LAODONG)\r\n"
					+ "IF @TONG =0\r\n"
					+ "	BEGIN\r\n"
					+ "		SET @MANV = 'NV001'\r\n"
					+ "	END\r\n"
					+ "ELSE\r\n"
					+ "	BEGIN\r\n"
					+ "		SET @MANV = RIGHT((SELECT MAX(MALD) FROM LAODONG),3)\r\n"
					+ "		SET @SO = CAST(@MANV AS INT)+1\r\n"
					+ "		IF @SO<10\r\n"
					+ "			SET @MANV = 'NV00'+CAST(@SO AS nvarchar(3))\r\n"
					+ "		ELSE IF @SO>=10 AND @SO <100\r\n"
					+ "			SET @MANV = 'NV0'+CAST(@SO AS nvarchar(3))\r\n"
					+ "		ELSE IF @SO>=100\r\n"
					+ "			SET  @MANV = 'NV'+CAST(@SO AS nvarchar(3))\r\n"
					+ "	END\r\n"
					+ "SELECT @MANV";
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
	public boolean CHIHUY(String mald,String mact) {
		Connection con= Connect.getInstance().getConnection();
		try {
			PreparedStatement p=con.prepareStatement("DECLARE @MACT1 NCHAR(5),@MALD1 NCHAR(5),@MACM1 NCHAR(4),@OK1 BIT\r\n"
					+ "SET @MACT1 = '"+mact+"'\r\n"
					+ "SET @MALD1 = '"+mald+"'\r\n"
					+ "SET @MACM1 ='CM02'\r\n"
					+ "IF (SELECT MACM FROM LAODONG WHERE MALD=@MALD1)=@MACM1\r\n"
					+ "BEGIN\r\n"
					+ "	IF @MACM1 IN (SELECT MACM FROM LAODONG A join CHITIETCT B ON A.MALD=B.MALD JOIN CONGTRINH C ON B.MACT=C.MACT WHERE C.MACT=@MACT1)\r\n"
					+ "		BEGIN\r\n"
					+ "			SET @OK1 = 1\r\n"
					+ "		END\r\n"
					+ "	ELSE \r\n"
					+ "		SET @OK1 = 0\r\n"
					+ "END\r\n"
					+ "ELSE\r\n"
					+ "	SET @OK1=0\r\n"
					+ "SELECT @OK1");
			ResultSet r=p.executeQuery();
			while(r.next()) {
				if(r.getInt(1)==1)
					return false;
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;
	}
	public int soluongchuyenmon(String macm) {
		int soluong = 0;
		try {
			Connection con = Connect.getInstance().getConnection();
			String sql = "select count(*) from LAODONG where MACM='"+macm+"' group by MACM";
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				int so = rs.getInt(1);
				soluong = so;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soluong;
	}
	
}
