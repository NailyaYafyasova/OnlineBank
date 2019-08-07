public enum StockSecurity {
	Apple(0, "APPL"),
	Google(1, "GOOG");
	
	public final String ticker;
	protected int price;
	
	private StockSecurity(int id, String ticker) {
		this.ticker = ticker.toUpperCase();
		loadFromDatabase();
	}
	
	public static void update() {
		for(StockSecurity stock : values()) {
			stock.loadFromDatabase();
		}
	}
	
	private void loadFromDatabase() {
		
	}
}