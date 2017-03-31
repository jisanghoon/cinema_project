package cinema.movie.handler;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.dao.MovieDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class MovieDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int movieNo = Integer.parseInt(req.getParameter("movieNo"));
		try (Connection conn = ConnectionProvider.getConnection()) {
			MovieDao movieDao = new MovieDao();
			movieDao.delete(conn, movieNo);
		}
		res.sendRedirect("movieList.do");
		return null;
	}

}
