package org.group2.finalproject.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.group2.finalproject.classes.Membre;

public class MembreController {
	Connection conn;
	
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
	public void afficherFilmListe() {
		String query = "SELECT * FROM Films";
		try { 
			PreparedStatement statement = conn.prepareStatement(query);
			statement.executeQuery();
		}
		catch(SQLException e) {
			System.out.print("Afficher liste erreur: " + e);
		}
	}
}
