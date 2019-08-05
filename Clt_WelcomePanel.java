package bank_atm.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bank_atm.Bank;
import bank_atm.Client;

@SuppressWarnings("serial")
public class Clt_WelcomePanel extends JSplitPane {
	private JComponent welcomePanel, selectUserPanel;
	private JComboBox<Client> cbx_selectUser;

	public Clt_WelcomePanel() {
		setResizeWeight(1);
		setOrientation(JSplitPane.VERTICAL_SPLIT);

		initWelcomePanel();
		initSelectUserPanel();

		setTopComponent(welcomePanel);
		setBottomComponent(selectUserPanel);
	}

	private void initWelcomePanel() {
		welcomePanel = new JPanel();
		welcomePanel.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel(
				"<html><h1>Welcome to the Online ATM!<br></h1><h2>Please select an user, or create a new one</h2></html>");
		welcomePanel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
	}

	private void initSelectUserPanel() {
		selectUserPanel = new JPanel();
		selectUserPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		selectUserPanel.setLayout(new GridLayout(0, 1, 0, 15));

		JPanel panel = new JPanel();
		selectUserPanel.add(panel);
		panel.setLayout(new BorderLayout(20, 0));

		cbx_selectUser = new JComboBox<Client>();
		cbx_selectUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbx_selectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ("comboBoxEdited".equals(e.getActionCommand())) {
					Client c = new Client(cbx_selectUser.getSelectedItem().toString());
					Bank.BANK.addClient(c);
					cbx_selectUser.addItem(c);
					clean();
					((ClientPanel) getParent()).showClient(c);
				}
			}
		});
		cbx_selectUser.setEditable(true);
		panel.add(cbx_selectUser);

		JButton btn_selectUser = new JButton("<html><h3>Select User</h3></html>");
		btn_selectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbx_selectUser.getSelectedIndex() >= 0) {
					Client c = cbx_selectUser.getItemAt(cbx_selectUser.getSelectedIndex());
					((ClientPanel) getParent()).showClient(c);
				}
			}
		});

		btn_selectUser.setPreferredSize(new Dimension(200, 20));
		panel.add(btn_selectUser, BorderLayout.EAST);
	}

	private void clean() {
		cbx_selectUser.setSelectedItem(null);
		revalidate();
	}
}
