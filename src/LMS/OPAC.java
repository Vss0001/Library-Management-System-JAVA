package LMS;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class OPAC extends JFrame {
    static OPAC frame;
    private JTextField titleTextField;
    private JTextField ISBNTextField;
    private JTextField authorTextField;
    private JTable table;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new OPAC();
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
    public OPAC() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100,700);
        setLocationRelativeTo(null);

        // Return Button
        JButton returnButton = new JButton("Back");
        returnButton.setBounds(50,20,80,30);
        returnButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
        returnButton.setBackground(Color.decode("#82AC9F"));
        returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        returnButton.setOpaque(true);
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,200,100);
        layeredPane.add(returnButton, Integer.valueOf(0));
        add(layeredPane);

        JPanel opacPanel = new JPanel(new BorderLayout(10, 10));

        JLabel opacTitleLabel = new JLabel("Library OPAC");
            opacTitleLabel.setFont(new Font("SansSerif", Font.BOLD,28));
            opacTitleLabel.setForeground(Color.decode("#94778B"));
            opacTitleLabel.setBackground(Color.decode("#82D4BB"));
            opacTitleLabel.setOpaque(true);
            opacTitleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            opacTitleLabel.setPreferredSize(new Dimension(1500,100));
            opacTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel searchPanel = new JPanel();
            searchPanel.setBackground(Color.white);
            searchPanel.setLayout(new BorderLayout());
                JPanel searchForm = new JPanel();
                searchForm.setBackground(Color.white);
                searchForm.setPreferredSize(new Dimension(110,200));

                JLabel titleLabel = new JLabel("Title:      ");
                titleLabel.setFont(new Font("SansSerif", Font.PLAIN,14));
                JPanel titleFormGroup = new JPanel(new BorderLayout(5,5));
                titleFormGroup.setOpaque(false);
                titleTextField = new JTextField();
                titleTextField.setMargin(new Insets(5, 5, 5, 5));
                titleTextField.setFont(new Font("SansSerif", Font.PLAIN,14));
                titleFormGroup.add(titleLabel,BorderLayout.WEST);
                titleFormGroup.add(titleTextField, BorderLayout.CENTER);
                titleFormGroup.setBorder(BorderFactory.createEmptyBorder(5, 300, 10, 300));

                JLabel ISBNLabel = new JLabel("ISBN:     ");
                ISBNLabel.setFont(new Font("SansSerif", Font.PLAIN,14));
                JPanel ISBNFormGroup = new JPanel(new BorderLayout(5,5));
                ISBNTextField = new JTextField();
                ISBNTextField.setMargin(new Insets(5, 5, 5, 5));
                ISBNTextField.setFont(new Font("SansSerif", Font.PLAIN,14));
                ISBNFormGroup.setOpaque(false);
                ISBNFormGroup.add(ISBNLabel,BorderLayout.WEST);
                ISBNFormGroup.add(ISBNTextField, BorderLayout.CENTER);
                ISBNFormGroup.setBorder(BorderFactory.createEmptyBorder(5, 300, 10, 300));

                JLabel authorLabel = new JLabel("Author:  ");
                authorLabel.setFont(new Font("SansSerif", Font.PLAIN,14));
                JPanel authorFormGroup = new JPanel(new BorderLayout(5,5));
                authorTextField = new JTextField();
                authorTextField.setMargin(new Insets(5, 5, 5, 5));
                authorTextField.setFont(new Font("SansSerif", Font.PLAIN,14));
                authorFormGroup.setOpaque(false);
                authorFormGroup.add(authorLabel,BorderLayout.WEST);
                authorFormGroup.add(authorTextField, BorderLayout.CENTER);
                authorFormGroup.setBorder(BorderFactory.createEmptyBorder(5, 300, 20, 300));

                searchForm.setLayout(new BoxLayout(searchForm, BoxLayout.Y_AXIS));
                searchForm.add(titleFormGroup);
                searchForm.add(ISBNFormGroup);
                searchForm.add(authorFormGroup);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setOpaque(false);

                JButton searchButton = new JButton("Search");
                searchButton.setContentAreaFilled(false);
                searchButton.setFocusPainted(false);
                searchButton.setBackground(Color.decode("#82AC9F"));
                searchButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                searchButton.setOpaque(true);

                JButton searchAllButton = new JButton("Search All");
                searchAllButton.setContentAreaFilled(false);
                searchAllButton.setFocusPainted(false);
                searchAllButton.setBackground(Color.decode("#82AC9F"));
                searchAllButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                searchAllButton.setOpaque(true);

                buttonPanel.add(searchButton);
                buttonPanel.add(searchAllButton);

                searchForm.add(buttonPanel);
                searchPanel.add(searchForm,BorderLayout.NORTH);

            JPanel bookListPanel = new JPanel();
                bookListPanel.setBorder(new EmptyBorder(20, 5, 20, 5));
                bookListPanel.setBackground(Color.white);

                String[] column = {"Title","ISBN","Author","Genres","Quantity","Status"};
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(column);
                table = new JTable();
                table.setModel(model);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                table.setFillsViewportHeight(true);
                JScrollPane scroll = new JScrollPane(table);
                scroll.setHorizontalScrollBarPolicy(
                        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scroll.setVerticalScrollBarPolicy(
                        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                table.setFont(new Font("SansSerif", Font.PLAIN, 14));
                JTableUtilities.setCellsAlignment(table, SwingConstants.CENTER);
                model.addTableModelListener(new TableModelListener() {
                    @Override public void tableChanged(final TableModelEvent e) {
                        EventQueue.invokeLater(new Runnable() {
                            @Override public void run() {
                                table.setRowHeight(e.getFirstRow(), 25); //replace 15 with your own height
                            }
                        });
                    }
                });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                String title = titleTextField.getText();
                String ISBN = ISBNTextField.getText();
                String author = authorTextField.getText();
                System.out.println("title: "+title+" ISBN: "+ISBN+" author: "+author);

                int input = 0;
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/lms","root","");

//                  stmt1 & stmt2 to refresh 'bookStatus' table
                    Statement stmt1=conn.createStatement();
                    String sql = "DROP TABLE bookStatus";
                    stmt1.executeUpdate(sql);

                    String sql1 = "CREATE TABLE bookStatus AS\n" +
                            "SELECT books.Title, books.ISBN, books.Author, books.Genres,bookCount.bookcnt,\n" +
                            "IF(loanCount.loancnt=bookCount.bookcnt,\"Unavailable\",\"Available\") AS Availability\n" +
                            "FROM books\n" +
                            "JOIN (SELECT books.Title, COUNT(*) AS bookcnt FROM books GROUP BY books.Title, books.ISBN, books.Author, books.Genres) AS bookCount\n" +
                            "ON books.Title=bookCount.Title\n" +
                            "LEFT JOIN (SELECT books.Title, Count(*) AS loancnt, books.ISBN\n" +
                            "FROM books INNER JOIN loan ON books.CallNumber = loan.CallNumber\n" +
                            "GROUP BY books.Title, books.ISBN) AS loanCount\n" +
                            "ON books.Title=loanCount.Title\n" +
                            "GROUP BY books.Title, books.ISBN";
                    Statement stmt2=conn.createStatement();
                    stmt2.execute(sql1);

                    Statement stmt=conn.createStatement();
                    ResultSet rs=stmt.executeQuery("SELECT * FROM bookStatus");
                    while (rs.next()) {
                        if(!title.isBlank() ){
                            input = 1;
                        } else if (!ISBN.isBlank()) {
                            input = 2;
                        } else if (!author.isBlank()) {
                            input = 3;
                        }
                    }
                    if(input==1){
                        PreparedStatement ps=conn.prepareStatement("SELECT * FROM bookStatus where Title LIKE CONCAT('%', ?, '%')");
                        ps.setString(1,title);
                        ResultSet rss = ps.executeQuery();
                        while (rss.next()) {
                            String title1 = rss.getString("Title");
                            int isbn = rss.getInt("ISBN");
                            String author1 = rss.getString("Author");
                            String genres = rss.getString("Genres");
                            String noOfBooks = rss.getString("bookcnt");
                            String availability = rss.getString("Availability");
                            model.addRow(new Object[]{title1, isbn, author1, genres, noOfBooks, availability});
                        }
                        conn.close();
                    }
                    else if(input==2){
                        PreparedStatement ps2=conn.prepareStatement("SELECT * FROM bookStatus where ISBN LIKE CONCAT('%', ?, '%')");
                        ps2.setString(1,ISBN);
                        ResultSet result2=ps2.executeQuery();
                        while (result2.next()) {
                            String title1 = result2.getString("Title");
                            int isbn = result2.getInt("ISBN");
                            String author1 = result2.getString("Author");
                            String genres = result2.getString("Genres");
                            String noOfBooks = result2.getString("bookcnt");
                            String availability = result2.getString("Availability");
                            model.addRow(new Object[]{title1, isbn, author1, genres, noOfBooks, availability});
                        }
                        conn.close();
                    }
                    else if(input==3){
                        PreparedStatement ps3=conn.prepareStatement("SELECT * FROM bookStatus where Author LIKE CONCAT('%', ?, '%')");
                        ps3.setString(1,author);
                        ResultSet result3=ps3.executeQuery();
                        while (result3.next()) {
                            String title1 = result3.getString("Title");
                            int isbn = result3.getInt("ISBN");
                            String author1 = result3.getString("Author");
                            String genres = result3.getString("Genres");
                            String noOfBooks = result3.getString("bookcnt");
                            String availability = result3.getString("Availability");
                            model.addRow(new Object[]{title1, isbn, author1, genres, noOfBooks, availability});
                        }
                        conn.close();
                    }
                    else {
                        JOptionPane.showMessageDialog(OPAC.this,"Sorry, book not found!");
                        titleTextField.setText("");
                        ISBNTextField.setText("");
                        authorTextField.setText("");
                    }
                }catch(Exception ep){
                    System.out.println(ep);
                    JOptionPane.showMessageDialog(OPAC.this,"Sorry, book not found!");
                }
            }
        });

        searchAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/lms","root","");
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery("SELECT books.Title, books.ISBN, books.Author, books.Genres,bookCount.bookcnt,\n" +
                            "IF(loanCount.loancnt=bookCount.bookcnt,\"Unavailable\",\"Available\") AS Availability\n" +
                            "FROM books\n" +
                            "JOIN (SELECT books.Title, COUNT(*) AS bookcnt FROM books GROUP BY books.Title, books.ISBN, books.Author, books.Genres) AS bookCount\n" +
                            "ON books.Title=bookCount.Title\n" +
                            "LEFT JOIN (SELECT books.Title, Count(*) AS loancnt, books.ISBN\n" +
                            "FROM books INNER JOIN loan ON books.CallNumber = loan.CallNumber\n" +
                            "GROUP BY books.Title, books.ISBN) AS loanCount\n" +
                            "ON books.Title=loanCount.Title\n" +
                            "GROUP BY books.Title, books.ISBN");
                    while (rs.next()) {
                        String title = rs.getString("Title");
                        int isbn = rs.getInt("ISBN");
                        String author = rs.getString("Author");
                        String genres = rs.getString("Genres");
                        String noOfBooks = rs.getString("bookcnt");
                        String availability = rs.getString("Availability");
                        model.addRow(new Object[]{title, isbn, author, genres, noOfBooks, availability});
                    }
                    con.close();
                } catch (SQLException | ClassNotFoundException ecp) {
                    ecp.printStackTrace();
                }

            }
        });

                scroll.setPreferredSize(new Dimension(900,300));
                bookListPanel.add(scroll);
                searchPanel.add(bookListPanel,BorderLayout.CENTER);

        opacPanel.add(opacTitleLabel,BorderLayout.NORTH);
        opacPanel.add(searchPanel,BorderLayout.CENTER);
        opacPanel.setBackground(Color.white);
        add(opacPanel);

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentDashboard.main(new String[]{});
                frame.dispose();
            }
        });
    }

    public static class JTableUtilities
    {
        public static void setCellsAlignment(JTable table, int alignment)
        {
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(alignment);

            TableModel tableModel = table.getModel();

            for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++)
            {
                table.getColumnModel().getColumn(columnIndex).setCellRenderer(rightRenderer);
            }
        }
    }


}




