package views;
public enum StockSecurity {
	Apple(1, "APPL"),
	Google(2, "GOOG");

	protected final int id;
	public final String ticker;
	protected double price;

	private StockSecurity(int id, String ticker) {
		this.id = id;
		this.ticker = ticker.toUpperCase();
		loadFromDatabase();
	}

	public static void update() {
		for(StockSecurity stock : values()) {
			stock.loadFromDatabase();

			System.out.println(stock.ticker);

			System.out.println(stock.price);
		}
	}

	private void loadFromDatabase() {
		BankDatabase.loadStocks(this);
	}
}
