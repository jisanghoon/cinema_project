package information.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Movie;
import cinema.model.User;
import cinema.model.dao.MovieDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;
import review.model.ReviewPage;
import review.model.ReviewService;

public class MovieReadHandler implements CommandHandler {
	private ReviewService listService = new ReviewService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		System.out.println("MovieReadHandler-------------------");
		String no = req.getParameter("no");
		int noVal = Integer.parseInt(no);

		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}

		try (Connection conn = ConnectionProvider.getConnection()) {

			MovieDao movieDao = new MovieDao();
			Movie movie = null;
			movie = movieDao.selectByMoiveId(conn, noVal);
			req.setAttribute("viewMovieRead", movie);
			System.out.println(movie);

			System.out.println(pageNo + "pageNo");
			System.out.println(noVal + "noVal");
			
			// 리뷰 리스트
			ReviewPage reviewPage = listService.getReviewPage(pageNo, noVal);
			req.setAttribute("viewReviewList", reviewPage);
			System.out.println(reviewPage);

			// 리뷰등록자
			User user = (User) req.getSession().getAttribute("auth");
			req.setAttribute("user", user);

		}

		return "WEB-INF/view/cus/detailPage.jsp";
	}

}
