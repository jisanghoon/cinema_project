package cinema.movie.handler;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Movie;
import cinema.model.dao.MovieDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class MovieListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		try (Connection conn = ConnectionProvider.getConnection()) {
			MovieDao movieDao = new MovieDao();
			List<Movie> movieList = movieDao.selectMovieList(conn);
			req.setAttribute("movieList", movieList);
		}
		return "WEB-INF/view/admin/movie/admin_movie_list.jsp";
	}

}
