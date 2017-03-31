package cinema.model;

public class Seat {
	int seatNo;
	int row;
	int col;
	String seatName;
	Auditorium auditorium;

	public Seat() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(int seatNo) {
		this.seatNo = seatNo;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public void setAuditoriumNo(int auditoriumNo) {
		if (this.auditorium == null) {
			this.auditorium = new Auditorium();
		}
		this.auditorium.setAudiNo(auditoriumNo);
	}

	@Override
	public String toString() {
		return "Seat [seatNo=" + seatNo + ", row=" + row + ", col=" + col + ", seatName=" + seatName + ", auditorium="
				+ auditorium + "]";
	}

}
