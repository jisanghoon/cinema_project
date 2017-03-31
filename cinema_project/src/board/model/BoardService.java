package board.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;

public class BoardService {
	private int size = 10;

	public BoardPage getBoardPage(int pageNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			BoardDao boardDao = BoardDao.getInstance();
			int total = boardDao.selectCount(conn);
			List<Board> boards = boardDao.selectPasing(conn, (pageNum - 1) * size, size);

			return new BoardPage(total, pageNum, size, boards);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getBoardPage");
		}

		return null;

	}

}
