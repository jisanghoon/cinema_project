package cinema.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cinema.model.TicketPrice;
import jdbc.JdbcUtil;

public class TicketPriceDao {

	public void insert(Connection conn, TicketPrice ticketPrice) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "insert into ticket_price values(?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, ticketPrice.getPriceNo());
			pstmt.setString(2, ticketPrice.getCateDay());
			pstmt.setString(3, ticketPrice.getCateTime());
			pstmt.setString(4, ticketPrice.getCateAudi());
			pstmt.setString(5, ticketPrice.getCateScreen());
			pstmt.setString(6, ticketPrice.getCateSeat());
			pstmt.setString(7, ticketPrice.getCateAge());
			pstmt.setInt(8, ticketPrice.getPrice());

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public void delete(Connection conn, int priceNo) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "delete from ticket_price where price_no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, priceNo);
			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}
	}

	public List<TicketPrice> select(Connection conn) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ticket_price";

		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			List<TicketPrice> ticketPriceList = new ArrayList<>();
			TicketPrice ticketPrice = null;

			while (rs.next()) {

				ticketPrice = new TicketPrice();

				ticketPrice.setPriceNo(rs.getInt("price_no"));
				ticketPrice.setCateDay(rs.getString("cate_day"));
				ticketPrice.setCateTime(rs.getString("cate_time"));
				ticketPrice.setCateAudi(rs.getString("cate_audi"));
				ticketPrice.setCateScreen(rs.getString("cate_screen"));
				ticketPrice.setCateSeat(rs.getString("cate_seat"));
				ticketPrice.setCateAge(rs.getString("cate_age"));
				ticketPrice.setPrice(rs.getInt("price"));

				ticketPriceList.add(ticketPrice);

			}
			return ticketPriceList;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}

	}

	public TicketPrice selectById(Connection conn, int priceNo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from ticket_price where price_no=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, priceNo);
			rs = pstmt.executeQuery();

			TicketPrice ticketPrice = null;

			if (rs.next()) {
				ticketPrice = new TicketPrice();

				ticketPrice.setPriceNo(rs.getInt("price_no"));
				ticketPrice.setCateDay(rs.getString("cate_day"));
				ticketPrice.setCateTime(rs.getString("cate_time"));
				ticketPrice.setCateAudi(rs.getString("cate_audi"));
				ticketPrice.setCateScreen(rs.getString("cate_screen"));
				ticketPrice.setCateSeat(rs.getString("cate_seat"));
				ticketPrice.setCateAge(rs.getString("cate_age"));
				ticketPrice.setPrice(rs.getInt("price"));
			}
			return ticketPrice;

		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void update(Connection conn, TicketPrice ticketPrice) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "update ticket_price set cate_day=?, cate_time=?, cate_audi=?, cate_screen=?, cate_seat=?, cate_age=?, price=? "
				+ "where price_no=?";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, ticketPrice.getCateDay());
			pstmt.setString(2, ticketPrice.getCateTime());
			pstmt.setString(3, ticketPrice.getCateAudi());
			pstmt.setString(4, ticketPrice.getCateScreen());
			pstmt.setString(5, ticketPrice.getCateSeat());
			pstmt.setString(6, ticketPrice.getCateAge());
			pstmt.setInt(7, ticketPrice.getPrice());
			pstmt.setInt(8, ticketPrice.getPriceNo());

			pstmt.executeUpdate();

		} finally {
			JdbcUtil.close(pstmt);
		}

	}

}
