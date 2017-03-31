package cinema.schedule.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.ScheduleDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class ScheduleDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		try (Connection conn = ConnectionProvider.getConnection()) {
			int scheduleNo = Integer.parseInt(req.getParameter("scheduleNo"));
			System.out.println("ScheduleDeleteHandler =>" + scheduleNo);
			ScheduleDao scheduleDao = new ScheduleDao();
			scheduleDao.delete(conn, scheduleNo);
		}
		res.sendRedirect("scheduleList.do");
		return null;
	}

}
