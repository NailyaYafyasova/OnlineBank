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
	
	public String toString() {
		//if (mname == null) {
		if (mname.equals("UNDEFINED")) {
			return (fname + " " + lname);
		}
		else {
			return (fname + " " + mname + " " + lname);
		}
	}
	
	// get fname of user (for 
	public String getFname() {
		return fname;
	}
	
	// get lname of user (for DB)
	public String getLname() {
		return lname;
	}

}
