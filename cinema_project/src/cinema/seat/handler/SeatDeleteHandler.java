package cinema.seat.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.SeatDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class SeatDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int seatNo = Integer.parseInt(req.getParameter("seatNo"));

		try (Connection conn = ConnectionProvider.getConnection()) {
			SeatDao seatDao = new SeatDao();
			seatDao.delete(conn, seatNo);
		}
		res.sendRedirect("seatList.do");
		return null;
	}

}
