package member.handler;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Member;
import cinema.model.dao.MemberDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class JoinHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {
			return "/WEB-INF/view/cus/join.jsp";
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			try (Connection conn = ConnectionProvider.getConnection()) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String birth = req.getParameter("birth");

				Member member = new Member();
				member.setUserNo(0);
				member.setUserId(req.getParameter("id"));
				member.setUserPw(req.getParameter("password"));
				member.setUserName(req.getParameter("name"));
				member.setUserPhone(req.getParameter("mobile"));
				member.setUserEmail(req.getParameter("email"));
				member.setDateOfBirth(dateFormat.parse(birth));
				member.setStateOfJoin(true);
				member.setUserGender(req.getParameter("gender"));
				member.setRegDate(new Date());

				// 값이 하나라도 없으면 가입 불가

				MemberDao memberDao = new MemberDao();
				memberDao.insert(conn, member);
			}
			return "mainList.do";
		}

		return null;
	}

}
