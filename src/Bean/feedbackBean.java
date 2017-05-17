package Bean;

public class feedbackBean {
	
	private String projID;
	private String select;
	private String content;
	private String time;
	
	public void setProjID(String projID){
		this.projID = projID;
	}
	
	public String getProjID(){
		return projID;
	}
	
	public void setSelect(String select){
		this.select = select;
	}
	
	public String getSelect(){
		return select;
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
