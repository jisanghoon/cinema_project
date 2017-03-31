package member.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Member;
import cinema.model.User;
import cinema.model.dao.MemberDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class LoginHandler implements CommandHandler {
	private final String FORM_VIEW = "WEB-INF/view/cus/login.jsp";

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {
			return FORM_VIEW;
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			String id = req.getParameter("id");
			String password = req.getParameter("password");

			try (Connection conn = ConnectionProvider.getConnection()) {

				MemberDao memberDao = new MemberDao();
				Member member = memberDao.selectById(conn, id);

				if (member == null) {
					req.setAttribute("notJoin", true);
					return FORM_VIEW;
				}

				if (!member.matchPassword(password)) {
					req.setAttribute("idOrPwNotMatch", true);
					return FORM_VIEW;
				}

				// session에 로그인 셋팅
				User user = new User(member.getUserNo(), id, member.getUserName());
				req.getSession().setMaxInactiveInterval(60*60);
				req.getSession().setAttribute("auth", user);
				
				return "index.jsp";
			}
		}
		return null;
	}

}
