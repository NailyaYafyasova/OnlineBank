public class Savings extends Account {
	private double interest = 0.05;
	private double accrued;
	private Date over10;
	private Date newDate;
	
	public Savings() {
		super();
	}
	
	public Savings(double amount, Currency curr) {
		super(amount, curr);
		type = "Savings";
		if (amount >= 10000) {
			over10 = Date.getCurrentDate();
			newDate = new Date(over10.getMonth(),over10.getDay(),over10.getYear()+1);
		}
	}
	
	public Savings(double amount, Currency curr, Date date) {
		super(amount,curr,date);
		type = "Savings";
		if (amount >= 10000) {
			over10 = Date.getCurrentDate();
			newDate = new Date(over10.getMonth(),over10.getDay(),over10.getYear()+1);
		}
	}
	
	
	public boolean makeTransaction(String action, double amount, String reference, Date tdate) {
		// new transaction(date, action, amount, account, reference)
		
		if (action.equals("payment")) { // sending money to a business
			String type = "OUT";
			Transaction t = null;
			if (takeOut(amount) != -1)
				// make transaction
				t = new Transaction(tdate, this, amount, reference, type);
				transactions.add(t);
				
				return true;
		}
		
		else if (action.equals("receipt")) { // receiving money to account 
			String type = "IN";
			deposit(amount, getCurrency());
			Transaction t = new Transaction(tdate, this, amount, reference, type);
			transactions.add(t);
			
			
			if (over10 != null) {
				if (this.getBalance() >= 10000) {
					over10 = Date.getCurrentDate();
					newDate = new Date(over10.getMonth(),over10.getDay(),over10.getYear()+1);
				}
			}
			return true;
		}
		
		
		else if (action.equals("deposit")) { // depositing money to own account
			String type = "IN";
			reference = "Deposit";
			deposit(amount, getCurrency());
			// make transaction 
			Transaction t = new Transaction(tdate, this, amount, reference, type);
			transactions.add(t);
			
			
			if (over10 != null) {
				if (this.getBalance() >= 10000) {
					over10 = Date.getCurrentDate();
					newDate = new Date(over10.getMonth(),over10.getDay(),over10.getYear()+1);
				}
			}
			return true;
		}
		
		else if (action.equals("fee")) {
			String type = "OUT";
			Transaction t;
			takeOut(amount);
			t = new Transaction(tdate, this, amount, reference, type);
			transactions.add(t);
			
			return true;
		}
		
		System.out.println("Invalid Transaction");
		return false;
	}
	
	
	public boolean transfer(Account acc, double amount, Date tdate) {
		if ( !this.getCurrency().getAcronym().equals(acc.getCurrency().getAcronym() )) {
			Currency currOther = acc.getCurrency();
			double amountNew = currency.convertTo(amount, currOther.getAcronym());
			
			this.takeOut(amount);
			acc.deposit(amountNew, currOther);
			Transaction t,t2 = null;
			t = new Transaction(tdate, this, amount, "Transfer " + acc.getID(), "OUT");
		
			transactions.add(t);
			t2 = new Transaction(tdate, acc, amountNew, "Transfer " + this.getID(), "IN");
			
			acc.getTransactions().add(t2);
			
			if (over10 != null) {
				if (this.getBalance() >= 10000) {
					over10 = Date.getCurrentDate();
					newDate = new Date(over10.getMonth(),over10.getDay(),over10.getYear()+1);
				}
			}
			return true;
		}
		else {
			this.takeOut(amount);
			acc.deposit(amount, acc.getCurrency());
			Transaction t = new Transaction(tdate, this, amount, "Transfer " + acc.getID(), "OUT");
			Transaction t2 = new Transaction(tdate, acc, amount, "Transfer " + this.getID(), "IN");

			transactions.add(t);
			acc.getTransactions().add(t2);
			if (over10 != null) {
				if (this.getBalance() >= 10000) {
					over10 = Date.getCurrentDate();
					newDate = new Date(over10.getMonth(),over10.getDay(),over10.getYear()+1);
				}
			}
			
			return true;
		}
		
	}
	
	
	public boolean getInterest() {
		newDate = new Date(5, 6, 2008);
		if ( (Date.getCurrentDate().getMonth() >= newDate.getMonth()) && (Date.getCurrentDate().getDay() >= newDate.getDay()) && (Date.getCurrentDate().getYear() > newDate.getYear()) ) {
			double difference = Date.getCurrentDate().getYear() - newDate.getYear();
			accrued = Math.pow(1+interest, difference);
			balance *= accrued;
			return true;
		}
		System.out.println("The account hasn't reached its maturity date yet.");
		return false;
	}

}
