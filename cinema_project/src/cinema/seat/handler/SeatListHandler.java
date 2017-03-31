package cinema.seat.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Auditorium;
import cinema.model.dao.AuditoriumDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class SeatListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			AuditoriumDao auditoriumDao = new AuditoriumDao();
			List<Auditorium> auditoriumList = auditoriumDao.selectAudiForSeatCnt(conn);
			req.setAttribute("auditoriumList", auditoriumList);
			return "WEB-INF/view/admin/seat/admin_seat_list.jsp";
		}
	}

}
