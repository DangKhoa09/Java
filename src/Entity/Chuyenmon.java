package Entity;

public class Chuyenmon {
	private String macm;
	private String tencm;
	public String getMacm() {
		return macm;
	}
	public void setMacm(String macm) {
		this.macm = macm;
	}
	public String getTencm() {
		return tencm;
	}
	public void setTencm(String tencm) {
		this.tencm = tencm;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((macm == null) ? 0 : macm.hashCode());
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
		Chuyenmon other = (Chuyenmon) obj;
		if (macm == null) {
			if (other.macm != null)
				return false;
		} else if (!macm.equals(other.macm))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Chuyenmon [macm=" + macm + ", tencm=" + tencm + "]";
	}
	public Chuyenmon(String macm, String tencm) {
		super();
		this.macm = macm;
		this.tencm = tencm;
	}
	public Chuyenmon(String macm) {
		super();
		this.macm = macm;
	}
	public Chuyenmon() {
		
	}
}
