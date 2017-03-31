package cinema.member.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Member;
import cinema.model.dao.MemberDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class MemberListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			MemberDao memberDao = new MemberDao();
			List<Member> memberList = memberDao.select(conn);
			req.setAttribute("memberList", memberList);
		}
		return "WEB-INF/view/admin/member/admin_member_list.jsp";
	}
}
