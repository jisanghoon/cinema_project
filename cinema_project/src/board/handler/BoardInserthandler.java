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
import mvc.controller.CommandHandler;
import review.model.Writer;

public class BoardInserthandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return "WEB-INF/view/cus/board_insert.jsp";
		} else if (req.getMethod().equalsIgnoreCase("post")) {

			try (Connection conn = ConnectionProvider.getConnection()) {
				User user = (User) req.getSession().getAttribute("auth");
				Writer writer = new Writer(user.getId(), user.getName());

				Board board = new Board(req.getParameter("category"), req.getParameter("title"), new Date(), writer);
				System.out.println(board);
				BoardDao boardDao = BoardDao.getInstance();
				boardDao.insert(conn, board);

				BoardContent boardContent = new BoardContent(req.getParameter("content"));
				BoardContentDao boardContentDao = BoardContentDao.getInstance();
				boardContentDao.insert(conn, boardContent);
				
				
			}
			return "boardlist.do";
		}

		return null;

	}

}
