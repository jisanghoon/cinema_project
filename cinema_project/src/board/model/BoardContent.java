package board.model;

public class BoardContent {
	private int number;
	private Board board;
	private String content;

	public BoardContent(int number, Board board, String content) {
		super();
		this.number = number;
		this.board = board;
		this.content = content;
	}

	public BoardContent(Board board, String content) {
		super();
		this.board = board;
		this.content = content;
	}

	public BoardContent(int number, String content) {
		super();
		this.number = number;
		this.content = content;
	}

	public BoardContent(String content) {
		this.content = content;
	}

	public int getNumber() {
		return number;
	}

	public Board getBoard() {
		return board;
	}

	public String getContent() {
		return content;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardContent [number=" + number + ", board=" + board + ", content=" + content + "]";
	}

}
