package fancy_bank.portfolio;

import fancy_bank.org.*;

public abstract class MyTransaction {
	public final Account sender;
	public final Account reciever;
	public final double amount;
	
	public MyTransaction(Account sender, Account reciever, double amount) {
		this.sender = sender;
		this.reciever = reciever;
		this.amount = amount;
		
		recordTransaction();
	}
	
	protected abstract void recordTransaction();
	
	public String toString() {
		return String.format("(%s, %.2f)", this.getClass().getName(), amount);
	}
}
