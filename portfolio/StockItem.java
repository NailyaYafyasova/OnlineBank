package fancy_bank.portfolio;

public enum StockItem {
	Apple(0, "APPL"),
	Google(1, "GOOG");
	
	public final String ticker;
	protected int price;
	
	private StockItem(int id, String ticker) {
		this.ticker = ticker.toUpperCase();
		loadFromDatabase();
	}
	
	public static void update() {
		for(StockItem stock : values()) {
			stock.loadFromDatabase();
		}
	}
	
	private void loadFromDatabase() {
		
	}
}