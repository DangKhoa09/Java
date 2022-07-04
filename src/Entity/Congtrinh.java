package Entity;

import java.sql.Date;



public class Congtrinh {
	private String mact;
	private String tenct;
	private boolean trangthai;
	private int soluong;
	private Date ngaycap;
	private Date ngaykhoicong;
	private Date ngaydukien;
	private String diadiem;
	
	public Congtrinh(String mact, String tenct, boolean trangthai, int soluong, Date ngaycap, Date ngaykhoicong,
			Date ngaydukien, String diadiem) {
		super();
		this.mact = mact;
		this.tenct = tenct;
		this.trangthai = trangthai;
		this.soluong = soluong;
		this.ngaycap = ngaycap;
		this.ngaykhoicong = ngaykhoicong;
		this.ngaydukien = ngaydukien;
		this.diadiem = diadiem;
	}
	public Congtrinh() {
		
	}
	public Congtrinh(String mact) {
		super();
		this.mact = mact;
	}

	public String getMact() {
		return mact;
	}
	public void setMact(String mact) {
		this.mact = mact;
	}
	public String getTenct() {
		return tenct;
	}
	public void setTenct(String tenct) {
		this.tenct = tenct;
	}
	public boolean isTrangthai() {
		return trangthai;
	}
	public void setTrangthai(boolean trangthai) {
		this.trangthai = trangthai;
	}
	public int getSoluong() {
		return soluong;
	}
	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	public Date getNgaycap() {
		return ngaycap;
	}
	public void setNgaycap(Date ngaycap) {
		this.ngaycap = ngaycap;
	}
	public Date getNgaykhoicong() {
		return ngaykhoicong;
	}
	public void setNgaykhoicong(Date ngaykhoicong) {
		this.ngaykhoicong = ngaykhoicong;
	}
	public Date getNgaydukien() {
		return ngaydukien;
	}
	public void setNgaydukien(Date ngaydukien) {
		this.ngaydukien = ngaydukien;
	}
	public String getDiadiem() {
		return diadiem;
	}
	public void setDiadiem(String diadiem) {
		this.diadiem = diadiem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mact == null) ? 0 : mact.hashCode());
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
		Congtrinh other = (Congtrinh) obj;
		if (mact == null) {
			if (other.mact != null)
				return false;
		} else if (!mact.equals(other.mact))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Congtrinh [mact=" + mact + ", tenct=" + tenct + ", trangthai=" + trangthai + ", soluong=" + soluong
				+ ", ngaycap=" + ngaycap + ", ngaykhoicong=" + ngaykhoicong + ", ngaydukien=" + ngaydukien
				+ ", diadiem=" + diadiem + "]";
	}
	
	
}
