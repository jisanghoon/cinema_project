package cinema.ticketPrice.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.TicketPrice;
import cinema.model.dao.TicketPriceDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TicketPriceListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionProvider.getConnection()) {
			TicketPriceDao ticketPriceDao = new TicketPriceDao();
			List<TicketPrice> ticketPriceList = ticketPriceDao.select(conn);

			req.setAttribute("ticketPriceList", ticketPriceList);
		}

		return "WEB-INF/view/admin/ticketPrice/admin_ticketPrice_list.jsp";
	}

}
