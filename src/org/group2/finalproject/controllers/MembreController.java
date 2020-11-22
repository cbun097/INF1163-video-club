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
		String query = "INSERT INTO Membres (CodeClient, NomClient, AdresseCourriel, NumeroTelephoneMaison, AdresseDomicile, "
				+ "CarteDeCredit, EstMembre, CodeSecret, MontantDu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try { 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setString(1, membre.getCodeClient());
			statement.setString(2, membre.getNomClient());
			statement.setString(3, membre.getAdresseCourriel());
			statement.setString(4, membre.getNumeroTelephoneMaison());
			statement.setString(5, membre.getAdresseDomicile());
			statement.setString(6, membre.getCarteDeCredit());
			statement.setBoolean(7, membre.getEstMembre());
			statement.setInt(8, membre.getCodeSecret());
			statement.setDouble(9, membre.getMontantDu());
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
		String query = "UPDATE Membres SET NomClient=?, AdresseCourriel=?, NumeroTelephoneMaison=?, AdresseDomicile=?,"
				+ "CarteDeCredit=?, EstMembre=?, CodeSecret=?, MontantDu=? WHERE CodeClient=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, membre.getNomClient());
			statement.setString(2, membre.getAdresseCourriel());
			statement.setString(3, membre.getNumeroTelephoneMaison());
			statement.setString(4, membre.getAdresseDomicile());
			statement.setString(5, membre.getCarteDeCredit());
			statement.setBoolean(6, membre.getEstMembre());
			statement.setInt(7, membre.getCodeSecret());
			statement.setDouble(8, membre.getMontantDu());
			statement.setString(9, membre.getCodeClient());
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
	public void supprimerMembre(Membre membre) {
		String query = "DELETE FROM Membres WHERE CodeClient=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, membre.getCodeClient());
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
			    String adresseDomicile = result.getString("AdresseDomicile");
			    String numTel = result.getString("NumeroTelephoneMaison");
			    String carteCredit = result.getString("CarteDeCredit");
			    Boolean estMembre = result.getBoolean("EstMembre");
			    int codeSecret = result.getInt("CodeSecret");
			    double montant = result.getDouble("MontantDu");
			    
			    listeMembres.add(new Membre(code, nom, email, adresseDomicile, numTel, estMembre, 
			    		carteCredit, codeSecret, montant));
			 
			    String output = "User #%d: %s - %s - %s - %s - %s - %s - %s - %s";
			    System.out.println(String.format(output, ++count, nom, email, adresseDomicile, numTel,
			    		carteCredit,estMembre, codeSecret, montant));
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
		String data[][] = new String[listeMembres.size()][9];
		
		for(int i = 0; i < listeMembres.size(); i++)
		{
			Membre m = listeMembres.get(i);
			data[i][0] = m.getCodeClient();
			data[i][1] = m.getNomClient();
			data[i][2] = m.getAdresseCourriel();
			data[i][3] = m.getNumeroTelephoneMaison();
			data[i][4] = m.getAdresseDomicile();
			data[i][5] = m.getCarteDeCredit();
			data[i][6] = Boolean.toString(m.getEstMembre());
			data[i][7] = Integer.toString(m.getCodeSecret());
			data[i][8] = Double.toString(m.getMontantDu());
		}
		
		return data;
	}
}
