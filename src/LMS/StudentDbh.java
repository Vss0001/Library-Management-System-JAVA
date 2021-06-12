package LMS;
import java.sql.*;

public class StudentDbh {
    // check connections
//    public static void main(String[] args) {
//        String databaseURL = "jdbc:ucanaccess://src/LMSDatabase.accdb";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con=DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/lms","root","");
//            Statement stmt=con.createStatement();
//            ResultSet rs=stmt.executeQuery("SELECT books.Title, books.ISBN, books.Author, books.Genres,bookCount.bookcnt,\n" +
//                    "IF(loanCount.loancnt=bookCount.bookcnt,\"Unavailable\",\"Available\") AS Availability\n" +
//                    "FROM books\n" +
//                    "JOIN (SELECT books.Title, COUNT(*) AS bookcnt FROM books GROUP BY books.Title, books.ISBN, books.Author, books.Genres) AS bookCount\n" +
//                    "ON books.Title=bookCount.Title\n" +
//                    "LEFT JOIN (SELECT books.Title, Count(*) AS loancnt, books.ISBN\n" +
//                    "FROM books INNER JOIN loan ON books.CallNumber = loan.CallNumber\n" +
//                    "GROUP BY books.Title, books.ISBN) AS loanCount\n" +
//                    "ON books.Title=loanCount.Title\n" +
//                    "GROUP BY books.Title, books.ISBN");
//            while(rs.next())
//                System.out.println(rs.getString(1)+" "+rs.getString(6));
//            con.close();
//        } catch (SQLException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static boolean validate(String studentId,String password){
        boolean status=false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lms","root","");
            PreparedStatement ps=conn.prepareStatement("select * from student where Student_id=? and Student_password=?");
            ps.setString(1,studentId);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            status=rs.next();
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
        return status;
    }

}
