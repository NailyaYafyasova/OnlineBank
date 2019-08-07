
import java.util.*;
import java.util.function.ToDoubleBiFunction;

public class Portfolio extends Account {
	private List<Security> trades;

	public Portfolio() {
		super();
		trades = new LinkedList<Security>();
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
		}
		operation(sec, amount, "BUY " + sec.type ,SecurityTransaction.BUY);
	}

	public void sell(Enum<?> obj, double amount) throws Exception {
		Security sec = find(obj);
		if (sec == null)
			throw new Exception("Not Found");
		if (amount > sec.amount)
			throw new Exception("Amount Overflow");
		operation(sec, amount, "SELL " + sec.type , SecurityTransaction.SELL);
	}

	public void sellAll(Enum<?> obj) throws Exception {
		Security sec = find(obj);
		if (sec == null)
			throw new Exception("Not Found");
		operation(sec, sec.amount, "SELL ALL", SecurityTransaction.SELL);
	}

	private void operation(Security sec, double amount, String type, ToDoubleBiFunction<Double, Double> operation) {
		transactions.add(new SecurityTransaction(this, sec, amount, type, operation));
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

	public double getUnrealizedValueBond() {
		return trades.stream().filter(x -> x instanceof Bond).mapToDouble(x -> x.getUnrealized()).sum();
	}

	public double getUnrealizedValueStock() {
		return trades.stream().filter(x -> x instanceof Stock).mapToDouble(x -> x.getUnrealized()).sum();
	}

	public double getUnrealizedValues() {
		return trades.stream().mapToDouble(x -> x.getUnrealized()).sum();
	}
	
	public String toString() {
		String s = "Portfolio Account #" + this.getID() + " has " + trades.size() + " type(s) of securities.\n";
		for (int i = 0; i < trades.size(); i++) {
			s+= (int) trades.get(i).amount + " " + trades.get(i).type +  "(s)\n";
		}
		return s;
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