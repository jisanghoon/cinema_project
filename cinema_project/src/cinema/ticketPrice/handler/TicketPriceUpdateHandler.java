package cinema.ticketPrice.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.TicketPrice;
import cinema.model.dao.TicketPriceDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TicketPriceUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			try (Connection conn = ConnectionProvider.getConnection()) {

				int priceNo = Integer.parseInt(req.getParameter("priceNo"));
				TicketPriceDao ticketPriceDao = new TicketPriceDao();
				TicketPrice ticketPrice = ticketPriceDao.selectById(conn, priceNo);
				req.setAttribute("ticketPrice", ticketPrice);
			}
			return "WEB-INF/view/admin/ticketPrice/admin_ticketPrice_insertAndUpdate.jsp";
		} else {
			try (Connection conn = ConnectionProvider.getConnection()) {

				TicketPrice ticketPrice = new TicketPrice();

				ticketPrice.setPriceNo(Integer.parseInt(req.getParameter("priceNo")));
				ticketPrice.setCateDay(req.getParameter("cateDay"));
				ticketPrice.setCateTime(req.getParameter("cateTime"));
				ticketPrice.setCateAudi(req.getParameter("cateAudi"));

				ticketPrice.setCateScreen(req.getParameter("cateScreen"));
				ticketPrice.setCateSeat(req.getParameter("cateSeat"));
				ticketPrice.setCateAge(req.getParameter("cateAge"));
				ticketPrice.setPrice(Integer.parseInt(req.getParameter("price")));

				TicketPriceDao ticketPriceDao = new TicketPriceDao();
				ticketPriceDao.update(conn, ticketPrice);


			}
			res.sendRedirect("ticketPriceList.do");
			return null;
		}
	}
}
