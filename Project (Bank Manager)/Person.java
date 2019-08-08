package views;
import java.util.ArrayList;

public abstract class Person {
	private Name name;
	private String login;
	private String password;
	
	
	// no-arg constructor
	public Person() {
		this.name = new Name();
	}
		
	// basic constructor
	public Person(String fname, String lname, String login) {
		this.name = new Name(fname, lname);
		this.login = login;
	}
	
	
	public Person(String fname, String mname, String lname, String login) {
		this.name = new Name(fname, mname, lname);
		this.login = login;
	}
	
		
	// accessor method for the name
	public Name name() {
		return name;
	}
	
	public String login() {
		return login;
	}
	
	public String password() {
		System.out.println(password);
		return this.password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}


	public String toString() {
		return name.toString();
	}
	
	public boolean checkPassword(String pass) {
		if ( this.password.equals(pass) )
			return true;
		System.out.println("Incorrect Password. Try again.");
		return false;
	}
	
	public boolean checkLogin(String login) {
		if ( this.login.equals(login)) {
			return true;
		}
		System.out.println("Incorrect Login. Try again.");
		return false;
	}
	
	
}