package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Seat;
import jdbc.JdbcUtil;

public class SeatDao {

	public void insertData(Connection conn, List<Seat> seatList) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into seating (seat_no,row,col,seat_name,audi_no) values(?,?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(sql);
			conn.setAutoCommit(false);

			for (Seat seat : seatList) {
				pstmt.setInt(1, seat.getSeatNo());
				pstmt.setInt(2, seat.getRow());
				pstmt.setInt(3, seat.getCol());
				pstmt.setString(4, seat.getSeatName());
				pstmt.setInt(5, seat.getAuditorium().getAudiNo());
				pstmt.addBatch();
				pstmt.clearParameters();
			}

			pstmt.executeBatch();
			conn.commit();

		} catch (SQLException e) {
			conn.rollback();
			System.out.println("좌석 데이터 입력이 취소 되었습니다.");

		} finally {
			conn.setAutoCommit(true);
			JdbcUtil.close(pstmt);
		}

	}

	public List<Seat> selectSeatList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select s.*, au.audi_name, au.theater_no, au.theater_name " + " from seating s left join"
				+ " (select a.*, t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) as au"
				+ " on s.audi_no=au.audi_no";

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Seat> seatList = new ArrayList<>();
			Seat seat = null;

			while (rs.next()) {
				seat = new Seat();

				seat.setCol(rs.getInt("col"));
				seat.setRow(rs.getInt("row"));
				seat.setSeatNo(rs.getInt("seat_no"));
				seat.setSeatName(rs.getString("seat_name"));

				seat.setAuditoriumNo(rs.getInt("audi_no"));
				seat.getAuditorium().setAudiName(rs.getString("audi_name"));

				seat.getAuditorium().setTheaterNo(rs.getInt("theater_no"));
				seat.getAuditorium().getTheater().setTheaterName(rs.getString("theater_name"));

				seatList.add(seat);
			}
			return seatList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int seatNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from seats where seat_no=?";
		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNo);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public Seat selectBySeatId(Connection conn, int seatNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select s.*, au.audi_name, au.theater_no, au.theater_name from seating s left join"
				+ " (select a.*, t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) as au"
				+ " on s.audi_no=au.audi_no where seat_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seatNo);
			rs = pstmt.executeQuery();

			Seat seat = null;

			if (rs.next()) {
				seat = new Seat();

				seat.setCol(rs.getInt("col"));
				seat.setRow(rs.getInt("row"));
				seat.setSeatNo(rs.getInt("seat_no"));

				seat.setAuditoriumNo(rs.getInt("audi_no"));
				seat.getAuditorium().setAudiName(rs.getString("audi_name"));

				seat.getAuditorium().setTheaterNo(rs.getInt("theater_no"));
				seat.getAuditorium().getTheater().setTheaterName(rs.getString("theater_name"));

			}
			return seat;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Seat> selectByaudiNo(Connection conn, int audiNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select s.*, au.audi_name, au.theater_name, au.theater_no  from seating s "
				+ " left join  (select audi.*, th.theater_name from auditorium as audi left join theater as th on audi.theater_no=th.theater_no) as au "
				+ " on s.audi_no=au.audi_no  where s.audi_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, audiNo);
			rs = pstmt.executeQuery();

			List<Seat> seatList = new ArrayList<>();

			Seat seat = null;

			while (rs.next()) {
				seat = new Seat();

				seat.setSeatNo(rs.getInt("seat_no"));
				seat.setCol(rs.getInt("col"));
				seat.setRow(rs.getInt("row"));
				seat.setSeatName(rs.getString("seat_name"));

				seat.setAuditoriumNo(rs.getInt("audi_no"));
				seat.getAuditorium().setAudiName(rs.getString("audi_name"));

				seat.getAuditorium().setTheaterNo(rs.getInt("theater_no"));
				seat.getAuditorium().getTheater().setTheaterName(rs.getString("theater_name"));

				seatList.add(seat);
			}
			return seatList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	/*
	 * public void update(Connection conn, Seat seat) throws SQLException {
	 * PreparedStatement pstmt = null; String sql = null; try { sql =
	 * "update seats set row=?, col=?, audi_no=? where seat_no=?"; pstmt =
	 * conn.prepareStatement(sql);
	 * 
	 * pstmt.setInt(1, seat.getRow()); pstmt.setInt(2, seat.getCol());
	 * pstmt.setInt(3, seat.getAuditorium().getAudiNo()); pstmt.setInt(4,
	 * seat.getSeatNo());
	 * 
	 * pstmt.executeUpdate();
	 * 
	 * } finally { JdbcUtil.close(pstmt); } }
	 */

}
