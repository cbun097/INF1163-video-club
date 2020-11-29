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
import org.group2.finalproject.classes.Membre;

public class LocationController {

	private ArrayList<DisqueLouer> ListeLouer = new ArrayList<>();
	
	// Louer un film
	public void louerFilm(DisqueLouer disqueLouer) {
		String query = "INSERT INTO DisqueLouer (NumeroTelephone, NomClient, AdresseCourriel, AdresseDomicile, "
				+ "CarteDeCredit, EstMembre, CodeSecret, MontantDu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setString(1, disqueLouer.getCodeClient());
			statement.setString(2, disqueLouer.getCodeDisque());
			statement.setString(3, disqueLouer.getDateLouer().toString());
			statement.setString(4, disqueLouer.getDateRetour().toString());
			statement.setString(5, disqueLouer.getDateDu().toString());
			statement.executeUpdate();
			System.out.println("Louaison ajouté avec succès!");
			updateDisqueLouer();
		}
		catch(SQLException e) 
		{
			System.out.println("Ajouter membre erreur: " + e);
		}
		finally
		{
			ConnexionDB.closeConnection();
		}
	}
	
	// Réserver un film
	public void reserveFilm(String CodeFilm) {
		// TODO Ajouter le reste
		
	}
	
	
	// Retourner un film 
	public void retourFilm(String CodeFilm) {
		
	}
	
	// Afficher la liste complete
	public void afficherLocationsListe() {
		
	}
	
	public void updateDisqueLouer() {
		String query = "SELECT * FROM DisqueLouer";
		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
		    ListeLouer.clear();
			while (result.next())
			{
			    String numTel = result.getString("NumeroTelephone");
			    String CodeDisque = result.getString("CodeDisque");
			    Date DateLouer = new SimpleDateFormat("dd/MM/yyyy").parse(result.getString("DateLouer"));
			    Date DateRetour = new SimpleDateFormat("dd/MM/yyyy").parse(result.getString("DateRetour"));
			    Date DateDu = new SimpleDateFormat("dd/MM/yyyy").parse(result.getString("DateDu"));
			    
			    ListeLouer.add(new DisqueLouer(numTel, CodeDisque, DateLouer, DateRetour, DateDu));
			}
			
			System.out.println("Liste des membres mise à jour");
		}
		catch(SQLException e)
		{
			System.out.print("Afficher liste erreur: " + e);
		} catch (ParseException e) {
		}
		finally 
		{
			ConnexionDB.closeConnection();
		}
	}
	
}
