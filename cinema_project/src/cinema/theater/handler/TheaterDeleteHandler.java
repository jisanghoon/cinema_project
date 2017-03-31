package cinema.theater.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TheaterDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int theaterNo = Integer.parseInt(req.getParameter("theaterNo"));

			TheaterDao theaterDao = new TheaterDao();
			theaterDao.delete(conn, theaterNo);
		}
		res.sendRedirect("theaterList.do");
		return null;
	}

}
