package review.handler;

import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;
import review.model.Review;
import review.model.ReviewDao;

public class ReviewUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {

			String reviewNo = req.getParameter("reviewNo");
			int reviewNoVal = Integer.parseInt(reviewNo);

			try (Connection conn = ConnectionProvider.getConnection()) {

				Review review = null;
				ReviewDao reviewDao = ReviewDao.getInstance();
				review = reviewDao.selectByReviewNo(conn, reviewNoVal);
				req.setAttribute("reviewData", review);
			}
			return "read.do";

		} else if (req.getMethod().equalsIgnoreCase("post")) {

			String reviewNo = req.getParameter("reviewNo");
			int reviewNoVal = Integer.parseInt(reviewNo);

			try (Connection conn = ConnectionProvider.getConnection()) {

				Review review = new Review();
				review.setReviewNo(reviewNoVal);
				review.setContent(req.getParameter("content"));
				review.setModDate(new Date());

				ReviewDao reviewDao = ReviewDao.getInstance();
				reviewDao.reviewUpdate(conn, review);
			}
			return "read.do";
		}
		return null;
	}

}
