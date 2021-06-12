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

public class AddBookForm extends JFrame {
	static AddBookForm frame;
	private JPanel contentPane;
	private JTextField callNoField;
	private JTextField titleField;
	private JTextField authorField;
	private JTextField genreField;
	private JTextField isbnField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AddBookForm();
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
	public AddBookForm() {
		setBounds(new Rectangle(0, 0, 900, 4000));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblAddBooks = new JLabel("Add Books");
		lblAddBooks.setIgnoreRepaint(true);
		lblAddBooks.setBounds(0, 64, 885, 46);
		lblAddBooks.setBorder(null);
		lblAddBooks.setBackground(new Color(130, 212, 187));
		lblAddBooks.setOpaque(true);
		lblAddBooks.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		lblAddBooks.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBooks.setForeground(new Color(153, 153, 153));
		lblAddBooks.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lbltitle = new JLabel("Title");
		lbltitle.setBounds(235, 160, 80, 31);
		lbltitle.setHorizontalAlignment(SwingConstants.LEFT);
		lbltitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblisbn = new JLabel("ISBN");
		lblisbn.setBounds(235, 207, 80, 31);
		lblisbn.setHorizontalAlignment(SwingConstants.LEFT);
		lblisbn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblauthor = new JLabel("Author");
		lblauthor.setBounds(235, 259, 80, 28);
		lblauthor.setHorizontalAlignment(SwingConstants.LEFT);
		lblauthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblgenres = new JLabel("Genres");
		lblgenres.setBounds(235, 306, 80, 30);
		lblgenres.setHorizontalAlignment(SwingConstants.LEFT);
		lblgenres.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblcallno = new JLabel("Call Number");
		lblcallno.setBounds(235, 360, 80, 30);
		lblcallno.setHorizontalAlignment(SwingConstants.LEFT);
		lblcallno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton btnAddBooks = new JButton("ADD");
		btnAddBooks.setForeground(new Color(255, 255, 255));
		btnAddBooks.setBackground(new Color(130, 172, 159));
		btnAddBooks.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddBooks.setBounds(581, 420, 101, 31);
		btnAddBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String callno=callNoField.getText();
				String title=titleField.getText();
				String genre=genreField.getText();
				String author=authorField.getText();
				String squantity=isbnField.getText();
				int isbn=Integer.parseInt(squantity);
				if(callno.isBlank() || title.isBlank() || genre.isBlank() || author.isBlank() || squantity.isBlank()){
					JOptionPane.showMessageDialog(AddBookForm.this,"Please fill in all the fields!");
					return;
				}
				int i=BookForm.save(title,isbn,author,genre,callno);
				if(i>0){
					JOptionPane.showMessageDialog(AddBookForm.this,"Added Successfully!");
					AddBookForm.main(new String[]{});
					frame.dispose();
				}else{
					JOptionPane.showMessageDialog(AddBookForm.this,"Please Try Again!");
					AddBookForm.main(new String[]{});
					frame.dispose();
				}
			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBack.setBackground(new Color(130, 172, 159));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setBounds(235, 420, 101, 31);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminDashboard.main(new String[]{});
                frame.dispose();
			}
		});

		titleField = new JTextField();
		titleField.setBounds(333, 160, 349, 29);
		titleField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		titleField.setColumns(10);

		isbnField = new JTextField();
		isbnField.setBounds(333, 207, 349, 29);
		isbnField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		isbnField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		isbnField.setColumns(10);
		
		authorField = new JTextField();
		authorField.setBounds(333, 259, 349, 29);
		authorField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		authorField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		authorField.setColumns(10);
		
		genreField = new JTextField();
		genreField.setBounds(333, 306, 349, 29);
		genreField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		genreField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		genreField.setColumns(10);

		callNoField = new JTextField();
		callNoField.setBounds(333, 360, 349, 29);
		callNoField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		callNoField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		callNoField.setColumns(10);

		contentPane.setLayout(null);
		contentPane.add(btnAddBooks);
		contentPane.add(lblAddBooks);
		contentPane.add(lbltitle);
		contentPane.add(lblcallno);
		contentPane.add(lblauthor);
		contentPane.add(lblisbn);
		contentPane.add(lblgenres);
		contentPane.add(titleField);
		contentPane.add(authorField);
		contentPane.add(genreField);
		contentPane.add(isbnField);
		contentPane.add(callNoField);
		contentPane.add(btnBack);
	}

}