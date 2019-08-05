package bank_atm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SuppressWarnings("serial")
public class WelcomePanel extends JSplitPane implements ActionListener{
	
	private BankFrame parent;
	private JButton mgr_user;
	private JButton clt_user;

	public WelcomePanel(BankFrame parent) {
		setPreferredSize(BankFrame.PREFERRED_SIZE);
		this.parent = parent;
		
		setResizeWeight(1.0);
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		
		
		JPanel topPanel = new JPanel();
		setTopComponent(topPanel);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JLabel welcomeLabel = new JLabel("<html><h1>Welcome to Bank ATM!<br>What kind of user are you?</h1></html>");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topPanel.add(welcomeLabel);
		
		
		
		JPanel botPanel = new JPanel();
		botPanel.setBorder(new EmptyBorder(25, 25, 25, 25));
		setBottomComponent(botPanel);
		
		clt_user = new JButton("<html><h3>I am a client</h3></html>");
		clt_user.addActionListener(this);
		
		mgr_user = new JButton("<html><h3>I am the manager</h3></html>");
		mgr_user.addActionListener(this);
		
		botPanel.setLayout(new GridLayout(0, 2, 20, 0));
		botPanel.add(mgr_user);
		botPanel.add(clt_user);
	}
	
	
	public void actionPerformed(ActionEvent e) {
		if(mgr_user == e.getSource()) 
			parent.switchPanel(0);
		else if(clt_user == e.getSource())
			parent.switchPanel(1);
	}

}
