package cinema.model;

public class Auditorium {
	int audiNo;
	String audiName;
	String audiType;
	int seatCnt;
	String floor;
	Theater theater;
	int seatTotalCnt;
	
	
	
	public Auditorium() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Auditorium(int audiNo, String audiName, String audiType, int seatCnt, String floor, Theater theater) {
		super();
		this.audiNo = audiNo;
		this.audiName = audiName;
		this.audiType = audiType;
		this.seatCnt = seatCnt;
		this.floor = floor;
		this.theater = theater;
	}

	public int getSeatTotalCnt() {
		return seatTotalCnt;
	}

	public void setSeatTotalCnt(int seatTotalCnt) {
		this.seatTotalCnt = seatTotalCnt;
	}

	public int getSeatCnt() {
		return seatCnt;
	}

	public void setSeatCnt(int seatCnt) {
		this.seatCnt = seatCnt;
	}

	public String getAudiType() {
		return audiType;
	}

	public void setAudiType(String audiType) {
		this.audiType = audiType;
	}

	public int getAudiNo() {
		return audiNo;

	}

	public void setAudiNo(int audiNo) {
		this.audiNo = audiNo;
	}

	public String getAudiName() {
		return audiName;
	}

	public void setAudiName(String audiName) {
		this.audiName = audiName;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Theater getTheater() {
		return theater;
	}

	public void setTheater(Theater theater) {
		this.theater = theater;
	}

	public void setTheaterNo(int theaterNo) {
		if (this.theater == null) {
			this.theater = new Theater();
		}
		this.theater.setTheaterNo(theaterNo);
	}

	@Override
	public String toString() {
		return "Auditorium [audiNo=" + audiNo + ", audiName=" + audiName + ", audiType=" + audiType + ", seatCnt="
				+ seatCnt + ", floor=" + floor + ", theater=" + theater + "]";
	}

}
