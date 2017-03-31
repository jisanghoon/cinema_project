package cinema.member.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.MemberDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class MemberDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int userNo = Integer.parseInt(req.getParameter("userNo"));
		try (Connection conn = ConnectionProvider.getConnection()) {
			MemberDao memberDao = new MemberDao();
			memberDao.delete(conn, userNo);
		}
		res.sendRedirect("memberList.do");
		return null;
	}

}
