package dto;


public class User_dto {
	private String account;
	private String password;
	private boolean islogin;
	private String msg;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public User_dto() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User_dto [account=" + account + ", password=" + password + ", islogin=" + islogin + "]";
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isIslogin() {
		return islogin;
	}
	public void setIslogin(boolean islogin) {
		this.islogin = islogin;
	}
	public User_dto(String account, String password) {
		super();
		this.account = account;
		this.password = password;
		this.islogin = false;
		this.msg = "’ ∫≈¥ÌŒÛ";
	}
	
	
}
