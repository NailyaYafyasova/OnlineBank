import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Person{
	
	private static ArrayList<LoanRequest> loanRequests = new ArrayList<LoanRequest>();

	public static final Manager manager = new Manager("Jane", "Doe", "jd");
	public static Date currentSystemDate = new Date();
	
	
	public Manager(String fname, String lname, String login) {
		super(fname, lname, login);
	}
	
	public Manager(String fname, String mname, String lname, String login) {
		super(fname, mname, lname, login);
	}
	

	public static void updateDate(Date newDate) {
		currentSystemDate = newDate;
	}
	
	public static void addLoanRequests(LoanRequest loan) {
		loanRequests.add(loan);
	}
	
	
	public static boolean considerLoan(LoanRequest loan) {
		if (loan.amount() <= loan.collateral())
			return true;
		return false;
	}
	
	
	public static void approveLoan(Client client, LoanRequest loan) {
		if (considerLoan(loan)) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please indicate the annual interest rate");
			double interest = scan.nextDouble();
			System.out.println("Please indicate the due date by entering the month, date, and year on separate lines");
			int month = scan.nextInt();
			int day = scan.nextInt();
			int year = scan.nextInt();
			
			Date dueDate = new Date(month,day,year);
			client.getLoan(loan.amount(), loan.collateral(), loan.currency(), dueDate, interest);
		}
	}
	
	// CONNECT TO SECURITIES AND DATABASE
	public void updateSecurityPrice(Security security, double newPrice) {
		BankDatabase.updateSecurity(newPrice);
		// HOW TO RECORD THIS???
	}
	
//	// CONNECT TO SECURITIES AND DATABASE
//	public boolean addSecurity(Portfolio portfolio, Enum<?> item, double amount) {
//		return portfolio.buy(item, amount);
//	}
	
	// CONNECT TO SECURITIES AND DATABASE
	public void viewSecurities() {
		
	}
	
	
	
	// CONNECT TO THE DATABASE
	public String checkClient(Client customer) {
		Client client = BankDatabase.getClient(customer);
		//String s = client.viewBalances();
		
		if (client.hasLoan()) {
			for (int i = 0; i < client.getAccounts().size(); i++) {
				if (client.getAccounts().get(i).type() == "loan") {
					s += "\n Client"  + client.name() + "has a loan of size " +  Math.round( ((Loan) client.getAccounts().get(i)).amountOwed() * 100.00) / 100.00;
					System.out.println("Client " + client.name() + "has a loan of size " +  Math.round( ((Loan) client.getAccounts().get(i)).amountOwed() * 100.00) / 100.00 );
				}
			}
		}
		return s;
			
	}
	
	
	// CONNECT TO THE DATABASE
	public String getDailyReport(Date tdate) {
		return Database.getDailyReport(tdate);
	}
	
	public String toString() {
		return ( "Manager " + this.name() );
	}

}
