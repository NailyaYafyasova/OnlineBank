package fancy_bank.portfolio;

public class Bond extends Security {
	
	public final BondItem bondItem;
	public final long buyTime;
	
	public Bond(BondItem bondIem) {
		this.bondItem = bondIem;
		buyTime = System.currentTimeMillis();
	}
	
	@Override
	public double getCurrentValue(double amount) {
		long timePassed = 0;		
		// timePassed = Bank.time - buyTime
		return bondItem.price * ((timePassed > bondItem.interestTime) ? bondItem.interestRate : 1);
	}

	@Override
	public Enum<?> getItem() {
		// TODO Auto-generated method stub
		return null;
	}

}
