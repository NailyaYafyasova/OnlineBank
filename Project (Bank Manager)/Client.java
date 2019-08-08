package views;
import java.util.ArrayList;
import java.util.Scanner;

public class Client extends Person{
	//private ArrayList<Account> accounts = new ArrayList<Account>();
	private int userID;
	//private ArrayList<Account> accounts = BankDatabase.loadAccounts(userID);
	private ArrayList<Account> accounts = new ArrayList<Account>();
	private double loan;
	private boolean haveLoan = false;
	private boolean collateral;
	



	public Client(String fname, String lname, String login) {
		super(fname, lname, login);
		//Database.addClient(this);
	}


	public Client(String fname, String mname, String lname, String login) {
		super(fname, mname, lname, login);
		//Database.addClient(this);
	}

	// constructor that includes userID
	public Client(String fname, String lname, String login, int userID) {
		super(fname, lname, login);
		this.userID = userID;
		//this.accounts = BankDatabase.loadAccounts(userID);
		this.accounts.addAll(BankDatabase.loadAccounts(userID));
	}


	public void openAccount(Account acc) {
		accounts.add(acc);

	}
	
	
	public int getUserID() {
		return this.userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public void openTypeAccount(String type, double balance, Currency currency) { //, Date date) {
		if (type.equals("Checking")) {
			Checking newchecking = new Checking(balance, currency);
			accounts.add(newchecking);
			BankDatabase.addAccount(newchecking, this.userID);
		}
		else if (type.equals("Savings")) {
			Savings newsavings = new Savings(balance, currency);
			accounts.add(newsavings);
			BankDatabase.addAccount(newsavings, this.userID);
		}

		// ADD SECURITIES LATER
	}


	public void closeAccount(Account acc) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).equals(acc))
				accounts.remove(acc);
		}
	}

	public boolean equals(Client other) {
		if ( this.login().equals(other.login()) )
			return true;
		return false;
	}


	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public boolean hasLoan() {
		return haveLoan;
	}

	public double loanSize() {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).type().equals("loan")) {
				return accounts.get(i).getBalance();
			}
		}
		return -999999;
	}

	public Account getAccount(String id) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getID().equals(id)) {
				//System.out.println(accounts.get(i));
				return accounts.get(i);
			}
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

	public double withdraw(Account acc, double amount, Date tdate) {

		if ( acc.makeTransaction("payment", amount, "WITHDRAWAL", tdate) )
			return amount;

		return -999999;
	}



	public boolean deposit(Account acc, double amount, Date tdate) {
		return acc.makeTransaction("deposit", amount, "Deposit", tdate);
	}


	public boolean payment(Account acc, String reference, double amount, Date tdate) {
		return acc.makeTransaction("payment", amount, reference, tdate);
	}


	public boolean receipt(Account acc, String reference, double amount, Date tdate) {
		return acc.makeTransaction("receipt", amount, reference, tdate);
	}


	public boolean transfer(Account first, Account second, double amount, Date tdate) {
		return first.transfer(second, amount, tdate);
	}


	public void changeAccountCurrency(Account acc, Currency cur) {
		acc.changeAccountCurrency(cur);
	}


	// need to figure out the loan
	public boolean requestLoan(Loan loan) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Do you have a collateral to cover your loan of this size: " + loan.getBalance() + "? (Y/N)");
		String answer = scan.nextLine();

		if (answer.equals("Y")) 
			collateral = true;
		else {
			collateral = false;
			return false;
		}
		haveLoan = true;
		this.openAccount(loan);
		return true;

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


	public String viewBalances() {
		String s = "";
		System.out.println("TEST: ACCOUNT BALANCES: " + accounts.size());
		for (int i = 0; i < accounts.size(); i++) {
			s += "Account #" + accounts.get(i).getID() + " has a balance of " + accounts.get(i).getBalance() +"\n";
		}

		System.out.println(s);
		return s;
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


	//	public void viewBalances() {
	//		String s = "";
	//		
	//		for (int i = 0; i < accounts.size(); i++) {
	//			s += "Account #" + accounts.get(i).getID() + " has a balance of " + accounts.get(i).getBalance() +"\n";
	//		}
	//		
	//		System.out.println(s);
	//	}


}
