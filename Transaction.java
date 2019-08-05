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
	
	
	public String toString() {
		String sym = "";
		if (this.type.equals("IN"))
			sym = "+";
		else if (this.type.equals("OUT"))
			sym = "-";
		
		
		return this.date + " ACC.: " + accountID + " REF.: " + reference + " TYPE: " + type + " " + sym + amount + " BAL.: " + newBalance; 
	}
}
