import java.util.Scanner;

public class ATM {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please select how you would like to log in.(C/M)"); 
		
		String answer = scan.nextLine();
		if ( answer.equals("c") ) {
			System.out.println("Please spell your name (first name, last name");
			answer = scan.next();
			String fname = answer;
			answer = scan.next();
			String lname = answer;
			
			System.out.println("Please create a login");
			answer = scan.next();
			String login = answer;
			Client c = new Client(fname, lname, login);
			System.out.println("Please create a password");
			answer = scan.next();
			c.setPassword(answer);
			
			
			System.out.println("Please create an account with us");
			Currency usd = new Currency("USD", "dollar");
			c.openAccount(new Account(500, usd));
			System.out.println(c);
			System.out.println("Please input account number");
			answer = scan.next();

			
			c.deposit(c.getAccount(answer), 670, Date.getCurrentDate());
			System.out.println(c);
			System.out.println();
			
			System.out.println("new balance is " + c.getBalance(c.getAccount(answer)));
			c.withdraw(c.getAccount(answer), 200, Date.getCurrentDate());
			System.out.println(c);
			System.out.println();
			
			c.openAccount(new Account(40000, usd));
			System.out.println(c);
			
			System.out.println("Please input account number");
			String answer2 = scan.next();
			System.out.println();
			
			System.out.println(answer);
			System.out.println(answer2);
			c.transfer(c.getAccount(answer), c.getAccount(answer2), 400, Date.getCurrentDate());
			c.viewTransactions();
			System.out.println(c.getAccount(answer2).getBalance());
			
			c.payment(c.getAccount(answer2), "Spotify" , 25.99, Date.getCurrentDate());
			c.receipt(c.getAccount(answer2), "Work", 30.2020, Date.getCurrentDate());
			c.viewBalances();
			c.viewTransactions();
			
			c.requestLoan(new Loan(50000, usd, new Date(7, 30, 2025)));
			c.openAccount(new Savings(10000, usd));
			System.out.println(c);
			System.out.println("Please input account number");
			String answer3 = scan.next();
			((Savings) c.getAccount(answer3)).getInterest();
			
			
			System.out.println(c);
			
			Manager m = new Manager("man", "ager", "efkjafdkhjd");
			m.checkClient(c);
			
			
		}
	}
	
}
