package bank_atm.gui;

import javax.swing.JPanel;
import bank_atm.Client;
import bank_atm.ClientAccount;

import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class ClientPanel extends JPanel {

	private Clt_WelcomePanel welcomePanel;
	private Clt_ClientPanel clientPanel;
	private Clt_AccountPanel accountPanel;
	
	public ClientPanel() {
		
		welcomePanel = new Clt_WelcomePanel();
		clientPanel  = new Clt_ClientPanel();
		accountPanel = new Clt_AccountPanel();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		add(welcomePanel);
		add(clientPanel);
		add(accountPanel);

		showWelcome();
	}
	
	public void showWelcome() {
		clientPanel.setVisible(false);
		accountPanel.setVisible(false);
		welcomePanel.setVisible(true);
	}
	
	public void showClient(Client clt) {
		clientPanel.setClient(clt);
		
		welcomePanel.setVisible(false);
		accountPanel.setVisible(false);
		clientPanel.setVisible(true);
	}
	
	public void showAccount(ClientAccount acc) {
		accountPanel.setAccount(acc);
		
		welcomePanel.setVisible(false);
		clientPanel.setVisible(false);
		accountPanel.setVisible(true);
	}
}
