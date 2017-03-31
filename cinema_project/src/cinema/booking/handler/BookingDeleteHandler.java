package cinema.booking.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.BookingDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class BookingDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int bookingNo = Integer.parseInt(req.getParameter("bookingNo"));
		System.out.println(bookingNo);
		try (Connection conn = ConnectionProvider.getConnection()) {
			BookingDao bookingDao = new BookingDao();
			bookingDao.delete(conn, bookingNo);
		}
		res.sendRedirect("bookingList.do");
		return null;
	}

}
