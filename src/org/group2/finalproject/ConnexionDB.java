package org.group2.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// NOT TESTED
public class ConnexionDB {
	//Source: https://www.codejava.net/java-se/jdbc/jdbc-tutorial-sql-insert-select-update-and-delete-examples
	private static Connection conn;
	
	public static Connection getConnexion()
	{
		return conn;
	}
	
	public static void initConnexion() {
		try 
		{
			String url = "jdbc:sqlite:videoclubDB.db";
		    conn = DriverManager.getConnection(url);
		 
		    if (conn != null) 
		        System.out.println("Connected");
		}
		catch (SQLException ex)
		{
		    ex.printStackTrace();
		}
	}
	
	public static void closeConnection()
	{
		try
		{
			if(conn!=null)
				conn.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}
