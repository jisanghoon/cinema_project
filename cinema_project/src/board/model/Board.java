package board.model;

import java.util.Date;

import review.model.Writer;

public class Board {
	private int boardNo;
	private String category;
	private String title;
	private Date date;
	private Writer writer;

	public Board(int boardNo, String category, String title, Date date, Writer writer) {
		super();
		this.boardNo = boardNo;
		this.category = category;
		this.title = title;
		this.date = date;
		this.writer = writer;
	}

	public Board(String category, String title, Date date, Writer writer) {
		super();
		this.category = category;
		this.title = title;
		this.date = date;
		this.writer = writer;
	}

	public Board(int boardNo) {
		super();
		this.boardNo = boardNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public String getCategory() {
		return category;
	}

	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return date;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", category=" + category + ", title=" + title + ", date=" + date
				+ ", writer=" + writer + "]";
	}

}
