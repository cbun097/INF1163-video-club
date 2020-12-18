package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.ListesUtil;
import org.group2.finalproject.classes.Membre;

public class MembreController 
{
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
			System.out.println("Membre ajout� avec succ�s!");
//			updateMembreListe();
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
			System.out.println("Membre modifi� avec succ�s!");
//			updateMembreListe();
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
			System.out.println("Membre supprim� avec succ�s!");
//			updateMembreListe();
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
	public void updateMembreListe(String para) {
		
		String query = "SELECT * FROM Membre ";
		if (para!=null && !para.isEmpty()) 
			query += " where NomClient like '%" + para + "%'";
		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
		    ListesUtil.LISTE_MEMBRES.clear();
			while (result.next())
			{
			    String numTel = result.getString("NumeroTelephone");
			    String nom = result.getString("NomClient");
			    String email = result.getString("AdresseCourriel");
			    String adresseDomicile = result.getString("AdresseDomicile");
			    String carteCredit = result.getString("CarteDeCredit");
			    Boolean estMembre = result.getBoolean("EstMembre");
			    int codeSecret = result.getInt("CodeSecret");
			    
			    ListesUtil.LISTE_MEMBRES.add(new Membre(nom, email, adresseDomicile, numTel, estMembre, 
			    		carteCredit, codeSecret));
			}
			
			System.out.println("Liste des membres mise � jour");
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
	
	public String[][] getListeMembresData()
	{
		String data[][] = new String[ListesUtil.LISTE_MEMBRES.size()][7];
		
		for(int i = 0; i < ListesUtil.LISTE_MEMBRES.size(); i++)
		{
			Membre m = ListesUtil.LISTE_MEMBRES.get(i);
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
