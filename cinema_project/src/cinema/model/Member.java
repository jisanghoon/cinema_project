package cinema.model;

import java.util.Date;

public class Member {
	int userNo;
	String userId;
	String userPw;
	String userName;
	String userPhone;
	String userEmail;
	Date dateOfBirth;
	boolean stateOfJoin;
	String userGender;
	Date regDate;

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public boolean isStateOfJoin() {
		return stateOfJoin;
	}

	public void setStateOfJoin(boolean stateOfJoin) {
		this.stateOfJoin = stateOfJoin;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public boolean matchPassword(String userPw) {
		return this.userPw.equals(userPw);
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPw=" + userPw + ", userName=" + userName
				+ ", userPhone=" + userPhone + ", userEmail=" + userEmail + ", dateOfBirth=" + dateOfBirth
				+ ", stateOfJoin=" + stateOfJoin + ", userGender=" + userGender + ", regDate=" + regDate + "]";
	}

}
