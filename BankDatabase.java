import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class BankDatabase {

	/*
	 * Class to connect to DB
	 * Open MySQL from terminal (Mac): /usr/local/mysql/bin/mysql -u root -p
	 * Assume that there exists a DB for use 
	 */
	

	// create a table with the parameter "createstatement" (NOT USED RIGHT NOW)
	public void initializeTable(String tablename, String createstatement) throws Exception {
		try {
			Connection DBconnect = getConnection();
			PreparedStatement create = DBconnect.prepareStatement(createstatement);
			create.executeUpdate();
			System.out.println("Table '" + tablename + "' has been initialied.");
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// initialize tables for this project
	public static void initializeTables() {
		try {
			Connection DBconnect = getConnection();
			Statement createclienttable = DBconnect.createStatement();
			createclienttable.execute("CREATE TABLE IF NOT EXISTS Clients(userID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, fname varchar(20), lname varchar(20), login varchar(25), password varchar(25))");
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// reset the database
	public void clearTable(String tablename) {
		try {
			Connection DBconnect = getConnection();
			PreparedStatement create = DBconnect.prepareStatement("TRUNCATE TABLE " + tablename);
			create.executeUpdate();
			System.out.println("Table data has been reset.");
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// add table data
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.jdbc.Driver";
			
			// port number (3306) and database name (BankData) may be different for different users
			// NOTE: MUST SPECIFY TIME ZONE IN THE URL BECAUSE IT IS REQUIRED NOW
			String url = "jdbc:mysql://localhost:3306/BankData?serverTimezone=UTC";
			String user = "root";
			String pass = "";
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected to Database");
			return conn;
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	// add new user (Why does this method need to be static?)
	public static void addUser(Client newclient) {
		try {
			String fname = newclient.name().getFname();
			String lname = newclient.name().getLname();
			String login = newclient.login();
			String pass = newclient.password();
			Connection DBconnect = getConnection();
			PreparedStatement create = DBconnect.prepareStatement("INSERT INTO Clients(fname, lname, login, password) VALUES ('"+fname+"', '"+lname+"', '"+login+"', '"+pass+"')");
			create.executeUpdate();
			//DBconnect.commit();
			DBconnect.close();
			System.out.println("Client " + newclient.name() + " has been added to the system.");
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// on restart, load all clients
	public static ArrayList<Client> loadUsers() {
		try {
			ArrayList<Client> clients = new ArrayList<Client>();
			Connection DBconnect = getConnection();
			Statement stmt = DBconnect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Clients");
			while (rs.next()) {
				String currfname = rs.getString("fname");
				String currlname = rs.getString("lname");
				String currlogin = rs.getString("login");
				//String currpass = ;		Is password not used in Person Class? Not used for safety reasons?
				String currpass = rs.getString("password");
				System.out.println("login is " + currlogin);
				System.out.println("fname is " + currfname);
				System.out.println("lname is " + currlname);
				Client currclient = new Client(currfname, currlname, currlogin);
				System.out.println("TETSETSET");
				currclient.setPassword(currpass);
				clients.add(currclient);
			}
			DBconnect.close();
			return clients;
		}
		
		// return empty list if there's an exception error
		catch(Exception e) {
			System.out.println(e);
			return new ArrayList<Client>();
		}
	}
	
	// on restart, load all client accounts for user
	public static ArrayList<Account> loadAccounts(int userID) {
		try {
			ArrayList<Client> clients = new ArrayList<Client>();
			Connection DBconnect = getConnection();
			Statement stmt = DBconnect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts WHERE userID = " + Integer.toString(userID));
			while (rs.next()) {
				
			}
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// on restart, load all transactions for user
	public static ArrayList<Transaction> loadTransactions(int userID) {
		try {
			ArrayList<Client> clients = new ArrayList<Client>();
			Connection DBconnect = getConnection();
			Statement stmt = DBconnect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Transactions");
		}
		
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	// Upon restart,
	
	// test methods that utilize the MySQL DB
	public static void main(String args[]) throws Exception {
		//initializeTable("testtable", "CREATE TABLE IT NOT EXISTS testtable(id int)");
		initializeTables();
	}
}