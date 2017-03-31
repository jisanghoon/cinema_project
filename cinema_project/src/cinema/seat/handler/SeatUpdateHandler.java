package cinema.seat.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Auditorium;
import cinema.model.Seat;
import cinema.model.Theater;
import cinema.model.dao.AuditoriumDao;
import cinema.model.dao.SeatDao;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class SeatUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				TheaterDao theaterDao = new TheaterDao();
				List<Theater> theaterList = theaterDao.selectTheaterList(conn);

				AuditoriumDao auditoriumDao = new AuditoriumDao();
				List<Auditorium> auditoriumList = auditoriumDao.selectAuditoriumList(conn);

				int seatNo = Integer.parseInt(req.getParameter("seatNo"));
				SeatDao seatDao = new SeatDao();
				Seat seat = seatDao.selectBySeatId(conn, seatNo);

				req.setAttribute("theaterList", theaterList);
				req.setAttribute("auditoriumList", auditoriumList);
				req.setAttribute("seat", seat);
			}
			return "WEB-INF/view/admin/seat/admin_seat_insertAndUpdate.jsp";
		} else {

			// Seat seat = null;
			try (Connection conn = ConnectionProvider.getConnection()) {
				// seat = new Seat();
				/*
				 * seat.setSeatNo(Integer.parseInt(req.getParameter("seatNo")));
				 * seat.setRow(req.getParameter("row").toUpperCase());
				 * seat.setCol(Integer.parseInt(req.getParameter("col")));
				 * seat.setAuditoriumNo(Integer.parseInt(req.getParameter(
				 * "audiNo")));
				 */

				// SeatDao seatDao = new SeatDao();
				// seatDao.update(conn, seat);
			}
		}
		res.sendRedirect("seatList.do");
		return null;
	}
}
