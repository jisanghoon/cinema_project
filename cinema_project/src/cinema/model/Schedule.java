package cinema.model;

import java.time.LocalTime;
import java.util.Date;

public class Schedule {

	int scheduleNo;
	LocalTime startTime;
	LocalTime endTime;
	Date showDate;
	Auditorium auditorium;
	Screen screen;
	String cateTime;

	public int getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public Date getShowDate() {
		return showDate;
	}

	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}

	public Auditorium getAuditorium() {
		return auditorium;
	}

	public void setAuditorium(Auditorium auditorium) {
		this.auditorium = auditorium;
	}

	public String getCateTime() {
		return cateTime;
	}

	public void setCateTime(String cateTime) {
		this.cateTime = cateTime;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setAuditoriumNo(int auditoriumNo) {
		if (this.auditorium == null) {
			this.auditorium = new Auditorium();
		}

		this.auditorium.setAudiNo(auditoriumNo);
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public void setScreenNo(int screenNo) {
		if (this.screen == null) {
			this.screen = new Screen();
		}
		this.screen.setScreenNo(screenNo);
	}

	@Override
	public String toString() {
		return "Schedule [scheduleNo=" + scheduleNo + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", showDate=" + showDate + ", auditorium=" + auditorium + ", screen=" + screen + ", cateTime="
				+ cateTime + "]";
	}

}
