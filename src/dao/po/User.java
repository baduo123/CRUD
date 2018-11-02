package dao.po;

import util.annotion.Column;
import util.annotion.ID;
import util.annotion.PO;
import util.annotion.TABLE;

@TABLE("User")
public class User {
	@Column("uname")
	private String uname;
	@Column("account")
	private String account;
	@Column("pass")
	private String pass;
	@Column("id")
	@ID("id")
	private Integer id;
	
	@Override
	public String toString() {
		return "User [uname=" + uname + ", account=" + account + ", pass=" + pass + ", id=" + id + "]";
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User(String uname, String account, String pass) {
		super();
		this.uname = uname;
		this.account = account;
		this.pass = pass;
	}

	
	
	
}
