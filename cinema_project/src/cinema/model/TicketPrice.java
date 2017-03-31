package cinema.model;

public class TicketPrice {

	int priceNo;
	String cateDay;
	String cateTime;
	String cateAudi;
	String cateScreen;// 2D or 3D 구분
	String cateSeat;
	String cateAge;
	int price;

	public TicketPrice() {
		// TODO Auto-generated constructor stub
	}

	public TicketPrice(int priceNo, String cateDay, String cateTime, String cateAudi, String cateScreen,
			String cateSeat, String cateAge, int price) {
		super();
		this.priceNo = priceNo;
		this.cateDay = cateDay;
		this.cateTime = cateTime;
		this.cateAudi = cateAudi;
		this.cateScreen = cateScreen;
		this.cateSeat = cateSeat;
		this.cateAge = cateAge;
		this.price = price;
	}

	public int getPriceNo() {
		return priceNo;
	}

	public void setPriceNo(int priceNo) {
		this.priceNo = priceNo;
	}

	public String getCateDay() {
		return cateDay;
	}

	public void setCateDay(String cateDay) {
		this.cateDay = cateDay;
	}

	public String getCateTime() {
		return cateTime;
	}

	public void setCateTime(String cateTime) {
		this.cateTime = cateTime;
	}

	public String getCateAudi() {
		return cateAudi;
	}

	public void setCateAudi(String cateAudi) {
		this.cateAudi = cateAudi;
	}

	public String getCateScreen() {
		return cateScreen;
	}

	public void setCateScreen(String cateScreen) {
		this.cateScreen = cateScreen;
	}

	public String getCateSeat() {
		return cateSeat;
	}

	public void setCateSeat(String cateSeat) {
		this.cateSeat = cateSeat;
	}

	public String getCateAge() {
		return cateAge;
	}

	public void setCateAge(String cateAge) {
		this.cateAge = cateAge;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}
