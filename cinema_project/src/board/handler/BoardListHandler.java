package board.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardPage;
import board.model.BoardService;
import cinema.model.User;
import mvc.controller.CommandHandler;

public class BoardListHandler implements CommandHandler {
	private BoardService listService = new BoardService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		try {
			BoardPage boardPage = listService.getBoardPage(pageNo);
			req.setAttribute("boardList", boardPage);
			
			//관리자
			User user = (User) req.getSession().getAttribute("auth");
			req.setAttribute("admin", user);
					
		} finally {
			
		}
		return "WEB-INF/view/cus/board.jsp";

	}

}
