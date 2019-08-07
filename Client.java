import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Person{
	
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private boolean loan;
	private double collateral;
	private boolean portfolio;
	private int userID;
	private boolean enoughSavings = false;
	
	
	public Client(String fname, String mname, String lname, String login) {
		super(fname, mname, lname, login);
	}
	
	
	public Client(String fname, String lname, String login, int userID) {
		super(fname, lname, login);
		this.userID = userID;
		//this.accounts.addAll(BankDatabase.loadAccounts(userID));
	}
	
	
	public void openTypeAccount(String type, double balance, Currency currency) { 
		Account acc = null;
		if (type == "Checking") {
			acc = new Checking(balance, currency);
			acc.makeTransaction("fee", Fee.Open.amount, "OPEN FEE", Date.getCurrentDate());
			//BankDatabase.addAccount(acc, this.userID);
		}
		else if (type == "Savings") {
			acc = new Savings(balance, currency);
			if(balance >= 5000) 
				enoughSavings = true;
			acc.makeTransaction("fee", Fee.Open.amount, "OPEN FEE", Date.getCurrentDate());
			//BankDatabase.addAccount(acc, this.userID);
		}
		else if (type == "Portfolio") {
			if (enoughSavings) {
				if (portfolio) 
					System.out.println("Portfolio is already open. Cannot open another Portfolio.");
				else {
					portfolio = true;
					acc = new Portfolio();
					acc.makeTransaction("fee", Fee.Open.amount, "OPEN FEE", Date.getCurrentDate());
				}
			}
		}
		if (acc != null)
			accounts.add(acc);
	}
	
	
	
	// method to close an account
	public void closeAccount(Account acc) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).equals(acc)) {
				if (accounts.get(i).type() == "Portfolio")
					portfolio = false;
				else if (accounts.get(i).type() == "Loan")
					loan = false;
				acc.makeTransaction("fee", Fee.Close.amount, "CLOSE FEE", Date.getCurrentDate());
				accounts.remove(acc);
			}
		}
	}
	
	// compares userIDs
	public boolean equals(Client other) {
		if ( this.userID == other.userID )
			return true;
		return false;
	}
	
	
	// accessor for the list of accounts
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void applyForLoan(double amount, double collateral, Currency cur) {
		Manager.addLoanRequests(new LoanRequest(amount, collateral, cur));
	}
	
	// due Date - get from the Manager that approves the loan
	public void getLoan(double amount, double collateral, Currency currency, Date dueDate, double interest)  {
		if (loan)
			System.out.println("You have already taken out a loan. Please pay it off to get a new loan.");
			
		else if (collateral < amount) 
			System.out.println("Not enough collateral for the loan amount");

		else {
			this.collateral = collateral;
			Loan loan = new Loan(amount, currency, dueDate, interest);
			loan.makeTransaction("fee", Fee.Open.amount, "OPEN FEE", Date.getCurrentDate());
			accounts.add(loan);
		}
	}
		
	
	// accessor - returns whether the client has a loan
	public boolean hasLoan() {
		return (loan);
	}
	
	
	
	public Account getAccount(String id) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getID().equals(id)) 
				return accounts.get(i);
			
		}
		System.out.println("Account not found.");
		return null;
	}
	
	
	
	public double getBalance(Account acc) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).equals(acc));
				return acc.getBalance();
		}
		System.out.println("Account not found.");
		return -999999;
	}
	
	public String toString() {
		String s = "Client " + this.name() + " has " + accounts.size() + " account(s).\n";
		for (int i = 0; i < accounts.size(); i++) {
			s += "Account #" + accounts.get(i).getID() + " has a balance of " + accounts.get(i).toString() + "\n";
		}
		return s;
	}
	
	public String viewBalances() {
		String s = "";
		
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i) instanceof Portfolio) {
				s += accounts.get(i).toString()+ "\n";
			}
			else
				s += "Account #" + accounts.get(i).getID() + " has a balance of " + accounts.get(i).getBalance() +"\n";
		}
		
		return s;
	}
	
	
	// CHANGE THIS ONCE UPDATE THE TRANSACTION CLASS
	public double withdraw(Account acc, double amount, Date tdate) {
		
		if ( acc.makeTransaction("payment", amount, "WITHDRAWAL", tdate) ) {
			acc.makeTransaction("fee", Fee.Withdrawal.amount, "WITHDRAWAL FEE", Date.getCurrentDate());
			return amount;
		}
		return -999999;
	}
	
	

	public boolean deposit(Account acc, double amount, Date tdate) {
		acc.makeTransaction("fee", Fee.Deposit.amount, "Deposit FEE", Date.getCurrentDate());
		if (acc instanceof Savings) {
			if (acc.balance + amount >= 5000)
				enoughSavings = true;
		}
		return acc.makeTransaction("deposit", amount, "Deposit", tdate);
	}
	
	
	public boolean payment(Account acc, String reference, double amount, Date tdate) {
		acc.makeTransaction("fee", Fee.Transfer.amount, "TRANSFER FEE", Date.getCurrentDate());
		return acc.makeTransaction("payment", amount, reference, tdate);
	}
	
	
	public boolean receipt(Account acc, String reference, double amount, Date tdate) {
		return acc.makeTransaction("receipt", amount, reference, tdate);
	}
	
	
	public boolean transfer(Account first, Account second, double amount, Date tdate) {
		first.makeTransaction("fee", Fee.Transfer.amount, "TRANSFER FEE", Date.getCurrentDate());
		second.makeTransaction("fee", Fee.Transfer.amount, "TRANSFER FEE", Date.getCurrentDate());
		return first.transfer(second, amount, tdate);
	}

	
	public void changeAccountCurrency(Account acc, Currency cur) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).equals(acc))
				accounts.get(i).changeAccountCurrency(cur);
		}
	}
	
	
	// NEED TO CONNECT WITH THE DATABASE
	public ArrayList<Security> getSecuritiesInfo() {
		String s = "";
		return BankDatabase.securitiesInfo();
	}
	
	
	// need to check in with the implementation of the Bank
	public String viewTransactions() {
		String s = "";
		for (int i = 0; i < accounts.size(); i++) {
			s += "</br>Account: " + accounts.get(i).getID() + "</br>";
			System.out.println("Account: " + accounts.get(i).getID());
			
			s += accounts.get(i).displayTransactions();
			System.out.println( accounts.get(i).displayTransactions() + "\n" );	
		}
		return s;
	}
	
	// NEED TO CONNECT WITH THE PORTFOLIO
	public boolean buyBonds(BondSecurity bond, double amount) {
		if (portfolio) {
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i) instanceof Portfolio)
					try {
						((Portfolio) accounts.get(i)).buy(bond, amount);
						accounts.get(i).makeTransaction("fee", Fee.Buy.amount, "BUY FEE", Date.getCurrentDate());
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
			}
		}
		return false;
	}
	
	// NEED TO CONNECT WITH THE PORTFOLIO
	public boolean buyStocks(StockSecurity stock, double amount) {
		if (portfolio) {
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i) instanceof Portfolio)
					try {
						((Portfolio) accounts.get(i)).buy(stock, amount);
						accounts.get(i).makeTransaction("fee", Fee.Buy.amount, "BUY FEE", Date.getCurrentDate());
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
			}
		}
		return false;
	}
	
	
	// NEED TO CONNECT WITH THE PORTFOLIO
	public boolean sellStocks(StockSecurity stock, double amount) {
		if (portfolio) {
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i) instanceof Portfolio)
					try {
						((Portfolio) accounts.get(i)).sell(stock, amount);
						accounts.get(i).makeTransaction("fee", Fee.Sell.amount, "SELL FEE", Date.getCurrentDate());
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
			}
		}
		return false;
	}
	
	
	public boolean sellBonds(BondSecurity bond, double amount) {
		if (portfolio) {
			for (int i = 0; i < accounts.size(); i++) {
				if (accounts.get(i) instanceof Portfolio)
					try {
						((Portfolio) accounts.get(i)).sell(bond, amount);
						accounts.get(i).makeTransaction("fee", Fee.Sell.amount, "SELL FEE", Date.getCurrentDate());
						return true;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
			}
		}
		return false;
	}
	
	
	private Portfolio getPortfolio() {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i) instanceof Portfolio) {
				return (Portfolio)accounts.get(i);
			}
		}
		return null;
	}
	
	public SecurityTransaction[] viewTradingHistory() {
		if (getPortfolio() != null) {
			return getPortfolio().getSecurityTransactions();
		}
		return null;
	}
	
	public ArrayList<Transaction> getTypeTransaction(String type) {
		ArrayList<Transaction> list = new ArrayList<Transaction>();
		
		for (int i = 0; i < accounts.size(); i++) {
			for (int j = 0; j < accounts.get(i).getTransactions().size(); j ++) {
				if ( accounts.get(i).getTransactions().get(j).type() == type ) 
					list.add( accounts.get(i).getTransactions().get(j) );
			}
		}
		return list;
	}
	
	public int countAccounts() {
		int count = 0;
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i) != null)
				count++;
		}
		return count;
	}
	
	public String viewAccounts() {
		String res = "<html>";
		if (countAccounts() == 0) {
			res += "You have no accounts open with us.";
		}
		else {
			for (int i = 0; i < countAccounts(); i++) {
				res+= accounts.get(i).type + " " + accounts.get(i).getID() + " | BALANCE: " + accounts.get(i).getBalance() + " " + accounts.get(i).getCurrency() + "<br/>";
			}
		}
		res += "</html>";
		return res;
	}
	
	public String viewAccount(Account acc) {
		String s = "";
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).equals(acc)) {
				s += accounts.get(i).type + " Account #" + accounts.get(i).getID() + "\nBalance: " + accounts.get(i).getBalance() + " " + accounts.get(i).currency +"\n\n";
				s += accounts.get(i).displayTransactions();
			}
		}
		return s;
	}
	
	
	public static void main(String[] args) {
		Client client1 = new Client("jane","doe", "janedow", 1);
		client1.setPassword("janedoe");
		
		//client1.openTypeAccount("Checking", 100, new Currency("USD", "Dollars"));
		client1.openTypeAccount("Savings", 6000, new Currency("RUB", "Rubles"));
		client1.getLoan(10000, 50000, new Currency("USD", "USD"), new Date(9,1, 2020), 0.05);
		
		System.out.println(client1);
		
		client1.openTypeAccount("Portfolio",10, new Currency("USD", "USD"));
		//System.out.println(client1);
		
		System.out.println(client1);
		//System.out.println("Please input 1st account number");
		
		Scanner scan = new Scanner(System.in);
		//String answer = scan.next();
		//System.out.println("Please input 2nd account number");
		String answer2;

		
		//client1.deposit(client1.getAccount(answer), 670, Date.getCurrentDate());
		//System.out.println(client1);
		//System.out.println();
		
		//client1.transfer(client1.getAccount(answer), client1.getAccount(answer2),670, Date.getCurrentDate());
		//System.out.println(client1);
		//System.out.println();
		
		//System.out.println("Please input account number");
		//answer2 = scan.next();
		//Bond bond = new Bond(BondSecurity.Test);
		client1.buyBonds(BondSecurity.Test, 5);
		client1.buyStocks(StockSecurity.Apple, 3);
		client1.sellStocks(StockSecurity.Apple, 3);
		System.out.println(client1);
		System.out.println("Please input account number");
		answer2 = scan.next();
		System.out.println(client1.getAccount(answer2).getStringTransactions());
		
		System.out.println("Please input account number");
		answer2 = scan.next();
		System.out.println(client1.viewAccount(client1.getAccount(answer2)));
		System.out.println(client1.viewBalances());
		
		
	}

}
