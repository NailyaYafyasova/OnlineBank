package views;
public class Rate {
	
	private String acronym;
	private double toRate; // to EUR 
	private double fromRate; // from EUR
	
	public Rate(String acronym, double rate) {
		this.acronym = acronym;
		toRate = rate;
		fromRate = 1 / rate;
	}
	
	public String getAcronym() {
		return acronym;
	}
	
	public double getRateToEUR() {
		return toRate;
	}
	
	public double getRateFromEur() {
		return fromRate;
	}
}
