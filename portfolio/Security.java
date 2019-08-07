package fancy_bank.portfolio;

public abstract class Security {
	public final double buyPrice;
	protected double currecntPrice;
	protected double amount;
		
	protected Security(double amount, double pricePer) {
		this.amount = amount;
		this.buyPrice = pricePer * amount;
	}
	
	public double getCurrentValue() { return getCurrentValue(amount); }
	public abstract double getCurrentValue(double amount);
	
	public double getUnforseenValue() {
		return getCurrentValue() - buyPrice;
	}
	
	public void buy(double amount) {
		this.amount += amount;
	}
	
	public double sell(double amount) {
		double value = getCurrentValue(amount);
		this.amount -= amount;
		return value;
	}
	
	public double sellAll() {
		return sell(amount);
	}
}
