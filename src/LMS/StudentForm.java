package LMS;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.DriverManager;

public class StudentForm {

	public static int save(String studId, String name, String password, int phone) {
		int status=0;
	    try{
//	        String databaseURL = "jdbc:ucanaccess://src/LMS/LMSDatabase.accdb";
//	        Connection conn = DriverManager.getConnection(databaseURL);
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/lms","root","");
	        PreparedStatement ps=conn.prepareStatement("insert into student(Student_id,Student_name,Student_password,Phone) values(?,?,?,?)");
	        ps.setString(1,studId);
			ps.setString(2,name);
			ps.setString(3,password);
			ps.setInt(4,phone);
			status=ps.executeUpdate();
	        conn.close();
	    }catch(Exception e){System.out.println(e);}
	    return status;
	}
}


