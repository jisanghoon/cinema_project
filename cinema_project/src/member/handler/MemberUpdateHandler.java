package member.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.User;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.model.Member;
import member.model.MemberDao;
import mvc.controller.CommandHandler;

public class MemberUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			Connection conn = null;

			User user = (User) req.getSession().getAttribute("auth");

			try {
				conn = ConnectionProvider.getConnection();
				Member member = null;
				MemberDao memberDao = MemberDao.getInstance();
				member = memberDao.selectById(conn, user.getId());

				req.setAttribute("member", member);

			} finally {
				JdbcUtil.close(conn);
			}
			return "WEB-INF/view/myPage.jsp";

		} else if (req.getMethod().equalsIgnoreCase("post")) {
			Connection conn = null;

			User user = (User) req.getSession().getAttribute("auth");

			try {
				conn = ConnectionProvider.getConnection();
				Member member = new Member(req.getParameter("password"), req.getParameter("mobile"),
						req.getParameter("email"));
				MemberDao memberDao = MemberDao.getInstance();
				memberDao.memberUpdate(conn, member, user.getId());

			} finally {
				JdbcUtil.close(conn);
			}

			return "list.do";
		}

		return null;

	}

}
