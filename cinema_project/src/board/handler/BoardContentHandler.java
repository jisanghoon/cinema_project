package board.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.Board;
import board.model.BoardContent;
import board.model.BoardContentDao;
import board.model.BoardDao;
import cinema.model.User;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class BoardContentHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		String no = req.getParameter("no");
		int noVal = Integer.parseInt(no);

		try {
			conn = ConnectionProvider.getConnection();
			BoardDao boardDao = BoardDao.getInstance();
			Board board = null;
			board = boardDao.selectByNo(conn, noVal);
			req.setAttribute("board", board);
			
			conn = ConnectionProvider.getConnection();
			BoardContentDao boardContentDao = BoardContentDao.getInstance();
			BoardContent boardContent = null;
			boardContent = boardContentDao.boardContentByNo(conn, noVal);
			req.setAttribute("content", boardContent);
			
			//관리자
			User user = (User) req.getSession().getAttribute("auth");
			req.setAttribute("admin", user);
		} finally {

		}
		return "WEB-INF/view/cus/boardContent.jsp";
	}

}
