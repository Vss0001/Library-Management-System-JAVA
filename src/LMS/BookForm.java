package LMS;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class BookForm {

public static int save(String title, int isbn, String author, String genre, String callno) {
	int status=0;
    try{
//        String databaseURL = "jdbc:ucanaccess://src/LMS/LMSDatabase.accdb";
//        Connection conn = DriverManager.getConnection(databaseURL);

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn=DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/lms","root","");
        PreparedStatement ps=conn.prepareStatement("insert into books(Title,ISBN,Author,Genres,CallNumber) values(?,?,?,?,?)");
        ps.setString(1,title);
		ps.setInt(2,isbn);
		ps.setString(3,author);
		ps.setString(4,genre);
		ps.setString(5,callno);
		status=ps.executeUpdate();
        conn.close();
    }catch(Exception e){System.out.println(e);}
    return status;
}
}