package review.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import cinema.model.Member;
import cinema.model.Movie;
import jdbc.JdbcUtil;

public class ReviewDao {
	private static ReviewDao dao = new ReviewDao();

	private ReviewDao() {
		super();
	}

	public static ReviewDao getInstance() {
		return dao;
	}

	// 리뷰 등록
	public void reviewInsert(Connection conn, Review review) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into review(movie_no, user_no, content, reg_date, modi_date, like_cnt) values(?, ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, review.getMovie().getMovieNo());
			pstmt.setInt(2, review.getMember().getUserNo());
			pstmt.setString(3, review.getContent());

			pstmt.setTimestamp(4, new Timestamp((new Date()).getTime()));
			pstmt.setTimestamp(5, new Timestamp((new Date()).getTime()));
			pstmt.setInt(6, review.getCount());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 리뷰 삭제
	public void reviewDelete(Connection conn, int reviewNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from review where review_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public Review selectByReviewNo(Connection conn, int reviewNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review left join `member` on review.user_no=`member`.user_no where review_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewNo);
			rs = pstmt.executeQuery();
			Review review = null;

			if (rs.next()) {
				Writer writer = new Writer(rs.getString("user_id"), rs.getString("user_name"));
				Movie movie = new Movie();
				movie.setMovieNo(rs.getInt("movie_no"));

				Member member = new Member();
				member.setUserNo(rs.getInt("user_no"));

				review = new Review();

				review.setReviewNo(rs.getInt("review_no"));
				review.setWriter(writer);
				review.setContent(rs.getString("content"));
				review.setMovie(movie);
				review.setMember(member);
				review.setRegDate(rs.getDate("reg_date"));
				review.setModDate(rs.getDate("modi_date"));
				review.setCount(rs.getInt("like_cnt"));
			}
			return review;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 리뷰 수정
	public void reviewUpdate(Connection conn, Review review) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update review set content = ?, reg_date = ? where review_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, review.getContent());
			pstmt.setTimestamp(2, new Timestamp(review.getRegDate().getTime()));
			pstmt.setInt(3, review.getReviewNo());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 영화별 리뷰 목록
	public List<Review> selectByNo(Connection conn, int movieNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// select * from review where movie_no = '1';
		String sql = "select * from review left join `member` on review.user_no=`member`.user_no where movie_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				List<Review> reviewList = new ArrayList<>();
				do {

					Writer writer = new Writer(rs.getString("user_id"), rs.getString("user_name"));
					Movie movie = new Movie();
					movie.setMovieNo(rs.getInt("movie_no"));

					Member member = new Member();
					member.setUserNo(rs.getInt("user_no"));

					/*
					 * Review review = new Review(rs.getInt("review_no"),
					 * writer, rs.getString("content"), movieInfo, member,
					 * rs.getDate("register_date"), rs.getDate("modify_date"),
					 * rs.getInt("count"));
					 */

					Review review = new Review();
					review.setReviewNo(rs.getInt("review_no"));

					review.setWriter(writer);
					review.setContent(rs.getString("content"));
					review.setMovie(movie);
					review.setMember(member);
					review.setRegDate(rs.getDate("reg_date"));
					review.setModDate(rs.getDate("modi_date"));
					review.setCount(rs.getInt("like_cnt"));

					reviewList.add(review);
				} while (rs.next());
				return reviewList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Review> reviewList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from review left join `member` on review.user_no=`member`.user_no";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				List<Review> reviewList = new ArrayList<>();
				do {

					Writer writer = new Writer(rs.getString("user_id"), rs.getString("user_name"));
					Movie movie = new Movie();
					movie.setMovieNo(rs.getInt("movie_no"));

					Member member = new Member();
					member.setUserNo(rs.getInt("user_no"));

					Review review = new Review();

					review.setReviewNo(rs.getInt("review_no"));
					review.setWriter(writer);
					review.setContent(rs.getString("content"));
					review.setMovie(movie);
					review.setMember(member);
					review.setRegDate(rs.getDate("reg_date"));
					review.setModDate(rs.getDate("modi_date"));
					review.setCount(rs.getInt("like_cnt"));

					reviewList.add(review);
				} while (rs.next());
				return reviewList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 영화별 페이지 개수를 구하기 위한 전체 게시글 개수
	public int selectCount(Connection conn, int movieNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// select count(*) from review where movie_no = '1';
		String sql = "select count(*) from review where movie_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 영화별 리뷰 리스트
	public List<Review> selectByNoPaging(Connection conn, int startRow, int size, int movieNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// select * from review where movie_no = '1' order by review_no desc
		// limit 1, 10;

		String sql = "select * from review left join `member` on review.user_no=`member`.user_no where movie_no = ? order by review_no desc limit ?, ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, movieNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, size);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				List<Review> reviewList = new ArrayList<>();
				do {

					Writer writer = new Writer(rs.getString("user_id"), rs.getString("user_name"));
					Movie movie = new Movie();
					movie.setMovieNo(rs.getInt("movie_no"));

					Member member = new Member();
					member.setUserNo(rs.getInt("user_no"));

					Review review = new Review();

					review.setReviewNo(rs.getInt("review_no"));
					review.setWriter(writer);
					review.setContent(rs.getString("content"));
					review.setMovie(movie);
					review.setMember(member);
					review.setRegDate(rs.getDate("reg_date"));
					review.setModDate(rs.getDate("modi_date"));
					review.setCount(rs.getInt("like_cnt"));

					reviewList.add(review);

				} while (rs.next());
				return reviewList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
}
