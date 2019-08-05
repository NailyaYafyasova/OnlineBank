package bank_atm.gui;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class BankPanel extends JTabbedPane {

	private ManagerPanel mgr_component;
	private ClientPanel clt_component;

	public BankPanel() {
		setPreferredSize(BankFrame.PREFERRED_SIZE);
		mgr_component = new ManagerPanel();
		addTab("Manager", null, mgr_component, "This is the tab for the manager");

		clt_component = new ClientPanel();
		addTab("Client", null, clt_component, "This is the tab for clients");

		addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (((JTabbedPane) e.getSource()).getSelectedComponent() == mgr_component) {
					mgr_component.showWelcome();
				} else {
					clt_component.showWelcome();
				}
			}
		});
	}
}
