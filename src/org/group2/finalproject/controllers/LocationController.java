package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.classes.DisqueLouer;

public class LocationController {
	
	private ArrayList<DisqueLouer> listeLocations = new ArrayList<>();

	// Louer un film
	public void louerFilm(DisqueLouer disque) {
		// TODO Ajouter le reste
		String query = "INSERT INTO DisqueLouer ()  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, disque.getCodeClient());
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
	public void reserveFilm(DisqueLouer disque) {
		// TODO Ajouter le reste
		String query = "UPDATE Membres SET NomClient=? WHERE SOMETHING_TO=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, disque.getCodeClient());
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
		String query = "SELECT * FROM DisqueLouer";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			int count = 0;
			
		    listeLocations.clear();
			while (result.next()){
			    String codeClient = result.getString("");
			    String codeDisque = result.getString("");
			    Date dateLouer = result.getDate("");
			    Date dateRetour = result.getDate("");
			    Date dateDu = result.getDate("");
			    
			    listeLocations.add(new DisqueLouer(codeClient, codeDisque, dateLouer, dateRetour, dateDu));
			 
			    String output = "User #%d: %s - %s - %s - %s - %s";
			    System.out.println(String.format(output, ++count, codeClient, codeDisque, dateLouer, dateRetour, dateDu));
			}  
		}
		catch(SQLException e) {
			System.out.print("Supprimer une nouvelle location erreur: " + e);
		}
		finally{
			ConnexionDB.closeConnection();
		}
	}
	
	public ArrayList<DisqueLouer> getListeLocations()
	{
		return listeLocations;
	}
	
	public int calculPrixLocation() {
		return 0;
	}
	
	public String[][] getListeLocationsData(){
		String data[][] = new String[listeLocations.size()][9];
		
		for(int i = 0; i < listeLocations.size(); i++)
		{
			DisqueLouer disqueLoue = listeLocations.get(i);
			data[i][0] = disqueLoue.getCodeClient();
			data[i][1] = disqueLoue.getCodeDisque();
			data[i][2] = disqueLoue.getDateLouer().toString();
			data[i][3] = disqueLoue.getDateRetour().toString();
			data[i][4] = disqueLoue.getDateDu().toString();
		}
		return data;
		
	}
}
