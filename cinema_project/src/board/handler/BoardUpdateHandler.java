package board.handler;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardContent;
import board.model.BoardContentDao;
import board.model.BoardDao;
import cinema.model.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;
import review.model.Writer;

public class BoardUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			String no = req.getParameter("no");
			int noVal = Integer.parseInt(no);

			Connection conn = null;

			try {
				// 보더 내용
				conn = ConnectionProvider.getConnection();
				Board board = null;
				BoardDao boardDao = BoardDao.getInstance();
				board = boardDao.selectByNo(conn, noVal);
				req.setAttribute("board", board);
				// 보더 컨텐트
				conn = ConnectionProvider.getConnection();
				BoardContent boardContent = null;
				BoardContentDao boardContentDao = BoardContentDao.getInstance();
				boardContent = boardContentDao.boardContentByNo(conn, noVal);
				req.setAttribute("content", boardContent);

			} finally {
				JdbcUtil.close(conn);
			}
			return "WEB-INF/view/cus/board_update.jsp";
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			String no = req.getParameter("no");
			int noVal = Integer.parseInt(no);

			User user = (User) req.getSession().getAttribute("auth");
			Writer writer = new Writer(user.getId(), user.getName());

			Connection conn = null;

			try {
				// 보더 컨텐트
				conn = ConnectionProvider.getConnection();
				BoardContent boardContent = new BoardContent(noVal, req.getParameter("content"));
				BoardContentDao boardContentDao = BoardContentDao.getInstance();
				boardContentDao.update(conn, boardContent);

				// 보더 수정
				conn = ConnectionProvider.getConnection();
				Board board = new Board(noVal, req.getParameter("category"), req.getParameter("title"), new Date(),
						writer);
				BoardDao boardDao = BoardDao.getInstance();
				boardDao.update(conn, board);

			} finally {
				JdbcUtil.close(conn);
			}
			res.sendRedirect("boardlist.do");
		}

		return null;
	}

}
