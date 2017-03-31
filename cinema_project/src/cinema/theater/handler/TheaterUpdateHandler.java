package cinema.theater.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Theater;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TheaterUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				int theaterNo = Integer.parseInt(req.getParameter("theaterNo"));
				TheaterDao theaterDao = new TheaterDao();
				Theater theater = theaterDao.selectByTheaterId(conn, theaterNo);
				req.setAttribute("theater", theater);
			}
			return "WEB-INF/view/admin/theater/admin_theater_insertAndUpdate.jsp";

		} else {
			/*
			 * int theaterNo; String theaterName; String theaterAddress; String
			 * theaterManager; String theaterTel;
			 */
			try (Connection conn = ConnectionProvider.getConnection()) {

				Theater theater = new Theater();
				theater.setTheaterNo(Integer.parseInt(req.getParameter("theaterNo")));
				theater.setTheaterName(req.getParameter("theaterName"));
				theater.setTheaterAddrNum(req.getParameter("theaterAddrNum"));
				theater.setTheaterAddrStr(req.getParameter("theaterAddrStr"));
				theater.setTheaterManager(req.getParameter("theaterManager"));
				theater.setTheaterTel(req.getParameter("theaterTel"));

				TheaterDao theaterDao = new TheaterDao();
				theaterDao.update(conn, theater);
			}
			res.sendRedirect("theaterList.do");
			return null;
		}
	}

}
