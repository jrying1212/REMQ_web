package Bean;

public class feedbackBean {
	
	private String projID;
	private String content;
	private String time;
	
	public void setProjID(String projID){
		this.projID = projID;
	}
	
	public String getProjID(){
		return projID;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String getTime(){
		return time;
	}

}
