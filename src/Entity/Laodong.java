package Entity;

import java.sql.Date;

public class Laodong {
	private String Mald;
	private String Tenld;
	private Chuyenmon Macm;
	private boolean phai;
	private Date ngaysinh;
	private Date ngaylamviec;
	private String diachi;
	private String cmnd;
	private String sdt;
	private boolean trangthai;
	
	public Laodong() {
		
	}
	public Laodong(String mald) {
		super();
		Mald = mald;
	}
	public Laodong(String mald, String tenld, Chuyenmon macm, boolean phai, Date ngaysinh, Date ngaylamviec,
			String diachi, String cmnd, String sdt, boolean trangthai) {
		super();
		this.Mald = mald;
		this.Tenld = tenld;
		this.Macm = macm;
		this.phai = phai;
		this.ngaysinh = ngaysinh;
		this.ngaylamviec = ngaylamviec;
		this.diachi = diachi;
		this.cmnd = cmnd;
		this.sdt = sdt;
		this.trangthai = trangthai;
	}
	@Override
	public String toString() {
		return "Laodong [Mald=" + Mald + ", Tenld=" + Tenld + ", Macm=" + Macm + ", phai=" + phai + ", ngaysinh="
				+ ngaysinh + ", ngaylamviec=" + ngaylamviec + ", diachi=" + diachi + ", cmnd=" + cmnd + ", sdt=" + sdt
				+ ", trangthai=" + trangthai + "]";
	}
	public String getMald() {
		return Mald;
	}
	public void setMald(String mald) {
		Mald = mald;
	}
	public String getTenld() {
		return Tenld;
	}
	public void setTenld(String tenld) {
		Tenld = tenld;
	}
	public Chuyenmon getMacm() {
		return Macm;
	}
	public void setMacm(Chuyenmon macm) {
		Macm = macm;
	}
	public boolean isPhai() {
		return phai;
	}
	public void setPhai(boolean phai) {
		this.phai = phai;
	}
	public Date getNgaysinh() {
		return ngaysinh;
	}
	public void setNgaysinh(Date ngaysinh) {
		this.ngaysinh = ngaysinh;
	}
	public Date getNgaylamviec() {
		return ngaylamviec;
	}
	public void setNgaylamviec(Date ngaylamviec) {
		this.ngaylamviec = ngaylamviec;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public boolean isTrangthai() {
		return trangthai;
	}
	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Mald == null) ? 0 : Mald.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Laodong other = (Laodong) obj;
		if (Mald == null) {
			if (other.Mald != null)
				return false;
		} else if (!Mald.equals(other.Mald))
			return false;
		return true;
	}
	
	
	
	
	
}
