package entity;

public class AccountEntity {
	private Long id;
	private String userAcc;
	private String userPwd;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserAcc() {
		return userAcc;
	}
	public void setUserAcc(String userAcc) {
		this.userAcc = userAcc;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	@Override
	public String toString() {
		return "AccountEntity [id=" + id + ", userAcc=" + userAcc
				+ ", userPwd=" + userPwd + "]";
	}
	
	
}
