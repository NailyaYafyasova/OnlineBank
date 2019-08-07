public class LoanRequest {
	
	private double amount;
	private double collateral;
	private Currency currency;
	
	public LoanRequest(double amount, double collateral, Currency cur) {
		this.amount = amount;
		this.collateral = collateral;
		this.currency = cur;
	}
	
	public double amount() {
		return amount;
	}
	
	public double collateral() {
		return collateral;
	}
	
	public Currency currency() {
		return currency;
	}

}
