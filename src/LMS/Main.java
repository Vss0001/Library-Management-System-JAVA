package LMS;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.net.URL;

public class Main extends JFrame {
	static Main frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame= new Main();
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
	public Main() {
		setSize(900,410);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		URL imageUrl = ClassLoader.getSystemResource("lms_bg.png");
		JLabel background = new JLabel(new ImageIcon(imageUrl));

//		Select admin or student login
		JPanel selectionPanel = new JPanel();
		selectionPanel.setOpaque(false);
		selectionPanel.setBounds(50,50,450,280);

//		Library Management System Label
		JPanel lmsLabelPanel = new JPanel();
		lmsLabelPanel.setOpaque(false);
		lmsLabelPanel.setBounds(450,50,450,280);

		JLabel lblLibrary = new JLabel("Library");
		lblLibrary.setFont(new Font("Serif", Font.BOLD, 50));
		lblLibrary.setForeground(Color.decode("#000"));
		JLabel lblManagement = new JLabel("Management");
		lblManagement.setFont(new Font("Serif", Font.BOLD, 50));
		lblManagement.setForeground(Color.decode("#000"));
		JLabel lblSystem = new JLabel("System");
		lblSystem.setFont(new Font("Serif", Font.BOLD, 50));
		lblSystem.setForeground(Color.decode("#000"));

//		Buttons
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.setBackground(new Color(130, 152, 152));
		btnAdminLogin.setForeground(new Color(255, 255, 255));
		btnAdminLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AdminLogin.main(new String[]{});
			frame.dispose();
			}
		});
		btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton btnStudentLogin = new JButton("Student Login");
		btnStudentLogin.setBackground(new Color(130, 152, 152));
		btnStudentLogin.setForeground(new Color(255, 255, 255));
		btnStudentLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnStudentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				StudentLogin.main(new String[]{});
				frame.dispose();
			}
		});
		btnStudentLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStudentLogin.setPreferredSize(new Dimension(120,50));

		JPanel buttons = new JPanel(new GridLayout(0, 1, 5, 50));
		buttons.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));
		buttons.setOpaque(false);
		buttons.add(btnAdminLogin);
		buttons.add(btnStudentLogin);
		selectionPanel.add(buttons);

		JPanel labelPanel = new JPanel(new GridLayout(0, 1, 5, 0));
		labelPanel.setBorder(BorderFactory.createEmptyBorder(30, 5, 5, 5));
		labelPanel.setOpaque(false);
		labelPanel.add(lblLibrary);
		labelPanel.add(lblManagement);
		labelPanel.add(lblSystem);
		lmsLabelPanel.add(labelPanel);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,900,400);
		layeredPane.add(selectionPanel, Integer.valueOf(1));
		layeredPane.add(lmsLabelPanel, Integer.valueOf(1));

		add(layeredPane);
		add(background);

	}
}
