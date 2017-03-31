package cinema.booking.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Theater;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class BookOneHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//int movieNoVal = Integer.parseInt(req.getParameter("movieNo"));

		try (Connection conn = ConnectionProvider.getConnection()) {

			TheaterDao theaterDao = new TheaterDao();

			List<Theater> theaters = theaterDao.selectTheaterList(conn);

			req.setAttribute("selTheater", theaters);

		}
		return "WEB-INF/view/cus/book_date.jsp";
	}

}
