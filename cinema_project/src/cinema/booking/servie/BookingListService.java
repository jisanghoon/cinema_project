package cinema.booking.servie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cinema.model.Booking;
import cinema.model.dao.BookingDao;

public class BookingListService {
	public List<Booking> setBooking(Connection conn) throws SQLException  {

		List<Booking> bookingList = new BookingDao().select(conn);
		/*Schedule schedule=new ScheduleDao();*/
		
		Booking booking = null;

		booking = new Booking();

		bookingList.add(booking);
		
		return bookingList;

	}

}
