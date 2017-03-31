package board.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardContentDao;
import board.model.BoardDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class BoardDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		String no = req.getParameter("no");
		int noVal = Integer.parseInt(no);

		try {
			conn = ConnectionProvider.getConnection();
			BoardContentDao boardContentDao = BoardContentDao.getInstance();
			boardContentDao.deleteContent(conn, noVal);
			
			conn = ConnectionProvider.getConnection();
			BoardDao boardDao = BoardDao.getInstance();
			boardDao.delete(conn, noVal);
			
		} finally {
			JdbcUtil.close(conn);
		}
		return "boardlist.do";
	}

}
