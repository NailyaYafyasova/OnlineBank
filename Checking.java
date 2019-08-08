public class Checking extends Account {

	public Checking() {
		super();
	}
	
	public Checking(double amount, Currency curr) {
		super(amount, curr);
		type = "Checking";
	}
	
	public Checking(double amount, Currency curr, Date date) {
		super(amount,curr,date);
		type = "Checking";
	}
	
	public Checking(double amount, Currency curr, String accountid) {
		super(amount, curr, accountid);
		type = "Checking";
	}
	

}
