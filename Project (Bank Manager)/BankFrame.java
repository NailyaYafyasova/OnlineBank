package views;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JSlider;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.SwingConstants;

public class BankFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton;
	private JTextField txtID;
	private JPasswordField txtPassword;
	private JCheckBox rdbtnProfo;
	private JCheckBox rdbtnGeneral;
	private JCheckBox rdbtnSaving;
	private JCheckBox rdbtnLoan;
	private JButton btnLogin;
	private JButton btn$20;
	private JButton btn$40;
	private JButton btn$80;
	private JButton btn$100;
	private JButton btn$200;
	private JButton btn$400;
	private JPanel panel;
	private JPanel panel_1;
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankFrame frame = new BankFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public BankFrame() {
		
		
		initComponents();
		createEvents();
		
	}
	
	
	
	private void initComponents() {
		
//		ImageHeader header = new ImageHeader();
//		add(header);
		
		setTitle("ATM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmCreateAccount = new JMenuItem("Create Account");
		menuBar.add(mntmCreateAccount);
		
		JCheckBoxMenuItem chckbxmntmMangerViews = new JCheckBoxMenuItem("Manger Views");
		menuBar.add(chckbxmntmMangerViews);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		getContentPane().setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBounds(6, 22, 426, 152);
		contentPane.add(panel_1);
		
		JLabel lblFirstName = new JLabel("User ID");
		
		JLabel lblAccountType = new JLabel("Account type:");
		
		txtID = new JTextField();
		txtID.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		txtPassword = new JPasswordField();
		
		rdbtnProfo = new JCheckBox("portfolio");
		
		rdbtnSaving = new JCheckBox("Saving");
		
		rdbtnGeneral = new JCheckBox("Checking");
		rdbtnGeneral.setSelected(true);
		
		rdbtnLoan = new JCheckBox("Loan");
		
		btnLogin = new JButton("Login");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(lblFirstName)
					.addGap(146)
					.addComponent(lblAccountType))
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(txtID, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword)
						.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
					.addGap(53)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(rdbtnGeneral)
						.addComponent(rdbtnProfo)
						.addComponent(rdbtnSaving)
						.addComponent(rdbtnLoan))
					.addGap(20)
					.addComponent(btnLogin))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFirstName)
						.addComponent(lblAccountType))
					.addGap(11)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(1)
							.addComponent(txtID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(lblPassword)
							.addGap(12)
							.addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(22)
									.addComponent(rdbtnGeneral))
								.addComponent(rdbtnProfo))
							.addGap(2)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnSaving)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(21)
									.addComponent(rdbtnLoan))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(34)
							.addComponent(btnLogin))))
		);
		panel_1.setLayout(gl_panel_1);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Quick Options", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 200, 438, 152);
		contentPane.add(panel);
		
		btn$100 = new JButton("$100");
		btn$100.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btn$20 = new JButton("$20");
		
		btn$200 = new JButton("$200");
		
		btn$40 = new JButton("$40");
		btn$40.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btn$400 = new JButton("$400");
		
		btn$80 = new JButton("$80");
		btn$80.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblQuickWithdraw = new JLabel("Quick Withdraw:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btn$100)
						.addComponent(btn$20))
					.addGap(78)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuickWithdraw, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btn$40)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btn$80))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btn$200)
								.addGap(63)
								.addComponent(btn$400))))
					.addContainerGap(63, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(26, Short.MAX_VALUE)
					.addComponent(lblQuickWithdraw, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn$20)
						.addComponent(btn$40)
						.addComponent(btn$80))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn$100)
						.addComponent(btn$200)
						.addComponent(btn$400))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		
	}
	
	
	private void createEvents() {
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					
//					String query = "select* from EmployeeInfo where username =? and password=?";
//					PreparedStatement pst = connection.prepareStatement(query);
//					pst.setString(1, txtID.getText());
//					pst.setString(2, txtID.getText());
//					
//
//					int ret = BankDatabase.checkClient(username, password);
//					
//					ResultSet rs = pst.executeQuery();
//					int count = 0;
//					while(rs.next()) {
//						
//						if(count == 1) {
//							
//							JOptionPane.showMessageDialog(null, "Connection Successful!");
//							
//						}
//						else if (count == 2) {
//							
//							JOptionPane.showMessageDialog(null, "Duplicated UserName");
//							
//						}
//						else {
//							
//							JOptionPane.showMessageDialog(null, "Wrong Info Please try again!!");
//							
//						}
//						
//						rs.close();
//						pst.close();
//						
//						
//					}
						
						
						
						
						
						
						
				}catch(Exception e1) {
					
					
					JOptionPane.showMessageDialog(null, e1);
					
				}
				
				
				
				
				
				
				if (rdbtnProfo.isSelected()) {
					//show portfolio info
					JOptionPane.showMessageDialog(null, "portfolio");
					
					
				}
				
				if (rdbtnGeneral.isSelected()) {
					// show Checking info
					
					int count = 0;
					
//					CheckingView cv = new CheckingView();
					
					if (count == 0) {
//						cv.setModal(true);
//						cv.setVisible(true);
						
					}
					else {
						
						
					}
					
					
					
				}
				
				if (rdbtnSaving.isSelected()) {
					// show Saving info
//					JOptionPane.showMessageDialog(null, "Saving");
					
//					SavingView sv = new SavingView();
					//if Client has a Checking account:
//					sv.setVisible(true);
					//else:
				}
				
				if (rdbtnLoan.isSelected()) {
					// show Loan info
					JOptionPane.showMessageDialog(null, "Loan");	
				}
				
				else{
					
//					MangerView mv = new MangerView();
//					mv.setVisible(true);
					
				}
				
				txtID.setText("");
				txtPassword.setText("");
				
			}
		});
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				panel.setToolTipText("Quick Withdraw from your checking account");
			}
		});
		
		
		
		
	}
}



