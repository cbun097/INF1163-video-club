package org.group2.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// NOT TESTED
public class ConnexionDB {
	//Source: https://www.codejava.net/java-se/jdbc/jdbc-tutorial-sql-insert-select-update-and-delete-examples
	
	public static void getConnexion() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:videoclubDB.db";
		    conn = DriverManager.getConnection(url);
		 
		    if (conn != null) {
		        System.out.println("Connected");
		    }
		}
		catch (SQLException ex) {
		    ex.printStackTrace();
		}
		finally {
			try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		}
	}
}
