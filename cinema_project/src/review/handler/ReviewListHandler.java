package review.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;
import review.model.ReviewPage;
import review.model.ReviewService;

public class ReviewListHandler implements CommandHandler {
	private ReviewService listService = new ReviewService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("post")) {
			Connection conn = null;
			//영화번호 값
			String no = req.getParameter("no");
			int noVal = Integer.parseInt(no);
			
			//페이지번호 값
			String pageNoVal = req.getParameter("pageNo");
			System.out.println(pageNoVal);
			int pageNo = 1;
			if (pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}

			try {
				conn = ConnectionProvider.getConnection();				
				
				//리뷰 리스트				
				ReviewPage reviewPage = listService.getReviewPage(pageNo, noVal);
				req.setAttribute("viewReviewList", reviewPage);
				//---------------------------------------------------------------
				
				/*// data를 JSON으로 변경				
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(reviewPage);
				System.out.println("JSON값 : " + json);
				
				// JSON 발신
				res.setContentType("application/json;charset=utf-8");
				PrintWriter pw = res.getWriter();
				pw.print(json);
				pw.flush();*/
				
			} finally {
				JdbcUtil.close(conn);
			}
			
		}
		return null;
	}

}
