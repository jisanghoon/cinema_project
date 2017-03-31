package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Theater;
import jdbc.JdbcUtil;

public class TheaterDao {
	public void insert(Connection conn, Theater theater) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into theater(theater_name,theater_addr_num,theater_addr_street,theater_manager,theater_tel) values(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, theater.getTheaterName().trim());
			pstmt.setString(2, theater.getTheaterAddrNum().trim());
			pstmt.setString(3, theater.getTheaterAddrStr().trim());
			pstmt.setString(4, theater.getTheaterManager().trim());
			pstmt.setString(5, theater.getTheaterTel().trim());

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public List<Theater> selectTheaterList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from theater";

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Theater> theaterList = new ArrayList<>();
			Theater theater;

			while (rs.next()) {
				theater = new Theater();

				theater.setTheaterNo(rs.getInt("theater_no"));
				theater.setTheaterManager(rs.getString("theater_manager"));
				theater.setTheaterAddrNum(rs.getString("theater_addr_num"));
				theater.setTheaterAddrStr(rs.getString("theater_addr_street"));
				theater.setTheaterName(rs.getString("theater_name"));
				theater.setTheaterTel(rs.getString("theater_tel"));

				theaterList.add(theater);
			}

			return theaterList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	public Theater selectByTheaterId(Connection conn, int theaterNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from theater where theater_no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			rs = pstmt.executeQuery();

			Theater theater = null;

			if (rs.next()) {
				theater = new Theater();

				theater.setTheaterNo(rs.getInt("theater_no"));
				theater.setTheaterManager(rs.getString("theater_manager"));
				theater.setTheaterAddrNum(rs.getString("theater_addr_num"));
				theater.setTheaterAddrStr(rs.getString("theater_addr_street"));
				theater.setTheaterName(rs.getString("theater_name"));
				theater.setTheaterTel(rs.getString("theater_tel"));
			}
			return theater;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int theaterNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from theater where theater_no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, Theater theater) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update theater set theater_name=?, theater_addr_num=?,theater_addr_street=? ,theater_manager=?, theater_tel=? "
				+ "where theater_no=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, theater.getTheaterName().trim());
			pstmt.setString(2, theater.getTheaterAddrNum().trim());
			pstmt.setString(3, theater.getTheaterAddrStr().trim());
			pstmt.setString(3, theater.getTheaterManager().trim());
			pstmt.setString(4, theater.getTheaterTel().trim());
			pstmt.setInt(5, theater.getTheaterNo());

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public boolean findTheater(Connection conn, Theater theater) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from theater where theater_name=? and  theater_tel=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, theater.getTheaterName().trim());
			pstmt.setString(2, theater.getTheaterTel().trim());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				return true;
			}

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return false;
	}

}
