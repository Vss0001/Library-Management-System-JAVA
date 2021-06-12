package LMS;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.border.BevelBorder;

public class AdminLogin extends JFrame {
	static AdminLogin frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminLogin();
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
	public AdminLogin() {
		setSize(900,410);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);


		URL imageUrl = ClassLoader.getSystemResource("lms_bg2.png");
		JLabel background = new JLabel(new ImageIcon(imageUrl));

//		admin login panel
		JPanel adminPanel = new JPanel();
		adminPanel.setOpaque(false);
		adminPanel.setBounds(210,0,450,400);

		adminPanel.setBackground(Color.decode("#82C09A"));
		adminPanel.setPreferredSize(new Dimension(450,400));
			JLabel loginLabel = new JLabel();
			loginLabel.setText("Admin Login");
			loginLabel.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 5));
			loginLabel.setPreferredSize(new Dimension(450,70));
			loginLabel.setVerticalAlignment(JLabel.CENTER);
			loginLabel.setHorizontalAlignment(JLabel.CENTER);
			loginLabel.setFont(new Font("Monospaced", Font.BOLD,22));
			loginLabel.setForeground(Color.decode("#ffffff"));
		adminPanel.add(loginLabel, BorderLayout.NORTH);

		JPanel loginForm = new JPanel();
		loginForm.setOpaque(false);
		loginForm.setPreferredSize(new Dimension(350,120));
			JLabel lblEnterName = new JLabel("Admin ID:   ");
			lblEnterName.setFont(new Font("SansSerif", Font.PLAIN,18));
			lblEnterName.setForeground(Color.decode("#ffffff"));
			JPanel usernameFormGroup = new JPanel(new BorderLayout(5,5));
			usernameFormGroup.setOpaque(false);
			lblEnterName.setPreferredSize(new Dimension(100,20));
			textField = new JTextField("", 10);
			textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			textField.setFont(new Font("SansSerif", Font.PLAIN,16));
			usernameFormGroup.add(lblEnterName,BorderLayout.WEST);
			usernameFormGroup.add(textField, BorderLayout.CENTER);
			usernameFormGroup.setBorder(BorderFactory.createEmptyBorder(30, 5, 10, 5));

			JLabel passwordLabel = new JLabel("Password: ");
			passwordLabel.setFont(new Font("SansSerif", Font.PLAIN,18));
			passwordLabel.setForeground(Color.decode("#ffffff"));
			JPanel passwordFormGroup = new JPanel(new BorderLayout(5,5));
			passwordField = new JPasswordField("",10);
			passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			passwordField.setFont(new Font("SansSerif", Font.PLAIN,16));
			passwordFormGroup.setOpaque(false);
			passwordLabel.setPreferredSize(new Dimension(100,20));
			passwordFormGroup.add(passwordLabel,BorderLayout.WEST);
			passwordFormGroup.add(passwordField, BorderLayout.CENTER);
			passwordFormGroup.setBorder(BorderFactory.createEmptyBorder(20, 5, 10, 5));

		loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
		loginForm.add(usernameFormGroup);
		loginForm.add(passwordFormGroup);
		adminPanel.add(loginForm, BorderLayout.CENTER);

		JPanel buttons = new JPanel();
		buttons.setOpaque(false);
		buttons.setPreferredSize(new Dimension(400,100));
		JButton btnLogin = new JButton("LOGIN");
		JButton btnClose = new JButton("CLOSE");
		btnLogin.setPreferredSize(new Dimension(90,32));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(130, 152, 152));
		btnLogin.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setOpaque(true);
		btnClose.setPreferredSize(new Dimension(90,32));
		btnClose.setForeground(new Color(255, 255, 255));
		btnClose.setBackground(new Color(130, 152, 152));
		btnClose.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnClose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClose.setOpaque(true);

		buttons.add(Box.createRigidArea(new Dimension(40, 0)));
		buttons.add(btnClose);
		buttons.add(Box.createRigidArea(new Dimension(40, 0)));
		buttons.add(btnLogin);
		buttons.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 40));
		adminPanel.add(buttons, BorderLayout.SOUTH);

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			String id=textField.getText();
			String password=String.valueOf(passwordField.getPassword());
			if(AdminLoginDB.validate(id, password)) {
				AdminDashboard.main(new String[] {});
				frame.dispose();
			}else{
				JOptionPane.showMessageDialog(AdminLogin.this, "Please enter valid Username and Password!");
				textField.setText("");
				passwordField.setText("");
			}
			}
		});

		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.main(new String[]{});
                frame.dispose();
			}
		});

		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		layeredPane.setBounds(0,0,900,400);
		layeredPane.add(adminPanel, Integer.valueOf(1));

		add(layeredPane);
		add(background);
	}
}
