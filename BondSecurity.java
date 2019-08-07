
public enum BondSecurity {
	Test(1),
	Test2(2);
	
	protected int price;
	protected double interestRate;
	protected int maturityInDays;
	
	private BondSecurity(int id) {
		loadFromDatabase();
	}
	
	public static void update() {
		for(BondSecurity bond : values()) {
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
