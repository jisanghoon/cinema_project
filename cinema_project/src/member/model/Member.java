package member.model;

import java.util.Date;

public class Member {
	private int memberNo;
	private String id;
	private String password;
	private String name;
	private String mobile;
	private String email;
	private Date birth;
	private String gender;
	private Date register;
	private boolean stateOfJoin = true;

	public Member() {
		super();
	}

	public Member(int memberNo, String id, String password, String name, String mobile, String email, Date birth,
			String gender, Date register) {
		super();
		this.memberNo = memberNo;
		this.id = id;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.register = register;
	}

	public Member(int memberNo) {
		super();
		this.memberNo = memberNo;
	}

	public Member(String password, String mobile, String email) {
		super();
		this.password = password;
		this.mobile = mobile;
		this.email = email;
	}

	// 비밀번호
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}
	// ----------------------------------------

	public int getMemberNo() {
		return memberNo;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getMobile() {
		return mobile;
	}

	public String getEmail() {
		return email;
	}

	public Date getBirth() {
		return birth;
	}

	public String getGender() {
		return gender;
	}

	public Date getRegister() {
		return register;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setRegister(Date register) {
		this.register = register;
	}

	public boolean isStateOfJoin() {
		return stateOfJoin;
	}

	public void setStateOfJoin(boolean stateOfJoin) {
		this.stateOfJoin = stateOfJoin;
	}

}
