package LMS;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class AdminLoginDB {
    public static boolean checkConnection() {
        boolean status=false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lms","root","");
            PreparedStatement ps=conn.prepareStatement("select * from admin");
            ResultSet rs=ps.executeQuery();
            status=rs.next();
            conn.close();
        }catch(Exception e){System.out.println(e);}
        return status;
    }
 
    public static boolean validate(String id,String password){
        boolean status=false;
        try{
//            String databaseURL = "jdbc:ucanaccess://src/LMS/LMSDatabase.accdb";
//            Connection conn = DriverManager.getConnection(databaseURL);
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lms","root","");
            PreparedStatement ps=conn.prepareStatement("select * from admin where AdminID=? and AdminPassword=?");
            ps.setString(1,id);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            status=rs.next();
            conn.close();
        }catch(Exception e){System.out.println(e);}
        return status;
    }

}
