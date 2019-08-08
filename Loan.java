package views;
public class Loan extends Account {
	private double amountOwed;
	private double interest = 0.06;
	private double interestPaid;
	private Date payBy;
	
	public Loan() {
		super();
	}
	
	public Loan(double amount, Currency curr, Date pay) {
		super(amount, curr);
		type = "loan";
		payBy = pay;
		double difference = payBy.getYear() - Date.getCurrentDate().getYear();
		//System.out.println(difference);
		amountOwed = (interest * difference * amount) / (1 - (Math.pow(1 + interest, -difference)) );
	}
	
	public double amountOwed() {
		return amountOwed;
	}
	
	
	public boolean payInterest() {
		if ( (Date.getCurrentDate().getMonth() >= payBy.getMonth()) && (Date.getCurrentDate().getDay() >= payBy.getDay()) && (Date.getCurrentDate().getYear() > payBy.getYear()) ) {
			double difference = Date.getCurrentDate().getYear() - payBy.getYear();
			interestPaid = Math.pow(interest, difference);
			balance -= balance*interestPaid;
			return true;
		}
		System.out.println("The account hasn't reached its maturity date yet.");
		return false;
	}

}
