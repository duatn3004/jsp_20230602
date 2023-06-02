package orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecotr {
	
	//싱글톤 방식
	private static DatabaseConnecotr dbc = new DatabaseConnecotr();
	//url, user, password
	private Connection conn = null; //DB관련 접속 규격
	//jdbcDriver
	private String jdbcDriver = "com.mysql.jdbc.Driver";
	//mysql URL
	private String jdbcUrl = "jdbc:mysql://localhost/javadb";
	
	private DatabaseConnecotr() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, "javauser", "mysql");
			
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("jdbc드라이버를 찾을 수 없습니다.");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("연결정보가 정확하지 않습니다.");
			e.printStackTrace();
		}
	}
	
	//싱글톤방식
	public static DatabaseConnecotr getInstance() {
		return dbc;
	}
	
	public Connection getConnection() {
		return conn;
	}
}
