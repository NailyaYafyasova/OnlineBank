package views;
public enum BondSecurity {
	Test(1),
	Test2(2);
	
	
	protected int id;
	protected double price;
	protected double interestRate;
	protected int maturityInDays;

	private BondSecurity(int id) {
		this.id = id;
		loadFromDatabase();
	}

	public static void update() {
		for(BondSecurity bond : values()) {
			bond.loadFromDatabase();
		}
	}
	
	private void loadFromDatabase() {
		BankDatabase.loadBonds(this);
	}
}
