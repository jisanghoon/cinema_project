package cinema.schedule.handler;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Schedule;
import cinema.model.Theater;
import cinema.model.dao.ScheduleDao;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class ScheduleWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				TheaterDao theaterDao = new TheaterDao();
				List<Theater> theaterList = theaterDao.selectTheaterList(conn);
				req.setAttribute("theaterList", theaterList);
			}
			return "WEB-INF/view/admin/schedule/admin_schedule_insertAndUpdate.jsp";
		} else {

			try (Connection conn = ConnectionProvider.getConnection()) {
				ScheduleDao scheduleDao = new ScheduleDao();
				Schedule schedule = new Schedule();
				Date showDate = null;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

				if (req.getParameter("showDate") != null) {

					showDate = format.parse(req.getParameter("showDate"));
				} else {
					showDate = null;
				}

				schedule.setAuditoriumNo(Integer.parseInt(req.getParameter("audiNo")));
				schedule.setScreenNo(Integer.parseInt(req.getParameter("screenNo")));
				schedule.setShowDate(showDate);
				schedule.setStartTime(LocalTime.parse(req.getParameter("startTime")));
				schedule.setEndTime(LocalTime.parse(req.getParameter("endTime")));
				schedule.setCateTime(req.getParameter("cateTime"));
				scheduleDao.insert(conn, schedule);
			}

			res.sendRedirect("scheduleList.do");
			return null;
		}

	}
}
