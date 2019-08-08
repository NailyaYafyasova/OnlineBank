package views;
public class Currency {
	private String acronym;
	private String name;
	
	private Rate[] currencies = {new Rate("USD", 0.9), 
								 new Rate("RUB", 0.014), 
								 new Rate("CNY", 0.13), 
								 new Rate("GBP", 1.11)
								};
	
	
	public Currency() {
		acronym = "EUR";
		name = "Euro";
	}
	
	
	public Currency(String acronym, String name) {
		this.acronym = acronym;
		this.name = name;
	}
	
	public double toEURO(double amount) {
		for (int i = 0; i < currencies.length; i++) {
			if (currencies[i].getAcronym().equals(this.acronym)) 
				return currencies[i].getRateToEUR() * amount;
		}
		
		System.out.println("Conversion to EUR failed.");
		return amount;
	}
	
	
	public double convertTo(double amount, String acronym) {
		if (acronym.equals("EUR"))
			return toEURO(amount);
		
		double eur = this.toEURO(amount);
		
		for (int j = 0; j < currencies.length; j++) {
			if (currencies[j].getAcronym().equals(acronym)) {
				double fin = eur * currencies[j].getRateFromEur();
				return Math.round(fin * 100.00) / 100.00; // to the tenths place
			}
		}
		System.out.println("Conversion from " + this.acronym + " to " + acronym + " has failed.");
		return amount;
	}
	
	
	public String toString() {
		return name;
	}
	
	
	public String getAcronym() {
		return acronym;
	}
	
	
	public String getName() {
		return name;
	}
	
}
