import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class JDBC_test2 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql:// remotemysql.com:3306/LDtiCH8aaA";

	//  Database credentials
	static final String USER = "LDtiCH8aaA";
	static final String PASS = "Xkr3IQ9FKh";

	
    private final static String select = "select * from user where username = ?";
	private final static String insert = "insert into user(username,password) values(?,?)";
	private final static String update = "update user set score = ? where username = ?";
	
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement prestmt =null;
	
	
	public boolean isExist(User user) {

		try{

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			prestmt = conn.prepareStatement(select);
			prestmt.setString(1, user.getName());
			ResultSet existResult = prestmt.executeQuery();
			prestmt.clearParameters();
			return existResult.next();						
		}catch(SQLException se){
			se.printStackTrace();
			 return false;
		}catch(Exception e){
			e.printStackTrace();
			 return false;
		}finally{
			try{
				if(prestmt!=null)	conn.close();
			}catch(SQLException se){}
			try{
				if(conn!=null)	conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}
	public boolean isAdd(User user) {

		try{

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			prestmt = conn.prepareStatement(insert);
			prestmt.setString(1, user.getName());
			prestmt.setString(2, user.getPassword());
           

            int result=prestmt.executeUpdate();
			prestmt.clearParameters();	

			return  result==1;
								
		}catch(SQLException se){
			se.printStackTrace();
			 return false;
		}catch(Exception e){
			e.printStackTrace();
			 return false;
		}finally{
			try{
				if(prestmt!=null)	conn.close();
			}catch(SQLException se){}
			try{
				if(conn!=null)	conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}
	
	public boolean score(User user) {

		try{

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			prestmt = conn.prepareStatement(update);
			prestmt.setInt(1, user.getScore());
			prestmt.setString(2, user.getName());
            int result = prestmt.executeUpdate();
            prestmt.clearParameters();
            return  result == 1;
									
		}catch(SQLException se){
			se.printStackTrace();
			 return false;
		}catch(Exception e){
			e.printStackTrace();
			 return false;
		}finally{
			try{
				if(prestmt!=null)	conn.close();
			}catch(SQLException se){}
			try{
				if(conn!=null)	conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		
	}
	
	public User login(User user) {
		User result=new User();
		try{

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			prestmt = conn.prepareStatement(select);
			prestmt.setString(1, user.getName());
			ResultSet resultset = prestmt.executeQuery();

			if(resultset.next()) {
				result.setName(resultset.getString("username"));
				result.setPassword(resultset.getString("password"));
				result.setScore(resultset.getInt("score"));
			}
			prestmt.clearParameters();
            return  result;
									
		}catch(SQLException se){
			se.printStackTrace();
            return  result;

		}catch(Exception e){
			e.printStackTrace();
            return  result;
		}finally{
			try{
				if(prestmt!=null)	conn.close();
			}catch(SQLException se){}
			try{
				if(conn!=null)	conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	
}