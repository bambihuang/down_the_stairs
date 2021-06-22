package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector {
	String player, message, timestamp; // timestamp sample:2021-06-21 00:00:00
	int score;
	
	static final String RANKINGTABLE = "rankingtest";
	static final String MESSAGETABLE = "messageboard";
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://remotemysql.com:3306/R0OJxo6kKl";
	private static final String USERNAME = "R0OJxo6kKl";
	private static final String PASSWORD = "IFiiUvNCUH";
	public String insertRanking = "INSERT INTO R0OJxo6kKl.rankingtest VALUES ('"+player+"',"+score+")";
	public String selectRanking = "SELECT * FROM R0OJxo6kKl.rankingtest ORDER BY score DESC";
	public String insertNewMessage = "INSERT INTO R0OJxo6kKl.messageboard VALUES ('"+player+"','"+message+"','"+timestamp+"')";
	public String selectAllMessage = "SELECT message, account, time FROM R0OJxo6kKl.messageboard ORDER BY time DESC";
	private String data = "";

	public void connectMysql(String command) {
		Connection conn = null; // 和資料庫取得連線
		Statement stmt = null; // 與資料庫互動的核心
		ResultSet rs = null;
		try {
			// JDBC Driver register
			Class.forName(JDBC_DRIVER);

			// link
			System.out.println("prepare connect DB...");
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

			// get statement
			System.out.println(" 建構子.並產生Statement實體...");
			stmt = conn.createStatement();

			if (command.contains("SELECT"))
				rs = stmt.executeQuery(command);
			else
				stmt.executeUpdate(command);

			// Database dataset
			while (rs.next()) {
				data += rs.getString("player") + "," + rs.getString("score") + ";";
				System.out.print(rs.getString("player"));
				System.out.println("," + rs.getString("score"));
			}
			
			// Close connection
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// handle JDBC exception
			se.printStackTrace();
		} catch (Exception e) {
			// handle Class.forName error
			e.printStackTrace();
		} finally {
			// 收尾處理，關閉一些資料
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Exit Select Mysql DB Example!");
	}
	
	String getData() {
		return data;
	}
}
