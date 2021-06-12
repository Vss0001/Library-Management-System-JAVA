package LMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class ReturnBookDB {
	public static void delete(String callno,String borrower){
		try{
//			String databaseURL = "jdbc:ucanaccess://src/LMS/LMSDatabase.accdb";
//            Connection conn = DriverManager.getConnection(databaseURL);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lms","root","");
			PreparedStatement ps=conn.prepareStatement("delete from loan where CallNumber=? and Borrower=?");
			ps.setString(1,callno);
			ps.setString(2,borrower);
			ps.executeUpdate();
			conn.close();
		}catch(Exception e){System.out.println(e);}
	}
}