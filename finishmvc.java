package hw5.model;



public class finishmvc {

	String user;
	
	String time;
	
	String quarter;

	String code;

	String name;

	String prereq;

	public finishmvc() {

	}

	public finishmvc(String quarter, String code) {
		this.quarter = quarter;
		this.code = code;
	}

	public finishmvc(String quarter, String code, String name, String prereq) {
		this.quarter = quarter;
		this.code = code;
		this.name = name;
		this.prereq = prereq;
	}

	public finishmvc(String user, String time, String quarter, String code, String name, String prereq) {
		this.user=user;
		this.time=time;
		this.quarter = quarter;
		this.code = code;
		this.name = name;
		this.prereq = prereq;
	}

	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrereq() {
		return prereq;
	}

	public void setPrereq(String prereq) {
		this.prereq = prereq;
	}

}
