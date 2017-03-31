package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cinema.model.Member;
import jdbc.JdbcUtil;

public class MemberDao {

	public void insert(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		// insert into member(memberid, pasword, name, mobile, email, birth,
		// gender, register) values('test01', '1234', '이준호', '01094798043',
		// 'joonho@gamil.com', '1988-08-02', '남자', '2017-03-15');
		String sql = "insert into `member`(`user_id`,`user_pw`,`user_name`,`user_phone`,`user_email`,`date_of_birth`,`state_of_join`,`user_gender`,`reg_date` ) "
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserId());
			pstmt.setString(2, member.getUserPw());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getUserPhone());
			pstmt.setString(5, member.getUserEmail());
			pstmt.setTimestamp(6, new Timestamp(member.getDateOfBirth().getTime()));
			pstmt.setBoolean(7, member.isStateOfJoin());
			pstmt.setString(8, member.getUserGender());
			pstmt.setTimestamp(9, new Timestamp(member.getRegDate().getTime()));
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 아이디 중복 검사
	public boolean checkID(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member where user_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (id == null || rs.next() == true) {
				return false;
			} else {
				return true;
			}
		} finally {
			if (rs != null)
				JdbcUtil.close(rs);
			if (pstmt != null)
				JdbcUtil.close(pstmt);
		}
	}

	public List<Member> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<Member> memberList = new ArrayList<>();
			Member member = null;
			while (rs.next()) {
				member = new Member();
				member.setUserNo(rs.getInt("user_no"));
				member.setUserId(rs.getString("user_id"));
				member.setUserPw(rs.getString("user_pw"));
				member.setUserName(rs.getString("user_name"));
				member.setUserPhone(rs.getString("user_phone"));
				member.setUserEmail(rs.getString("user_email"));
				member.setDateOfBirth(rs.getDate("date_of_birth"));
				member.setStateOfJoin(rs.getBoolean("state_of_join"));
				member.setUserGender(rs.getString("user_gender"));
				member.setRegDate(rs.getDate("reg_date"));

				memberList.add(member);

			}
			return memberList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int userNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from member where user_no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// select * from member where memberid = 'test';
		String sql = "select * from member where user_id = ?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member();
				member.setUserNo(rs.getInt("user_no"));
				member.setUserId(rs.getString("user_id"));
				member.setUserPw(rs.getString("user_pw"));
				member.setUserName(rs.getString("user_name"));
				member.setUserPhone(rs.getString("user_phone"));
				member.setUserEmail(rs.getString("user_email"));
				member.setDateOfBirth(rs.getDate("date_of_birth"));
				member.setStateOfJoin(rs.getBoolean("state_of_join"));
				member.setUserGender(rs.getString("user_gender"));
				member.setRegDate(rs.getDate("reg_date"));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
