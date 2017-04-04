package edu.bv;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	
	
	public static Connection createDbConnection(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pathwaydb","root", "");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return conn;
	}

}
