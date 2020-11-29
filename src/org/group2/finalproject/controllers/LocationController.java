package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.classes.DisqueLouer;

public class LocationController {
	
	private ArrayList<DisqueLouer> listeLocations = new ArrayList<>();

	// Louer un film
	public void louerFilm(DisqueLouer disque) throws ParseException {
		// TODO Ajouter le reste
		String query = "INSERT INTO DisqueLouer (NumeroTelephoneClient, CodeDisque, DateLouer, DateRetour, DateDu" +
			")  VALUES (?, ?, ?, ?, ?)";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, disque.getCodeClient());
			statement.setString(2, disque.getCodeDisque());
			statement.setString(3, disque.getDateLouer().toString());
			statement.setString(4, disque.getDateRetour().toString());
			statement.setString(5, disque.getDateDu().toString());
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
	public void reserveFilm(DisqueLouer disque) throws ParseException {
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
	public void retourFilm() throws ParseException {
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
	public void updateLocationListe() throws ParseException {
		String query = "SELECT * FROM DisqueLouer";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			
			ResultSet result = statement.executeQuery();
			
			int count = 0;
			
		    listeLocations.clear();
			while (result.next()){
			    String codeClient = result.getString("NumeroTelephoneClient");
			    System.out.println(result.getString("NumeroTelephoneClient"));
			    String codeDisque = result.getString("CodeDisque");
			    Date dateLouer = new SimpleDateFormat("dd/MM/yyyy").parse(result.getString("DateLouer"));
			    Date dateRetour = new SimpleDateFormat("dd/MM/yyyy").parse(result.getString("DateRetour"));
			    Date dateDu = new SimpleDateFormat("dd/MM/yyyy").parse(result.getString("DateDu"));
			    
			    listeLocations.add(new DisqueLouer(codeClient, codeDisque, dateLouer, dateRetour, dateDu));
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
		String data[][] = new String[listeLocations.size()][5];
		
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
