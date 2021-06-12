package LMS;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

import javax.swing.SwingConstants;

public class StudentDashboard extends JFrame {
    static StudentDashboard frame;
    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new StudentDashboard();
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
    public StudentDashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 400);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBackground(new Color(130, 192, 154));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblStudentSection = new JLabel("Student Dashboard");
        lblStudentSection.setBounds(355, 49, 200, 40);
        lblStudentSection.setHorizontalAlignment(SwingConstants.CENTER);
        lblStudentSection.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblStudentSection.setForeground(new Color(255, 255, 255));

        JButton opacButton = new JButton("OPAC");
        opacButton.setBounds(204, 140, 181, 49);
        opacButton.setForeground(new Color(255, 255, 255));
        opacButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        opacButton.setBackground(new Color(130, 152, 152));
        opacButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        opacButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OPAC.main(new String[]{});
                frame.dispose();
            }
        });

        JButton borrowBookButton = new JButton("Borrow Book");
        borrowBookButton.setBounds(491, 140, 181, 49);
        borrowBookButton.setForeground(new Color(255, 255, 255));
        borrowBookButton.setBackground(new Color(130, 152, 152));
        borrowBookButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        borrowBookButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        borrowBookButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                StudentBookLoan.main(new String[]{});
                frame.dispose();
            }
        });

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(388, 257, 105, 42);
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StudentLogin.main(new String[]{});
                frame.dispose();
            }
        });
        btnLogout.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        btnLogout.setBackground(new Color(119, 136, 153));
        btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 16));
        contentPane.setLayout(null);
        contentPane.add(lblStudentSection);
        contentPane.add(opacButton);
        contentPane.add(borrowBookButton);
        contentPane.add(btnLogout);
    }
}