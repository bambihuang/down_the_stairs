package project;

import java.sql.*;

public class JDBC_test2 {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql:// remotemysql.com:3306/LDtiCH8aaA";
	static final String DB_URL_2 = "jdbc:mysql:// remotemysql.com:3306/R0OJxo6kKl";

	//  Database credentials
	static final String USER = "LDtiCH8aaA";
	static final String PASS = "Xkr3IQ9FKh";
	static final String USER_2 = "R0OJxo6kKl";
	static final String PASS_2 = "IFiiUvNCUH";

	
    private final static String select = "select * from user where username = ?";
    private final static String selectAll = "select username, score from user order by score desc";
	private final static String insert = "insert into user(username,password) values(?,?)";
	private final static String update = "update user set score = ? where username = ?";
	private final static String insertNewMessage = "INSERT INTO messageboard VALUES (?,?,?,?)";
	private final static String selectAllMessage = "SELECT message, account, time FROM messageboard ORDER BY time DESC";
	
	
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

	public String getRanking() {
		String result = "";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			stmt = conn.createStatement();
			ResultSet resultset = stmt.executeQuery(selectAll);

			while (resultset.next()) {
				result += resultset.getString("username") + ",";
				result += resultset.getInt("score") + ";";
			}
			
            return  result;
		}catch(SQLException se){
			se.printStackTrace();
            return  result;

		}catch(Exception e){
			e.printStackTrace();
            return  result;
		}finally{
			try{
				if(stmt!=null)	conn.close();
			}catch(SQLException se){}
			try{
				if(conn!=null)	conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}

	public String getAllMessage() {
		String result = "";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL_2, USER_2, PASS_2);
			
			stmt = conn.createStatement();
			ResultSet resultset = stmt.executeQuery(selectAllMessage);

			while (resultset.next()) {
				result += resultset.getString("message") + ",";
				result += resultset.getString("account") + ",";
				result += resultset.getString("time") + ";";
			}
			
            return  result;
		}catch(SQLException se){
			se.printStackTrace();
            return  result;

		}catch(Exception e){
			e.printStackTrace();
            return  result;
		}finally{
			try{
				if(stmt!=null)	conn.close();
			}catch(SQLException se){}
			try{
				if(conn!=null)	conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}

	public void addNewMessage(int no, User user, String msg, String time) {
		User player = user;
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL_2, USER_2, PASS_2);
			
			prestmt = conn.prepareStatement(insertNewMessage);
			prestmt.setInt(1, no);
			prestmt.setString(2, player.getName());
			prestmt.setString(3, msg);
			prestmt.setString(4, time);
			prestmt.executeUpdate();

			prestmt.clearParameters();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(stmt!=null)	conn.close();
			}catch(SQLException se){}
			try{
				if(conn!=null)	conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
	
}