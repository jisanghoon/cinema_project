package member.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.MemberDao;
import mvc.controller.CommandHandler;

public class MeberDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		User user = (User) req.getSession().getAttribute("auth");

		try {
			conn = ConnectionProvider.getConnection();
			MemberDao memberDao = MemberDao.getInstance();
			memberDao.memberDelete(conn, user.getId());

		} finally {
			JdbcUtil.close(conn);
		}
		return "logout.do";
	}

}
