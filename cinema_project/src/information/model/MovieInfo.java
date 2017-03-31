package information.model;

import java.util.Date;

public class MovieInfo {
	private int movieNo;
	private String title;
	private String engTitle;
	private String director;
	private String actors;
	private Date releaseDate;
	private String ageRequire;
	private int duringTime;
	private String country;
	private int rating;
	private int totalAttendance;
	private String genre;
	private String content;
	private String smallPicUrl;
	private String bigPicUrl;

	public MovieInfo() {
		super();
	}

	public MovieInfo(int movieNo, String title, String engTitle, String director, String actors, Date releaseDate,
			String ageRequire, int duringTime, String country, int rating, int totalAttendance, String genre,
			String content, String smallPicUrl, String bigPicUrl) {
		super();
		this.movieNo = movieNo;
		this.title = title;
		this.engTitle = engTitle;
		this.director = director;
		this.actors = actors;
		this.releaseDate = releaseDate;
		this.ageRequire = ageRequire;
		this.duringTime = duringTime;
		this.country = country;
		this.rating = rating;
		this.totalAttendance = totalAttendance;
		this.genre = genre;
		this.content = content;
		this.smallPicUrl = smallPicUrl;
		this.bigPicUrl = bigPicUrl;
	}

	public MovieInfo(int movieNo) {
		this.movieNo = movieNo;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public String getTitle() {
		return title;
	}

	public String getEngTitle() {
		return engTitle;
	}

	public String getDirector() {
		return director;
	}

	public String getActors() {
		return actors;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getAgeRequire() {
		return ageRequire;
	}

	public int getDuringTime() {
		return duringTime;
	}

	public String getCountry() {
		return country;
	}

	public int getRating() {
		return rating;
	}

	public int getTotalAttendance() {
		return totalAttendance;
	}

	public String getGenre() {
		return genre;
	}

	public String getContent() {
		return content;
	}

	public String getSmallPicUrl() {
		return smallPicUrl;
	}

	public String getBigPicUrl() {
		return bigPicUrl;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setEngTitle(String engTitle) {
		this.engTitle = engTitle;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setAgeRequire(String ageRequire) {
		this.ageRequire = ageRequire;
	}

	public void setDuringTime(int duringTime) {
		this.duringTime = duringTime;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setTotalAttendance(int totalAttendance) {
		this.totalAttendance = totalAttendance;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setSmallPicUrl(String smallPicUrl) {
		this.smallPicUrl = smallPicUrl;
	}

	public void setBigPicUrl(String bigPicUrl) {
		this.bigPicUrl = bigPicUrl;
	}

}
