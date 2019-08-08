package views;
public class Transaction {
	
	private Date date;
	private double amount;
	private double newBalance;
	private String accountID;
	private String reference;
	private String type; // IN OR OUT
	

	public Transaction(Date date, Account acc, double amount, String reference, String type) {
		this.date = date;
//		System.out.println(date);
		
		this.amount = amount;
		this.reference = reference;
		this.type = type;
		newBalance = acc.getBalance();
		accountID = acc.getID();
	}
	
	public Transaction(Date date, Account acc, double amount, String reference, String type, String accountID) {
		this.date = date;
//		System.out.println(date);
		
		this.amount = amount;
		this.reference = reference;
		this.type = type;
		newBalance = acc.getBalance();
		this.accountID = accountID;
	}
	
	public Transaction(Date date, Account acc, double amount, double newbalance, String reference, String type, String accountID) {
		this.date = date;
//		System.out.println(date);
		
		this.amount = amount;
		this.reference = reference;
		this.type = type;
		newBalance = newbalance;
		this.accountID = accountID;
	}
	
	// next methods are new (mainly for database use)
	public String getReference() {
		return this.reference;
	}
	
	public double getNewBalance() {
		return this.newBalance;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getAccountID() {
		return this.accountID;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	// getter method for date
	public Date getDateInitialized() {
		return this.date;
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
