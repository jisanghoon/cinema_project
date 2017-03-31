package cinema.model;

import java.util.Date;

public class Booking {

	int bookingNo;
	Member member;
	TicketPrice ticketPrice;
	Schedule schedule;
	String seatRow;
	int seatCol;
	Date acceptedDate;

	public int getBookingNo() {
		return bookingNo;
	}

	public void setBookingNo(int bookingNo) {
		this.bookingNo = bookingNo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public TicketPrice getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(TicketPrice ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public String getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatCol() {
		return seatCol;
	}

	public void setSeatCol(int seatCol) {
		this.seatCol = seatCol;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(Date acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public void setMemberNo(int userNo) {
		if (this.member == null) {
			this.member = new Member();
		}
		this.member.setUserNo(userNo);
	}

	public void setTicketPriceNo(int priceNo) {
		if (this.ticketPrice == null) {
			this.ticketPrice = new TicketPrice();
		}
		this.ticketPrice.setPriceNo(priceNo);
	}

	public void setScheduleNo(int scheduleNo) {
		if (this.schedule == null) {
			this.schedule = new Schedule();
		}
		this.schedule.setScheduleNo(scheduleNo);
	}

	@Override
	public String toString() {
		return "Booking [bookingNo=" + bookingNo + ", member=" + member + ", ticketPrice=" + ticketPrice + ", schedule="
				+ schedule + ", seatRow=" + seatRow + ", seatCol=" + seatCol + ", acceptedDate=" + acceptedDate + "]";
	}

}
