package Bean;

public class resultBean {
	
	private String packageName;
	private String  id;
	private int classNum;
	private double simplicity;
	private double reusability;
	private double cohesion;
	private double coupling;
	private double AHF;
	private double HC;
	private double security;
	private String time;
	private String AHFComment;
	private String HardCodedComment;
	private String cohNeverUsed;
	private String cohSeldomUsed;
	private String couplingAll;
	private String couplingHigh;
	
	public void setPackageName(String packageName){
		this.packageName = packageName;
	}
	
	public String getPackageName(){
		return packageName;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public String getID(){
		return id;
	}
	
	public void setClassNum(int classNum){
		this.classNum = classNum;
	}
	
	public int getClassNum(){
		return classNum;
	}
	
	public void setSimplicity(double simplicity){
		this.simplicity = simplicity;
	}
	
	public double getSimplicity(){
		return simplicity;
	}
	
	public void setResuability(double reusability){
		this.reusability = reusability;
	}
	
	public double getResuability(){
		return reusability;
	}
	
	public void setCohesion(double cohesion){
		this.cohesion = cohesion;
	}
	
	public double getCohesion(){
		return cohesion;
	}
	
	public void setCoupling(double coupling){
		this.coupling = coupling;
	}
	
	public double getCoupling(){
		return coupling;
	}
	
	public void setAHF(double AHF){
		this.AHF = AHF;
	}
	
	public double getAHF(){
		return AHF;
	}
	
	public void setHC(double HC){
		this.HC = HC;
	}
	
	public double getHC(){
		return HC;
	}
	
	public void setSecurity(double security){
		this.security = security;
	}
	
	public double getSecurity(){
		return security;
	}
	
	public void setTime(String time){
		this.time = time;
	}
	
	public String getTime(){
		return time;
	}
	
	public void setAHFComment(String AHFComment){
		this.AHFComment = AHFComment;
	}
	
	public String getAHFComment(){
		return AHFComment;
	}
	
	public void setHardCodedComment(String HardCodedComment){
		this.HardCodedComment = HardCodedComment;
	}
	
	public String getHardCodedComment(){
		return HardCodedComment;
	}

	public void setCohNeverUsed(String cohNeverUsed){
		this.cohNeverUsed = cohNeverUsed;
	}
	
	public String getCohNeverUsed(){
		return cohNeverUsed;
	}
	
	public void setCohSeldomUsed(String cohSeldomUsed){
		this.cohSeldomUsed = cohSeldomUsed;
	}
	
	public String getCohSeldomUsed(){
		return cohSeldomUsed;
	}

	public void setCouplingAll(String couplingAll){
		this.couplingAll = couplingAll;
	}
	
	public String getCouplingAll(){
		return couplingAll;
	}
	public void setCouplingHigh(String couplingHigh){
		this.couplingHigh = couplingHigh;
	}
	
	public String getCouplingHigh(){
		return couplingHigh;
	}
}
