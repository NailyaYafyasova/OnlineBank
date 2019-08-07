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
			Statement createtable = DBconnect.createStatement();
			createtable.execute("CREATE TABLE IF NOT EXISTS Clients(userID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, fname varchar(20), lname varchar(20), login varchar(25), password varchar(25))");
			createtable.execute("CREATE TABLE IF NOT EXISTS Accounts(accountID INT NOT NULL PRIMARY KEY, type varchar(15), balance DOUBLE, currencyacr varchar(4), currencyname varchar(15), userID int, FOREIGN KEY (userID) REFERENCES Clients(userID))");
			createtable.execute("CREATE TABLE IF NOT EXISTS Transactions(transactionID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, date  DATE NOT NULL, reference varchar(10), type varchar(15), newbalance DOUBLE, accountID INT, FOREIGN KEY (accountID) REFERENCES Accounts(accountID))");
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
	
	public static int getLastID(String table, String field) {
		try {
			int returnID = 0;
			Connection DBconnect = getConnection();
			Statement stmt = DBconnect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + table + " ORDER BY " + field + " DESC LIMIT 1");
			while (rs.next()) {
				returnID = rs.getInt(field);
			}
			System.out.println("returnid is " + Integer.toString(returnID));
			return returnID;
		
		}
		
		catch(Exception e) {
			System.out.println(e);
			return -1;
		}
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
				int curruserid = rs.getInt("userID");
				Client currclient = new Client(currfname, currlname, currlogin, curruserid);
				currclient.setPassword(currpass);
				//currclient.setUserID(curruserid);
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
	
	// add new account to database
		public static void addAccount(Account newaccount, int userID) {
			try {
				String type = newaccount.type();
				double balance = newaccount.getBalance();
				String currencyacronym = newaccount.getCurrency().getAcronym();
				String currencyname = newaccount.getCurrency().getName();
				int curruserid = userID;
				int curraccountid = Integer.parseInt(newaccount.getID());
				Connection DBconnect = getConnection();
				PreparedStatement create = DBconnect.prepareStatement("INSERT INTO Accounts(accountID, type, balance, currencyacr, currencyname, userID) VALUES ('"+curraccountid+"', '"+type+"', '"+balance+"', '"+currencyacronym+"', '"+currencyname+"', '"+curruserid+"')");
				create.executeUpdate();
				DBconnect.close();
				System.out.println("New account has been added to the system.");
			}
			
			catch(Exception e) {
				System.out.println(e);
			}
		}
	
		
	// on restart, load all client accounts for a user
	public static ArrayList<Account> loadAccounts(int userID) {
		try {
			ArrayList<Account> accounts = new ArrayList<Account>();
			Connection DBconnect = getConnection();
			Statement stmt = DBconnect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Accounts WHERE userID = " + Integer.toString(userID));
			while (rs.next()) {
				String currtype = rs.getString("type");
				double currbalance = rs.getDouble("balance");
				int curraccountid = rs.getInt("accountID");
				Currency currcurrency = new Currency(rs.getString("currencyacr"), rs.getString("currencyname"));
				if (currtype.equals("Checking")) {
					Checking currchecking = new Checking(currbalance, currcurrency, Integer.toString(curraccountid));
					accounts.add(currchecking);
				}
				else if (currtype.equals("Savings")) {
					Savings currsaving = new Savings(currbalance, currcurrency, Integer.toString(curraccountid));
					accounts.add(currsaving);
				}
			}
			DBconnect.close();
			return accounts;
		}
		
		catch(Exception e) {
			System.out.println(e);
			return new ArrayList<Account>();
		}
	}
	// add new transaction to database
			public static void addTransaction(Transaction transaction , String accountid) {
				try {
					
					String currdate = transaction.getDateInitialized().toSqlString();
					String currreference = transaction.getReference();
					String currtype = transaction.getType();
					double currbalance = transaction.getNewBalance();
					String curraccount = transaction.getAccountID();
					Connection DBconnect = getConnection();
					PreparedStatement create = DBconnect.prepareStatement("INSERT INTO Transactions(date, reference, type, newbalance, accountID) VALUES ('"+currdate+"', '"+currreference+"', '"+currtype+"', '"+currbalance+"', '"+curraccount+"')");
					create.executeUpdate();
					DBconnect.close();
					System.out.println("New account has been added to the system.");
				}
				
				catch(Exception e) {
					System.out.println(e);
				}
			}
			
			
	// on restart, load all transactions for user
	public static ArrayList<Transaction> loadTransactions(String accountID, Account thisaccount) {
		try {
			ArrayList<Transaction> transactions = new ArrayList<Transaction>();
			Connection DBconnect = getConnection();
			Statement stmt = DBconnect.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Transactions WHERE accountID = " + accountID);
			while (rs.next()) {
				String currdatestring = rs.getString("date");
				Date currdate = new Date(Integer.parseInt(currdatestring.substring(5, 6)), Integer.parseInt(currdatestring.substring(8)), Integer.parseInt(currdatestring.substring(0, 4)));
				String currreference = rs.getString("reference");
				String currtype = rs.getString("type");
				Double currbalance = rs.getDouble("newbalance");
				Transaction transaction = new Transaction(currdate, thisaccount,  currbalance, currreference, currtype, accountID);
				transactions.add(transaction);
			}
			return transactions;
		}
		
		catch(Exception e) {
			System.out.println(e);
			return new ArrayList<Transaction>();
		}
	}
	
	// adjust balance of an account after a transaction
	public static void adjustAccountBalance(Account curraccount) {
		
	}
	
	// Upon restart,
	
	// test methods that utilize the MySQL DB
	public static void main(String args[]) throws Exception {
		//initializeTable("testtable", "CREATE TABLE IT NOT EXISTS testtable(id int)");
		initializeTables();
	}
}
