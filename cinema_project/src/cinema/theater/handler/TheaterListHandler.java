package cinema.theater.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Theater;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TheaterListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			TheaterDao theaterDao = new TheaterDao();
			List<Theater> theaterList = theaterDao.selectTheaterList(conn);
			req.setAttribute("theaterList", theaterList);
			
			return "WEB-INF/view/admin/theater/admin_theater_list.jsp";
		}
	}

}
