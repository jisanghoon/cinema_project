package cinema.ticketPrice.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.TicketPriceDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TicketPriceDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		try (Connection conn = ConnectionProvider.getConnection()) {
			int priceNo = Integer.parseInt(req.getParameter("priceNo"));
			TicketPriceDao ticketPriceDao = new TicketPriceDao();
			ticketPriceDao.delete(conn, priceNo);
			res.sendRedirect("ticketPriceList.do");
		}
		return null;
	}

}
