package cinema.model;

import java.util.Date;

public class Movie {
	int movieNo;
	String korTitle;
	String engTitle;
	String actors;
	String director;
	Date releaseDate;
	String ageRequire;
	int timeLength;
	int ratings;
	String country;
	String genre;
	String content;
	String bigPicUrl;
	String smallPicUrl;
	int totalAttendance;

	public int getTotalAttendance() {
		return totalAttendance;
	}

	public void setTotalAttendance(int totalAttendance) {
		this.totalAttendance = totalAttendance;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public String getBigPicUrl() {
		return bigPicUrl;
	}

	public void setBigPicUrl(String bigPicUrl) {
		this.bigPicUrl = bigPicUrl;
	}

	public String getSmallPicUrl() {
		return smallPicUrl;
	}

	public void setSmallPicUrl(String smallPicUrl) {
		this.smallPicUrl = smallPicUrl;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public void setAgeRequire(String ageRequire) {
		this.ageRequire = ageRequire;
	}

	public void setTimeLength(int timeLength) {
		this.timeLength = timeLength;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCountry() {
		return country;
	}

	public String getEngTitle() {
		return engTitle;
	}

	public int getTimeLength() {
		return timeLength;
	}

	public int getMovieNo() {
		return movieNo;
	}

	public String getKorTitle() {
		return korTitle;
	}

	public String getActors() {
		return actors;
	}

	public String getDirector() {
		return director;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public String getAgeRequire() {
		return ageRequire;
	}

	public String getGenre() {
		return genre;
	}

	public String getContent() {
		return content;
	}

	public void setKorTitle(String korTitle) {
		this.korTitle = korTitle;
	}

	public void setEngTitle(String engTitle) {
		this.engTitle = engTitle;
	}

	public void setMovieNo(int movieNo) {
		this.movieNo = movieNo;
	}

	@Override
	public String toString() {
		return "Movie [movieNo=" + movieNo + ", korTitle=" + korTitle + ", engTitle=" + engTitle + ", actors=" + actors
				+ ", director=" + director + ", releaseDate=" + releaseDate + ", ageRequire=" + ageRequire
				+ ", timeLength=" + timeLength + ", country=" + country + ", genre=" + genre + ", content=" + content
				+ ", bigPicUrl=" + bigPicUrl + ", smallPicUrl=" + smallPicUrl + "]";
	}

}
