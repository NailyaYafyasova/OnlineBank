package fancy_bank.portfolio;

import java.util.function.ToDoubleBiFunction;

import fancy_bank.org.Account;
import fancy_bank.org.Transaction;

public class SecurityTransaction extends Transaction{

	public final Account account;
	public final Security security;
	public final double amount;
	public final ToDoubleBiFunction<Double, Double> operation;

	public static final ToDoubleBiFunction<Double, Double> BUY = (x, y) -> x + y;
	public static final ToDoubleBiFunction<Double, Double> SELL = (x, y) -> x - y;
	
	public SecurityTransaction(Account acc, Security sec, double amount, ToDoubleBiFunction<Double, Double> operation) {
		super();
		
		this.account = acc;
		this.security = sec;
		this.amount = amount;
		this.operation = operation;
		
		recordTransaction(operation);
	}


	private void recordTransaction(ToDoubleBiFunction<Double, Double> func) {
		account.balance = func.applyAsDouble(account.balance, security.getCurrentValue(amount) * -1);
		security.amount = func.applyAsDouble(security.amount, amount);
		security.buyPrice = func.applyAsDouble(security.buyPrice, security.getCurrentValue(amount));
	}

}
