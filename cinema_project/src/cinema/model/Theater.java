package cinema.model;

public class Theater {
	int theaterNo;
	String theaterName;
	String theaterAddrNum;
	String theaterAddrStr;
	String theaterManager;
	String theaterTel;

	public int getTheaterNo() {
		return theaterNo;
	}

	public void setTheaterNo(int theaterNo) {
		this.theaterNo = theaterNo;
	}

	public String getTheaterName() {
		return theaterName;
	}

	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	public String getTheaterAddrNum() {
		return theaterAddrNum;
	}

	public void setTheaterAddrNum(String theaterAddrNum) {
		this.theaterAddrNum = theaterAddrNum;
	}

	public String getTheaterAddrStr() {
		return theaterAddrStr;
	}

	public void setTheaterAddrStr(String theaterAddrStr) {
		this.theaterAddrStr = theaterAddrStr;
	}

	public String getTheaterManager() {
		return theaterManager;
	}

	public void setTheaterManager(String theaterManager) {
		this.theaterManager = theaterManager;
	}

	public String getTheaterTel() {
		return theaterTel;
	}

	public void setTheaterTel(String theaterTel) {
		this.theaterTel = theaterTel;
	}

	@Override
	public String toString() {
		return "Theater [theaterNo=" + theaterNo + ", theaterName=" + theaterName + ", theaterAddrNum=" + theaterAddrNum
				+ ", theaterAddrStr=" + theaterAddrStr + ", theaterManager=" + theaterManager + ", theaterTel="
				+ theaterTel + "]";
	}

}
