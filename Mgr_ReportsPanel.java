package bank_atm.gui;

import bank_atm.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Mgr_ReportsPanel extends JSplitPane {

	private JPanel pnl_report;
	private JPanel pnl_fee;
	private JPanel pnl_loan;
	private JPanel pnl_interest;

	public Mgr_ReportsPanel() {
		setOrientation(JSplitPane.VERTICAL_SPLIT);

		pnl_report = new JPanel();
		pnl_report.setBorder(new EmptyBorder(10, 15, 10, 15));
		setTopComponent(pnl_report);
		pnl_report.setLayout(new GridLayout(0, 2, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		setBottomComponent(scrollPane);
		
		JPanel viewport = new JPanel();
		viewport.setLayout(new BoxLayout(viewport, BoxLayout.X_AXIS));
		scrollPane.setViewportView(viewport);
		
		pnl_fee = new JPanel();
		pnl_fee.setBorder(new EmptyBorder(0, 10, 10, 10));
		viewport.add(pnl_fee);
		pnl_fee.setLayout(new GridLayout(0, 3, 0, 0));
		
		pnl_loan = new JPanel();
		pnl_loan.setBorder(new EmptyBorder(0, 10, 10, 10));
		viewport.add(pnl_loan);
		pnl_loan.setLayout(new GridLayout(0, 3, 0, 0));
		
		pnl_interest = new JPanel();
		pnl_interest.setBorder(new EmptyBorder(0, 10, 10, 10));
		viewport.add(pnl_interest);
		pnl_interest.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel header = new JPanel();
		header.setBorder(new EmptyBorder(0, 10, 0, 10));
		scrollPane.setColumnHeaderView(header);
		header.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Fees Paid by Client");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Loans Interest");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Savings Account Interest");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(lblNewLabel_2);
		
		init();
	}
	
	public void init() {
		initBasicReport();
		initFeeReport();
		initLoanReport();
		initInterestReport();
		
		Bank.BANK.cleanReport();
	}
	


	public void clean() {
		pnl_report.removeAll();
		pnl_fee.removeAll();
		pnl_loan.removeAll();
		pnl_interest.removeAll();
	}

	private void initBasicReport() {
		double fee 			= Arrays.asList(Bank.BANK.getClients()).stream().mapToDouble(
				x -> Arrays.asList(x.getAcounts()).stream().mapToDouble(
						y -> y.getFeePaid()).sum()).sum();
		
		double feeLast 		= Arrays.asList(Bank.BANK.getClients()).stream().mapToDouble(
				x -> Arrays.asList(x.getAcounts()).stream().mapToDouble(
						y -> y.getFeePaidLastReport()).sum()).sum();
		
		double loan			= Arrays.asList(Bank.BANK.getClients()).stream().mapToDouble(
				x -> Arrays.asList(x.getLoans()).stream().mapToDouble(
						y -> y.getLoanInterest()).sum()).sum();
		
		double loanLast 	= Arrays.asList(Bank.BANK.getClients()).stream().mapToDouble(
				x -> Arrays.asList(x.getLoans()).stream().mapToDouble(
						y -> y.getLoanInterestLastReport()).sum()).sum();

		double interest 	= Arrays.asList(Bank.BANK.getClients()).stream().mapToDouble(
				x -> Arrays.asList(x.getAcounts()).stream().mapToDouble(
						y -> y.getSavingInterests()).sum()).sum();

		double interestLast	= Arrays.asList(Bank.BANK.getClients()).stream().mapToDouble(
				x -> Arrays.asList(x.getAcounts()).stream().mapToDouble(
						y -> y.getSavingInterestsLastReport()).sum()).sum();
		
		JLabel lbl_fee = new JLabel("<html><p>Fees paid by Clients:</p></html>");
		pnl_report.add(lbl_fee);

		String feeString = String.format("<html><p>$%.2f (<font color='%s'>%.2f</font>) </p></html>", 
				fee, feeLast <= 0 ? "red" : "green", feeLast);
		JLabel lbl_feeAmount = new JLabel(feeString);
		pnl_report.add(lbl_feeAmount);

		JLabel lbl_loan = new JLabel("<html><p>Loans Interests:</p></html>");
		lbl_loan.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnl_report.add(lbl_loan);

		String loanString = String.format("<html><p>$%.2f (<font color='%s'>%.2f</font>) </p></html>", 
				loan, loanLast <= 0 ? "red" : "green", loanLast);
		JLabel lbl_loanAmount = new JLabel(loanString);
		lbl_loanAmount.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnl_report.add(lbl_loanAmount);

		JLabel lbl_interest = new JLabel("<html><p>Saving Account Interest:</p></html>");
		lbl_interest.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnl_report.add(lbl_interest);

		String interestString = String.format("<html><p>$%.2f (<font color='%s'>%.2f</font>) </p></html>", 
				interest, interestLast <= 0 ? "green" : "red", interestLast);
		JLabel lbl_interestAmount = new JLabel(interestString);
		lbl_interestAmount.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		pnl_report.add(lbl_interestAmount);
	}

	private void initFeeReport() {
		JLabel lbl_name = new JLabel("Name");
		lbl_name.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_fee.add(lbl_name);
		
		JLabel lbl_fee = new JLabel("Fee");
		lbl_fee.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lbl_fee.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_fee.add(lbl_fee);
		
		JLabel lbl_amount = new JLabel("Amount");
		lbl_amount.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_fee.add(lbl_amount);
		
		for (Client clt : Bank.BANK.getClients())
			for (ClientAccount acc : clt.getAcounts())
				for (Transaction t : acc.getTransactionListLastReport())
					if(t instanceof Fee) {
						JLabel name = new JLabel(String.format("%s", clt.name));
						name.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
						name.setHorizontalAlignment(SwingConstants.CENTER);
						pnl_fee.add(name);
						
						JLabel fee = new JLabel(String.format("%s", t.toString()));
						fee.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
						fee.setHorizontalAlignment(SwingConstants.CENTER);
						pnl_fee.add(fee);
						
						JLabel amount = new JLabel(String.format("%.2f", t.amount));
						amount.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
						amount.setHorizontalAlignment(SwingConstants.CENTER);
						pnl_fee.add(amount);
					}
	}
	
	private void initLoanReport() {
		JLabel lbl_name = new JLabel("Name");
		lbl_name.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_loan.add(lbl_name);
		
		JLabel lbl_amount = new JLabel("Amount");
		lbl_amount.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_loan.add(lbl_amount);
		
		JLabel lbl_interest = new JLabel("Interest");
		lbl_interest.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_interest.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_loan.add(lbl_interest);
		
		for (Client clt : Bank.BANK.getClients())
			for (Loan loan : clt.getLoans())
				for (LoanInterest t : loan.getLoanInterestLastReportList()) {
					JLabel name = new JLabel(String.format("%s", clt.name));
					name.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
					name.setHorizontalAlignment(SwingConstants.CENTER);
					pnl_loan.add(name);
					
					JLabel amount = new JLabel(String.format("%.2f", t.initalAmount));
					amount.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
					amount.setHorizontalAlignment(SwingConstants.CENTER);
					pnl_loan.add(amount);
					
					JLabel interest = new JLabel(String.format("%.2f", t.amount));
					interest.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
					interest.setHorizontalAlignment(SwingConstants.CENTER);
					pnl_loan.add(interest);
				}
	}
	
	private void initInterestReport() {
		JLabel lbl_name = new JLabel("Name");
		lbl_name.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_name.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_interest.add(lbl_name);
		
		JLabel lbl_fee = new JLabel("Amount");
		lbl_fee.setBorder(new MatteBorder(1, 0, 1, 0, (Color) new Color(0, 0, 0)));
		lbl_fee.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_interest.add(lbl_fee);
		
		JLabel lbl_amount = new JLabel("Interest");
		lbl_amount.setBorder(new LineBorder(new Color(0, 0, 0)));
		lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
		pnl_interest.add(lbl_amount);
		
		for (Client clt : Bank.BANK.getClients())
			for (ClientAccount acc : clt.getAcounts())
				for (Transaction t : acc.getTransactionList())
					if(t instanceof SavingsInterest) {
						JLabel name = new JLabel(String.format("%s", clt.name));
						name.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
						name.setHorizontalAlignment(SwingConstants.CENTER);
						pnl_interest.add(name);
						
						JLabel amount = new JLabel(String.format("%.2f", ((SavingsInterest) t).initalAmount));
						amount.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
						amount.setHorizontalAlignment(SwingConstants.CENTER);
						pnl_interest.add(amount);
						
						JLabel interest = new JLabel(String.format("%.2f", t.amount));
						interest.setBorder(new MatteBorder(0, 1, 1, 1, Color.BLACK));
						interest.setHorizontalAlignment(SwingConstants.CENTER);
						pnl_interest.add(interest);
					}
	}


}
