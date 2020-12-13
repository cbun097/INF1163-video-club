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
	public void ajouterMembre(Membre membre)
	{
		String query = "INSERT INTO Membre (NumeroTelephone, NomClient, AdresseCourriel, AdresseDomicile, "
				+ "CarteDeCredit, EstMembre, CodeSecret) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setString(1, membre.getNumeroTelephone());
			statement.setString(2, membre.getNomClient());
			statement.setString(3, membre.getAdresseCourriel());
			statement.setString(4, membre.getAdresseDomicile());
			statement.setString(5, membre.getCarteDeCredit());
			statement.setBoolean(6, membre.getEstMembre());
			statement.setInt(7, membre.getCodeSecret());
			statement.executeUpdate();
			System.out.println("Membre ajouté avec succès!");
			updateMembreListe();
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
	
	// Modifier un membre
	public void modifierMembre(Membre membre) {
		String query = "UPDATE Membre SET NomClient=?, AdresseCourriel=?, AdresseDomicile=?,"
				+ " CarteDeCredit=?, EstMembre=?, CodeSecret=? WHERE NumeroTelephone=?";
		try
		{
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, membre.getNomClient());
			statement.setString(2, membre.getAdresseCourriel());
			statement.setString(3, membre.getAdresseDomicile());
			statement.setString(4, membre.getCarteDeCredit());
			statement.setBoolean(5, membre.getEstMembre());
			statement.setInt(6, membre.getCodeSecret());
			statement.setString(7, membre.getNumeroTelephone());
			statement.executeUpdate();
			System.out.println("Membre modifié avec succès!");
			updateMembreListe();
		}
		catch(SQLException e) 
		{
			System.out.println("Modifier membre erreur: " + e);
		}
		finally 
		{
			ConnexionDB.closeConnection();
		}
	}
	
	
	// Supprimer un membre 
	public void supprimerMembre(Membre membre) {
		String query = "DELETE FROM Membre WHERE NumeroTelephone=?";
		try 
		{
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, membre.getNumeroTelephone());
			statement.executeUpdate();
			System.out.println("Membre supprimé avec succès!");
			updateMembreListe();
		}
		catch(SQLException e)
		{
			System.out.println("Supprimer membre erreur: " + e);
		}
		finally 
		{
			ConnexionDB.closeConnection();
		}
	}
	
	// Afficher la liste complete
	public void updateMembreListe() {
		String query = "SELECT * FROM Membre";
		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
		    listeMembres.clear();
			while (result.next())
			{
			    String numTel = result.getString("NumeroTelephone");
			    String nom = result.getString("NomClient");
			    String email = result.getString("AdresseCourriel");
			    String adresseDomicile = result.getString("AdresseDomicile");
			    String carteCredit = result.getString("CarteDeCredit");
			    Boolean estMembre = result.getBoolean("EstMembre");
			    int codeSecret = result.getInt("CodeSecret");
			    
			    listeMembres.add(new Membre(numTel, nom, email, adresseDomicile, estMembre, 
			    		carteCredit, codeSecret));
			}
			
			System.out.println("Liste des membres mise à jour");
		}
		catch(SQLException e)
		{
			System.out.print("Afficher liste erreur: " + e);
		}
		finally 
		{
			ConnexionDB.closeConnection();
		}
	}
	
	public ArrayList<Membre> getListeMembres()
	{
		return listeMembres;
	}
	
	public String[][] getListeMembresData()
	{
		String data[][] = new String[listeMembres.size()][7];
		
		for(int i = 0; i < listeMembres.size(); i++)
		{
			Membre m = listeMembres.get(i);
			data[i][0] = m.getNumeroTelephone();
			data[i][1] = m.getNomClient();
			data[i][2] = m.getAdresseCourriel();
			data[i][3] = m.getAdresseDomicile();
			data[i][4] = m.getCarteDeCredit();
			data[i][5] = Boolean.toString(m.getEstMembre());
			data[i][6] = Integer.toString(m.getCodeSecret());
		}
		
		return data;
	}
}
