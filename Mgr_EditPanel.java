package bank_atm.gui;

import bank_atm.*;
import java.awt.*;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Mgr_EditPanel extends JPanel {
	private JFormattedTextField[] textFields;

	public Mgr_EditPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		textFields = new JFormattedTextField[Bank.Fee.values().length];

		for (int i = 0; i < Bank.Fee.values().length; i++) {
			JPanel panel = new JPanel();
			panel.setBorder(new EmptyBorder(5, 20, 5, 20));
			add(panel);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

			JLabel lblNewLabel = new JLabel(Bank.Fee.values()[i].name());
			lblNewLabel.setBorder(new EmptyBorder(0, 0, 0, 20));
			lblNewLabel.setPreferredSize(new Dimension(200, 25));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			panel.add(lblNewLabel);

			try {
				MaskFormatter formatter = new MaskFormatter("**************");
				formatter.setAllowsInvalid(false);
				textFields[i] = new JFormattedTextField(formatter);
				textFields[i].setValue(Bank.Fee.values()[i].getAmount());
				textFields[i].setBorder(new EmptyBorder(0, 10, 0, 0));
				panel.add(textFields[i]);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
		}

		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new EmptyBorder(10, 20, 10, 20));
		add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Skip Time!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Bank.BANK.monthlyChange();
				JOptionPane.showMessageDialog(null, "Time have been skipped.", "Skip Time", JOptionPane.PLAIN_MESSAGE);
			}
		});
		btnNewButton.setToolTipText("Have interest added to every account and every loan.");
		panel_9.add(btnNewButton, BorderLayout.WEST);

		JButton btnNewButton_1 = new JButton("Save Changes");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		panel_9.add(btnNewButton_1, BorderLayout.EAST);
	}

}
