package views;
public class Manager extends Person{

	public Manager(String fname, String lname, String login) {
		super(fname, lname, login);
	}
	
	public Manager(String fname, String mname, String lname, String login) {
		super(fname, mname, lname, login);
	}
	
	public String toString() {
		return ( "Manager " + this.name() );
	}
	
	// need to know how the Bank works
	public String checkClient(Client customer) {
		Client client = Database.getClient(customer);
		String s = client.viewBalances();
		
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
	
	
	// need to know how the Bank works;
	public String getDailyReport(Date tdate) {
		return Database.getDailyReport(tdate);
	}

}
