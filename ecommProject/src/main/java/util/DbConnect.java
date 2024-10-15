package util;
import java.sql.*;
public class DbConnect {
	private static DbConnect instance;
	private static Connection con;
	private DbConnect(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommdb","root","Hemasai@1234");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static DbConnect getInstance() {
		if(instance==null) {
			instance= new DbConnect();
		}
		return instance;
	}
	public static Connection getConnection() {
		return con;
	}
	
}
