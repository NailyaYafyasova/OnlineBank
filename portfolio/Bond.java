package fancy_bank.portfolio;

public enum Bond{
	Test(1),
	Test2(2);
	
	protected int price;
	protected double interest1, interest2, interest3;
	protected long interest1Time, interest2Time, interest3Time;
	
	private Bond(int id) {
		loadFromDatabase();
	}
	
	public static void update() {
		for(Bond bond : values()) {
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
