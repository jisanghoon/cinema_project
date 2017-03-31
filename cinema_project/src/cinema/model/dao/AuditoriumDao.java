package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Auditorium;
import jdbc.JdbcUtil;

public class AuditoriumDao {

	public void insert(Connection conn, Auditorium auditorium) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "insert into auditorium(audi_name,audi_type,floor,theater_no) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, auditorium.getAudiName());
			pstmt.setString(2, auditorium.getAudiType());
			pstmt.setString(3, auditorium.getFloor());
			pstmt.setInt(4, auditorium.getTheater().getTheaterNo());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<Auditorium> selectAuditoriumList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select a.*, t.theater_no, t.theater_name from auditorium as a left join theater as t on a.theater_no =t.theater_no order by a.audi_no";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Auditorium> auditoriumList = new ArrayList<>();
			Auditorium auditorium;

			while (rs.next()) {

				auditorium = new Auditorium();
				auditorium.setAudiNo(rs.getInt("audi_no"));
				auditorium.setAudiName(rs.getString("audi_name"));
				auditorium.setAudiType(rs.getString("audi_type"));
				auditorium.setFloor(rs.getString("floor"));
				auditorium.setTheaterNo(rs.getInt("theater_no"));
				auditorium.getTheater().setTheaterName(rs.getString("theater_name"));
				auditoriumList.add(auditorium);
			}

			return auditoriumList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Auditorium selectById(Connection conn, int audiNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {

			sql = "select a.*, t.theater_name from auditorium as a left join theater as t on a.theater_no =t.theater_no where audi_no=?";

			/*
			 * sql =
			 * "select audi.*, th.theater_name, seating.* from auditorium as audi "
			 * + " left join seating on audi.audi_no=seating.audi_no " +
			 * " left join theater th on audi.theater_no=th.theater_no where audi.audi_no=?"
			 * ;
			 */
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, audiNo);
			rs = pstmt.executeQuery();

			Auditorium auditorium = null;
			if (rs.next()) {
				System.out.println(rs.getInt("audi_no"));
				System.out.println(rs.getInt("theater_no"));

				auditorium = new Auditorium();
				auditorium.setAudiNo(rs.getInt("audi_no"));
				auditorium.setAudiName(rs.getString("audi_name"));
				auditorium.setAudiType(rs.getString("audi_type"));
				auditorium.setFloor(rs.getString("floor"));
				auditorium.setTheaterNo(rs.getInt("theater_no"));
				auditorium.getTheater().setTheaterName(rs.getString("theater_name"));

			}
			return auditorium;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int audiNo) throws SQLException {
		PreparedStatement pstmt = null;
		try {
			String sql = "delete from auditorium where audi_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, audiNo);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<Auditorium> selectByTheaterNo(Connection conn, int theaterNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		try {
			sql = "select a.*, t.theater_no, t.theater_name from auditorium as a left join theater as t on a.theater_no =t.theater_no where a.theater_no=? order by a.audi_no ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, theaterNo);
			rs = pstmt.executeQuery();

			List<Auditorium> auditoriumList = new ArrayList<>();
			Auditorium auditorium;

			while (rs.next()) {

				auditorium = new Auditorium();
				auditorium.setAudiNo(rs.getInt("audi_no"));
				auditorium.setAudiName(rs.getString("audi_name"));
				auditorium.setAudiType(rs.getString("audi_type"));
				auditorium.setFloor(rs.getString("floor"));
				auditorium.setTheaterNo(rs.getInt("theater_no"));
				auditorium.getTheater().setTheaterName(rs.getString("theater_name"));
				auditoriumList.add(auditorium);
			}
			return auditoriumList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, Auditorium auditorium) throws SQLException {
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "update auditorium set audi_name=?, audi_type=?, floor=?, theater_no=? where audi_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, auditorium.getAudiName());
			pstmt.setString(2, auditorium.getAudiType());
			pstmt.setString(3, auditorium.getFloor());
			pstmt.setInt(4, auditorium.getTheater().getTheaterNo());
			pstmt.setInt(5, auditorium.getAudiNo());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public List<Auditorium> selectAudiForSeatCnt(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select audi.*,th.theater_name, count(if(seat_name='empty',null,1)) as seat_cnt from auditorium as audi "
					+ " left join seating on audi.audi_no=seating.audi_no "
					+ " left join theater th on audi.theater_no=th.theater_no  group by audi.audi_no";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Auditorium> auditoriumList = new ArrayList<>();
			Auditorium auditorium;

			while (rs.next()) {

				auditorium = new Auditorium();
				auditorium.setAudiNo(rs.getInt("audi_no"));
				auditorium.setAudiName(rs.getString("audi_name"));
				auditorium.setAudiType(rs.getString("audi_type"));
				auditorium.setSeatTotalCnt(rs.getInt("seat_cnt"));

				auditorium.setTheaterNo(rs.getInt("theater_no"));
				auditorium.getTheater().setTheaterName(rs.getString("theater_name"));

				auditoriumList.add(auditorium);
			}

			return auditoriumList;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
