
public class Stock extends Security {
	protected StockSecurity stockItem;

	public Stock(StockSecurity stockItem) {
		this.stockItem = stockItem;
	}

	public Stock(StockSecurity sec, Double currprice, int currquantity) {
		this.stockItem = sec;
		this.buyPrice = currprice;
		this.amount = currquantity;
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
