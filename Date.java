import java.util.Calendar;
import java.time.*;

public class Date {
	private int month,day,year;
	
	public Date() {
		month = Calendar.getInstance().getTime().getMonth() + 1;
		day = Calendar.getInstance().getTime().getDate();
		year = 1900 + Calendar.getInstance().getTime().getYear();
		
	}
	
	public Date(int month, int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	
	public int getMonth() {
		return month;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getYear() {
		return year;
	}
	
	public boolean equals(Date other) {
		if (this.month == other.month && this.day == other.day && this.year == other.year)
			return true;
		return false;
	}
	
	public String toString() {
		return month + "/" + day + "/" + year;
	}
	
	public void incrementDay() { // rudimentary date system
		if (day == 31) {
			day = 1;
			if (month == 12) {
				month = 1;
				year++;
			}
			else 
				month++;
		}
		else 
			day++;
	}
	
	public static Date getCurrentDate() {
		int month = Calendar.getInstance().getTime().getMonth() + 1;
		int day = Calendar.getInstance().getTime().getDate();
		int year = 1900 + Calendar.getInstance().getTime().getYear();
		return new Date(month, day, year);
	}
	
//	public static void main(String[] args) {
////		System.out.println(LocalDateTime.now());
//		int day = Calendar.getInstance().getTime().getMonth();
//		System.out.println(day);
////		System.out.println(Calendar.getInstance().getTime());
//		Date d = new Date();
//		System.out.println(d);
//		//System.out.println(d.getCurrentDate());
		
//	}
}