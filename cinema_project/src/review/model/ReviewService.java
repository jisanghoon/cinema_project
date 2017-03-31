package review.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;

public class ReviewService {
	private int size = 10;

	public ReviewPage getReviewPage(int pageNum, int movieNo) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			ReviewDao reviewDao = ReviewDao.getInstance();

			int total = reviewDao.selectCount(conn, movieNo);
			List<Review> reviews = reviewDao.selectByNoPaging(conn, (pageNum - 1) * size, size, movieNo);
			System.out.println("reviewsize:"+reviews.size());
			return new ReviewPage(total, pageNum, size, reviews);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getReviewPage");
		}

		return null;
	}
}
