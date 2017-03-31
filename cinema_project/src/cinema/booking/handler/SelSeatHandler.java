package cinema.booking.handler;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import cinema.model.Schedule;
import cinema.model.Seat;
import cinema.model.dao.BookingDao;
import cinema.model.dao.ScheduleDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class SelSeatHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String audiNo = req.getParameter("audiNo");
		int audiVal = Integer.parseInt(audiNo);
		String scheduleNo = req.getParameter("scheduleNo");
		int scheduleVal = Integer.parseInt(scheduleNo);

		try (Connection conn = ConnectionProvider.getConnection()) {

			BookingDao bookingDao = new BookingDao();
			List<Seat> seats = null;

			seats = bookingDao.selSeat(conn, audiVal);

			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(seats);
			req.setAttribute("seatList", json);

			Map<String, Integer> seatCnt = bookingDao.selectForSeatCnt(conn, audiVal);
			ObjectMapper om1 = new ObjectMapper();
			String json1 = om1.writeValueAsString(seatCnt);

			req.setAttribute("seatCnt", json1);

			ScheduleDao scheduleDao = new ScheduleDao();
			Schedule schedule = null;
			schedule = scheduleDao.selectById(conn, scheduleVal);
			req.setAttribute("schedules", schedule);

		}
		return "WEB-INF/view/cus/book_seat.jsp";
	}

}
