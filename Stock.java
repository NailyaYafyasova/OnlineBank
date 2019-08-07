public class Stock extends Security {

	protected StockSecurity stockItem;
	
	public Stock(StockSecurity stockItem) {
		this.stockItem = stockItem;
		this.type = "Stock";
	}

	@Override
	public double getCurrentValue(double amount) {
		return amount * stockItem.price;
	}
	
	@Override
	public StockSecurity getItem() {
		return stockItem;
	}

}