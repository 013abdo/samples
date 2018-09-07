package application;

import java.sql.SQLException;


public class Exceptions {
	public static void SQLExceptions(SQLException s) {
		System.err.println("problem in:"+s.getSQLState());
		System.err.println("problem in:"+s.getMessage());
	}
	public static void Exception(Exception e) {
		System.err.println("problem in:"+e.getCause());
		System.err.println("problem in:"+e.getMessage());
	}
}
