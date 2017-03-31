package review.handler;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import cinema.model.Member;
import cinema.model.Movie;
import cinema.model.User;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;
import review.model.Review;
import review.model.ReviewDao;
import review.model.Writer;

public class ReviewInsertHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("post")) {

			String no = req.getParameter("no");
			int noVal = Integer.parseInt(no);

			// System.out.println(noVal);

			try (Connection conn = ConnectionProvider.getConnection()) {

				ReviewDao reviewDao = ReviewDao.getInstance();
				User user = (User) req.getSession().getAttribute("auth");

				Movie movie = new Movie();
				movie.setMovieNo(noVal);

				/*
				 * Review review = new Review(new Writer(user.getId(),
				 * user.getName()), req.getParameter("content"), movie, new
				 * Member(user.getMemberNo()), new Date(), 0);
				 */
				Review review = new Review();
				Member member = new Member();
				member.setUserNo(user.getMemberNo());

				review.setWriter(new Writer(user.getId(), user.getName()));
				review.setContent(req.getParameter("content"));
				review.setMovie(movie);
				review.setMember(member);
				review.setRegDate(new Date());
				review.setCount(0);

				reviewDao.reviewInsert(conn, review);

				// data를 JSON으로 변경
				ObjectMapper om = new ObjectMapper();
				String json = om.writeValueAsString(review);

				// JSON 발신
				res.setContentType("application/json;charset=utf-8");
				PrintWriter pw = res.getWriter();
				pw.print(json);
				pw.flush();
			}
		}
		return null;
	}

}
