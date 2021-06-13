package LMS;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;

public class StudentLogin extends JFrame {
    static StudentLogin frame;
    private JTextField studentIdTextField;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new StudentLogin();
//                    frame.setUndecorated(true);
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
    public StudentLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,400);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel welcomePanel = new JPanel();
        JPanel loginPanel = new JPanel();

//      welcome panel
        URL imageUrl = ClassLoader.getSystemResource("bookshelf.png");
        ImageIcon image = new ImageIcon(imageUrl);
        welcomePanel.setBackground(Color.decode("#82D4BB"));
        welcomePanel.setPreferredSize(new Dimension(450,400));
        JLabel libraryOPAClabel = new JLabel();
        libraryOPAClabel.setText("Library OPAC");
        libraryOPAClabel.setIcon(image);
        libraryOPAClabel.setHorizontalTextPosition(JLabel.CENTER); // set text LEFT, CENTER, RIGHT of imageicon
        libraryOPAClabel.setVerticalTextPosition(JLabel.TOP); // set text TOP, CENTER, BOTTOM of imageicon
        libraryOPAClabel.setFont(new Font("Monospaced", Font.BOLD,20));
        libraryOPAClabel.setForeground(Color.decode("#829298"));
        libraryOPAClabel.setIconTextGap(5); // set gap of text to image
        libraryOPAClabel.setVerticalAlignment(JLabel.CENTER);
        libraryOPAClabel.setHorizontalAlignment(JLabel.CENTER);
        libraryOPAClabel.setBorder(BorderFactory.createEmptyBorder(20,5,5,0));
        welcomePanel.add(libraryOPAClabel);

//      login panel
        loginPanel.setBackground(Color.decode("#82C09A"));
        loginPanel.setPreferredSize(new Dimension(450,400));
            JLabel loginLabel = new JLabel();
            loginLabel.setText("Student Login");
            loginLabel.setPreferredSize(new Dimension(450,70));
            loginLabel.setVerticalAlignment(JLabel.CENTER);
            loginLabel.setHorizontalAlignment(JLabel.CENTER);
            loginLabel.setFont(new Font("Monospaced", Font.BOLD,22));
            loginLabel.setForeground(Color.decode("#ffffff"));
        loginPanel.add(loginLabel, BorderLayout.NORTH);

            JPanel loginForm = new JPanel();
            loginForm.setOpaque(false);
            loginForm.setPreferredSize(new Dimension(350,120));
            JLabel usernameLabel = new JLabel("Student ID: ");
            usernameLabel.setFont(new Font("SansSerif", Font.PLAIN,18));
            usernameLabel.setForeground(Color.decode("#ffffff"));
            JPanel usernameFormGroup = new JPanel(new BorderLayout(5,5));
            usernameFormGroup.setOpaque(false);
            studentIdTextField = new JTextField("", 10);
            studentIdTextField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            studentIdTextField.setFont(new Font("SansSerif", Font.PLAIN,16));
            usernameFormGroup.add(usernameLabel,BorderLayout.WEST);
            usernameFormGroup.add(studentIdTextField, BorderLayout.CENTER);
            usernameFormGroup.setBorder(BorderFactory.createEmptyBorder(30, 5, 10, 5));

            JLabel passwordLabel = new JLabel("Password:  ");
            passwordLabel.setFont(new Font("SansSerif", Font.PLAIN,18));
            passwordLabel.setForeground(Color.decode("#ffffff"));
            JPanel passwordFormGroup = new JPanel(new BorderLayout(5,5));
            passwordField = new JPasswordField("",10);
            passwordField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            passwordField.setFont(new Font("SansSerif", Font.PLAIN,16));
            passwordFormGroup.setOpaque(false);
            passwordFormGroup.add(passwordLabel,BorderLayout.WEST);
            passwordFormGroup.add(passwordField, BorderLayout.CENTER);
            passwordFormGroup.setBorder(BorderFactory.createEmptyBorder(20, 5, 10, 5));

            loginForm.setLayout(new BoxLayout(loginForm, BoxLayout.Y_AXIS));
            loginForm.add(usernameFormGroup);
            loginForm.add(passwordFormGroup);
        loginPanel.add(loginForm, BorderLayout.CENTER);

            JPanel buttons = new JPanel();
            buttons.setOpaque(false);
            buttons.setPreferredSize(new Dimension(300,100));
            JButton loginButton = new JButton("Login");
            JButton closeButton = new JButton("Close");
            loginButton.setPreferredSize(new Dimension(90,32));
            loginButton.setBorder(new RoundedBorder(10));
            loginButton.setContentAreaFilled(false);
            loginButton.setFocusPainted(false);
            loginButton.setBackground(Color.decode("#829298"));
            loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            loginButton.setOpaque(true);
            closeButton.setPreferredSize(new Dimension(90,32));
            closeButton.setBorder(new RoundedBorder(10));
            closeButton.setContentAreaFilled(false);
            closeButton.setFocusPainted(false);
            closeButton.setBackground(Color.decode("#829298"));
            closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            closeButton.setOpaque(true);

            buttons.add(Box.createRigidArea(new Dimension(40, 0)));
            buttons.add(loginButton);
            buttons.add(Box.createRigidArea(new Dimension(40, 0)));
            buttons.add(closeButton);
            buttons.setBorder(BorderFactory.createEmptyBorder(50, 5, 5, 5));
        loginPanel.add(buttons, BorderLayout.SOUTH);

        add(welcomePanel,BorderLayout.WEST);
        add(loginPanel,BorderLayout.EAST);

//      Enter to submit & Button Events
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                if (arg0.getKeyCode()==10) {
                    loginButton.doClick();
                }
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(AdminLoginDB.checkConnection()) {
                    System.out.println("Connected to Database Successfully");
                } else  {
                    JOptionPane.showMessageDialog(StudentLogin.this, "Not Connected to Database Server!");
                    return;
                }
                String studentId=studentIdTextField.getText();
                String password=String.valueOf(passwordField.getPassword());
//                System.out.println(studentId+" "+password);
                if(StudentDbh.validate(studentId, password)){
                    StudentDashboard.main(new String[]{});
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(StudentLogin.this, "Sorry, Username or Password Error","Login Error!", JOptionPane.ERROR_MESSAGE);
                    studentIdTextField.setText("");
                    passwordField.setText("");
                }
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.main(new String[]{});
                frame.dispose();
            }
        });

    }

    private class RoundedBorder implements Border {
            private int radius;

            RoundedBorder(int radius) {
                this.radius = radius;
            }

            public Insets getBorderInsets(Component c) {
                return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
            }

            public boolean isBorderOpaque() {
                return true;
            }

            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.drawRoundRect(x, y, width-1, height-1, radius, radius);
            }
    }
}


