import java.util.ArrayList;
import java.util.Random;

public class Account {
	
	protected String type;
	protected double balance;
	protected Currency currency;
//	private String accountID;
	protected String accountID;
	protected Date dateOpened;
	
	protected ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	protected int tindex = 0;
	
	
	private Date globalDate; // MOVE SOMEWHERE ELSE? 
	
	Random random = new Random();
	
	public Account() {
		balance = 0; 
		currency = new Currency();
		accountID = String.format("%04d", random.nextInt(10000)); // 4 digit ID
	}
	
	public Account(double amount, Currency curr) {
		balance = Math.round(amount * 100.00) / 100.00;
		currency = curr;
		accountID = String.format("%04d", random.nextInt(10000)); // 4 digit ID
	}
	
	public Account(double amount, Currency curr, Date date) {
		dateOpened = date;
		balance = Math.round(amount * 100.00) / 100.00;
		currency = curr;
		accountID = String.format("%04d", random.nextInt(10000)); // 4 digit ID
	}
	
	public Account(double amount, Currency curr, String accountID) {
		balance = Math.round(amount * 100.00) / 100.00;;
		currency = curr;
		this.accountID = accountID;
		this.transactions.addAll(BankDatabase.loadTransactions(accountID, this));
	}
	
	public double getBalance() {
		return balance;
	}
	
	public Currency getCurrency() {
		return currency;
	}
	
	
	public String getID() {
		return accountID;
	}
	
	public String type() {
		return type;
	}
	
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}
	
	
	public int getTransactionIndex() {
		return tindex;
	}
	
	
	public void incrementTransactionIndex() {
		tindex++;
	}
	
	
	public boolean equals(Account other) {
		if (this.accountID == other.accountID)
			return true;
		else 
			return false;
	}
	
	
 	
	// TRANSACTION METHOD - USE THIS AND TRANSFER INSTEAD OF DEPOSIT AND TAKE OUT
	
																// business name
	public boolean makeTransaction(String action, double amount, String reference, Date tdate) {
		// new transaction(date, action, amount, account, reference)
		
		if (action.equals("payment")) { // sending money to a business or withdrawing
			String type = "OUT";
			Transaction t = null;
			if (takeOut(amount) != -1)
				// make transaction
				t = new Transaction(tdate, this, amount, reference, type);
				//transactions[tindex] = t;		remove this?
			transactions.add(t);
			BankDatabase.addTransaction(t,accountID);
			BankDatabase.adjustAccountBalance(this, type);
				tindex++;
				return true;
		}
		else if (action.equals("receipt")) { // receiving money to account 
			String type = "IN";
			Transaction t;
			deposit(amount, getCurrency());
			// make transaction
			t = new Transaction(tdate, this, amount, reference, type);
			//transactions[tindex] = t;		remove this?
			transactions.add(t);
			BankDatabase.addTransaction(t, accountID);
			BankDatabase.adjustAccountBalance(this, type);
			tindex++;
			return true;
		}
		
		else if (action.equals("deposit")) { // depositing money to own account
			String type = "IN";
			Transaction t;
			reference = "Deposit";
			deposit(amount, getCurrency());
			// make transaction 
			t = new Transaction(tdate, this, amount, reference, type);
			transactions.add(t);
			BankDatabase.addTransaction(t, accountID);
			BankDatabase.adjustAccountBalance(this, type);
			tindex++;
			return true;
		}
		
		System.out.println("Invalid Transaction");
		return false;
	}
	
	
	
	public boolean transfer(Account acc, double amount, Date tdate) {
		
		if (this.getCurrency().getAcronym().equals(acc.getCurrency().getAcronym())) {
			this.takeOut(amount);
			acc.deposit(amount, acc.getCurrency());
			Transaction t,t2;
			t = new Transaction(tdate, this, amount, "Transfer " + acc.getID(), "OUT");
			t2 = new Transaction(tdate, acc, amount, "Transfer " + this.getID(), "IN");
			
			transactions.add(t);
			acc.getTransactions().add(t2);
			
			BankDatabase.addTransaction(t, accountID);
			BankDatabase.adjustAccountBalance(this, "OUT");
			BankDatabase.addTransaction(t2, acc.getID());
			BankDatabase.adjustAccountBalance(acc, "IN");
			
			return true;
		}
		else {
			System.out.println("Transfer failed. The accounts are based in different currencies.");
			return false;
		}
	}
	
	
	
	public boolean deposit(double money, Currency curr) {
		if (currency == curr) {
			balance += Math.round(money * 100.00) / 100.00;
			return true;
		}	
		else { 
			// maybe give them option to switch currencies or convert deposit into curr in GUI
			System.out.println("Invalid Currency. This account is opened in " + currency.getAcronym() +".");
			return false;
		}
	}
	
	public double takeOut(double money) {
		if (Math.round(money * 100.00) / 100.00 <= balance) {
			balance -= Math.round(money * 100.00) / 100.00;
			return money;
		}
		else {
			System.out.println("Insufficient funds. Your current balance is " + balance);
			return -1;
		}
	}
	
	public boolean changeAccountCurrency(Currency cur) {
		if (getCurrency().equals(cur)) {
			System.out.println("Account already based on " + cur.getAcronym());
			return false;
		}
		else {
			double newbal = currency.convertTo(balance, cur.getAcronym()); // new balance after Currency change
			balance = newbal;
			currency = cur;
			return true;
		}
	}
	
	public String toString() {
		return balance + " " + currency;
	}
	
	public String displayTransactions() {
		String res = "<html>";
		for (int i = 0; i < transactions.size(); i++) {
			if (transactions.get(i) == null) 
				return res;
			res+= transactions.get(i) + "<br/>";
		}
		return res+"</html>";
	}
	
//	public static void main(String[] args) {
//		Currency USD = new Currency("USD", "Dollars");
//		Currency RUB = new Currency("RUB", "Rubles");
//		System.out.println(USD.convertTo(500, "RUB"));
//		
//		Account c = new Account(500,USD);
//		c.deposit(500.53512312, USD);
//		System.out.println(c);
//		c.takeOut(50.544);
//		System.out.println(c);
//		
//		Account d = new Account(100,USD);
//		//c.transfer(d, 50);
//		System.out.println(c);
////		System.out.println(d);
////		
////		d.changeAccountCurrency(RUB);
////		System.out.println(d);
//		
//		Date date = new Date(1,1,1990);
//		c.makeTransaction("payment", 25.50, "Dominos", date);
//		c.makeTransaction("deposit", 5.50, "Dominos", date);
//		c.makeTransaction("receipt", 15.00, "Work", date);
//		System.out.println(c);
//		System.out.println(c.displayTransactions());
//	}
	
	

	
}
