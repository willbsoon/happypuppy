package team.project.dto;

//User DTO
public class User {
	private String userId;// 유저 Id
	private String email;// 유저 email
	private String password;// 유저 비밀번호
	private String name;// 유저 이름
	private String gender;// 유저 성별
	private String born;// 유저 생년월일
	private String regDt;// 유저 가입일

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", name=" + name + ", gender="
				+ gender + ", born=" + born + ", regDt=" + regDt + "]";
	}

}
