import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class Portfolio extends Account {
	
	private List<Security> trades;

	public Portfolio() {
		super();
		this.accountID = "1234";
		trades = BankDatabase.loadSecurities(this);
		
		System.out.println();
	}

	public Bond[] getBonds() {
		return trades.stream().filter(x -> x instanceof Bond).map(x -> (Bond) x).toArray(Bond[]::new);
	}

	public Stock[] getStocks() {
		return trades.stream().filter(x -> x instanceof Stock).map(x -> (Stock) x).toArray(Stock[]::new);
	}

	public SecurityTransaction[] getSecurityTransactions() {
		return transactions.stream().filter(x -> x.getClass().getName().equals("fancy_bank.portfolio.SecurityTransaction"))
				.toArray(SecurityTransaction[]::new);
	}

	public void buy(Enum<?> obj, double amount) {
		Security sec = find(obj);
		if (sec == null) {
			sec = (obj instanceof BondSecurity) ? new Bond((BondSecurity) obj) : new Stock((StockSecurity) obj);
			trades.add(sec);
			
			if (obj instanceof BondSecurity)
				BankDatabase.insertSecurity((Bond) sec, getID());
			else
				BankDatabase.insertSecurity((Stock) sec, getID());
		}
		operation(sec, amount, SecurityTransaction.BUY);
		
		if (obj instanceof BondSecurity)
			BankDatabase.adjustSecurity((Bond) sec, getID());
		else
			BankDatabase.adjustSecurity((Stock) sec, getID());
	}

	public void sell(Enum<?> obj, double amount) throws Exception {
		Security sec = find(obj);
		if (sec == null)
			throw new Exception("Not Found");
		if (amount > sec.amount)
			throw new Exception("Amount Overflow");
		operation(sec, amount, SecurityTransaction.SELL);
		
		if (obj instanceof BondSecurity)
			BankDatabase.deleteSecurity((Bond) sec, getID());
		else
			BankDatabase.deleteSecurity((Stock) sec, getID());
	}

	public void sellAll(Enum<?> obj) throws Exception {
		Security sec = find(obj);
		if (sec == null)
			throw new Exception("Not Found");
		operation(sec, sec.amount, SecurityTransaction.SELL);
		
		if (obj instanceof BondSecurity)
			BankDatabase.deleteSecurity((Bond) sec, getID());
		else
			BankDatabase.deleteSecurity((Stock) sec, getID());
	}

	private void operation(Security sec, double amount, ToDoubleBiFunction<Double, Double> operation) {
		transactions.add(new SecurityTransaction(this, sec, amount, operation));
	}

	private Security find(Enum<?> obj) {
		Iterator<Security> it = trades.iterator();
		while (it.hasNext()) {
			Security cur = it.next();
			if (cur.getItem() == obj)
				return cur;
		}
		return null;
	}

	public double getUnforseenValueBond() {
		return trades.stream().filter(x -> x instanceof Bond).mapToDouble(x -> x.getUnrealized()).sum();
	}

	public double getUnforseenValueStock() {
		return trades.stream().filter(x -> x instanceof Stock).mapToDouble(x -> x.getUnrealized()).sum();
	}

	public double getUnforseenValues() {
		return trades.stream().mapToDouble(x -> x.getUnrealized()).sum();
	}

	//	@override
	public void close() {
		for (Security sec : trades) {
			try {
				sellAll(sec.getItem());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
