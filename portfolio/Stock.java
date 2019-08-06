package fancy_bank.portfolio;

public enum Stock {
	Apple(0, "APPL"),
	Google(1, "GOOG");
	
	public final String ticker;
	protected int price;
	
	private Stock(int id, String ticker) {
		this.ticker = ticker.toUpperCase();
		loadFromDatabase();
	}
	
	public static void update() {
		for(Stock stock : values()) {
			stock.loadFromDatabase();
		}
	}
	
	private void loadFromDatabase() {
		
	}
}