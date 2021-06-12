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
import java.sql.*;
import javax.swing.border.BevelBorder;

public class StudentBookLoan extends JFrame {
    static StudentBookLoan frame;
    private JPanel contentPane;
    private JTextField callNoField;
    private JTextField nameField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new StudentBookLoan();
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
    public StudentBookLoan() {
        setBounds(new Rectangle(0, 0, 900, 4000));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 598);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblAddBooks = new JLabel("Student Self Book Loan Checkout");
        lblAddBooks.setIgnoreRepaint(true);
        lblAddBooks.setBounds(0, 64, 885, 46);
        lblAddBooks.setBorder(null);
        lblAddBooks.setBackground(new Color(130, 212, 187));
        lblAddBooks.setOpaque(true);
        lblAddBooks.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        lblAddBooks.setHorizontalAlignment(SwingConstants.CENTER);
        lblAddBooks.setForeground(new Color(153, 153, 153));
        lblAddBooks.setFont(new Font("Tahoma", Font.BOLD, 20));

        JLabel lblCallNo = new JLabel("Call No");
        lblCallNo.setBounds(235, 225, 80, 31);
        lblCallNo.setHorizontalAlignment(SwingConstants.LEFT);
        lblCallNo.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JLabel lblName = new JLabel("Student ID");
        lblName.setBounds(235, 178, 80, 31);
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JButton btnAddBooks = new JButton("LOAN");
        btnAddBooks.setForeground(new Color(255, 255, 255));
        btnAddBooks.setBackground(new Color(130, 172, 159));
        btnAddBooks.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnAddBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnAddBooks.setBounds(581, 393, 101, 31);
        btnAddBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String callno=callNoField.getText();
                String studentId=nameField.getText();
                boolean match=false;
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/lms","root","");
                    PreparedStatement stmt=conn.prepareStatement("SELECT CallNumber FROM books");
                    ResultSet rs=stmt.executeQuery();
                    PreparedStatement stmt1=conn.prepareStatement("SELECT * FROM loan WHERE CallNumber=?");
                    stmt1.setString(1,callno);
                    ResultSet result=stmt1.executeQuery();

                    // exit if book already loan or found in load table
                    if (result.next()) {
                        System.out.println("found");
                        result.getString("CallNumber");
                        JOptionPane.showMessageDialog(StudentBookLoan.this,"Invalid Call Number! Please try again. Contact librarian if problem persists.");
                        nameField.setText("");
                        callNoField.setText("");
                        return;
                    }

                    // if book call number is found in book table, then approval
                    while (rs.next()) {
                        if(callno.equals(rs.getString("CallNumber")) ){
                            match = true;
                        }
                    }

                    if(match==true){
                        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                        PreparedStatement ps=conn.prepareStatement("INSERT INTO loan(CallNumber, Borrower, DateOut) values(?,?,?) ");
                        ps.setString(1,callno);
                        ps.setString(2,studentId);
                        ps.setTimestamp(3, date);
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(StudentBookLoan.this,"Book loan successfully!");
                        nameField.setText("");
                        callNoField.setText("");
                        conn.close();
                    } else {
                        JOptionPane.showMessageDialog(StudentBookLoan.this,"Invalid Call Number! Please try again. Contact librarian if problem persists.");
                        nameField.setText("");
                        callNoField.setText("");
                    }
                }catch(Exception ep){
                    System.out.println(ep);
                    JOptionPane.showMessageDialog(StudentBookLoan.this,"Invalid Call Number! Please try again. Contact librarian if problem persists.");
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
                StudentDashboard.main(new String[]{});
                frame.dispose();
            }
        });

        nameField = new JTextField();
        nameField.setBounds(333, 180, 349, 29);
        nameField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 13));
        nameField.setColumns(10);

        callNoField = new JTextField();
        callNoField.setBounds(333, 227, 349, 29);
        callNoField.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        callNoField.setColumns(10);

        contentPane.setLayout(null);
        contentPane.add(btnAddBooks);
        contentPane.add(lblAddBooks);
        contentPane.add(lblName);
        contentPane.add(lblCallNo);
        contentPane.add(callNoField);
        contentPane.add(nameField);
        contentPane.add(btnBack);
    }

}