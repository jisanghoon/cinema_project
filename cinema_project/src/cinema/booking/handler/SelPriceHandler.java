package cinema.booking.handler;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import cinema.model.TicketPrice;
import cinema.model.dao.BookingDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class SelPriceHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String cateTime = req.getParameter("cateTime");
		
		
		try (Connection conn = ConnectionProvider.getConnection()) {

			BookingDao bookingDao = new BookingDao();
			List<TicketPrice> ticketPrices = bookingDao.selPrice(conn, cateTime.trim());

			for (TicketPrice ticketPrice : ticketPrices) {
				System.out.println("SelPriceHandler == >" + ticketPrice);
			}

			
			
			// data를 JSON으로 변경
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(ticketPrices);
			System.out.println("JSON값 : " + json);

			// JSON 발신
			res.setContentType("application/json;charset=utf-8");
			PrintWriter pw = res.getWriter();
			pw.print(json);
			pw.flush();
		}
		return null;
	}

}
