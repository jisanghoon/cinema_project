package cinema.booking.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Theater;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class BookOneHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		String movieNo = req.getParameter("movieNo");
		int movieNoVal = Integer.parseInt(movieNo);

		try {
			conn = ConnectionProvider.getConnection();

			TheaterDao theaterDao = new TheaterDao();
			List<Theater> theaters = null;
			theaters = theaterDao.selectTheaterList(conn);
			req.setAttribute("selTheater", theaters);

		} finally {
			JdbcUtil.close(conn);
		}

		return "WEB-INF/view/book_date.jsp";
	}

}
