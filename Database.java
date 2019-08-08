import java.util.ArrayList;

public class Database {
	
	//private static ArrayList<Client> clients = new ArrayList<Client>();
	private static ArrayList<Client> clients = BankDatabase.loadUsers();
	
	public Database() {
		// load all users held in the DB on startup
		//clients.addAll(BankDatabase.loadUsers());
	}
	
	public static void addClient(Client client) {
		clients.add(client);
	}
	
	public static Client getClient(Client client) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).equals(client));
				return clients.get(i);
		}
		return null;
	}
	
	
	public static Client findClient(String username, String password) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).checkLogin(username)) {
				if (clients.get(i).checkPassword(password))
					return clients.get(i);
			}
		}
		System.out.println("Client not found.");
		return null;
	}
	
	public static boolean checkUserPass(String username, String password) {
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i).checkLogin(username)) {
				if (clients.get(i).checkPassword(password))
					return true;
			}
		}
		return false;
	}
	
	public static String getDailyReport(Date tdate) {
		String s = "";
		for (int i = 0; i < clients.size(); i++) {
			s += "</br>" + clients.get(i).viewTransactions();
		}
		return s ;
	}

}