import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;

public class clientCreate extends JPanel {
	
	private static JPanel panel = new JPanel();
	private static JTextField fnameC;
	private static JTextField mnameC;
	private static JTextField lnameC;
	private static JTextField loginC;
	private static JTextField passwordC;
	
	
	
	public static JPanel init(JFrame frame) {
		panel.setLayout(null);
		
		JLabel info = new JLabel("Please Enter Your Information");
		info.setBounds(0, 18, 450, 16);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(info);
		
		JLabel fname = new JLabel("First Name");
		fname.setBounds(104, 57, 120, 20);
		panel.add(fname);
		fname.setHorizontalAlignment(SwingConstants.LEFT);
		
		fnameC = new JTextField();
		fnameC.setBounds(211, 57, 120, 20);
		panel.add(fnameC);
		fnameC.setColumns(10);
		
		JLabel mname = new JLabel("Middle Name");
		mname.setBounds(104, 86, 120, 20);
		panel.add(mname);
		mname.setHorizontalAlignment(SwingConstants.LEFT);
		
		mnameC = new JTextField();
		mnameC.setBounds(211, 86, 120, 20);
		panel.add(mnameC);
		mnameC.setColumns(10);
		
		JLabel lname = new JLabel("Last Name");
		lname.setBounds(104, 118, 120, 20);
		panel.add(lname);
		lname.setHorizontalAlignment(SwingConstants.LEFT);
		
		lnameC = new JTextField();
		lnameC.setBounds(211, 118, 120, 20);
		panel.add(lnameC);
		lnameC.setColumns(10);
		
		JLabel login = new JLabel("Login");
		login.setBounds(104, 150, 120, 20);
		panel.add(login);
		login.setHorizontalAlignment(SwingConstants.LEFT);
		
		loginC = new JTextField();
		loginC.setBounds(211, 150, 120, 20);
		panel.add(loginC);
		loginC.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setBounds(104, 182, 120, 20);
		panel.add(password);
		password.setHorizontalAlignment(SwingConstants.LEFT);
		
		passwordC = new JTextField();
		passwordC.setBounds(211, 182, 120, 20);
		panel.add(passwordC);
		passwordC.setColumns(10);
		
		JButton create = new JButton("Create");
		create.setBounds(159, 214, 117, 29);
		
		
		class createListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				
				System.out.println("Created");
				String fname = fnameC.getText();				
				String mname = mnameC.getText();
				String lname = lnameC.getText();
				String login = loginC.getText();
				String password = passwordC.getText();
				
				
				Client c = new Client(fname,mname,lname,login);
				c.setPassword(password);
				Database.addClient(c);
				
				// add new client to DB
				BankDatabase.addUser(c);
				
				// set client's userID field
				c.setUserID(BankDatabase.getLastID("Clients", "userID"));
				
				JPanel panel_New = clientLogin.init(frame);
				
				frame.setContentPane(panel_New);
				frame.revalidate();
				frame.repaint();
			}
		}
		
		createListener createL = new createListener();
		create.addActionListener(createL);
		
		panel.add(create);
		return panel;
	}
	
}
