package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.classes.Membre;

public class MembreController 
{
	private ArrayList<Membre> listeMembres = new ArrayList<>();
	
	// Ajouter un nouveau membre
	public void ajouterMembre(Membre membre) {
		// TODO Ajouter le reste
		String query = "INSERT INTO Membres (CodeClient, NomClient, AdresseCourriel) VALUES (?, ?, ?)";
		// TODO
		try { 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setString(1, membre.getCodeClient());
			statement.setString(2, membre.getNomClient());
			statement.setString(3, membre.getAdresseCourriel());
			statement.executeUpdate();
			
			updateMembreListe();
		}
		catch(SQLException e) {
			System.out.print("Ajouter membre erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
	}
	
	// Modifier un membre
	public void modifierMembre(Membre membre) {
		// TODO Ajouter le reste
		String query = "UPDATE Membres SET NomClient=?, AdresseCourriel=? WHERE CodeClient=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, membre.getNomClient());
			statement.setString(2, membre.getAdresseCourriel());
			statement.setString(3, membre.getCodeClient());
			statement.executeUpdate();
			
			updateMembreListe();
		}
		catch(SQLException e) {
			System.out.print("Modifier membre erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
	}
	
	
	// Supprimer un membre 
	public void supprimerMembre(String codeMembre) {
		String query = "DELETE FROM Membres WHERE CodeClient=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, codeMembre);
			statement.executeUpdate();
			
			updateMembreListe();
		}
		catch(SQLException e) {
			System.out.print("Supprimer membre erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
	}
	
	// Afficher la liste complete
	public void updateMembreListe() {
		String query = "SELECT * FROM Membres";
		try { 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			int count = 0;
			
		    listeMembres.clear();
			while (result.next()){
			    String code = result.getString("CodeClient");
			    String nom = result.getString("NomClient");
			    String email = result.getString("AdresseCourriel");
			    
			    listeMembres.add(new Membre(code, nom, email));
			 
			    String output = "User #%d: %s - %s";
			    System.out.println(String.format(output, ++count, nom, email));
			}  
		}
		catch(SQLException e) {
			System.out.print("Afficher liste erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
	}
	
	public ArrayList<Membre> getListeMembres()
	{
		return listeMembres;
	}
	
	public String[][] getListeMembresData()
	{
		String data[][] = new String[listeMembres.size()][4];
		
		for(int i = 0; i < listeMembres.size(); i++)
		{
			Membre m = listeMembres.get(i);
			data[i][0] = m.getCodeClient();
			data[i][1] = m.getNomClient();
			data[i][2] = m.getNumeroTelephoneMaison();
			data[i][3] = m.getAdresseCourriel();
		}
		
		return data;
	}
}
