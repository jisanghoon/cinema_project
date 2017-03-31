package cinema.model;

public class User {
	int memberNo;
	String id;
	String name;

	public User(int memberNo, String id, String name) {
		super();
		this.memberNo = memberNo;
		this.id = id;
		this.name = name;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [memberNo=" + memberNo + ", id=" + id + ", name=" + name + "]";
	}
	

}
