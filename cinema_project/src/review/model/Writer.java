package review.model;

public class Writer {
	private String id;
	private String name;

	public Writer(String id) {
		super();
		this.id = id;
	}

	public Writer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Writer [id=" + id + ", name=" + name + "]";
	}
	

}
