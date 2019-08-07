package fancy_bank.portfolio;

public enum BondItem{
	Test(1),
	Test2(2);
	
	protected int price;
	protected double interestRate;
	protected long interestTime;
	
	private BondItem(int id) {
		loadFromDatabase();
	}
	
	public static void update() {
		for(BondItem bond : values()) {
			bond.loadFromDatabase();
		}
	}
	
	private void loadFromDatabase() {
		// to-do update prices and interests from database
		// Bankdatabase.getbonddata(this
		
		
		// getter
		// bond.price = price;
	}
}
