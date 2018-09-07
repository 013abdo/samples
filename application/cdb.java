package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cdb {
	private static String username = "root";
	private static String pass="";
//	private static String url ="jdbc:mysql://localhost/celebration";
	private static String url ="jdbc:sqlite:C:/WorkShops/JavaFX/celebrationshop/celebration.sqlite";

	public static Connection sqlcon() throws SQLException{
		return DriverManager.getConnection(url, username, pass);
	}
}
