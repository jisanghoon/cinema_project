package joon.temp;

import java.sql.Date;

import cinema.model.Schedule;
import cinema.model.TicketPrice;
import member.model.Member;

public class Booking {
	private int bookingNo;
	private Member cusNo;
	private TicketPrice priceNo;
	private Schedule scheduleNo;
	private String bookSeatRow;
	private int bookSeatcol;
	private Date acceptedDate;

	public Booking(int bookingNo, Member cusNo, TicketPrice priceNo, Schedule scheduleNo, String bookSeatRow,
			int bookSeatcol, Date acceptedDate) {
		super();
		this.bookingNo = bookingNo;
		this.cusNo = cusNo;
		this.priceNo = priceNo;
		this.scheduleNo = scheduleNo;
		this.bookSeatRow = bookSeatRow;
		this.bookSeatcol = bookSeatcol;
		this.acceptedDate = acceptedDate;
	}

	public int getBookingNo() {
		return bookingNo;
	}

	public Member getCusNo() {
		return cusNo;
	}

	public TicketPrice getPriceNo() {
		return priceNo;
	}

	public Schedule getScheduleNo() {
		return scheduleNo;
	}

	public String getBookSeatRow() {
		return bookSeatRow;
	}

	public int getBookSeatcol() {
		return bookSeatcol;
	}

	public Date getAcceptedDate() {
		return acceptedDate;
	}

}
