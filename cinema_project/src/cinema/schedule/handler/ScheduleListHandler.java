package cinema.schedule.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Schedule;
import cinema.model.dao.ScheduleDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class ScheduleListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			ScheduleDao scheduleDao = new ScheduleDao();
			List<Schedule> scheduleList = scheduleDao.select(conn);
			
			req.setAttribute("scheduleList", scheduleList);

		}

		return "WEB-INF/view/admin/schedule/admin_schedule_list.jsp";
	}

}
