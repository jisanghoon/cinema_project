package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Screen;
import jdbc.JdbcUtil;

public class ScreenDao {

	public void insert(Connection conn, Screen screen) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into screen( movie_no, screen_mode, buy_date, screen_price, supplier, start_date, end_date) values(?,?,?,?,?,?,?)";

		Timestamp startDate;
		Timestamp endDate;
		Timestamp buyDate;
		System.out.println("dao test1");
		if (screen.getStartDate() != null)
			startDate = new Timestamp(screen.getStartDate().getTime());
		else
			startDate = null;

		if (screen.getEndDate() != null)
			endDate = new Timestamp(screen.getEndDate().getTime());
		else
			endDate = null;

		if (screen.getBuyDate() != null)
			buyDate = new Timestamp(screen.getBuyDate().getTime());
		else
			buyDate = null;

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, screen.getMovie().getMovieNo());
			pstmt.setString(2, screen.getScreenMode());
			pstmt.setTimestamp(3, buyDate);
			pstmt.setLong(4, screen.getScreenPrice());
			pstmt.setString(5, screen.getSupplier());
			pstmt.setTimestamp(6, startDate);
			pstmt.setTimestamp(7, endDate);

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public List<Screen> selectScreenList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select screen.*, movie.title_kor from screen left join movie"
				+ " on screen.movie_no=movie.movie_no order by start_date DESC, movie_no, screen_no asc";

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Screen> result = new ArrayList<>();
			Screen screen;

			while (rs.next()) {

				screen = new Screen();
				screen.setScreenNo(rs.getInt("screen_no"));
				screen.setScreenMode(rs.getString("screen_mode"));
				screen.setBuyDate(rs.getDate("buy_date"));
				screen.setSupplier(rs.getString("supplier"));
				screen.setScreenPrice(rs.getLong("screen_price"));

				screen.setStartDate(rs.getDate("start_date"));
				screen.setEndDate(rs.getDate("end_date"));

				screen.setMovieNo(rs.getInt("movie_no"));
				screen.getMovie().setKorTitle(rs.getString("title_kor"));

				result.add(screen);
			}

			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int screenNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from screen where screen_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screenNo);

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public Screen selectByScreenId(Connection conn, int screenNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select screen.*, movie.title_kor from screen left join movie"
				+ " on screen.movie_no=movie.movie_no where screen_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, screenNo);

			rs = pstmt.executeQuery();
			Screen screen = null;

			if (rs.next()) {
				screen = new Screen();

				screen.setScreenNo(rs.getInt("screen_no"));
				screen.setScreenMode(rs.getString("screen_mode"));
				screen.setBuyDate(rs.getDate("buy_date"));
				screen.setSupplier(rs.getString("supplier"));
				screen.setScreenPrice(rs.getLong("screen_price"));

				screen.setStartDate(rs.getDate("start_date"));
				screen.setEndDate(rs.getDate("end_date"));

				screen.setMovieNo(rs.getInt("movie_no"));
				screen.getMovie().setKorTitle(rs.getString("title_kor"));
			}

			return screen;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, Screen screen) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update screen set movie_no=?, screen_mode=?, buy_date=?, screen_price=? , supplier=?, start_date=?, end_date=?  where screen_no=?";

		Timestamp startDate;
		Timestamp endDate;
		Timestamp buyDate;

		if (screen.getStartDate() != null)
			startDate = new Timestamp(screen.getStartDate().getTime());
		else
			startDate = null;

		if (screen.getEndDate() != null)
			endDate = new Timestamp(screen.getEndDate().getTime());
		else
			endDate = null;

		if (screen.getBuyDate() != null)
			buyDate = new Timestamp(screen.getBuyDate().getTime());
		else
			buyDate = null;

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, screen.getMovie().getMovieNo());
			pstmt.setString(2, screen.getScreenMode());
			pstmt.setTimestamp(3, buyDate);
			pstmt.setLong(4, screen.getScreenPrice());
			pstmt.setString(5, screen.getSupplier());
			pstmt.setTimestamp(6, startDate);
			pstmt.setTimestamp(7, endDate);
			pstmt.setInt(8, screen.getScreenNo());

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public List<Screen> selectScreenList(Connection conn, int theaterNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select sm.*"
				+ " from (select s.*, m.title_kor from screen s left join movie m on s.movie_no=m.movie_no) as sm"
				+ " where t.theater_no=? ";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			rs = pstmt.executeQuery();

			List<Screen> result = new ArrayList<>();
			Screen screen;

			while (rs.next()) {

				screen = new Screen();
				screen.setScreenNo(rs.getInt("screen_no"));
				screen.setScreenMode(rs.getString("screen_mode"));
				screen.setBuyDate(rs.getDate("buy_date"));
				screen.setSupplier(rs.getString("supplier"));
				screen.setScreenPrice(rs.getLong("screen_price"));

				screen.setStartDate(rs.getDate("start_date"));
				screen.setEndDate(rs.getDate("end_date"));

				screen.setMovieNo(rs.getInt("movie_no"));
				screen.getMovie().setKorTitle(rs.getString("title_kor"));

				result.add(screen);
			}

			return result;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
