import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class managerCheckClient extends JPanel {
	private static JTextField fnameField;
	private static JTextField mnameField;
	private static JTextField lnameField;
	private static JTextField loginField;
	private static JPanel panel = new JPanel();
	

	public static JPanel init(JFrame frame, Manager manager) {
		panel.setLayout(null);
		
		JLabel please = new JLabel("Please input information of the client you are looking for:");
		please.setBounds(39, 37, 371, 16);
		please.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(please);
		
		JLabel fname = new JLabel("First Name");
		fname.setBounds(112, 70, 100, 20);
		panel.add(fname);
		
		JLabel mname = new JLabel("Middle Name");
		mname.setBounds(112, 99, 100, 20);
		panel.add(mname);
		
		JLabel lname = new JLabel("Last Name");
		lname.setBounds(112, 131, 100, 20);
		panel.add(lname);
		
		JLabel login = new JLabel("Login");
		login.setBounds(112, 163, 100, 20);
		panel.add(login);
		
		fnameField = new JTextField();
		fnameField.setBounds(224, 70, 130, 20);
		panel.add(fnameField);
		fnameField.setColumns(10);
		
		mnameField = new JTextField();
		mnameField.setColumns(10);
		mnameField.setBounds(224, 96, 130, 20);
		panel.add(mnameField);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(224, 128, 130, 20);
		panel.add(lnameField);
		
		loginField = new JTextField();
		loginField.setColumns(10);
		loginField.setBounds(224, 160, 130, 20);
		panel.add(loginField);
		
		JButton check = new JButton("Check Client");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String firstN = fnameField.getText();
				String middleN = mnameField.getText();
				String lastN = lnameField.getText();
				String loginN = loginField.getText();
				
				JLabel checkClient = new JLabel( manager.checkClient(new Client(firstN, lastN, loginN)) );
				JPanel clientPanel = new JPanel();
				clientPanel.add(checkClient);
				
				JButton back = new JButton("Back");
				
				class backListener implements ActionListener {
					public void actionPerformed(ActionEvent e ) {
						JPanel panel_New = managerDisplay.init(frame, manager);
						
						frame.setContentPane(panel_New);
						frame.revalidate();
						frame.repaint();
					}
				}
				
				backListener backL = new backListener();
				back.addActionListener(backL);
				clientPanel.add(back);
				
				frame.setContentPane(clientPanel);
				frame.revalidate();
				frame.repaint();

			}
		});
		check.setBounds(166, 205, 117, 29);
		panel.add(check);
		return panel;

	}

}
