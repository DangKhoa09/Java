package Entity;

import java.sql.Date;

public class Chitietct {
	private Congtrinh mact;
	private Laodong mald;
	private Date ngaybatdau;
	private Date ngayketthuc;
	public Congtrinh getMact() {
		return mact;
	}
	public void setMact(Congtrinh mact) {
		this.mact = mact;
	}
	public Laodong getMald() {
		return mald;
	}
	public void setMald(Laodong mald) {
		this.mald = mald;
	}
	public Date getNgaybatdau() {
		return ngaybatdau;
	}
	public void setNgaybatdau(Date ngaybatdau) {
		this.ngaybatdau = ngaybatdau;
	}
	public Date getNgayketthuc() {
		return ngayketthuc;
	}
	public void setNgayketthuc(Date ngayketthuc) {
		this.ngayketthuc = ngayketthuc;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mact == null) ? 0 : mact.hashCode());
		result = prime * result + ((mald == null) ? 0 : mald.hashCode());
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
		Chitietct other = (Chitietct) obj;
		if (mact == null) {
			if (other.mact != null)
				return false;
		} else if (!mact.equals(other.mact))
			return false;
		if (mald == null) {
			if (other.mald != null)
				return false;
		} else if (!mald.equals(other.mald))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Chitietct [mact=" + mact + ", mald=" + mald + ", ngaybatdau=" + ngaybatdau + ", ngayketthuc="
				+ ngayketthuc + "]";
	}
	public Chitietct(Congtrinh mact, Laodong mald, Date ngaybatdau, Date ngayketthuc) {
		super();
		this.mact = mact;
		this.mald = mald;
		this.ngaybatdau = ngaybatdau;
		this.ngayketthuc = ngayketthuc;
	}
	public Chitietct(Congtrinh mact, Laodong mald) {
		super();
		this.mact = mact;
		this.mald = mald;
	}

	
	
}
