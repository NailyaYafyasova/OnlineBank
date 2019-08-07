package fancy_bank.portfolio;

public class Stock extends Security {

	protected StockItem stockItem;
	
	public Stock(StockItem stockItem) {
		this.stockItem = stockItem;
	}

	@Override
	public double getCurrentValue(double amount) {
		return amount * stockItem.price;
	}
	
	@Override
	public StockItem getItem() {
		return stockItem;
	}

}
