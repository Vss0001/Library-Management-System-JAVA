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
import javax.swing.border.BevelBorder;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReturnBookForm extends JFrame {
	static ReturnBookForm frame;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ReturnBookForm();
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
	public ReturnBookForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblReturnBook = new JLabel("Return Book");
		lblReturnBook.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblReturnBook.setBorder(null);
		lblReturnBook.setBackground(new Color(130, 212, 187));
		lblReturnBook.setOpaque(true);
		lblReturnBook.setHorizontalAlignment(SwingConstants.CENTER);
		lblReturnBook.setBounds(0, 86, 984, 48);
		lblReturnBook.setForeground(Color.GRAY);
		lblReturnBook.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblBookCallno = new JLabel("Call No");
		lblBookCallno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblBookCallno.setBounds(259, 201, 80, 31);
		
		JLabel lblStudentId = new JLabel("Student ID");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudentId.setBounds(259, 259, 80, 31);
		
		textField = new JTextField();
		textField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField.setBounds(357, 204, 349, 29);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		textField_1.setBounds(357, 262, 349, 29);
		textField_1.setColumns(10);
		
		JButton btnReturnBook = new JButton("RETURN");
		btnReturnBook.setForeground(Color.WHITE);
		btnReturnBook.setBackground(new Color(130, 172, 159));
		btnReturnBook.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReturnBook.setBounds(605, 395, 101, 31);
		btnReturnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String callno=textField.getText();
				String borrower=textField_1.getText();
				boolean match=false;
				try{
//					String databaseURL = "jdbc:ucanaccess://src/LMS/LMSDatabase.accdb";
//					Connection conn = DriverManager.getConnection(databaseURL);

					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/lms","root","");
					PreparedStatement stmt=conn.prepareStatement("SELECT * FROM loan");
					ResultSet rs=stmt.executeQuery();
					while (rs.next()) {
						if(callno.equals(rs.getString("CallNumber"))  && borrower.equals(rs.getString("Borrower")) ){
							match = true;
						}
					}

					if(match==true){
						PreparedStatement ps=conn.prepareStatement("delete from loan where CallNumber=? and Borrower=?");
						ps.setString(1,callno);
						ps.setString(2,borrower);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(ReturnBookForm.this,"Book returned successfully!");
						IssueBookForm.main(new String[]{});
						frame.dispose();
						conn.close();
					} else {
						JOptionPane.showMessageDialog(ReturnBookForm.this,"Sorry, unable to return book!");
						textField.setText("");
						textField_1.setText("");
					}
				}catch(Exception ep){
					System.out.println(ep);
					JOptionPane.showMessageDialog(ReturnBookForm.this,"Sorry, unable to return book!");
				}
			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setBackground(new Color(130, 172, 159));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(259, 395, 101, 31);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDashboard.main(new String[]{});
                frame.dispose();
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(lblStudentId);
		contentPane.add(lblBookCallno);
		contentPane.add(textField_1);
		contentPane.add(textField);
		contentPane.add(btnReturnBook);
		contentPane.add(lblReturnBook);
		contentPane.add(btnBack);
	}

}
