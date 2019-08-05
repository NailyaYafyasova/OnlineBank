package bank_atm.gui;

import bank_atm.*;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
public class Clt_ClientPanel extends JPanel {

	private JPanel accountPanel;
	private Client client;
	private JComboBox<ClientAccount> cbx_checkingAccount, cbx_savingAccount;
	private JComboBox<Currency> cbx_prefCurrency, cbx_currencyChecking, cbx_currencySaving;
	private JLabel lbl_hello;
	private JPanel viewport;
	private JComboBox<LoanData> cbx_loans;

	public Clt_ClientPanel() {
		setLayout(new BorderLayout(0, 0));

		viewport = new JPanel();
		add(viewport, BorderLayout.CENTER);
		viewport.setLayout(new BorderLayout(0, 0));

		initMessage();

		initMainPanel();

		initAccountPanel(true);
		initAccountPanel(false);
		initLoans();
	}

	private void initMessage() {
		JPanel welcomeMessage = new JPanel();
		welcomeMessage.setBorder(new CompoundBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)),
				new EmptyBorder(25, 25, 15, 15)));
		viewport.add(welcomeMessage, BorderLayout.NORTH);
		welcomeMessage.setLayout(new BorderLayout(0, 0));

		lbl_hello = new JLabel();
		lbl_hello.setText("Hello ..");
		lbl_hello.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessage.add(lbl_hello, BorderLayout.WEST);

		JPanel options = new JPanel();
		options.setPreferredSize(new Dimension(150, 44));
		welcomeMessage.add(options, BorderLayout.EAST);
		options.setLayout(new GridLayout(0, 1, 0, 5));

		JButton btn_logout = new JButton("<html><h4>Logout</h4></html>");
		options.add(btn_logout);

		JPanel prefCurrency = new JPanel();
		options.add(prefCurrency);
		prefCurrency.setLayout(new BorderLayout(0, 0));

		cbx_prefCurrency = new JComboBox<Currency>();
		cbx_prefCurrency.setModel(new DefaultComboBoxModel<Currency>(Currency.values()));
		cbx_prefCurrency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Currency c = cbx_prefCurrency.getItemAt(cbx_prefCurrency.getSelectedIndex());
				client.preferredCurrency = c;
				cbx_currencyChecking.setSelectedItem(c);
				cbx_currencySaving.setSelectedItem(c);

				for (int i = 0; i < cbx_loans.getItemCount(); i++) {
					cbx_loans.getItemAt(i).curr = c;
					cbx_loans.setVisible(false);
					cbx_loans.setVisible(true);

				}
			}
		});
		prefCurrency.add(cbx_prefCurrency, BorderLayout.EAST);

		JLabel lblNewLabel = new JLabel("Preffered Currency: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		prefCurrency.add(lblNewLabel, BorderLayout.CENTER);
		btn_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
				((ClientPanel) getParent()).showWelcome();
			}
		});
	}

	private void initMainPanel() {
		accountPanel = new JPanel();
		accountPanel.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		viewport.add(accountPanel, BorderLayout.CENTER);
		accountPanel.setLayout(new BoxLayout(accountPanel, BoxLayout.Y_AXIS));
	}

	private void initAccountPanel(boolean isCheckingAccount) {
		JPanel checkAccountPanel = new JPanel();
		checkAccountPanel.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)),
				new EmptyBorder(10, 15, 10, 0)));
		accountPanel.add(checkAccountPanel);
		checkAccountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));

		JLabel lbl_checkingAccount = new JLabel(String.format("<html><h3>%s:</h3></html>",
				isCheckingAccount ? "Checkings Account" : "Savings Account"));
		lbl_checkingAccount.setPreferredSize(new Dimension(120, 40));
		checkAccountPanel.add(lbl_checkingAccount);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 30, 0, 0));
		checkAccountPanel.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 89, 0, 30, 30, 0 };
		gbl_panel.rowHeights = new int[] { 23, 23, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JComboBox<ClientAccount> cbx_account = new JComboBox<ClientAccount>();
		cbx_account.setMinimumSize(new Dimension(50, 50));
		GridBagConstraints gbc_cbx_account = new GridBagConstraints();
		gbc_cbx_account.gridwidth = 4;
		gbc_cbx_account.fill = GridBagConstraints.BOTH;
		gbc_cbx_account.insets = new Insets(0, 0, 5, 5);
		gbc_cbx_account.gridx = 0;
		gbc_cbx_account.gridy = 0;
		panel.add(cbx_account, gbc_cbx_account);

		if (isCheckingAccount)
			cbx_checkingAccount = cbx_account;
		else
			cbx_savingAccount = cbx_account;

		JButton btn_accountDetail = new JButton("<html><h4>More Detail</h4></html>");
		btn_accountDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientAccount account = (isCheckingAccount)
						? cbx_checkingAccount.getItemAt(cbx_checkingAccount.getSelectedIndex())
						: cbx_savingAccount.getItemAt(cbx_savingAccount.getSelectedIndex());
				if (account == null) {
					JOptionPane.showMessageDialog(null, "No Account Selected", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					clean();
					((ClientPanel) getParent()).showAccount(account);
				}
			}
		});
		btn_accountDetail.setPreferredSize(new Dimension(150, 50));
		GridBagConstraints gbc_btn_accountDetail = new GridBagConstraints();
		gbc_btn_accountDetail.fill = GridBagConstraints.BOTH;
		gbc_btn_accountDetail.insets = new Insets(0, 0, 5, 0);
		gbc_btn_accountDetail.gridx = 6;
		gbc_btn_accountDetail.gridy = 0;
		panel.add(btn_accountDetail, gbc_btn_accountDetail);

		JLabel lbl_newAccountMoney = new JLabel("<html><h4>Initial Deposit: </h4></html>");
		lbl_newAccountMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lbl_newAccountMoney = new GridBagConstraints();
		gbc_lbl_newAccountMoney.insets = new Insets(0, 0, 5, 5);
		gbc_lbl_newAccountMoney.gridx = 0;
		gbc_lbl_newAccountMoney.gridy = 1;
		panel.add(lbl_newAccountMoney, gbc_lbl_newAccountMoney);

		JComboBox<Currency> cbx_currency = new JComboBox<Currency>();
		cbx_currency.setFont(new Font("Tahoma", Font.BOLD, 16));
		cbx_currency.setPreferredSize(new Dimension(50, 50));
		cbx_currency.setModel(new DefaultComboBoxModel<Currency>(Currency.values()));
		GridBagConstraints gbc_cbx_currency = new GridBagConstraints();
		gbc_cbx_currency.insets = new Insets(0, 0, 0, 5);
		gbc_cbx_currency.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbx_currency.gridx = 1;
		gbc_cbx_currency.gridy = 1;
		panel.add(cbx_currency, gbc_cbx_currency);

		if (isCheckingAccount)
			cbx_currencyChecking = cbx_currency;
		else
			cbx_currencySaving = cbx_currency;

		NumberFormat wholeFormat = NumberFormat.getInstance();
		NumberFormatter wholeFormatter = new NumberFormatter(wholeFormat);
		wholeFormatter.setValueClass(Long.class);
		wholeFormatter.setAllowsInvalid(false);
		wholeFormatter.setMinimum(0l);
		JFormattedTextField ftf_initialDepositWhole = new JFormattedTextField(wholeFormatter);
		ftf_initialDepositWhole.setValue(new Long(0));
		ftf_initialDepositWhole.setPreferredSize(new Dimension(100, 50));
		ftf_initialDepositWhole.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_ftf_initialDepositWhole = new GridBagConstraints();
		gbc_ftf_initialDepositWhole.insets = new Insets(0, 0, 0, 5);
		gbc_ftf_initialDepositWhole.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftf_initialDepositWhole.gridx = 2;
		gbc_ftf_initialDepositWhole.gridy = 1;
		panel.add(ftf_initialDepositWhole, gbc_ftf_initialDepositWhole);

		NumberFormat fractionFormat = NumberFormat.getInstance();
		NumberFormatter fractionFormatter = new NumberFormatter(fractionFormat);
		fractionFormatter.setValueClass(Long.class);
		fractionFormatter.setAllowsInvalid(false);
		fractionFormatter.setMaximum(99l);
		JFormattedTextField ftf_initialDepositFraction = new JFormattedTextField(fractionFormatter);
		ftf_initialDepositFraction.setValue(new Long(0));
		ftf_initialDepositFraction.setPreferredSize(new Dimension(40, 50));
		ftf_initialDepositFraction.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_ftf_initialDepositFraction = new GridBagConstraints();
		gbc_ftf_initialDepositFraction.insets = new Insets(0, 0, 0, 5);
		gbc_ftf_initialDepositFraction.fill = GridBagConstraints.HORIZONTAL;
		gbc_ftf_initialDepositFraction.gridx = 3;
		gbc_ftf_initialDepositFraction.gridy = 1;
		panel.add(ftf_initialDepositFraction, gbc_ftf_initialDepositFraction);

		JButton btn_createAccount = new JButton("<html><h4>Create Account</h4></html>");
		btn_createAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double deposit = ((Long) ftf_initialDepositWhole.getValue()).doubleValue()
						+ ((Long) ftf_initialDepositFraction.getValue()).doubleValue() / 100;
				deposit /= client.preferredCurrency.conversion;

				String text = String.format(
						"Opening an account will cost you %s%.2f.\n Are you sure you want to do so?",
						client.preferredCurrency,
						Bank.Fee.OpenAccount.getAmount() * client.preferredCurrency.conversion);

				int choice = JOptionPane.showOptionDialog(null, text, "Account", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, 0);

				if (choice == 0) {
					if (isCheckingAccount) {
						ClientAccount acc = new CheckingAccount(deposit);
						client.addAccount(acc);
						cbx_checkingAccount.addItem(acc);
					} else {
						ClientAccount acc = new SavingsAccount(deposit);
						client.addAccount(acc);
						cbx_savingAccount.addItem(acc);
					}
				}
			}
		});
		btn_createAccount.setPreferredSize(new Dimension(150, 50));
		GridBagConstraints gbc_btn_createAccount = new GridBagConstraints();
		gbc_btn_createAccount.gridx = 6;
		gbc_btn_createAccount.gridy = 1;
		panel.add(btn_createAccount, gbc_btn_createAccount);
	}

	private void initLoans() {
		JPanel Loans = new JPanel();
		viewport.add(Loans, BorderLayout.SOUTH);
		Loans.setBorder(new CompoundBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)),
				new EmptyBorder(10, 15, 10, 0)));
		Loans.setLayout(new BorderLayout(0, 0));

		JLabel label = new JLabel("<html><h3>Loans:</h3></html>");
		label.setPreferredSize(new Dimension(120, 40));
		Loans.add(label, BorderLayout.WEST);

		JPanel center = new JPanel();
		Loans.add(center, BorderLayout.CENTER);
		center.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		cbx_loans = new JComboBox<LoanData>();
		cbx_loans.setPreferredSize(new Dimension(150, 50));
		center.add(cbx_loans);
		cbx_loans.setMinimumSize(new Dimension(50, 50));

		JButton btn_payLoan = new JButton("<html><h4>Pay Loan</h4></html>");
		btn_payLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				ClientAccount account = (ClientAccount) JOptionPane.showInputDialog(null,
						"Which account do you want to pay the loan with?", "Pay Loan", JOptionPane.QUESTION_MESSAGE,
						null, getOpenAccounts(), null);

				if (account == null)
					return;

				String payString = JOptionPane.showInputDialog(null, "How much do you want to pay off?", "Pay Loan",
						JOptionPane.QUESTION_MESSAGE);

				if (payString == null)
					return;

				try {
					double pay = Double.parseDouble(payString);
					pay /= client.preferredCurrency.conversion;
					Loan loan = cbx_loans.getItemAt(cbx_loans.getSelectedIndex()).loan;

					if (pay < 0 || pay > account.getMoney())
						throw new NumberFormatException();
					else if (pay >= loan.getMoney()) {
						double excess = pay - loan.getMoney();
						new LoanPayment(account, loan.getMoney(), loan);
						cbx_loans.removeItemAt(cbx_loans.getSelectedIndex());
						cbx_loans.setSelectedItem(null);
						if (excess > 0) 
							new Deposit(account, excess);
						
					} else {
						new LoanPayment(account, pay, loan);
					}

					cbx_loans.setVisible(false);
					cbx_loans.setVisible(true);

				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid amount", "Pay Loan", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		center.add(btn_payLoan);
		btn_payLoan.setPreferredSize(new Dimension(150, 50));

		JPanel right = new JPanel();
		right.setBorder(new EmptyBorder(0, 0, 0, 35));
		Loans.add(right, BorderLayout.EAST);

		JButton btn_newLoan = new JButton("<html><h4>Get New Loan</h4></html>");
		btn_newLoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClientAccount account = (ClientAccount) JOptionPane.showInputDialog(null,
						"Which account do you want the loan to be deposited in?", "Loan", JOptionPane.QUESTION_MESSAGE,
						null, getOpenAccounts(), null);

				if (account == null)
					return;

				String amountString = JOptionPane.showInputDialog(null, "How much do you wish to loan?", "Loan",
						JOptionPane.PLAIN_MESSAGE);

				if (amountString == null)
					return;

				try {
					double amount = Double.parseDouble(amountString);

					amount /= client.preferredCurrency.conversion;

					Loan loan = client.addLoan(amount, account);
					cbx_loans.addItem(new LoanData(loan, client.preferredCurrency));

				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid amount", "Loan", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		right.add(btn_newLoan);
		btn_newLoan.setPreferredSize(new Dimension(150, 50));

	}

	public void setClient(Client client) {
		this.client = client;

		for (ClientAccount acc : client.getAcounts()) {
			if (!acc.isClosed) {
				if (acc instanceof CheckingAccount)
					cbx_checkingAccount.addItem(acc);
				else
					cbx_savingAccount.addItem(acc);
			}
		}

		lbl_hello.setText(String.format("<html><h1>Hello, %s</h1></html>", client.name));
		cbx_prefCurrency.setSelectedItem(client.preferredCurrency);
		cbx_currencyChecking.setSelectedItem(client.preferredCurrency);
		cbx_currencySaving.setSelectedItem(client.preferredCurrency);

		for (Loan loan : client.getLoans()) {
			if (loan.getMoney() > 0)
				cbx_loans.addItem(new LoanData(loan, client.preferredCurrency));
		}
		revalidate();
	}

	private void clean() {
		client = null;
		cbx_checkingAccount.removeAllItems();
		cbx_savingAccount.removeAllItems();
		lbl_hello.setText("");
		cbx_loans.removeAllItems();

		revalidate();
	}
	
	private ClientAccount[] getOpenAccounts() {
		ClientAccount[] options = new ClientAccount[cbx_checkingAccount.getItemCount() + cbx_savingAccount.getItemCount()];
		for(int i = 0; i < cbx_checkingAccount.getItemCount(); i++) 
			options[i] = cbx_checkingAccount.getModel().getElementAt(i);
		for(int i = 0; i < cbx_savingAccount.getItemCount(); i++)
			options[i + cbx_checkingAccount.getItemCount()] = cbx_savingAccount.getModel().getElementAt(i);
		return options;
	}
}

class LoanData {
	public Loan loan;
	public Currency curr;

	public LoanData(Loan loan, Currency curr) {
		this.loan = loan;
		this.curr = curr;
	}

	public String toString() {
		return String.format("%.2f", loan.getMoney() * curr.conversion);
	}
}
