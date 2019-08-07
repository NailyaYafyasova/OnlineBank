import java.util.ArrayList;
import java.util.Calendar;

public class Loan extends Account {
	private double amountOwed;
	private double interest;
	private int difference;
	private double monthlyInterest;
	private double discountFactor;
	private double monthlyPmt;
	private int monthsPaid;
	private Date payBy;
	private boolean[] monthPMTS =  new boolean[difference];
	//private double interestAmount;
	//private double interestPaid;
	
	//private ArrayList<Double> interestToBePaid = new ArrayList<Double>();
	//private int currentInterest;
	
	
	public Loan() {
		super();
	}
	
	public Loan(double amount, Currency curr, Date pay, double interest) {
		super(amount, curr);
		type = "Loan";
		payBy = pay;
		this.interest = interest;
		this.monthlyInterest = this.interest / 12;
		this.difference = Date.differenceInMonths(payBy);
		this.discountFactor = (Math.pow(1 + monthlyInterest, difference) - 1) / (monthlyInterest * Math.pow(1 + monthlyInterest, difference));
		amountOwed = monthlyPmt * difference;
	}
	
	public double amountOwed() {
		return amountOwed;
	}
	
	
//	public boolean payInterest() {
//			if ( (Date.getCurrentDate().getMonth() >= payBy.getMonth()) && (Date.getCurrentDate().getDay() >= payBy.getDay()) && (Date.getCurrentDate().getYear() > payBy.getYear()) ) {
//				double difference = Date.getCurrentDate().getYear() - payBy.getYear();
//				interestPaid = Math.pow(interest, difference);
//				balance -= balance*interestPaid;
//				return true;
//			}
//			System.out.println("The account hasn't reached its maturity date yet.");
//			return false;
	
	
	
	
	
	public boolean makeTransaction(String action, double amount, String reference, Date tdate) {
		// new transaction(date, action, amount, account, reference)
		
		if (action.equals("payment")) { // sending money to a business
			String type = "OUT";
			Transaction t = null;
			if (takeOut(amount) != -1)
				// make transaction
				makeTransaction("fee", Fee.Transfer.amount, reference, tdate );
				t = new Transaction(tdate, this, amount, reference, type);
				transactions.add(t);
				
				return true;
		}
		else if (action.equals("receipt")) { // receiving money to account 
			String type = "IN";
			Transaction t;
			deposit(amount, getCurrency());
			// make transaction
			t = new Transaction(tdate, this, amount, reference, type);
			transactions.add(t);
			
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
			
			return true;
		} 
		
		else if (action.equals("loan pmt")) { // monthly loan payment
			String type = "IN";
			Transaction t;
			reference = "Loan Payment";
			makePayment();
			// make transaction 
			t = new Transaction(tdate, this, amount, reference, type);
			transactions.add(t);
		
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
	

	
	// NEED TO MAKE IT A TRANSACTION
	public boolean makePayment() {
		if (monthsPaid != difference) {
			monthsPaid++;
			amountOwed -= monthlyPmt;
			return true;
		}
		return false;
	}

}
