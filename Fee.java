
public enum Fee {
	Deposit(1),
	Withdrawal(2),
	Transfer(2),
	Open(5),
	Close(5),
	Sell(5),
	Buy(5);
	
	///
	public final double amount;
	
	private Fee(double amount) {
		this.amount = amount;
	}
}
