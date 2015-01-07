package hw5.model;

public class getset {
 
	
	String courseCode;
	
	String courseName;
	
	String prereq;
	
	String error;
	
	String uname;
	
	String pwd;
	
	String firstname;
	
	String lastname;

	
	public getset()
	{}
	
	
	public getset(  String courseCode , String courseName , String prereq){
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.prereq = prereq;
	}



	public getset(String error){
		this.error = error;
	}
		
	public getset( String uname, String pwd, String firstname, String lastname){
		this.uname = uname;
		this.pwd = pwd;
		this.firstname = firstname;
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getError() {
		return error;
	}


	public void setError(String error) {
		this.error = error;
	}


	public String getUname() {
		return uname;
	}


	public void setUname(String uname) {
		this.uname = uname;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getPrereq() {
		return prereq;
	}

	public void setPrereq(String prereq) {
		this.prereq = prereq;
	}
}
