package LMS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.Cursor;
import javax.swing.border.BevelBorder;

public class AddStudent extends JFrame {
	static AddStudent frame;
	private JPanel contentPane;
	private JTextField studIdField;
	private JTextField nameField;
	private JTextField passwordField;
	private JTextField phNoField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddStudent();
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
	public AddStudent() {
		setBounds(new Rectangle(0, 0, 900, 4000));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddBooks = new JLabel("Add Student");
		lblAddBooks.setIgnoreRepaint(true);
		lblAddBooks.setBounds(0, 64, 885, 46);
		lblAddBooks.setBorder(null);
		lblAddBooks.setBackground(new Color(130, 212, 187));
		lblAddBooks.setOpaque(true);
		lblAddBooks.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblAddBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBooks.setForeground(new Color(153, 153, 153));
		lblAddBooks.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblStudID = new JLabel("Student ID");
		lblStudID.setBounds(235, 158, 80, 31);
		lblStudID.setHorizontalAlignment(SwingConstants.LEFT);
		lblStudID.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(235, 205, 80, 31);
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(235, 260, 80, 28);
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPhNo = new JLabel("Phone No");
		lblPhNo.setBounds(235, 305, 80, 30);
		lblPhNo.setHorizontalAlignment(SwingConstants.LEFT);
		lblPhNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		studIdField = new JTextField();
		studIdField.setBounds(333, 160, 349, 29);
		studIdField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		studIdField.setColumns(10);
		
		JButton btnAddBooks = new JButton("ADD");
		btnAddBooks.setForeground(new Color(255, 255, 255));
		btnAddBooks.setBackground(new Color(130, 172, 159));
		btnAddBooks.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddBooks.setBounds(581, 393, 101, 31);
		btnAddBooks.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String studId=studIdField.getText();
			String name=nameField.getText();
			String password=passwordField.getText();
			String sphone=phNoField.getText();
			if(studId.isBlank() || name.isBlank() || password.isBlank() || sphone.isBlank()){
				JOptionPane.showMessageDialog(AddStudent.this,"Please fill in all the fields!");
				return;
			}
			int phone=Integer.parseInt(sphone);
			int i=StudentForm.save(studId,name,password,phone);
			if(i>0){
				JOptionPane.showMessageDialog(AddStudent.this,"Added Successfully!");
				AddStudent.main(new String[]{});
				frame.dispose();
			}else{
				JOptionPane.showMessageDialog(AddStudent.this,"Please Try Again!");
				AddStudent.main(new String[]{});
				frame.dispose();
			}
			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setBackground(new Color(130, 172, 159));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(235, 393, 101, 31);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDashboard.main(new String[]{});
                frame.dispose();
			}
		});
		
		nameField = new JTextField();
		nameField.setBounds(333, 207, 349, 29);
		nameField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(333, 259, 349, 29);
		passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setColumns(10);
		
		phNoField = new JTextField();
		phNoField.setBounds(333, 306, 349, 29);
		phNoField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		phNoField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		phNoField.setColumns(10);
		contentPane.setLayout(null);
		contentPane.add(btnAddBooks);
		contentPane.add(lblAddBooks);
		contentPane.add(lblStudID);
		contentPane.add(lblName);
		contentPane.add(lblPassword);
		contentPane.add(lblPhNo);
		contentPane.add(phNoField);
		contentPane.add(passwordField);
		contentPane.add(nameField);
		contentPane.add(studIdField);
		contentPane.add(btnBack);
	}

}