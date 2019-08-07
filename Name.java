public class Name {
	private String fname, mname, lname; 
	
	public Name() {
		fname = "UNDEFINED";
		mname = "UNDEFINED";
		lname = "UNDEFINED";
	}
	
	public Name(String fname, String lname) {
		this.fname = fname;
		this.lname = lname;
	}
	
	public Name(String fname, String mname, String lname) {
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
	}
	
	public String getFname() {
		return fname;
	}

	public String getLname() {
		return lname;
	}
	
	public String toString() {
		if (mname == null)
			return (fname + " " + lname);
		else
			return (fname + " " + mname + " " + lname);
	}
	

}
