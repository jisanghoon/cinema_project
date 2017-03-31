package cinema.booking.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Booking;
import cinema.model.dao.BookingDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class BookingListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			BookingDao bookingDao = new BookingDao();

			List<Booking> bookingList = bookingDao.select(conn);
			req.setAttribute("bookingList", bookingList);

		}

		return "WEB-INF/view/admin/booking/admin_booking_list.jsp";
	}

}
