package information.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbc.JdbcUtil;

public class MovieInfoDao {
	private static MovieInfoDao dao = new MovieInfoDao();

	private MovieInfoDao() {
		super();
	}

	public static MovieInfoDao getInstance() {
		return dao;
	}

	public List<MovieInfo> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from movie";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				List<MovieInfo> movieInfos = new ArrayList<>();
				do {
					MovieInfo movieInfo = new MovieInfo(rs.getInt("movie_no"), rs.getString("title_kor"),
							rs.getString("title_eng"), rs.getString("director"), rs.getString("actors"),
							rs.getDate("release_date"), rs.getString("age_require"), rs.getInt("time_length"),
							rs.getString("country"), rs.getInt("ratings"), rs.getInt("total_attendance"),
							rs.getString("genre"), rs.getString("content"), rs.getString("small_pic_url"),
							rs.getString("big_pic_url"));
					movieInfos.add(movieInfo);
				} while (rs.next());
				return movieInfos;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public MovieInfo selectById(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from movie where movie_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			MovieInfo movieInfo = new MovieInfo();

			if (rs.next()) {
				movieInfo = convertProject(rs);
			}
			return movieInfo;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	private MovieInfo convertProject(ResultSet rs) throws SQLException {
		return new MovieInfo(rs.getInt("movie_no"), rs.getString("title_kor"), rs.getString("title_eng"),
				rs.getString("director"), rs.getString("actors"), rs.getDate("release_date"),
				rs.getString("age_require"), rs.getInt("time_length"), rs.getString("country"), rs.getInt("ratings"),
				rs.getInt("total_attendance"), rs.getString("genre"), rs.getString("content"),
				rs.getString("small_pic_url"), rs.getString("big_pic_url"));
	}
	
	
}
