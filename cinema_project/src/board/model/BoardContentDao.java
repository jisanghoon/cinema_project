package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;

public class BoardContentDao {
	private static BoardContentDao dao = new BoardContentDao();

	private BoardContentDao() {
		super();
	}

	public static BoardContentDao getInstance() {
		return dao;
	}

	public BoardContent boardContentByNo(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// select * from board_content where bo_cont_no = '1';
		String sql = "select * from board_content where board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Board board = new Board(no);
		
			BoardContent boardContent = null;
			if (rs.next()) {
				boardContent = new BoardContent(rs.getInt("bo_cont_no"), board, rs.getString("content"));
			
			}
			System.out.println("boardContentByNo 1=> " + boardContent);
			return boardContent;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 게시판 컨텐츠 등록 last_insert_id();
	public void insert(Connection conn, BoardContent boardContent) throws SQLException {
		PreparedStatement pstmt = null;
		// insert into board_content(board_no, content) values ( (select
		// max(last_insert_id()) from board) , '공지사항입니다.');
		String sql = "insert into board_content(board_no, content) values ( (select max(last_insert_id()) from board) , ?)";

		try {
			System.out.println(boardContent);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardContent.getContent());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, BoardContent boardContent) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update board_content set content = ? where bo_cont_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardContent.getContent());
			pstmt.setInt(2, boardContent.getNumber());
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void deleteContent(Connection conn, int boardNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from board_content where board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
}
