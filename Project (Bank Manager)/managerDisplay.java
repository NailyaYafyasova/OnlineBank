package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class managerDisplay extends JPanel {

	private static JPanel panel = new JPanel();
	
	
	public static JPanel init(JFrame frame, Manager manager) {
		panel.setLayout(null);
		
		JLabel lblPleaseChooseWhat = new JLabel("Please choose what you would like to do:");
		lblPleaseChooseWhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseChooseWhat.setBounds(74, 103, 296, 20);
		panel.add(lblPleaseChooseWhat);
		
		JButton checkClient = new JButton("Check Client");
		checkClient.setBounds(82, 160, 117, 29);
		
		
		JButton getReport = new JButton("Get Daily Report");
		getReport.setBounds(213, 160, 151, 29);
		
		JButton back = new JButton("Back");
		back.setBounds(150, 242, 117, 29);
		
		
		
		class reportListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("Getting Daily Report");
				
				JLabel phrase = new JLabel("DAILY REPORT FOR " + Date.getCurrentDate().toString());
				JLabel dailyReport = new JLabel( "<html>"+ manager.getDailyReport(Date.getCurrentDate()) + "</html>");
				JPanel reportPanel = new JPanel();
				
				reportPanel.add(phrase);
				reportPanel.add(dailyReport);
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
				
				reportPanel.add(back);
				frame.setContentPane(reportPanel);
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
		
		
		class checkListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("CHECK");
				JPanel panelNew = managerCheckClient.init(frame, manager);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		}
		
		
		reportListener reportL = new reportListener();
		getReport.addActionListener(reportL);
		
		backListener backL = new backListener();
		back.addActionListener(backL);
		
		checkListener checkL = new checkListener();
		checkClient.addActionListener(checkL);
		
		
		panel.add(getReport);
		panel.add(checkClient);
		panel.add(back);
		
		return panel;
	}

}
