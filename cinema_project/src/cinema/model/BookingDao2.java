/*package cinema.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cinema.model.Auditorium;
import cinema.model.BookStepOne;
import cinema.model.BookTotal;
import cinema.model.Booking2;
import cinema.model.Movie;
import cinema.model.Schedule;
import cinema.model.Screen;
import cinema.model.Seat;
import cinema.model.Theater;
import cinema.model.TicketPrice;
import jdbc.JdbcUtil;

public class BookingDao2 {
	private static BookingDao2 dao = new BookingDao2();

	public BookingDao2() {
		super();
	}

	public static BookingDao2 getInstance() {
		return dao;
	}

	public List<BookTotal> listAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select schedule.*, t2.*, bookinfo1.* from schedule "
				+ "left join (select seating.*, t1.audi_name, audi_type, floor, theater_no, theater_name from seating "
				+ "left join (select auditorium.*, theater.theater_name from auditorium left join theater on auditorium.theater_no = theater.theater_no) t1 on seating.audi_no = t1.audi_no) t2 on schedule.schedule_no = t2.audi_no "
				+ "left join  bookinfo1 on schedule.screen_no = bookinfo1.screen_no";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			BookTotal bookTotal = null;
			if (rs.next()) {
				List<BookTotal> bookTotals = new ArrayList<>();
				do {
					Theater theater = new Theater(rs.getInt("theater_no"), rs.getString("theater_name"));
					Auditorium auditorium = new Auditorium(rs.getString("audi_name"), rs.getString("audi_type"),
							rs.getString("floor"), theater);
					Movie movie = new Movie(rs.getInt("movie_no"), rs.getString("title_kor"), rs.getString("title_eng"),
							rs.getString("director"), rs.getString("actors"), rs.getDate("release_date"),
							rs.getString("age_require"), rs.getInt("time_length"), rs.getString("country"),
							rs.getInt("ratings"), rs.getInt("total_attendance"), rs.getString("genre"),
							rs.getString("content"), rs.getString("small_pic_url"), rs.getString("big_pic_url"));
					Screen screen = new Screen(rs.getInt("screen_no"), movie, rs.getString("screen_mode"),
							rs.getDate("buy_date"), rs.getLong("screen_price"), rs.getString("supplier"),
							rs.getDate("start_date"), rs.getDate("end_date"));
					Schedule schedule = new Schedule(rs.getInt("schedule_no"), rs.getTime("start_time").toLocalTime(),
							rs.getTime("end_time").toLocalTime(), rs.getDate("show_date"), auditorium, screen);
					Seat seat = new Seat(rs.getInt("seat_no"), rs.getInt("row"), rs.getInt("col"),
							rs.getString("seat_name"), auditorium);

					bookTotal = new BookTotal(schedule, seat, auditorium, theater, screen, movie);
					bookTotals.add(bookTotal);
				} while (rs.next());
				return bookTotals;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<BookStepOne> stepOneList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from schedule sch left join (select s.*,m.title_kor, title_eng, actors, director, release_date, age_require, time_length, country, ratings, total_attendance, genre, content, small_pic_url, big_pic_url from screen s left join movie m on s.movie_no=m.movie_no) sr on sch.screen_no=sr.screen_no left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi on sch.audi_no=audi.audi_no group by movie_no";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			BookStepOne bookStepOne = null;
			if (rs.next()) {
				List<BookStepOne> bookStepOnes = new ArrayList<>();
				do {
					Theater theater = new Theater(rs.getInt("theater_no"), rs.getString("theater_name"));
					Auditorium auditorium = new Auditorium(rs.getString("audi_name"), rs.getString("audi_type"),
							rs.getString("floor"), theater);
					Movie movie = new Movie(rs.getInt("movie_no"), rs.getString("title_kor"), rs.getString("title_eng"),
							rs.getString("director"), rs.getString("actors"), rs.getDate("release_date"),
							rs.getString("age_require"), rs.getInt("time_length"), rs.getString("country"),
							rs.getInt("ratings"), rs.getInt("total_attendance"), rs.getString("genre"),
							rs.getString("content"), rs.getString("small_pic_url"), rs.getString("big_pic_url"));
					Screen screen = new Screen(rs.getInt("screen_no"), movie, rs.getString("screen_mode"),
							rs.getDate("buy_date"), rs.getLong("screen_price"), rs.getString("supplier"),
							rs.getDate("start_date"), rs.getDate("end_date"));
					Schedule schedule = new Schedule(rs.getInt("schedule_no"), rs.getTime("start_time").toLocalTime(),
							rs.getTime("end_time").toLocalTime(), rs.getDate("show_date"), auditorium, screen);
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

	public List<BookStepOne> selectByNo(Connection conn, int movieNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from schedule sch left join (select s.*,m.title_kor, title_eng, actors, director, release_date, age_require, time_length, country, ratings, total_attendance, genre, content, small_pic_url, big_pic_url from screen s left join movie m on s.movie_no=m.movie_no) sr on sch.screen_no=sr.screen_no left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi on sch.audi_no=audi.audi_no where movie_no= ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			BookStepOne bookStepOne = null;
			if (rs.next()) {
				List<BookStepOne> bookStepOnes = new ArrayList<>();
				do {
					Theater theater = new Theater(rs.getInt("theater_no"), rs.getString("theater_name"));
					Auditorium auditorium = new Auditorium(rs.getString("audi_name"), rs.getString("audi_type"),
							rs.getString("floor"), theater);
					Movie movie = new Movie(rs.getInt("movie_no"), rs.getString("title_kor"), rs.getString("title_eng"),
							rs.getString("director"), rs.getString("actors"), rs.getDate("release_date"),
							rs.getString("age_require"), rs.getInt("time_length"), rs.getString("country"),
							rs.getInt("ratings"), rs.getInt("total_attendance"), rs.getString("genre"),
							rs.getString("content"), rs.getString("small_pic_url"), rs.getString("big_pic_url"));
					Screen screen = new Screen(rs.getInt("screen_no"), movie, rs.getString("screen_mode"),
							rs.getDate("buy_date"), rs.getLong("screen_price"), rs.getString("supplier"),
							rs.getDate("start_date"), rs.getDate("end_date"));
					Schedule schedule = new Schedule(rs.getInt("schedule_no"), rs.getTime("start_time").toLocalTime(),
							rs.getTime("end_time").toLocalTime(), rs.getDate("show_date"), auditorium, screen);
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
					Theater theater = new Theater(rs.getInt("theater_no"), rs.getString("theater_name"));
					Auditorium auditorium = new Auditorium(rs.getString("audi_name"), rs.getString("audi_type"),
							rs.getString("floor"), theater);
					Movie movie = new Movie(rs.getInt("movie_no"), rs.getString("title_kor"), rs.getString("title_eng"),
							rs.getString("director"), rs.getString("actors"), rs.getDate("release_date"),
							rs.getString("age_require"), rs.getInt("time_length"), rs.getString("country"),
							rs.getInt("ratings"), rs.getInt("total_attendance"), rs.getString("genre"),
							rs.getString("content"), rs.getString("small_pic_url"), rs.getString("big_pic_url"));
					Screen screen = new Screen(rs.getInt("screen_no"), movie, rs.getString("screen_mode"),
							rs.getDate("buy_date"), rs.getLong("screen_price"), rs.getString("supplier"),
							rs.getDate("start_date"), rs.getDate("end_date"));
					Schedule schedule = new Schedule(rs.getInt("schedule_no"), rs.getTime("start_time").toLocalTime(),
							rs.getTime("end_time").toLocalTime(), rs.getDate("show_date"), auditorium, screen);
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

	// 시간 선택
	public List<BookStepOne> selMovie(Connection conn, int theaterNo, int movieNo, String date) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from schedule sch "
				+ "left join (select s.*,m.title_kor, title_eng, actors, director, release_date, age_require, time_length, country, ratings, total_attendance, genre, content, small_pic_url, big_pic_url from screen s left join movie m on s.movie_no=m.movie_no) sr on sch.screen_no=sr.screen_no "
				+ "left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi on sch.audi_no=audi.audi_no where theater_no = ? and movie_no = ? and show_date = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			pstmt.setInt(2, movieNo);
			pstmt.setString(3, date);
			rs = pstmt.executeQuery();
			BookStepOne bookStepOne = null;
			if (rs.next()) {
				List<BookStepOne> bookStepOnes = new ArrayList<>();
				do {
					Theater theater = new Theater(rs.getInt("theater_no"), rs.getString("theater_name"));
					
					Auditorium auditorium = new Auditorium(rs.getInt("audi_no"), rs.getString("audi_name"),
							rs.getString("audi_type"), rs.getString("floor"), theater);
					
					Movie movie = new Movie(rs.getInt("movie_no"), rs.getString("title_kor"), rs.getString("title_eng"),
							rs.getString("director"), rs.getString("actors"), rs.getDate("release_date"),
							rs.getString("age_require"), rs.getInt("time_length"), rs.getString("country"),
							rs.getInt("ratings"), rs.getInt("total_attendance"), rs.getString("genre"),
							rs.getString("content"), rs.getString("small_pic_url"), rs.getString("big_pic_url"));
					
					Screen screen = new Screen(rs.getInt("screen_no"), movie, rs.getString("screen_mode"),
							rs.getDate("buy_date"), rs.getLong("screen_price"), rs.getString("supplier"),
							rs.getDate("start_date"), rs.getDate("end_date"));
					
					Schedule schedule = new Schedule(rs.getInt("schedule_no"), rs.getTime("start_time").toLocalTime(),
							rs.getTime("end_time").toLocalTime(), rs.getDate("show_date"), auditorium, screen);
					
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

	// 좌석 선택
	public List<Seat> selSeat(Connection conn, int audiNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from seating where audi_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, audiNo);
			rs = pstmt.executeQuery();
			Seat seat = null;
			if (rs.next()) {
				List<Seat> seats = new ArrayList<>();
				do {
					Auditorium auditorium = new Auditorium(rs.getInt("audi_no"));
					seat = new Seat(rs.getInt("seat_no"), rs.getInt("row"), rs.getInt("col"), rs.getString("seat_name"),
							auditorium);
					seats.add(seat);
				} while (rs.next());
				return seats;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Map<String, Integer> selectForSeatCnt(Connection conn, int audiNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(distinct row) as rowCnt, count(distinct col) as colCnt from seating where audi_no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, audiNo);
			rs = pstmt.executeQuery();
			Map<String, Integer> seatMap = new HashMap<>();
			if (rs.next()) {

				seatMap.put("rowCnt", rs.getInt("rowCnt"));
				seatMap.put("colCnt", rs.getInt("colCnt"));

			}
			return seatMap;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	public List<TicketPrice> selPrice(Connection conn, String cateTime) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ticket_price where cate_time = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cateTime);
			rs = pstmt.executeQuery();
			TicketPrice ticketPrice = null;
			if (rs.next()) {
				List<TicketPrice> ticketPrices = new ArrayList<>();
				do {
					ticketPrice = new TicketPrice(rs.getInt("price_no"), rs.getString("cate_day"),
							rs.getString("cate_time"), rs.getString("cate_audi"), rs.getString("cate_Screen"),
							rs.getString("cate_seat"), rs.getString("cate_age"), rs.getInt("price"));
					ticketPrices.add(ticketPrice);
				} while (rs.next());
				return ticketPrices;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Booking2 insertBook(Connection conn, int cusNo, int priceNo, int scheduleNo) {
		return null;

	}

}
*/