package fancy_bank.portfolio;

public class StockSecurity extends Security {

	public final Stock stock;
	
	public StockSecurity(Stock stock, double amount) {
		super(amount, stock.price);
		this.stock = stock;
	}

	@Override
	public double getCurrentValue(double amount) {
		return amount * stock.price;
	}

}
