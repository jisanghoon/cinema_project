package review.model;

import java.util.Date;

import cinema.model.Member;
import cinema.model.Movie;

public class Review {
	private int reviewNo;
	private Writer writer;
	private String content;
	private Movie movie;
	private Member member;
	private Date regDate;
	private Date modDate;
	private int count;
	private String regFormatDate;

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getRegFormatDate() {
		return regFormatDate;
	}

	public void setRegFormatDate(String regFormatDate) {
		this.regFormatDate = regFormatDate;
	}

	@Override
	public String toString() {
		return "Review [reviewNo=" + reviewNo + ", writer=" + writer + ", content=" + content + ", movie=" + movie
				+ ", member=" + member + ", regDate=" + regDate + ", modDate=" + modDate + ", count=" + count
				+ ", regFormatDate=" + regFormatDate + "]";
	}

}
