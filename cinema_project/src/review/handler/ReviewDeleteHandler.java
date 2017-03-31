package review.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;
import review.model.ReviewDao;

public class ReviewDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;

		String reviewNo = req.getParameter("reviewNo");
		int reviewNoVal = Integer.parseInt(reviewNo);

		try {
			conn = ConnectionProvider.getConnection();
			ReviewDao reviewDao = ReviewDao.getInstance();
			reviewDao.reviewDelete(conn, reviewNoVal);
		} finally {
			JdbcUtil.close(conn);
		}

		return "read.do";
	}

}
