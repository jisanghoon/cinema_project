package cinema.movie.handler;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import cinema.model.Movie;
import cinema.model.dao.MovieDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class MovieWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {
			return "WEB-INF/view/admin/movie/admin_movie_insertAndUpdate.jsp";

		} else if (req.getMethod().equalsIgnoreCase("post")) {

			String uploadPath = req.getServletContext().getRealPath("upload");
			File dir = new File(uploadPath);

			if (dir.exists() == false)
				dir.mkdirs();

			int sizeLimit = 1024 * 1024 * 15;

			try (Connection conn = ConnectionProvider.getConnection()) {
				Movie movie = new Movie();

				MultipartRequest multi = new MultipartRequest(req, uploadPath, sizeLimit, "utf-8",
						new DefaultFileRenamePolicy());

				/*--------------------이미지 처리----------------------*/
				String bigPic = multi.getFilesystemName("bigPic");
				String smallPic = multi.getFilesystemName("smallPic");

				// String origfilename1 = multi.getOriginalFileName(keyfile1);

				if (bigPic != null && !bigPic.isEmpty()) {
					movie.setBigPicUrl(bigPic);
				}

				if (smallPic != null && !smallPic.isEmpty()) {
					movie.setSmallPicUrl(smallPic);
				}
				/*--------------------select 다중 선택 처리----------------------*/

				String[] arrayGenre = multi.getParameterValues("genre");
				String genre = "";

				for (int i = 0; i < arrayGenre.length; i++) {
					if (i != arrayGenre.length - 1) {
						genre += arrayGenre[i] + ", ";
					} else {
						genre += arrayGenre[i];
					}
				}

				/*--------------------날짜 처리----------------------*/

				String releaseDate = multi.getParameter("releaseDate").trim();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Date formatedDate = format.parse(releaseDate);

				/*--------------------movie setMethod----------------------*/
				movie.setMovieNo(0);
				movie.setKorTitle(multi.getParameter("korTitle").trim());
				movie.setEngTitle(multi.getParameter("engTitle").trim());
				movie.setActors(multi.getParameter("actors").trim());
				movie.setDirector(multi.getParameter("director").trim());
				movie.setAgeRequire(multi.getParameter("ageRequire"));
				movie.setReleaseDate(formatedDate);
				movie.setTimeLength(Integer.parseInt(multi.getParameter("timeLegth")));
				movie.setCountry(multi.getParameter("country").trim());
				movie.setGenre(genre);
				movie.setContent(multi.getParameter("content"));

				MovieDao movieDao = new MovieDao();

				movieDao.insert(conn, movie);

			}

			res.sendRedirect("movieList.do");
			return null;
		} else {

			return null;
		}

	}

}
