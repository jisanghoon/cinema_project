package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Movie;
import jdbc.JdbcUtil;

public class MovieDao {
	public void insert(Connection conn, Movie movie) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into movie(title_kor, title_eng, director, actors, release_date, age_require, time_length, country, genre, content, small_pic_url, big_pic_url)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("dao insert before");
			pstmt.setString(1, movie.getKorTitle());
			pstmt.setString(2, movie.getEngTitle());
			pstmt.setString(3, movie.getDirector());
			pstmt.setString(4, movie.getActors());
			pstmt.setTimestamp(5, new Timestamp(movie.getReleaseDate().getTime()));
			pstmt.setString(6, movie.getAgeRequire());
			pstmt.setInt(7, movie.getTimeLength());
			pstmt.setString(8, movie.getCountry());
			pstmt.setString(9, movie.getGenre());
			pstmt.setString(10, movie.getContent());
			pstmt.setString(11, movie.getSmallPicUrl());
			pstmt.setString(12, movie.getBigPicUrl());
			System.out.println("dao insert after");
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public List<Movie> selectMovieList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from movie";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Movie> result = new ArrayList<>();
			Movie movie = null;

			while (rs.next()) {

				movie = new Movie();
				movie.setMovieNo(rs.getInt("movie_no"));
				movie.setKorTitle(rs.getString("title_kor"));
				movie.setEngTitle(rs.getString("title_eng"));
				movie.setActors(rs.getString("actors"));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseDate(rs.getDate("release_date"));
				movie.setAgeRequire(rs.getString("age_require"));
				movie.setTimeLength(rs.getInt("time_length"));
				movie.setCountry(rs.getString("country"));
				movie.setGenre(rs.getString("genre"));
				movie.setContent(rs.getString("content"));
				movie.setSmallPicUrl(rs.getString("small_pic_url"));
				movie.setBigPicUrl(rs.getString("big_pic_url"));

				result.add(movie);
			}

			return result;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Movie selectByMoiveId(Connection conn, int movieNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from movie where movie_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);

			rs = pstmt.executeQuery();
			Movie movie = null;

			if (rs.next()) {
				movie = new Movie();
				movie.setMovieNo(rs.getInt("movie_no"));
				movie.setKorTitle(rs.getString("title_kor"));
				movie.setEngTitle(rs.getString("title_eng"));
				movie.setActors(rs.getString("actors"));
				movie.setDirector(rs.getString("director"));
				movie.setReleaseDate(rs.getDate("release_date"));
				movie.setAgeRequire(rs.getString("age_require"));
				movie.setTimeLength(rs.getInt("time_length"));
				movie.setCountry(rs.getString("country"));
				movie.setGenre(rs.getString("genre"));
				movie.setContent(rs.getString("content"));
				movie.setSmallPicUrl(rs.getString("small_pic_url"));
				movie.setBigPicUrl(rs.getString("big_pic_url"));
			}
			return movie;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int movieNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from movie where movie_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, Movie movie) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update movie set title_kor=?, title_eng=?, actors=?, director=?, release_date=?, age_require=?, time_length=?, country=?, genre=?, content=?,  small_pic_url=? , big_pic_url=? "
				+ "where movie_no=?";

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, movie.getKorTitle());
			pstmt.setString(2, movie.getEngTitle());
			pstmt.setString(3, movie.getActors());
			pstmt.setString(4, movie.getDirector());
			pstmt.setTimestamp(5, new Timestamp(movie.getReleaseDate().getTime()));
			pstmt.setString(6, movie.getAgeRequire());
			pstmt.setInt(7, movie.getTimeLength());
			pstmt.setString(8, movie.getCountry());
			pstmt.setString(9, movie.getGenre());
			pstmt.setString(10, movie.getContent());
			pstmt.setString(11, movie.getSmallPicUrl());
			pstmt.setString(12, movie.getBigPicUrl());

			pstmt.setInt(13, movie.getMovieNo());

			int cknum = pstmt.executeUpdate();

			if (cknum > 0) {
				System.out.println("데이터 업데이트 성공");
			} else {
				System.out.println("데이터 업데이트 실패");
			}

		} finally {
			JdbcUtil.close(pstmt);
		}
	}


}
