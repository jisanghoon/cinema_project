package cinema.seat.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import cinema.model.Auditorium;
import cinema.model.Seat;
import cinema.model.Theater;
import cinema.model.dao.AuditoriumDao;
import cinema.model.dao.SeatDao;
import cinema.model.dao.TheaterDao;
import jdbc.ConnectionProvider;
import mvc.controller.CommandHandler;

public class SeatWriteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {

			try (Connection conn = ConnectionProvider.getConnection()) {
				TheaterDao theaterDao = new TheaterDao();
				List<Theater> theaterList = theaterDao.selectTheaterList(conn);
				req.setAttribute("theaterList", theaterList);

				AuditoriumDao auditoriumDao = new AuditoriumDao();
				List<Auditorium> auditoriumList = auditoriumDao.selectAuditoriumList(conn);
				req.setAttribute("auditoriumList", auditoriumList);
			}
			return "WEB-INF/view/admin/seat/admin_seat_insertAndUpdate.jsp";

		} else {
			System.out.println("POST Method--------------------------------");
			System.out.println();
			System.out.println("theaterNo => " + req.getParameter("theaterNo"));
			System.out.println("audiNo => " + req.getParameter("audiNo"));
			System.out.println();
			System.out.println("-------------------------------------------");
			System.out.println("Json 파라미터 시작");
			System.out.println();

			//
			String jsonString = req.getParameter("data");
			ObjectMapper om = new ObjectMapper();
			JsonNode rootNode = om.readTree(jsonString);
			Iterator<JsonNode> it = rootNode.iterator();
			Iterator<JsonNode> innerit;

			//
			JsonNode tempSeat = null;
			Seat seat = null;
			List<Seat> seatList = new ArrayList<>();
			int audiNo = Integer.parseInt(req.getParameter("audiNo"));

			//
			while (it.hasNext()) {

				innerit = it.next().iterator();
				System.out.println("행 시작-----------------------------------");
				while (innerit.hasNext()) {
					tempSeat = innerit.next();

					seat = new Seat();
					seat.setRow(tempSeat.get("row").asInt());
					seat.setCol(tempSeat.get("col").asInt());
					seat.setSeatName(tempSeat.get("name").asText());
					seat.setAuditoriumNo(audiNo);

					System.out.println(seat);
					seatList.add(seat);
				}
			}
			System.out.println();
			System.out.println("-------------------------------------------");

			//
			if (seatList != null) {
				SeatDao seatDao = new SeatDao();
				try (Connection conn = ConnectionProvider.getConnection()) {
					seatDao.insertData(conn, seatList);
				}
			}
			res.sendRedirect("seatList.do");
			return null;
		}

	}
}
