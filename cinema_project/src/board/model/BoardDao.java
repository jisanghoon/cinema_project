package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jdbc.JdbcUtil;
import review.model.Writer;

public class BoardDao {
	private static BoardDao dao = new BoardDao();

	private BoardDao() {
		super();
	}

	public static BoardDao getInstance() {
		return dao;
	}

	// 게시글 등록
	public void insert(Connection conn, Board board) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into board(category, board_title, `reg_date`, user_id) values(?, ?, ?, ?)";
		System.out.println(board);
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setTimestamp(3, new Timestamp(board.getDate().getTime()));
			pstmt.setString(4, board.getWriter().getId());
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	// 게시글 수정
	public void update(Connection conn, Board board) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update board set category = ?, board_title = ?, `reg_date` = ?  where board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getCategory());
			pstmt.setString(2, board.getTitle());
			pstmt.setTimestamp(3, new Timestamp(board.getDate().getTime()));
			pstmt.setInt(4, board.getBoardNo());
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	// 게시글 삭제
	public void delete(Connection conn, int boardNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from board where board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<Board> boardList(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				List<Board> boards = new ArrayList<>();
				do {
					Writer writer = new Writer(rs.getString("id"));
					Board board = new Board(rs.getInt("board_no"), rs.getString("category"), rs.getString("board_title"),
							rs.getDate("date"), writer);
					boards.add(board);
				} while (rs.next());
				return boards;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public Board selectByNo(Connection conn, int no) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board where board_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			Board board = null;

			if (rs.next()) {
				Writer writer = new Writer(rs.getString("user_id"));
				board = new Board(rs.getInt("board_no"), rs.getString("category"), rs.getString("board_title"),
						rs.getDate("reg_date"), writer);
			}
			return board;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	// 페이징카운트
	public int selectCount(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from board";

		try {
			pstmt = conn.prepareStatement(sql);
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

	public List<Board> selectPasing(Connection conn, int startRow, int size) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from board order by board_no desc limit ?, ?;";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				List<Board> boardList = new ArrayList<>();
				do {
					Writer writer = new Writer(rs.getString("user_id"));
					Board board = new Board(rs.getInt("board_no"), rs.getString("category"), rs.getString("board_title"),
							rs.getDate("reg_date"), writer);
					boardList.add(board);
				} while (rs.next());
				return boardList;
			} else {
				return Collections.emptyList();
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

}
