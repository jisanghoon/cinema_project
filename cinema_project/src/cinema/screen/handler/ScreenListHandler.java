package cinema.screen.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Screen;
import cinema.model.dao.ScreenDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class ScreenListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		try (Connection conn = ConnectionProvider.getConnection()) {
			ScreenDao screenDao = new ScreenDao();
			List<Screen> screenList = screenDao.selectScreenList(conn);
			req.setAttribute("screenList", screenList);
		}
		return "WEB-INF/view/admin/screen/admin_screen_list.jsp";
	}

}
