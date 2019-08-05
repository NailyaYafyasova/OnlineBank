package bank_atm.gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class BankFrame extends JFrame{

	private JComponent welcomeComponent;
	private JComponent bankComponent;
	
	public static final Dimension PREFERRED_SIZE = new Dimension(750, 500);
	
	public BankFrame() {
		setName("Bank ATM");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		welcomeComponent = new WelcomePanel(this);
		bankComponent = new BankPanel();
		
		this.getContentPane().add(welcomeComponent);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void switchPanel(int selectedIndex) {
		((JTabbedPane) bankComponent).setSelectedIndex(selectedIndex);
		getContentPane().remove(welcomeComponent);
		getContentPane().add(bankComponent);
		getContentPane().repaint();
		getContentPane().revalidate();
		pack();
	}
	
	public static void main(String[] args) {
		new BankFrame().setVisible(true);
	}	
}
