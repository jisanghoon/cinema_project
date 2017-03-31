package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Schedule;
import jdbc.JdbcUtil;

public class ScheduleDao {

	public void insert(Connection conn, Schedule schedule) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into schedule( audi_no, screen_no, start_time, end_time, show_date,cate_time ) values(?,?,?,?,?,?)";

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, schedule.getAuditorium().getAudiNo());
			pstmt.setInt(2, schedule.getScreen().getScreenNo());
			pstmt.setTime(3, Time.valueOf(schedule.getStartTime()));
			pstmt.setTime(4, Time.valueOf(schedule.getEndTime()));
			pstmt.setTimestamp(5, new Timestamp(schedule.getShowDate().getTime()));
			pstmt.setString(6, schedule.getCateTime());

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public List<Schedule> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select * from schedule sch"
				+ " left join (select s.*,m.title_kor,m.small_pic_url,m.big_pic_url from screen s left join movie m on s.movie_no=m.movie_no) sr"
				+ " on sch.screen_no=sr.screen_no"
				+ " left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi"
				+ " on sch.audi_no=audi.audi_no";

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Schedule> scheduleList = new ArrayList<>();
			Schedule schedule = null;
			while (rs.next()) {

				schedule = new Schedule();
				schedule.setScheduleNo(rs.getInt("schedule_no"));
				schedule.setStartTime(rs.getTime("start_time").toLocalTime());
				schedule.setEndTime(rs.getTime("end_time").toLocalTime());
				schedule.setShowDate(rs.getDate("show_date"));
				schedule.setCateTime(rs.getString("cate_time"));

				schedule.setAuditoriumNo(rs.getInt("audi_no"));
				schedule.getAuditorium().setAudiName(rs.getString("audi_name"));
				schedule.getAuditorium().setFloor(rs.getString("floor"));

				schedule.getAuditorium().setTheaterNo(rs.getInt("theater_no"));
				schedule.getAuditorium().getTheater().setTheaterName(rs.getString("theater_name"));

				schedule.setScreenNo(rs.getInt("screen_no"));
				schedule.getScreen().setScreenMode(rs.getString("screen_mode"));

				schedule.getScreen().setMovieNo(rs.getInt("movie_no"));
				schedule.getScreen().getMovie().setKorTitle(rs.getString("title_kor"));
				schedule.getScreen().getMovie().setBigPicUrl(rs.getString("big_pic_url"));
				schedule.getScreen().getMovie().setSmallPicUrl(rs.getString("small_pic_url"));

				scheduleList.add(schedule);
				System.out.println(schedule);
			}
			return scheduleList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int scheduleNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from schedule where schedule_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleNo);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

	public Schedule selectById(Connection conn, int scheduleNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " select * from schedule sch"
				+ " left join (select s.*,m.title_kor from screen s left join movie m on s.movie_no=m.movie_no) sr"
				+ " on sch.screen_no=sr.screen_no"
				+ " left join (select a.*,t.theater_name from auditorium a left join theater t on a.theater_no=t.theater_no) audi"
				+ " on sch.audi_no=audi.audi_no where schedule_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scheduleNo);
			rs = pstmt.executeQuery();

			Schedule schedule = null;

			if (rs.next()) {

				schedule = new Schedule();
				schedule.setScheduleNo(rs.getInt("schedule_no"));
				schedule.setStartTime(rs.getTime("start_time").toLocalTime());
				schedule.setEndTime(rs.getTime("end_time").toLocalTime());
				schedule.setShowDate(rs.getDate("show_date"));
				schedule.setCateTime(rs.getString("cate_time"));

				schedule.setAuditoriumNo(rs.getInt("audi_no"));
				schedule.getAuditorium().setAudiName(rs.getString("audi_name"));
				schedule.getAuditorium().setFloor(rs.getString("floor"));

				schedule.getAuditorium().setTheaterNo(rs.getInt("theater_no"));
				schedule.getAuditorium().getTheater().setTheaterName(rs.getString("theater_name"));

				schedule.setScreenNo(rs.getInt("screen_no"));

				schedule.getScreen().setMovieNo(rs.getInt("movie_no"));
				schedule.getScreen().getMovie().setKorTitle(rs.getString("title_kor"));

			}
			return schedule;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, Schedule schedule) throws SQLException {
		PreparedStatement pstmt = null;

		String sql = "update schedule set audi_no=?, screen_no=?, start_time=?, end_time=? , show_date=?, cate_time=? where schedule_no=?";

		try {

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, schedule.getAuditorium().getAudiNo());
			pstmt.setInt(2, schedule.getScreen().getScreenNo());
			pstmt.setTime(3, Time.valueOf(schedule.getStartTime()));
			pstmt.setTime(4, Time.valueOf(schedule.getEndTime()));
			pstmt.setTimestamp(5, new Timestamp(schedule.getShowDate().getTime()));
			pstmt.setString(6, schedule.getCateTime());
			pstmt.setInt(7, schedule.getScheduleNo());

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

}
