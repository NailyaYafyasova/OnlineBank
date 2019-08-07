
public class Bond extends Security {
	
	public final BondSecurity bond;
	public final Date buyDate;
	protected boolean toSell = false;
	
	public Bond(BondSecurity bondIem) {
		this.bond = bondIem;
		this.type = "Bond";
		buyDate = Date.getCurrentDate();
	}
	
	public void update() {
		bond.update();
		currentPrice = bond.price;
		Date currentDate = Manager.manager.currentSystemDate;
		if (buyDate.differenceInDays(currentDate) >= bond.maturityInDays)
			toSell = true;
		
	}
	
	@Override
	public double getCurrentValue(double amount) {
		bond.update();
		int timePassed = 0;		
		// timePassed = Bank.time - buyTime
		return bond.price * ((timePassed > bond.maturityInDays) ? bond.interestRate : 1);
	}

	@Override
	public Enum<?> getItem() {
		// TODO Auto-generated method stub
		return null;
	}

}