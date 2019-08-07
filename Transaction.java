public class Transaction {
	
	protected Date date;
	private double amount;
	protected double newBalance;
	private String accountID;
	private String reference;
	protected String type; // IN OR OUT
	

	public Transaction() {
		this.date = Date.getCurrentDate();
	}
	
	public Transaction(Date date, Account acc, double amount, String reference, String type) {
		this.date = date;
		
		this.amount = amount;
		this.reference = reference;
		this.type = type;
		newBalance = acc.getBalance();
		accountID = acc.getID();
	}
	
	public String type() {
		return type;
	}
	
	
	public String toString() {
		String sym = "";
		if (this.type.equals("IN"))
			sym = "+";
		else if (this.type.equals("OUT"))
			sym = "-";
		
		
		return this.date + " ACC.: " + accountID + " REF.: " + reference + " TYPE: " + type + " " + sym + amount + " BAL.: " + newBalance; 
	}
}
