package bank_atm.gui;

import bank_atm.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class Mgr_ClientsPanel extends JScrollPane {

	private JPanel viewport;

	public Mgr_ClientsPanel() {
		viewport = new JPanel();
		viewport.setPreferredSize(new Dimension(1, 1));
		viewport.setBorder(new EmptyBorder(15, 15, 15, 15));
		setViewportView(viewport);
		viewport.setLayout(new GridLayout(0, 7, 0, 0));
		
		initEmpty();
	}

	protected void clean() {
		viewport.removeAll();
	}

	private void initEmpty() {
		JLabel lbl_name = new JLabel("<html><h3>Name</h3></html>");
		lbl_name.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		viewport.add(lbl_name);

		JLabel lbl_balance = new JLabel("<html><h3>Balance</h3></html>");
		lbl_balance.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_balance.setHorizontalAlignment(SwingConstants.CENTER);
		viewport.add(lbl_balance);

		JLabel lbl_transactions = new JLabel("<html><h3>Transactions</h3></html>");
		lbl_transactions.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_transactions.setHorizontalAlignment(SwingConstants.CENTER);
		viewport.add(lbl_transactions);

		JLabel lbl_loans = new JLabel("<html><h3>Loans</h3></html>");
		lbl_loans.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_loans.setHorizontalAlignment(SwingConstants.CENTER);
		viewport.add(lbl_loans);

		JLabel lbl_fees = new JLabel("<html><h3>Fees</h3></html>");
		lbl_fees.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_fees.setHorizontalAlignment(SwingConstants.CENTER);
		viewport.add(lbl_fees);

		JLabel lbl_interests = new JLabel("<html><h3>Interests</h3></html>");
		lbl_interests.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_interests.setHorizontalAlignment(SwingConstants.CENTER);
		viewport.add(lbl_interests);

		JLabel lbl_more = new JLabel();
		lbl_more.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_more.setHorizontalAlignment(SwingConstants.CENTER);
		viewport.add(lbl_more);
	}

	public void init() {
		initEmpty();
		
		List<Client> clientList = Arrays.asList(Bank.BANK.getClients());

		for (Client clt : clientList) {
			String name = clt.name;
			double balance 			= Arrays.asList(clt.getAcounts()).stream().mapToDouble(x -> x.getMoney()).sum();
			double last_balance 	= balance - Arrays.asList(clt.getAcounts()).stream().mapToDouble(x -> x.getMoneyLastReport()).sum();
			int transactions 		= Arrays.asList(clt.getAcounts()).stream().mapToInt(x -> x.getTransactionList().length).sum();
			int last_transactions 	= transactions - Arrays.asList(clt.getAcounts()).stream().mapToInt(x -> x.getTransactionListLastReport().length).sum();
			double loans			= Arrays.asList(clt.getLoans()).stream().mapToDouble(x -> x.getMoney()).sum();
			double last_loans 		= loans - Arrays.asList(clt.getLoans()).stream().mapToDouble(x -> x.getMoneyLastReport()).sum();
			double fees 			= Arrays.asList(clt.getAcounts()).stream().mapToDouble(x -> x.getFeePaid()).sum();
			double last_fees 		= Arrays.asList(clt.getAcounts()).stream().mapToDouble(x -> x.getFeePaidLastReport()).sum();
			double interests 		= Arrays.asList(clt.getAcounts()).stream().mapToDouble(x -> x.getSavingInterests()).sum() 
					+ Arrays.asList(clt.getLoans()).stream().mapToDouble(x -> x.getLoanInterest()).sum();
			double last_interests 	= Arrays.asList(clt.getAcounts()).stream().mapToDouble(x -> x.getSavingInterestsLastReport()).sum()
					+ Arrays.asList(clt.getLoans()).stream().mapToDouble(x -> x.getLoanInterestLastReport()).sum();

			JLabel lbl_name = new JLabel(String.format("<html><h4>%s</h4></html>", name));
			lbl_name.setBorder(new LineBorder(new Color(0, 0, 0)));
			lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
			viewport.add(lbl_name);

			JLabel lbl_balance = new JLabel(
					String.format("<html><h4>%.2f<br>\n(<font color=\"%s\">%.2f</font>)</h4></html>", balance,
							last_balance >= 0 ? "green" : "red", last_balance));
			lbl_balance.setBorder(new LineBorder(new Color(0, 0, 0)));
			lbl_balance.setHorizontalAlignment(SwingConstants.CENTER);
			viewport.add(lbl_balance);

			JLabel lbl_transactions = new JLabel(
					String.format("<html><h4>%d<br>\n(<font color=\"%s\">%d</font>)</h4></html>", transactions,
							last_transactions >= 0 ? "green" : "red", last_transactions));
			lbl_transactions.setBorder(new LineBorder(new Color(0, 0, 0)));
			lbl_transactions.setHorizontalAlignment(SwingConstants.CENTER);
			viewport.add(lbl_transactions);

			JLabel lbl_loans = new JLabel(String.format("<html><h4>%.2f<br>(<font color=\"%s\">%.2f</font>)</h4></html>",
					loans, last_loans >= 0 ? "green" : "red", last_loans));
			lbl_loans.setBorder(new LineBorder(new Color(0, 0, 0)));
			lbl_loans.setHorizontalAlignment(SwingConstants.CENTER);
			viewport.add(lbl_loans);

			JLabel lbl_fees = new JLabel(String.format("<html><h4>%.2f<br>(<font color=\"%s\">%.2f</font>)</h4></html>",
					fees, last_fees >= 0 ? "green" : "red", last_fees));
			lbl_fees.setBorder(new LineBorder(new Color(0, 0, 0)));
			lbl_fees.setHorizontalAlignment(SwingConstants.CENTER);
			viewport.add(lbl_fees);

			JLabel lbl_interests = new JLabel(
					String.format("<html><h4>%.2f<br>(<font color=\"%s\">%.2f</font>)</h4></html>", interests,
							last_interests >= 0 ? "green" : "red", last_interests));
			lbl_interests.setBorder(new LineBorder(new Color(0, 0, 0)));
			lbl_interests.setHorizontalAlignment(SwingConstants.CENTER);
			viewport.add(lbl_interests);

			JPanel panel = new JPanel();
			panel.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 5)));
			viewport.add(panel);
			panel.setLayout(new BorderLayout(0, 0));

			JButton btn_more = new JButton();
			panel.add(btn_more);
			btn_more.setText("<html><h4>More Details</h4></html>");
			btn_more.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Mgr_ClientDetailFrame(clt).setVisible(true);
				}
			});
		}
	}
}
