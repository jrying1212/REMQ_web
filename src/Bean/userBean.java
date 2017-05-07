package Bean;

public class userBean {
	
	private String account;
	private String password;
	public boolean valid;
	public String admin;
	
	public void setAccount(String account){
		this.account = account;
	}
	
	public String getAccount(){
		return account;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return password;
	}

		public void setValid(boolean valid){
		this.valid = valid;
	}
	
	public boolean isValid(){
		return valid;
	}
	
	public void setAdmin(String admin){
	this.admin = admin;
}
	
	public String isAdmin(){
		return admin;
	}
}
