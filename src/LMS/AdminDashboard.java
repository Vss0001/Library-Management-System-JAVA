package LMS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;


public class AdminDashboard extends JFrame {
	static AdminDashboard frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AdminDashboard();
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
	public AdminDashboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(130, 192, 154));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAdminSection = new JLabel("Admin Section");
		lblAdminSection.setBounds(332, 46, 217, 40);
		lblAdminSection.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminSection.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAdminSection.setForeground(new Color(255, 255, 255));
		
		JButton btnAdd = new JButton("Add Book");
		btnAdd.setBounds(355, 234, 171, 49);
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAdd.setBackground(new Color(130, 152, 152));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AddBookForm.main(new String[]{});
			frame.dispose();
			}
		});
		
		JButton btnStudent = new JButton("Add Student");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent.main(new String[]{});
                frame.dispose();	
			}
		});
		btnStudent.setForeground(Color.WHITE);
		btnStudent.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStudent.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnStudent.setBackground(new Color(130, 152, 152));
		btnStudent.setBounds(355, 97, 171, 49);
		contentPane.add(btnStudent);
		
		JButton btnViewStudentList = new JButton("View Student List");
		btnViewStudentList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListForm.main(new String[]{});
				frame.dispose();
			}
		});
		btnViewStudentList.setForeground(Color.WHITE);
		btnViewStudentList.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewStudentList.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnViewStudentList.setBackground(new Color(130, 152, 152));
		btnViewStudentList.setBounds(355, 166, 171, 49);
		contentPane.add(btnViewStudentList);
		
		JButton btnRecord = new JButton("View Book Record");
		btnRecord.setBounds(355, 303, 171, 49);
		btnRecord.setForeground(new Color(255, 255, 255));
		btnRecord.setBackground(new Color(130, 152, 152));
		btnRecord.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			BookRecordForm.main(new String[]{});
			frame.dispose();
			}
		});
		btnRecord.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnIssue = new JButton("View Issue Book");
		btnIssue.setBounds(355, 371, 171, 49);
		btnIssue.setForeground(new Color(255, 255, 255));
		btnIssue.setBackground(new Color(130, 152, 152));
		btnIssue.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnIssue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			IssueBookForm.main(new String[]{});
			frame.dispose();
			}
		});
		btnIssue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnReturn = new JButton("Return Book");
		btnReturn.setBounds(355, 440, 171, 49);
		btnReturn.setForeground(new Color(255, 255, 255));
		btnReturn.setBackground(new Color(130, 152, 152));
		btnReturn.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReturnBookForm.main(new String[]{});
				frame.dispose();
			}
		});
		btnReturn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(671, 487, 105, 42);
		btnLogout.setForeground(new Color(255, 255, 255));
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin.main(new String[]{});
                frame.dispose();	
			}
		});
		btnLogout.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogout.setBackground(new Color(119, 136, 153));
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.setLayout(null);
		contentPane.add(lblAdminSection);
		contentPane.add(btnAdd);
		contentPane.add(btnIssue);
		contentPane.add(btnRecord);
		contentPane.add(btnReturn);
		contentPane.add(btnLogout);
	}
}
