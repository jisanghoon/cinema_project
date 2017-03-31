package cinema.model;

import java.util.Date;

public class Screen {
	int screenNo;
	Movie movie;
	String screenMode;
	Date buyDate;
	long screenPrice;
	String supplier;
	Date startDate;
	Date endDate;

	public int getScreenNo() {
		return screenNo;
	}

	public void setScreenNo(int screenNo) {
		this.screenNo = screenNo;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getScreenMode() {
		return screenMode;
	}

	public void setScreenMode(String screenMode) {
		this.screenMode = screenMode;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public long getScreenPrice() {
		return screenPrice;
	}

	public void setScreenPrice(long screenPrice) {
		this.screenPrice = screenPrice;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setMovieNo(int movieNo) {

		if (this.movie == null) {
			this.movie = new Movie();
		}
		this.movie.setMovieNo(movieNo);
	}

	@Override
	public String toString() {
		return "Screen [screenNo=" + screenNo + ", movie=" + movie + ", screenMode=" + screenMode + ", buyDate="
				+ buyDate + ", screenPrice=" + screenPrice + ", supplier=" + supplier + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}

}
