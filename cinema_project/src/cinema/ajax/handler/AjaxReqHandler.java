package cinema.ajax.handler;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import cinema.model.Auditorium;
import cinema.model.Screen;
import cinema.model.ScreenViewDataForJson;
import cinema.model.dao.AuditoriumDao;
import cinema.model.dao.ScreenDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class AjaxReqHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Enumeration<?> params = req.getParameterNames();
		String name = null;
		ObjectMapper om = null;
		String Json = null;

		if (req.getMethod().equalsIgnoreCase("get")) {
			try (Connection conn = ConnectionProvider.getConnection()) {

				exit_while: while (params.hasMoreElements()) {
					name = (String) params.nextElement();

					switch (name) {
					case "theaterNo":

						int theaterNo = Integer.parseInt(req.getParameter("theaterNo"));
						AuditoriumDao auditoriumDao = new AuditoriumDao();

						List<Auditorium> auditoriumList = auditoriumDao.selectByTheaterNo(conn, theaterNo);
						ScreenDao screenDao = new ScreenDao();
						List<Screen> screenList = screenDao.selectScreenList(conn);

						ScreenViewDataForJson data = new ScreenViewDataForJson();
						data.setAuditoriumList(auditoriumList);
						data.setScreenList(screenList);

						om = new ObjectMapper();
						Json = om.writeValueAsString(data);
						res.setContentType("application/json;charset=utf-8");

						break exit_while;

					case "next_Ajax1":
						break exit_while;

					case "next_Ajax2":
						break exit_while;

					case "next_Ajax3":
						break exit_while;

					default:
						System.out.println("switch 디폴트 부분");
						continue;// go to next 'name from while'
					}// end of switch
				} // end of while
			} // end of try

			PrintWriter pw = res.getWriter();
			pw.print(Json);
			pw.flush();

			return null;

		} else {

			return null;
		}

	}

}
