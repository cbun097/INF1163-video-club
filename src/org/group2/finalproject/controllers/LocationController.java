package org.group2.finalproject.controllers;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.classes.Location;

public class LocationController {
	
	private ArrayList<Location> listeLocations = new ArrayList<>();

	// Louer un film
	public void louerFilm(Location location) {
		// TODO Ajouter le reste
		String query = "INSERT INTO Location(NumeroTelephone, CodeDisque, DateLouer, DateRetour, DateDu, MontantRetardDu)  VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, location.getNumeroTelephone());
			statement.setString(2, location.getCodeDisque());
			statement.setDate(3, location.getDateLouer());
			statement.setDate(4, location.getDateRetour());
			statement.setDate(5, location.getDateDu());
			statement.setDouble(6, location.getMontantRetardDu());
			updateLocationListe();
		}
		catch(SQLException e) {
			System.out.print("Ajouter une nouvelle location erreur: " + e);
		}
		finally{
			ConnexionDB.closeConnection();
		}
	}
	
	// R�server un film
	public void reserveFilm(Location location) {
		// TODO Ajouter le reste
		String query = "UPDATE Location SET NumeroTelephone=?, CodeDisque=?, DateLouer=?, DateRetour=?, DateDu=?, MontantRetardDu=? WHERE SOMETHING_TO=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, location.getNumeroTelephone());
			statement.setString(2, location.getCodeDisque());
			statement.setDate(3, location.getDateLouer());
			statement.setDate(4, location.getDateRetour());
			statement.setDate(5, location.getDateDu());
			statement.setDouble(6, location.getMontantRetardDu());
			updateLocationListe();
		}
		catch(SQLException e) {
			System.out.print("Modifier une nouvelle location erreur: " + e);
		}
		finally{
			ConnexionDB.closeConnection();
		}
		
	}
	
	
	// Retourner un film 
	public void retourFilm() {
		String query = "";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			updateLocationListe();
		}
		catch(SQLException e) {
			System.out.print("Ajouter une nouvelle location erreur: " + e);
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
			
		    listeLocations.clear();
			while (result.next()){
		    	String numTel = result.getString("");
		    	String codeDisque = result.getString("");
		    	Date dateLouer = result.getDate("");
		    	Date dateRetour = result.getDate("");
		    	Date dateDu = result.getDate("");
		    	Double montantRetardDu = result.getDouble("");
			    listeLocations.add(new Location(numTel, codeDisque, dateLouer, dateRetour, dateDu, montantRetardDu));
			 
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
	
	public ArrayList<Location> getListeLocations()
	{
		return listeLocations;
	}
	
	public int calculPrixLocation() {
		return 0;
	}
	
	public String[][] getListeLocationsData(){
		String data[][] = new String[listeLocations.size()][6];
		
		for(int i = 0; i < listeLocations.size(); i++)
		{
			Location location = listeLocations.get(i);
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
