package member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbc.JdbcUtil;

public class MemberDao {
	private static MemberDao dao = null;

	public static MemberDao getInstance() {
		if (dao == null) {
			dao = new MemberDao();
		}
		return dao;
	}

	/*
	 * `user_no` INT NOT NULL, -- 고객번호 `user_id` VARCHAR(30) NOT NULL, -- 아이디
	 * `user_pw` VARCHAR(30) NOT NULL, -- 패스워드 `user_name` VARCHAR(30) NOT NULL,
	 * -- 이름 `user_phone` VARCHAR(30) NOT NULL, -- 전화번호 `user_email` VARCHAR(50)
	 * NULL, -- 메일 `date_of_birth` DATETIME NOT NULL, -- 생년월일 `state_of_join`
	 * BOOLEAN NULL, -- 가입상태 `user_gender` VARCHAR(2) NULL, -- 성별 `reg_date`
	 */

	public void memberInsert(Connection conn, Member member) {
		PreparedStatement pstmt = null;
		// insert into member(memberid, pasword, name, mobile, email, birth,
		// gender, register) values('test01', '1234', '이준호', '01094798043',
		// 'joonho@gamil.com', '1988-08-02', '남자', '2017-03-15');
		String sql = "insert into member(user_id, user_pw, user_name, user_phone, user_email, date_of_birth, state_of_join, user_gender,reg_date) values(?, ?, ?, ?, ?,?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getMobile());
			pstmt.setString(5, member.getEmail());
			pstmt.setTimestamp(6, new Timestamp(member.getBirth().getTime()));
			pstmt.setBoolean(7, member.isStateOfJoin());
			pstmt.setString(8, member.getGender());
			pstmt.setTimestamp(9, new Timestamp(member.getRegister().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 회원 정보 수정
	public void memberUpdate(Connection conn, Member member, String id) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update `member` set user_pw = ?, user_phone = ?, user_email = ? where user_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getPassword());
			pstmt.setString(2, member.getMobile());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, id);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 회원 탈퇴
	public void memberDelete(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from `member` where user_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
				member = new Member(rs.getInt("user_no"), rs.getString("user_id"), rs.getString("user_pw"),
						rs.getString("user_name"), rs.getString("user_phone"), rs.getString("user_email"),
						rs.getDate("date_of_birth"), rs.getString("user_gender"), rs.getDate("reg_date"));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public List<Member> selectAll(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from member;";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				List<Member> memberList = new ArrayList<>();
				do {
					member = new Member(rs.getInt("user_no"), rs.getString("user_id"), rs.getString("user_pw"),
							rs.getString("user_name"), rs.getString("user_phone"), rs.getString("user_email"),
							rs.getDate("date_of_birth"), rs.getString("user_gender"), rs.getDate("reg_date"));
					memberList.add(member);
				} while (rs.next());
				return memberList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
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

}
