package fancy_bank.portfolio;

import java.util.*;
import java.util.stream.Collectors;

import fancy_bank.org.*;

public class PortfolioAccount extends Account{
	private List<BondSecurity> bonds;
	private List<StockSecurity> stocks;

	public PortfolioAccount() {
		super();
		bonds = new LinkedList<BondSecurity>();
		stocks = new LinkedList<StockSecurity>();
	}
	
	public BondSecurity[] getBonds() {
		return (BondSecurity[]) bonds.toArray(new BondSecurity[bonds.size()]);
	}
	
	public StockSecurity[] getStocks() {
		return (StockSecurity[]) stocks.toArray(new StockSecurity[stocks.size()]);
	}
	
	public SecurityTransaction[] getTrades() {
//		return transactions.stream().filter(t -> t instanceof SecurityTransaction).collect(Collectors.toList());
		return null;
		// t -> t.reference.equal("trade")
	}

	public void buy(Bond bond, double amount) {
		if (balance < bond.price * amount)
			return;
		
//		to do: add transaction
		
		Iterator<BondSecurity> it = bonds.iterator();
		while(it.hasNext()) {
			BondSecurity cur = it.next();
			if(cur.bond == bond) {
				cur.buy(amount);
				return;
			}
		}
		
		bonds.add(new BondSecurity(bond, amount));
	}
	
	public void buy(Stock stock, double amount) {
		if (balance < stock.price * amount)
			return;
		
//		to do: add transaction
		
		Iterator<StockSecurity> it = stocks.iterator();
		while(it.hasNext()) {
			StockSecurity cur = it.next();
			if(cur.stock == stock) {
				cur.buy(amount);
				return;
			}
		}
		
		stocks.add(new StockSecurity(stock, amount));
	}
	
	public double sell(Bond bond, double amount) {
		Iterator<BondSecurity> it = bonds.iterator();
		while(it.hasNext()) {
			BondSecurity cur = it.next();
			if(cur.bond == bond) {
//				to do: add transaction
				return cur.sell(amount);
			}
		}
		
		return 0;
	}
	
	public double sell(Stock stock, double amount) {
		Iterator<StockSecurity> it = stocks.iterator();
		while(it.hasNext()) {
			StockSecurity cur = it.next();
			if(cur.stock == stock) {
//				to do: add transaction
				return cur.sell(amount);
			}
		}
		
		return 0;
	}
	
	public double getUnforseenValueBond() {
		return bonds.stream().mapToDouble(x -> x.getUnforseenValue()).sum();
	}
	
	public double getUnforseenValueStock() {
		return stocks.stream().mapToDouble(x -> x.getUnforseenValue()).sum();
	}
	
	public double getUnforseenValues() {
		return getUnforseenValueBond() + getUnforseenValueStock();
	}
	
//	@override
	public double close() {
		double value = 0;
		for (BondSecurity bond : bonds) {
//			to do: add transaction
			value += bond.sellAll();
		}
		for (StockSecurity stock : stocks) {
//			to do: add transaction
			value += stock.sellAll();
		}
		return value;
	}
}
