package bank_atm.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Mgr_WelcomePanel extends JPanel{

	public Mgr_WelcomePanel() {
		setLayout(new BorderLayout(0, 0));

		JLabel welcomeLabel = new JLabel("<html><h1>Welcome back manager!<br>What would you like to do?</h1></html>");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(welcomeLabel, BorderLayout.CENTER);
	}
	
}
