package org.group2.finalproject.controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.ListesUtil;
import org.group2.finalproject.classes.Location;

public class LocationController {

	// Louer un film
	public void louerFilm(Location location) {
		// TODO Ajouter le reste
		String query = "INSERT INTO Location(NumeroTelephoneClient, CodeDisque, DateLouer, DateRetour, DateDu, MontantRetardDu)  VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, location.getNumeroTelephone());
			statement.setString(2, location.getCodeDisque());
			statement.setString(3, location.getDateLouer());
			statement.setString(4, location.getDateRetour());
			statement.setString(5, location.getDateDu());
			statement.setDouble(6, location.getMontantRetardDu());
			statement.execute();
			updateLocationListe();
		}
		catch(SQLException e) {
			System.out.print("Ajouter une nouvelle location erreur: " + e);
		}
		finally{
			ConnexionDB.closeConnection();
		}
	}
	
	// Modifier une location
	public void modifLocation(Location location, boolean update) {
		// TODO Ajouter le reste
		String query = "UPDATE Location SET DateLouer=?, DateRetour=?, DateDu=?, MontantRetardDu=? WHERE NumeroTelephoneClient=? AND CodeDisque=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, location.getDateLouer());
			statement.setString(2, location.getDateRetour());
			statement.setString(3, location.getDateDu());
			statement.setDouble(4, location.getMontantRetardDu());
			statement.setString(5, location.getNumeroTelephone());
			statement.setString(6, location.getCodeDisque());
			statement.execute();
			if(update)
				updateLocationListe();
		}
		catch(SQLException e) {
			System.out.print("Modifier une nouvelle location erreur: " + e);
		}
		finally{
			ConnexionDB.closeConnection();
		}
		
	}
	
	// Afficher la liste complete
	public void updateLocationListe() {
		String query = "SELECT * FROM Location";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			int count = 0;
			
			ListesUtil.LISTE_LOCATIONS.clear();
			while (result.next()){
		    	String numTel = result.getString("NumeroTelephoneClient");
		    	String codeDisque = result.getString("CodeDisque");
		    	String dateLouer = result.getString("DateLouer");
		    	String dateRetour = result.getString("DateRetour");
		    	String dateDu = result.getString("DateDu");
		    	Double montantRetardDu = result.getDouble("MontantRetardDu");
		    	ListesUtil.LISTE_LOCATIONS.add(new Location(numTel, codeDisque, dateLouer, dateRetour, dateDu, montantRetardDu));
			 
			    String output = "User #%d: %s - %s - %s - %s - %s";
			    System.out.println(String.format(output, ++count, numTel, codeDisque, dateLouer, dateRetour, dateDu));
			}  
		}
		catch(SQLException e) {
			System.out.print("Supprimer une nouvelle location erreur: " + e);
		}
		finally{
			ConnexionDB.closeConnection();
		}
	}
	
	public int calculPrixLocation() {
		return 0;
	}
	
	public String[][] getListeLocationsData(){
		String data[][] = new String[ListesUtil.LISTE_LOCATIONS.size()][6];
		
		for(int i = 0; i < ListesUtil.LISTE_LOCATIONS.size(); i++)
		{
			Location location = ListesUtil.LISTE_LOCATIONS.get(i);
			data[i][0] = location.getNumeroTelephone();
			data[i][1] = location.getCodeDisque();
			data[i][2] = location.getDateLouer().toString();
			data[i][3] = location.getDateRetour().toString();
			data[i][4] = location.getDateDu().toString();
			data[i][5] = Double.toString(location.getMontantRetardDu());
		}
		return data;
		
	}
}
