package cinema.screen.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.ScreenDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class ScreenDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int screenNo = Integer.parseInt(req.getParameter("screenNo"));

		try (Connection conn = ConnectionProvider.getConnection()) {
			ScreenDao screenDao = new ScreenDao();
			screenDao.delete(conn, screenNo);
		}

		res.sendRedirect("screenList.do");
		return null;
	}

}
