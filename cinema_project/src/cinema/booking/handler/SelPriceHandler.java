package cinema.booking.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.controller.CommandHandler;

public class SelPriceHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		String cateTime = req.getParameter("cateTime");

		try {
		/*	conn = ConnectionProvider.getConnection();
			BookingDao bookingDao = BookingDao.getInstance();
			List<TicketPrice> ticketPrices = null;
			ticketPrices = bookingDao.selPrice(conn, cateTime);

			// data를 JSON으로 변경
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(ticketPrices);
			System.out.println("JSON값 : " + json);

			// JSON 발신
			res.setContentType("application/json;charset=utf-8");
			PrintWriter pw = res.getWriter();
			pw.print(json);
			pw.flush();*/
		} finally {

		}
		return null;
	}

}
