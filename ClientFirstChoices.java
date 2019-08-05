import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class ClientFirstChoices extends JPanel {
	public ClientFirstChoices() {
	}
	
	public static JPanel init(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Please login into your account or sign up.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panelNew = new JPanel();
		panel.add(panelNew, BorderLayout.SOUTH);
		
		
		class loginListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("LOGIN");
				JPanel panel_New = clientLogin.init(frame);
				panel.removeAll();
				panel.add(panel_New);
				panel.revalidate();
				panel.repaint();
			}
		}
		
		class signUpListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("SIGN UP");
				JPanel panelNew = clientCreate.init(frame);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		}
		
		class backListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("BACK");
				JPanel panelNew = GUI.initialize(frame);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		}
		
		
		JButton login = new JButton("Login");
		JButton signUp = new JButton("Sign Up");
		JButton back = new JButton("Back");
		
		loginListener loglis = new loginListener();
		login.addActionListener(loglis);
		
		signUpListener sulis = new signUpListener();
		signUp.addActionListener(sulis);
		
		backListener backlis = new backListener();
		back.addActionListener(backlis);
		
		panelNew.add(login);
		panelNew.add(signUp);
		panelNew.add(back);
		
		return panel;
	}

}
