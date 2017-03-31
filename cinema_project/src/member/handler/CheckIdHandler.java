package member.handler;

import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.MemberDao;
import mvc.controller.CommandHandler;

public class CheckIdHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("post")) {
			String id = req.getParameter("checkId");
			boolean check = false;

			Connection conn = null;

			try {
				conn = ConnectionProvider.getConnection();
				MemberDao memberDao = MemberDao.getInstance();
				check = memberDao.checkID(conn, id);

				// data를 JSON으로 변경
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(check);
				//System.out.println("JSON값 : " + json);

				// JSON 발신
				res.setContentType("application/json;charset=utf-8");
				PrintWriter pw = res.getWriter();
				pw.print(json);
				pw.flush();
			} finally {
				JdbcUtil.close(conn);
			}
		}

		return null;

	}

}
