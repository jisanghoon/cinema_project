package cinema.auditorium.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Auditorium;
import cinema.model.Theater;
import cinema.model.dao.AuditoriumDao;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class AuditoriumWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {

			try (Connection conn = ConnectionProvider.getConnection()) {
				TheaterDao theaterDao = new TheaterDao();
				List<Theater> theaterList = theaterDao.selectTheaterList(conn);
				req.setAttribute("theaterList", theaterList);

				return "WEB-INF/view/admin/auditorium/admin_auditorium_insertAndUpdate.jsp";

			}
		} else {
			try (Connection conn = ConnectionProvider.getConnection()) {

				
				
				Auditorium auditorium = new Auditorium();
				auditorium.setAudiName(req.getParameter("audiName"));
				auditorium.setAudiType(req.getParameter("audiType"));
				auditorium.setFloor(req.getParameter("floor"));
				auditorium.setTheaterNo(Integer.parseInt(req.getParameter("theaterNo")));
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				AuditoriumDao auditoriumDao = new AuditoriumDao();
				auditoriumDao.insert(conn, auditorium);

				res.sendRedirect("auditoriumList.do");
				return null;

			}

		}

	}

}
