package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Booking;
import jdbc.JdbcUtil;

public class BookingDao {

	public List<Booking> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// String subSql1 = "select * from schedule sch left join (select
		// scr.*,mo.title_kor,mo.title_eng,mo.actors,mo.director,
		// mo.age_require, mo.time_length, mo.genre from screen scr left join
		// movie mo on scr.movie_no=mo.movie_no) as one on
		// sch.screen_no=one.screen_no left join (select audi.*,th.theater_name
		// from auditorium audi left join theater th on
		// audi.theater_no=th.theater_no) as two on sch.audi_no=two.audi_no";
		// String subSql1 = "select * from schedule sch left join (select
		// scr.*,mo.title_kor,mo.title_eng,mo.actors,mo.director,
		// mo.age_require, mo.time_length, mo.genre from screen scr left join
		// movie mo on scr.movie_no=mo.movie_no) as one on
		// sch.screen_no=one.screen_no left join (select audi.*,th.theater_name
		// from auditorium audi left join theater th on
		// audi.theater_no=th.theater_no) as two on sch.audi_no=two.audi_no";
		/*
		 * String sql = "select bo.*, sch.*,ti.* from booking bo " +
		 * " left join schedule sch on bo.schedule_no=sch.schedule_no " +
		 * " left join `member` m on bo.user_no=m.user_no " +
		 * " left join ticket_price ti on bo.price_no=ti.price_no order by bo.booking_no"
		 * ;
		 */

		String sql = "select bo.*, sch.*, m.*, ti.* from booking bo "
				+ "left join (select sch.*, one.movie_no, one.title_kor,one.title_eng, two.audi_name,two.theater_no, two.`floor`, two.theater_name from schedule sch left join  (select scr.*,mo.title_kor,mo.title_eng,mo.actors,mo.director, mo.age_require, mo.time_length, mo.genre   from screen scr  left join movie mo  on scr.movie_no=mo.movie_no) as one  on sch.screen_no=one.screen_no  left join  (select  audi.*,th.theater_name   from auditorium audi  left join theater th  on audi.theater_no=th.theater_no) as two   on sch.audi_no=two.audi_no) sch on bo.schedule_no=sch.schedule_no "
				+ "left join `member` m on bo.user_no=m.user_no "
				+ "left join ticket_price ti on bo.price_no=ti.price_no order by bo.booking_no";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Booking> bookingList = new ArrayList<>();
			Booking booking = null;
			while (rs.next()) {
				booking = new Booking();

				booking.setBookingNo(rs.getInt("booking_no"));
				booking.setSeatRow(rs.getString("book_seat_row"));
				booking.setSeatCol(rs.getInt("book_seat_col"));
				booking.setAcceptedDate(rs.getDate("accepted_date"));

				booking.setMemberNo(rs.getInt("user_no"));
				booking.getMember().setUserPhone(rs.getString("user_phone"));
				booking.getMember().setUserEmail(rs.getString("user_email"));
				booking.getMember().setUserId(rs.getString("user_id"));
				booking.getMember().setUserName(rs.getString("user_name"));

				booking.setTicketPriceNo(rs.getInt("price_no"));
				booking.getTicketPrice().setPrice(rs.getInt("price"));
				booking.getTicketPrice().setCateAge(rs.getString("cate_age"));
				booking.getTicketPrice().setCateSeat(rs.getString("cate_seat"));
				booking.getTicketPrice().setCateScreen(rs.getString("cate_screen"));
				booking.getTicketPrice().setCateAudi(rs.getString("cate_audi"));

				booking.setScheduleNo(rs.getInt("schedule_no"));
				booking.getSchedule().setShowDate(rs.getDate("show_date"));
				booking.getSchedule().setStartTime(rs.getTime("start_time").toLocalTime());
				booking.getSchedule().setEndTime(rs.getTime("end_time").toLocalTime());

				/*
				 * pstmt.setTime(3, Time.valueOf(schedule.getStartTime()));
				 * schedule.setEndTime(rs.getTime("end_time").toLocalTime());
				 */

				booking.getSchedule().setAuditoriumNo(rs.getInt("audi_no"));

				booking.getSchedule().getAuditorium().setAudiName(rs.getString("audi_name"));
				booking.getSchedule().getAuditorium().setFloor(rs.getString("floor"));

				booking.getSchedule().getAuditorium().setTheaterNo(rs.getInt("theater_no"));
				booking.getSchedule().getAuditorium().getTheater().setTheaterName(rs.getString("theater_name"));

				booking.getSchedule().setScreenNo(rs.getInt("screen_no"));
				// booking.getSchedule().getScreen().setScreenMode(rs.getString("screen_mode"));

				booking.getSchedule().getScreen().setMovieNo(rs.getInt("movie_no"));
				booking.getSchedule().getScreen().getMovie().setKorTitle(rs.getString("title_kor"));
				booking.getSchedule().getScreen().getMovie().setEngTitle(rs.getString("title_eng"));

				bookingList.add(booking);

			}
			return bookingList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int bookingNo) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from booking where booking_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bookingNo);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}

	}

}
