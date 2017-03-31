package cinema.screen.handler;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cinema.model.Movie;
import cinema.model.Screen;
import cinema.model.Theater;
import cinema.model.dao.MovieDao;
import cinema.model.dao.ScreenDao;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class ScreenWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		if (req.getMethod().equalsIgnoreCase("get")) {

			try (Connection conn = ConnectionProvider.getConnection()) {
				MovieDao movieDao = new MovieDao();
				List<Movie> movieList = movieDao.selectMovieList(conn);
				req.setAttribute("movieList", movieList);

				TheaterDao theaterDao = new TheaterDao();
				List<Theater> theaterList = theaterDao.selectTheaterList(conn);
				req.setAttribute("theaterList", theaterList);

			}

			return "WEB-INF/view/admin/screen/admin_screen_insertAndUpdate.jsp";

		} else {

			SimpleDateFormat format = null;
			Screen screen = null;
			Date startDate = null;
			Date endDate = null;
			Date buyDate = null;

			try (Connection conn = ConnectionProvider.getConnection()) {

				format = new SimpleDateFormat("yyyy-MM-dd");

				if ((req.getParameter("buyDate") == "" ? false : true))
					buyDate = format.parse(req.getParameter("buyDate"));
				else
					buyDate = null;

				if ((req.getParameter("startDate") == "" ? false : true))
					startDate = format.parse(req.getParameter("startDate"));
				else
					startDate = null;

				if ((req.getParameter("endDate") == "" ? false : true))
					endDate = format.parse(req.getParameter("endDate"));

				else
					endDate = null;

				screen = new Screen();

				screen.setMovieNo(Integer.parseInt(req.getParameter("movieNo")));
				screen.setScreenMode(req.getParameter("screenMode"));

				// ###,###,### 문자열로 된 숫자 포맷 처리
				String[] numSplitArr = null;
				String tempNum = "";
				if ((req.getParameter("screenPrice") == "" ? false : true)) {
					numSplitArr = req.getParameter("screenPrice").split(",");
					for (int i = 0; i < numSplitArr.length; i++) {
						tempNum += numSplitArr[i];
					}

				}

				screen.setScreenPrice(Long.parseLong(tempNum));
				screen.setSupplier(req.getParameter("supplier"));

				screen.setBuyDate(buyDate);
				screen.setStartDate(startDate);
				screen.setEndDate(endDate);

				ScreenDao screenDao = new ScreenDao();
				screenDao.insert(conn, screen);

			}
			res.sendRedirect("screenList.do");
			return null;
		}
	}
}
