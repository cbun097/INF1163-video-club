package org.group2.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionDB 
{
	private static Connection conn;
	private static final String DB_URL = "jdbc:sqlite:videoclubDB.db";
	
	public static Connection getConnexion()
	{
		return conn;
	}
	
	public static void initConnexion() 
	{
		try 
		{
			if(conn == null || conn.isClosed())
			{
				conn = DriverManager.getConnection(DB_URL);
				if(conn != null)
					System.out.println("Connected to DATABASE");
			}
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
			if(conn != null && !conn.isClosed())
			{
				conn.close();
				System.out.println("Disconnected from DATABASE");
			}
		    
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}
