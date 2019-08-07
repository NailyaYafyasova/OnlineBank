package fancy_bank.portfolio;

public class BondSecurity extends Security {
	
	public final Bond bond;
	public final long buyTime;
	
	public BondSecurity(Bond bond, double amount) {
		super(amount, bond.price);
		this.bond = bond;
		buyTime = System.currentTimeMillis();
	}
	
	@Override
	public double getCurrentValue(double amount) {
		long timePassed = 0;
		
		// timePassed = Bank.time - buyTime
		
		if(timePassed < bond.interest1Time)
			return bond.price * amount;
		else if(timePassed < bond.interest2Time)
			return bond.price * bond.interest1 * amount;
		else if(timePassed < bond.interest3Time)
			return bond.price * bond.interest2 * amount;
		else 
			return bond.price * bond.interest3 * amount;
	}

}
