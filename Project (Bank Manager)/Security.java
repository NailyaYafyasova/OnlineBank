package views;
public abstract class Security {
	protected double buyPrice;
	protected double currentPrice;
	protected double amount;

	public Security() { }

	public double getCurrentValue() { return getCurrentValue(amount); }
	public abstract double getCurrentValue(double amount);

	public double getUnrealized() {
		return getCurrentValue() - buyPrice;
	}

	public abstract Enum<?> getItem();
}