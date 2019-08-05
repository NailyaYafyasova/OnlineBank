package bank_atm.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ManagerPanel extends JSplitPane {

	private JComponent welcomePanel, optionsPanel;
	private Mgr_ClientsPanel clientsPanel;
	private Mgr_ReportsPanel reportsPanel;
	private Mgr_EditPanel editPanel;

	public ManagerPanel() {
		setResizeWeight(1.0);
		setOrientation(JSplitPane.VERTICAL_SPLIT);

		welcomePanel = new Mgr_WelcomePanel();
		clientsPanel = new Mgr_ClientsPanel();
		reportsPanel = new Mgr_ReportsPanel();
		editPanel	 = new Mgr_EditPanel();
		
		initOptionsPanel();

		setTopComponent(welcomePanel);
		setBottomComponent(optionsPanel);
	}

	private void initOptionsPanel() {
		optionsPanel = new JPanel();
		optionsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		optionsPanel.setLayout(new GridLayout(1, 0, 20, 0));

		JButton clientButton = new JButton("<html><h3>View Clients</h3></html>");
		clientButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTopComponent(clientsPanel);
				clientsPanel.clean();
				reportsPanel.clean();
				
				clientsPanel.init();
			}
		});
		optionsPanel.add(clientButton);

		JButton reportButton = new JButton("<html><h3>View Report</h3></html>");
		reportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTopComponent(reportsPanel);
				clientsPanel.clean();
				reportsPanel.clean();
				
				reportsPanel.init();
			}
		});
		optionsPanel.add(reportButton);

		JButton editButton = new JButton("<html><h3>Bank Option</h3></html>");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTopComponent(editPanel);
				clientsPanel.clean();
				reportsPanel.clean();
			}
		});
		optionsPanel.add(editButton);
	}
	
	public void showWelcome() {
		setTopComponent(welcomePanel);
	}
}
