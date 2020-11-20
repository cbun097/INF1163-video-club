package org.group2.finalproject.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.ResourcesUtil;
import org.group2.finalproject.classes.Membre;

public class MembreController {
	private Connection conn;
	
	// Ajouter un nouveau membre
	public void ajouterMembre(Membre membre) {
		// TODO Ajouter le reste
		String query = "INSERT INTO Membres (NomClient, AdresseCourrielClient) VALUES (?, ?)";
		// TODO
		try { 
			PreparedStatement statement = conn.prepareStatement(query);	
			statement.setString(1, membre.getNomClient());
			statement.setString(2, membre.getAdresseCourriel());
			statement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.print("Ajouter membre erreur: " + e);
		}
	}
	
	// Modifier un membre
	public void modifierMembre(String CodeMembre) {
		// TODO Ajouter le reste
		String query = "UPDATE Membre SET NomClient=?, AdresseCourrielClient=? WHERE CodeMembre=?";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.print("Modifier membre erreur: " + e);
		}
	}
	
	
	// Supprimer un membre 
	public void supprimerMembre(String CodeMembre) {
		String query = "DELETE FROM Membre WHERE CodeMembre=?";
		try {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeUpdate();
		}
		catch(SQLException e) {
			System.out.print("Supprimer membre erreur: " + e);
		}
	}
	
	// Afficher la liste complete
	public void afficherMembreListe() {
		String query = "SELECT * FROM Membres";
		try { 
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			int count = 0;
			 
			while (result.next()){
			    String nom = result.getString("NomClient");
			    String email = result.getString("AdresseCourriel");
			 
			    String output = "User #%d: %s - %s";
			    System.out.println(String.format(output, ++count, nom, email));
			}  
		}
		catch(SQLException e) {
			System.out.print("Afficher liste erreur: " + e);
		}
	}
	
	public String[][] getJsonListTemp()
	{
		String data[][] = new String[ResourcesUtil.LISTE_MEMBRES.size()][4];
		
		for(int i = 0; i < ResourcesUtil.LISTE_MEMBRES.size(); i++)
		{
			Membre m = ResourcesUtil.LISTE_MEMBRES.get(i);
			data[i][0] = m.getCodeClient();
			data[i][1] = m.getNomClient();
			data[i][2] = m.getNumeroTelephoneMaison();
			data[i][3] = m.getAdresseCourriel();
		}
		
		return data;
	}
}
