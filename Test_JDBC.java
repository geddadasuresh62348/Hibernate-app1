package hibernate.advanced.jdbc.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test_JDBC {

	public static void main(String[] args) {
		String jdbcURL="jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String uname="hbstudent";
		String password="hbstudent";
		
		try {
			System.out.println("Connecting to the database");
			Connection connection=DriverManager.getConnection(jdbcURL,uname,password);
			System.out.println("Connection established");
			System.out.println(connection);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
