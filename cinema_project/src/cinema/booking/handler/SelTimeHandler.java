package cinema.booking.handler;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import cinema.model.BookStepOne;
import cinema.model.dao.BookingDao;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import mvc.controller.CommandHandler;

public class SelTimeHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Connection conn = null;
		// seltime.do

		String theaterNo = req.getParameter("theaterNo");
		int theaterVal = Integer.parseInt(theaterNo);

		String movieNo = req.getParameter("movieNo");
		int movieVal = Integer.parseInt(movieNo);

		String dataNo = req.getParameter("dateNo");

		try {
			conn = ConnectionProvider.getConnection();

			BookingDao bookingDao = new BookingDao();
			List<BookStepOne> bookStepOnes = null;
			bookStepOnes = bookingDao.selMovie(conn, theaterVal, movieVal, dataNo);

			// data를 JSON으로 변경 ObjectMapper om = new ObjectMapper(); String
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(bookStepOnes);
			System.out.println("JSON값 : " + json);

			// JSON 발신 res.setContentType("application/json;charset=utf-8");
			res.setContentType("application/json;charset=utf-8");
			PrintWriter pw = res.getWriter();
			pw.print(json);
			pw.flush();

		} finally {
			JdbcUtil.close(conn);
		}
		return null;
	}

}
