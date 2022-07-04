package Entity;

public class taikhoan {
	private String maquanly;
	private String tenquanly;
	private String sdt;
	private String username;
	private String password;
	public String getMaquanly() {
		return maquanly;
	}
	public void setMaquanly(String maquanly) {
		this.maquanly = maquanly;
	}
	public String getTenquanly() {
		return tenquanly;
	}
	public void setTenquanly(String tenquanly) {
		this.tenquanly = tenquanly;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maquanly == null) ? 0 : maquanly.hashCode());
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
		taikhoan other = (taikhoan) obj;
		if (maquanly == null) {
			if (other.maquanly != null)
				return false;
		} else if (!maquanly.equals(other.maquanly))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "taikhoan [maquanly=" + maquanly + ", tenquanly=" + tenquanly + ", sdt=" + sdt + ", username=" + username
				+ ", password=" + password + "]";
	}
	public taikhoan(String maquanly, String tenquanly, String sdt, String username, String password) {
		super();
		this.maquanly = maquanly;
		this.tenquanly = tenquanly;
		this.sdt = sdt;
		this.username = username;
		this.password = password;
	}
	public taikhoan(String maquanly) {
		super();
		this.maquanly = maquanly;
	}
	public taikhoan() {
		
	}
	
}
