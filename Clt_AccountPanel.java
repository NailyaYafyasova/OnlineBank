package bank_atm.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bank_atm.Bank;
import bank_atm.CheckingAccount;
import bank_atm.Client;
import bank_atm.ClientAccount;
import bank_atm.SavingsAccount;
import bank_atm.Transaction;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.text.TabExpander;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import java.awt.Component;

@SuppressWarnings("serial")
public class Clt_AccountPanel extends JScrollPane {
	private ClientAccount account;
	private JLabel lbl_number, lbl_money;
	private JLabel lbl_type;
	private JButton btn_transfer;
	private JPanel bottom;

	public Clt_AccountPanel() {

		JPanel viewport = new JPanel();
		setViewportView(viewport);
		viewport.setLayout(new BorderLayout(0, 0));

		JSplitPane top = new JSplitPane();
		top.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		top.setResizeWeight(1.0);
		viewport.add(top, BorderLayout.NORTH);

		JPanel topLeft = new JPanel();
		topLeft.setBorder(new EmptyBorder(25, 25, 0, 0));
		top.setLeftComponent(topLeft);
		topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));

		lbl_type = new JLabel("type ...");
		topLeft.add(lbl_type);

		lbl_number = new JLabel("number ...");
		topLeft.add(lbl_number);

		lbl_money = new JLabel("noney ...");
		topLeft.add(lbl_money);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 10, 10));
		topLeft.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		panel_1.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 10));

		JButton btn_close = new JButton("Close Account");
		btn_close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = String.format("Closing the acount will cost %s%.2f",
						account.getOwnedBy().preferredCurrency,
						Bank.Fee.CloseAcount.getAmount() * account.getOwnedBy().preferredCurrency.conversion);
				int choice = JOptionPane.showConfirmDialog(null, message, "Close Account", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (choice == 0) {
					double amount = account.close();
					if (amount >= 0) {
						JOptionPane.showMessageDialog(null,
								String.format("You have successfully closed the account.\nYou withdrew %s%.2f",
										account.getOwnedBy().preferredCurrency,
										amount * account.getOwnedBy().preferredCurrency.conversion),
								"Close Account", JOptionPane.PLAIN_MESSAGE);

						account.isClosed = true;
						((ClientPanel) getParent()).showClient((Client) account.getOwnedBy());
						clean();

					} else {
						JOptionPane.showMessageDialog(null,
								String.format("You cannot close this account yet.\nYou still need %s%.2f",
										account.getOwnedBy().preferredCurrency,
										amount * account.getOwnedBy().preferredCurrency.conversion),
								"Close Account", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		panel_1.add(btn_close);

		JButton btn_back = new JButton("Back");
		panel_1.add(btn_back);
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((ClientPanel) getParent()).showClient((Client) account.getOwnedBy());
				clean();
			}
		});

		JPanel topRight = new JPanel();
		topRight.setBorder(new EmptyBorder(15, 15, 15, 15));
		top.setRightComponent(topRight);
		topRight.setLayout(new GridLayout(0, 1, 0, 15));

		btn_transfer = new JButton("Transfer");
		btn_transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accountNumber = JOptionPane.showInputDialog(null,
						"Please enter the account number you want to sent money to.", "Money Transfer",
						JOptionPane.PLAIN_MESSAGE);
				if (accountNumber == null)
					return;
				try {
					long number = Long.parseUnsignedLong(accountNumber);

					for (Client clt : Bank.BANK.getClients()) {
						for (ClientAccount reciever : clt.getAcounts()) {
							if (number == reciever.number) {
								String message = String.format("How much do you want to transfer to\n Account: %d ?",
										number);
								String transferString = JOptionPane.showInputDialog(null, message, "Money Transfer",
										JOptionPane.PLAIN_MESSAGE);
								
								if (transferString == null)
									return;
								
								double amount = Double.parseDouble(transferString);
								amount /= account.getOwnedBy().preferredCurrency.conversion;

								message = String.format("Transfering money will cost %s%.2f\nDo you want to continue?",
										account.getOwnedBy().preferredCurrency, Bank.Fee.Transaction.getAmount()
												* account.getOwnedBy().preferredCurrency.conversion);
								int choice = JOptionPane.showConfirmDialog(null, message, "Money Transfer",
										JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

								if (choice == 0) {
									if (!account.moneyTransfer(reciever, amount))
										JOptionPane.showMessageDialog(null, "Not enough balance.", "Money Transfer",
												JOptionPane.WARNING_MESSAGE);
								}

								ClientAccount temp = account;
								clean();
								setAccount(temp);
								revalidate();

								return;
							}
						}
					}
					throw new NumberFormatException();
				} catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(null, "Invalid number", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topRight.add(btn_transfer);

		JButton btn_withdrawal = new JButton("Withdrawal");
		btn_withdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String withdrawString = JOptionPane.showInputDialog(null, "How much do you want to withdraw?",
							"Withdrawal", JOptionPane.PLAIN_MESSAGE);

					if (withdrawString != null) {

						double amount = Double.parseDouble(withdrawString);
						amount /= account.getOwnedBy().preferredCurrency.conversion;

						String message = String.format("Withdrawal of money will cost %s%.2f\nDo you want to continue?",
								account.getOwnedBy().preferredCurrency,
								Bank.Fee.Withdrawal.getAmount() * account.getOwnedBy().preferredCurrency.conversion);
						int choice = JOptionPane.showConfirmDialog(null, message, "Withdrawal",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

						if (choice == 0) {
							if (!account.withdrawal(amount))
								JOptionPane.showMessageDialog(null, "Not enough balance.", "Withdrawal",
										JOptionPane.WARNING_MESSAGE);
						}

						ClientAccount temp = account;
						clean();
						setAccount(temp);
						revalidate();
					}

					return;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid number", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topRight.add(btn_withdrawal);

		JButton btn_deposit = new JButton("Deposit");
		btn_deposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String depositString = JOptionPane.showInputDialog(null, "How much do you want to deposit?",
							"Deposit", JOptionPane.PLAIN_MESSAGE);

					if (depositString != null) {

						double amount = Double.parseDouble(depositString);
						amount /= account.getOwnedBy().preferredCurrency.conversion;

						account.deposit(amount);

						ClientAccount temp = account;
						clean();
						setAccount(temp);
						revalidate();
					}

					return;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid number", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		topRight.add(btn_deposit);

		JButton lbl_btn_loans = new JButton("Get Loans");
		lbl_btn_loans.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String loanString = JOptionPane.showInputDialog(null, "How much do you want to loan out?",
							"Deposit", JOptionPane.PLAIN_MESSAGE);

					if (loanString != null) {
						double amount = Double.parseDouble(loanString);
						amount /= account.getOwnedBy().preferredCurrency.conversion;
						((Client) account.getOwnedBy()).addLoan(amount, account);

						ClientAccount temp = account;
						clean();
						setAccount(temp);
						revalidate();
					}

					return;
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid number", "Invalid", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		topRight.add(lbl_btn_loans);

		bottom = new JPanel();
		bottom.setBorder(new EmptyBorder(15, 25, 15, 25));
		viewport.add(bottom, BorderLayout.CENTER);
		bottom.setLayout(new GridLayout(0, 4, 0, 0));

		JLabel lbl_sender = new JLabel("<html><h3>Sender</h3></html>");
		lbl_sender.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sender.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_sender);

		JLabel lbl_reciever = new JLabel("<html><h3>Reciever</h3></html>");
		lbl_reciever.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reciever.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_reciever);

		JLabel lbl_transaction = new JLabel("<html><h3>Transaction</h3></html>");
		lbl_transaction.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_transaction.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_transaction);

		JLabel lbl_amount = new JLabel("<html><h3>Amount</h3></html>");
		lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_amount.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_amount);
	}

	public void setAccount(ClientAccount acc) {
		this.account = acc;

		this.lbl_type.setText(String.format("<html><h2>%s</h2></html>", acc.getClass().getName()));
		this.lbl_number.setText(String.format("<html><h4>Account Number: %016d</h4></html>", account.number));
		this.lbl_money.setText(String.format("<html><h4>Current Balance: %s%.2f</h4></html>",
				account.getOwnedBy().preferredCurrency.symbol,
				account.getMoney() * account.getOwnedBy().preferredCurrency.conversion));

		this.btn_transfer.setVisible(acc instanceof CheckingAccount);

		Transaction[] tArray = acc.getTransactionList();
		List<Transaction> lst = Arrays.asList(tArray);
		Collections.reverse(lst);
		lst.toArray(tArray);

		for (Transaction t : tArray) {
			String sender = t.sender.toString();
			String reciever = t.reciever.toString();
			String transaction = t.toString();
			double amount = t.amount;
			amount *= account.getOwnedBy().preferredCurrency.conversion;

			if (sender.equals("" + acc.number)) {
				sender = "This Account";
				amount *= -1;
			} else if (reciever.equals("" + acc.number)) {
				reciever = "This Account";
			}

			JLabel lbl_sender = new JLabel(String.format("<html><h4>%s</h4></html>", sender));
			lbl_sender.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_sender.setBorder(new LineBorder(new Color(0, 0, 0)));
			bottom.add(lbl_sender);

			JLabel lbl_reciever = new JLabel(String.format("<html><h4>%s</h4></html>", reciever));
			lbl_reciever.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_reciever.setBorder(new LineBorder(new Color(0, 0, 0)));
			bottom.add(lbl_reciever);

			JLabel lbl_transaction = new JLabel(String.format("<html><h4>%s</h4></html>", transaction));
			lbl_transaction.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_transaction.setBorder(new LineBorder(new Color(0, 0, 0)));
			bottom.add(lbl_transaction);

			JLabel lbl_amount = new JLabel(String.format("<html><h4>%.2f</h4></html>", amount));
			lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_amount.setBorder(new LineBorder(new Color(0, 0, 0)));
			bottom.add(lbl_amount);

			revalidate();
		}
	}

	private void clean() {
		account = null;

		bottom.removeAll();

		JLabel lbl_sender = new JLabel("<html><h3>Sender</h3></html>");
		lbl_sender.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sender.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_sender);

		JLabel lbl_reciever = new JLabel("<html><h3>Reciever</h3></html>");
		lbl_reciever.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reciever.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_reciever);

		JLabel lbl_transaction = new JLabel("<html><h3>Transaction</h3></html>");
		lbl_transaction.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_transaction.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_transaction);

		JLabel lbl_amount = new JLabel("<html><h3>Amount</h3></html>");
		lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_amount.setBorder(new LineBorder(new Color(0, 0, 0)));
		bottom.add(lbl_amount);

		revalidate();
	}

}
