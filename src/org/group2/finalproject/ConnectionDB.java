package org.group2.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Classe qui fait la connection a la base de données */
public class ConnectionDB {
	
	// Download le jar file pour sqlite-jdb: https://github.com/xerial/sqlite-jdbc/releases
	public static void connectDB()
    {
		Connection conn = null;
		try {
			// Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:sqlite:C://sqlite//test.db";
			conn = DriverManager.getConnection(url);
		}
		catch(SQLException e) {
			System.out.print(e.getMessage());
		}
		/**finally{
			try {
				if (connection != null) 
					connection.close();
			}
			catch(SQLException e) {
				System.out.print(e.getMessage());
			}
		}*/
		System.out.print("Opened database successfully");
	} 
}
