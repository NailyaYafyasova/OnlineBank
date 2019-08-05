package bank_atm.gui;

public enum Currency {
	USD("$", 1f),
	EUR("€", .9018f),
	GBP("£", .8249f);
	
	public final String symbol;
	public final double conversion;
	
	private Currency(String symbol, double conversion) {
		this.symbol = symbol;
		this.conversion = conversion;
	}
	
	public String toString() {
		return symbol;
	}
}
