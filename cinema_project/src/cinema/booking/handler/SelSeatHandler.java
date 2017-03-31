package cinema.booking.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class SelSeatHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		String audiNo = req.getParameter("audiNo");
		int audiVal = Integer.parseInt(audiNo);

		String scheduleNo = req.getParameter("scheduleNo");
		int scheduleVal = Integer.parseInt(scheduleNo);

		try {
			conn = ConnectionProvider.getConnection();

			/*
			 * BookingDao bookingDao = BookingDao.getInstance(); List<Seat>
			 * seats = null; seats = bookingDao.selSeat(conn, audiVal);
			 * 
			 * ObjectMapper om = new ObjectMapper(); String json =
			 * om.writeValueAsString(seats); req.setAttribute("seatList", json);
			 * 
			 * Map<String, Integer> seatCnt = bookingDao.selectForSeatCnt(conn,
			 * audiVal); ObjectMapper om1 = new ObjectMapper(); String json1 =
			 * om1.writeValueAsString(seatCnt);
			 * 
			 * req.setAttribute("seatCnt", json1);
			 * 
			 * ScheduleDao scheduleDao = new ScheduleDao(); Schedule schedule =
			 * null; schedule = scheduleDao.selectById(conn, scheduleVal);
			 * req.setAttribute("schedules", schedule);
			 */
		} finally {
			JdbcUtil.close(conn);
		}
		return "WEB-INF/view/book_seat.jsp";
	}

}
