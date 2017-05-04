package Bean;

public class userBean {
	
	private String account;
	private String password;
	private String company;
	public boolean valid;
	
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
	
	public void setCompany(String company){
		this.company = company;
	}
	
	public String getCompany(){
		return company;
	}
	
	public void setValid(boolean valid){
		this.valid = valid;
	}
	
	public boolean isValid(){
		return valid;
	}
}
