import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI {
	
	public static Currency USD = new Currency("USD", "Dollars");
	public static Currency RUB = new Currency("RUB", "Rubles");
	public static Currency CNY = new Currency("CNY", "Yen");
	public static Currency GBP = new Currency("GBP", "Pounds");
	public static Currency[] currencies = {USD,RUB,CNY,GBP};

	private static JFrame frame = new JFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize(frame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static JPanel initialize(JFrame frame) {
		//frame = new JFrame();
		frame.setBounds(100, 100, 480, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		class clientListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				
				JPanel panel_3 = ClientFirstChoices.init(frame);
				frame.setContentPane(panel_3);
				frame.revalidate();
				frame.repaint();
				
			}
		}
		
		
		class managerListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				
				JPanel panel_3 = managerLogin.init(frame);
				frame.setContentPane(panel_3);
				frame.revalidate();
				frame.repaint();
				
			}
		}
		
		
		clientListener clientL = new clientListener();
		managerListener managerL = new managerListener();
		
		JLabel welcome = new JLabel("Welcome to Fancy Bank!");
		welcome.setBounds(123, 98, 187, 20);
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		panel.add(welcome);
		
		
		JLabel choose = new JLabel("Please choose how you would like to login.");
		choose.setHorizontalAlignment(SwingConstants.CENTER);
		choose.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		choose.setBounds(54, 125, 335, 20);
		
		panel.add(choose);
		
		
		JButton client = new JButton("Client");
		client.setBounds(93, 185, 81, 29);
		JButton manager = new JButton("Manager");
		manager.setBounds(235, 185, 97, 29);
		
		client.addActionListener(clientL);
		manager.addActionListener(managerL);
		
		panel.add(client);
		panel.add(manager);
		
		
		frame.getContentPane().add(panel);
		
		return panel;
	}
}
