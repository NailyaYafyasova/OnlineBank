package views;
public class Bond extends Security {
	public final BondSecurity bond;
	public final Date buyDate;
	protected boolean toSell = false;

	public Bond(BondSecurity bondIem) {
		this.bond = bondIem;
		buyDate = Date.getCurrentDate();
	}

	public Bond(BondSecurity sec, Double currprice, int currquantity, Date currdate) {
		this.bond = sec;
		this.buyPrice = currprice;
		this.amount = currquantity;
		this.buyDate = currdate;
	}

	public void update() {
		BondSecurity.update();
		currentPrice = bond.price;
		Date currentDate = Manager.manager.currentSystemDate;
		if (buyDate.differenceInDays(currentDate) >= bond.maturityInDays)
			toSell = true;
	}

	@Override
	public double getCurrentValue(double amount) {
		return bond.price;
	}

	@Override
	public Enum<?> getItem() {
		return bond;
	}
}
