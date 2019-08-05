package bank_atm.gui;

import bank_atm.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Mgr_ClientDetailFrame extends JFrame {

	private Client clt;
	private JPanel pnl_transaction;

	public Mgr_ClientDetailFrame(Client clt) {
		this.clt = clt;

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JScrollPane account = new JScrollPane();
		tabbedPane.addTab("Account", null, account, null);

		JPanel viewport = new JPanel();
		viewport.setBorder(new EmptyBorder(15, 15, 15, 15));
		account.setViewportView(viewport);
		viewport.setLayout(new BorderLayout(0, 0));

		pnl_transaction = new JPanel();
		pnl_transaction.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnl_transaction.setPreferredSize(new Dimension(500, 200));
		viewport.add(pnl_transaction);
		pnl_transaction.setLayout(new GridLayout(0, 4, 0, 0));

		JComboBox<ClientAccount> comboBox = new JComboBox<ClientAccount>(clt.getAcounts());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clean();
				show(comboBox.getItemAt(comboBox.getSelectedIndex()).getTransactionList());
			}
		});
		clean();
		comboBox.setSelectedIndex(0);
		
		viewport.add(comboBox, BorderLayout.NORTH);
		
		pack();
	}

	private void clean() {
		pnl_transaction.removeAll();
		
		JLabel lbl_sender = new JLabel("<html><h3>Sender</h3></html>");
		lbl_sender.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sender.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_transaction.add(lbl_sender);

		JLabel lbl_reciever = new JLabel("<html><h3>Reciever</h3></html>");
		lbl_reciever.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reciever.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_transaction.add(lbl_reciever);

		JLabel lbl_transaction = new JLabel("<html><h3>Transaction</h3></html>");
		lbl_transaction.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_transaction.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_transaction.add(lbl_transaction);

		JLabel lbl_amount = new JLabel("<html><h3>Amount</h3></html>");
		lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_amount.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnl_transaction.add(lbl_amount);
	}

	private void show(Transaction[] tArray) {
		for (Transaction t : tArray) {
			String sender = t.sender.toString();
			String reciever = t.reciever.toString();
			String transaction = t.toString();
			double amount = t.amount;
			amount *= clt.preferredCurrency.conversion;

			JLabel lbl_sender = new JLabel(String.format("<html><h4>%s</h4></html>", sender));
			lbl_sender.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_sender.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnl_transaction.add(lbl_sender);

			JLabel lbl_reciever = new JLabel(String.format("<html><h4>%s</h4></html>", reciever));
			lbl_reciever.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_reciever.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnl_transaction.add(lbl_reciever);

			JLabel lbl_transaction = new JLabel(String.format("<html><h4>%s</h4></html>", transaction));
			lbl_transaction.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_transaction.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnl_transaction.add(lbl_transaction);

			JLabel lbl_amount = new JLabel(String.format("<html><h4>%.2f</h4></html>", amount));
			lbl_amount.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_amount.setBorder(new LineBorder(new Color(0, 0, 0)));
			pnl_transaction.add(lbl_amount);

			pnl_transaction.revalidate();
		}
	}
}
