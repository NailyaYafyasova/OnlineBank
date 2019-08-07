package fancy_bank.portfolio;

import fancy_bank.org.Transaction;

public class test {
	public static void main(String[] args) {
		Portfolio test = new Portfolio();
		test.buy(BondItem.Test, 10);
		
		test.transactions.add(new Transaction());
		
		System.out.println(test.transactions);
		System.out.println(test.getSecurityTransactions()[0]);
	}
}
