package cinema.auditorium.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.AuditoriumDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class AuditoriumDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int audiNo = Integer.parseInt(req.getParameter("audiNo"));
		try (Connection conn = ConnectionProvider.getConnection()) {
			AuditoriumDao auditoriumDao = new AuditoriumDao();
			auditoriumDao.delete(conn, audiNo);
		}
		res.sendRedirect("auditoriumList.do");
		return null;
	}

}
