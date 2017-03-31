package cinema.ticketPrice.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.TicketPrice;
import cinema.model.dao.TicketPriceDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TicketPriceWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return "WEB-INF/view/admin/ticketPrice/admin_ticketPrice_insertAndUpdate.jsp";

		} else {
			try (Connection conn = ConnectionProvider.getConnection()) {
				TicketPriceDao ticketPriceDao = new TicketPriceDao();
				TicketPrice ticketPrice = new TicketPrice();
				
				ticketPrice.setCateDay(req.getParameter("cateDay"));
				ticketPrice.setCateTime(req.getParameter("cateTime"));
				ticketPrice.setCateAudi(req.getParameter("cateAudi"));

				ticketPrice.setCateScreen(req.getParameter("cateScreen"));
				ticketPrice.setCateSeat(req.getParameter("cateSeat"));
				ticketPrice.setCateAge(req.getParameter("cateAge"));
				ticketPrice.setPrice(Integer.parseInt(req.getParameter("price")));

				
				ticketPriceDao.insert(conn, ticketPrice);
			}
		}

		res.sendRedirect("ticketPriceList.do");
		return null;

	}

}
