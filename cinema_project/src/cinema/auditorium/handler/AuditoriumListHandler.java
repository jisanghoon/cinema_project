package cinema.auditorium.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Auditorium;
import cinema.model.dao.AuditoriumDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class AuditoriumListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		try (Connection conn = ConnectionProvider.getConnection()) {

			AuditoriumDao auditoriumDao = new AuditoriumDao();
			List<Auditorium> auditoriumList = auditoriumDao.selectAuditoriumList(conn);

			req.setAttribute("auditoriumList", auditoriumList);
			return "WEB-INF/view/admin/auditorium/admin_auditorium_list.jsp";
		}
	}

}
