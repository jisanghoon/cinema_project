package cinema.seat.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Seat;
import cinema.model.dao.SeatDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class SeatDetailHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {

			try (Connection conn = ConnectionProvider.getConnection()) {
				SeatDao seatDao = new SeatDao();

				List<Seat> seatList = seatDao.selectByaudiNo(conn, Integer.parseInt(req.getParameter("audiNo")));
				req.setAttribute("seatList", seatList);
			}
			return "WEB-INF/view/admin/seat/admin_seat_detail.jsp";
		} else {

		}
		return null;
	}

}
