package cinema.theater.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Theater;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class TheaterWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return "WEB-INF/view/admin/theater/admin_theater_insertAndUpdate.jsp";
		} else {

			try (Connection conn = ConnectionProvider.getConnection()) {
				// theaterManager && theaterTel null 가능
				Theater theater = new Theater();
				theater.setTheaterName(req.getParameter("theaterName"));
				theater.setTheaterManager(req.getParameter("theaterManager"));
				theater.setTheaterAddrNum(req.getParameter("theaterAddrNum"));
				theater.setTheaterAddrStr(req.getParameter("theaterAddrStr"));
				theater.setTheaterTel(req.getParameter("theaterTel"));

				TheaterDao theaterDao = new TheaterDao();

				if (theaterDao.findTheater(conn, theater)) {
					System.out.println("중복데이터 존재  -> insert fail");
					req.setAttribute("duplication", true);
					req.setAttribute("theater", theater);
					return "WEB-INF/view/admin/theater/admin_theater_insertAndUpdate.jsp";
				} else {
					theaterDao.insert(conn, theater);
					return "test.jsp";
					/* return "theaterList.do"; */
				}
			}

		}

	}

}
