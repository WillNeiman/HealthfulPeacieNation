package dto;

import java.sql.Timestamp;

public class UserDTO {

	private String userId;
	private String userPw;
	private String userName;
	private String userPhoneNumber;
	private String userEmail;
	private Timestamp userCreatedAt;
	private long userPk;
	
	public UserDTO() {
	}
	
	//modifyUserPassword()에 사용
	public UserDTO(String userId, String userPw) {
		this.userId = userId;
		this.userPw = userPw;
	}

//registerUser()에 사용
	public UserDTO(String userId, String userPw, String userName, String userPhoneNumber, String userEmail) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userPhoneNumber = userPhoneNumber;
		this.userEmail = userEmail;
	}

	// modifyUserInformation()에 사용
	public UserDTO(String userId, String userPw, String userPhoneNumber, String userEmail) {
		this.userId = userId;
		this.userPw = userPw;
		this.userPhoneNumber = userPhoneNumber;
		this.userEmail = userEmail;
	}
	// findAll()에 사용
	public UserDTO(String userId, String userPw, String userName, String userPhoneNumber, String userEmail,
			Timestamp userCreatedAt, Long userPk) {
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userPhoneNumber = userPhoneNumber;
		this.userEmail = userEmail;
		this.userCreatedAt = userCreatedAt;
		this.userPk = userPk;
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

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Timestamp getUserCreatedAt() {
		return userCreatedAt;
	}

	public void setUserCreatedAt(Timestamp userCreatedAt) {
		this.userCreatedAt = userCreatedAt;
	}

	public long getUserPk() {
		return userPk;
	}

	public void setUserPk(long userPk) {
		this.userPk = userPk;
	}

}
