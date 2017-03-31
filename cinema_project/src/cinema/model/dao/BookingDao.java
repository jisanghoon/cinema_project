package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cinema.model.Auditorium;
import cinema.model.BookStepOne;
import cinema.model.Booking;
import cinema.model.Movie;
import cinema.model.Schedule;
import cinema.model.Screen;
import cinema.model.Theater;
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

	// 극장 선택
	public List<BookStepOne> selTheater(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from schedule sch left join (select s.*,m.title_kor, title_eng, actors, director, release_date, age_require, time_length, country, ratings, total_attendance, genre, content, small_pic_url, big_pic_url from screen s left join movie m on s.movie_no=m.movie_no) sr on sch.screen_no=sr.screen_no left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi on sch.audi_no=audi.audi_no where theater_no = ? group by movie_no";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			BookStepOne bookStepOne = null;
			if (rs.next()) {
				List<BookStepOne> bookStepOnes = new ArrayList<>();
				do {
			
					Theater theater = new Theater();
					theater.setTheaterNo(rs.getInt("theater_no"));
					theater.setTheaterName(rs.getString("theater_name"));

			
					Auditorium auditorium = new Auditorium();
					auditorium.setAudiName(rs.getString("audi_name"));
					auditorium.setAudiType(rs.getString("audi_type"));
					auditorium.setFloor(rs.getString("floor"));
					auditorium.setTheater(theater);

	
					Movie movie = new Movie();
					movie.setMovieNo(rs.getInt("movie_no"));
					movie.setKorTitle(rs.getString("title_kor"));
					movie.setEngTitle(rs.getString("title_eng"));
					movie.setDirector(rs.getString("director"));
					movie.setActors(rs.getString("actors"));
					movie.setReleaseDate(rs.getDate("release_date"));
					movie.setAgeRequire(rs.getString("age_require"));
					movie.setTimeLength(rs.getInt("time_length"));
					movie.setCountry(rs.getString("country"));
					movie.setRatings(rs.getInt("ratings"));
					movie.setTotalAttendance(rs.getInt("total_attendance"));
					movie.setGenre(rs.getString("genre"));
					movie.setContent(rs.getString("content"));
					movie.setSmallPicUrl(rs.getString("small_pic_url"));
					movie.setBigPicUrl(rs.getString("big_pic_url"));


					Screen screen = new Screen();

					screen.setScreenNo(rs.getInt("screen_no"));
					screen.setMovie(movie);
					screen.setScreenMode(rs.getString("screen_mode"));
					screen.setBuyDate(rs.getDate("buy_date"));
					screen.setScreenPrice(rs.getLong("screen_price"));

					screen.setSupplier(rs.getString("supplier"));
					screen.setStartDate(rs.getDate("start_date"));
					screen.setEndDate(rs.getDate("end_date"));

	
					Schedule schedule = new Schedule();
					schedule.setScheduleNo(rs.getInt("schedule_no"));
					schedule.setStartTime(rs.getTime("start_time").toLocalTime());
					schedule.setEndTime(rs.getTime("end_time").toLocalTime());
					schedule.setShowDate(rs.getDate("show_date"));
					schedule.setAuditorium(auditorium);
					schedule.setScreen(screen);

					bookStepOne = new BookStepOne(schedule, screen, movie, auditorium, theater);
					bookStepOnes.add(bookStepOne);
				} while (rs.next());
				return bookStepOnes;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<BookStepOne> selMovie(Connection conn, int theaterVal, int movieVal, String dataNo)
			throws SQLException {
		// 시간 선택

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from schedule sch "
				+ "left join (select s.*,m.title_kor, title_eng, actors, director, release_date, age_require, time_length, country, ratings, total_attendance, genre, content, small_pic_url, big_pic_url from screen s left join movie m on s.movie_no=m.movie_no) sr on sch.screen_no=sr.screen_no "
				+ "left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi on sch.audi_no=audi.audi_no where theater_no = ? and movie_no = ? and show_date = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterVal);
			pstmt.setInt(2, movieVal);
			pstmt.setString(3, dataNo);
			rs = pstmt.executeQuery();
			BookStepOne bookStepOne = null;
			if (rs.next()) {
				List<BookStepOne> bookStepOnes = new ArrayList<>();
				do {

					Theater theater = new Theater();
					theater.setTheaterNo(rs.getInt("theater_no"));
					theater.setTheaterName(rs.getString("theater_name"));

					Auditorium auditorium = new Auditorium();
					auditorium.setFloor(rs.getString("floor"));
					auditorium.setAudiName(rs.getString("audi_name"));
					auditorium.setAudiType(rs.getString("audi_type"));
					auditorium.setFloor(rs.getString("floor"));
					auditorium.setTheater(theater);
					
					
					Movie movie = new Movie();
					movie.setMovieNo(rs.getInt("movie_no"));
					movie.setKorTitle(rs.getString("title_kor"));
					movie.setEngTitle(rs.getString("title_eng"));
					movie.setDirector(rs.getString("director"));
					movie.setActors(rs.getString("actors"));
					movie.setReleaseDate(rs.getDate("release_date"));
					movie.setAgeRequire(rs.getString("age_require"));
					movie.setTimeLength(rs.getInt("time_length"));
					movie.setCountry(rs.getString("country"));
					movie.setRatings(rs.getInt("ratings"));
					movie.setTotalAttendance(rs.getInt("total_attendance"));
					movie.setGenre(rs.getString("genre"));
					movie.setContent(rs.getString("content"));
					movie.setSmallPicUrl(rs.getString("small_pic_url"));
					movie.setBigPicUrl(rs.getString("big_pic_url"));

					
					
					Screen screen = new Screen();

					screen.setScreenNo(rs.getInt("screen_no"));
					screen.setMovie(movie);
					screen.setScreenMode(rs.getString("screen_mode"));
					screen.setBuyDate(rs.getDate("buy_date"));
					screen.setScreenPrice(rs.getLong("screen_price"));

					screen.setSupplier(rs.getString("supplier"));
					screen.setStartDate(rs.getDate("start_date"));
					screen.setEndDate(rs.getDate("end_date"));

				
					Schedule schedule = new Schedule();
					schedule.setScheduleNo(rs.getInt("schedule_no"));
					schedule.setStartTime(rs.getTime("start_time").toLocalTime());
					schedule.setEndTime(rs.getTime("end_time").toLocalTime());
					schedule.setShowDate(rs.getDate("show_date"));
					schedule.setAuditorium(auditorium);
					schedule.setScreen(screen);
	
					
					bookStepOne = new BookStepOne(schedule, screen, movie, auditorium, theater);
					bookStepOnes.add(bookStepOne);
				} while (rs.next());
				return bookStepOnes;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

}
