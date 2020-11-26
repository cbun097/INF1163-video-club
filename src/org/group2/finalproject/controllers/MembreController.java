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
		String query = "INSERT INTO Membres (NumeroTelephone, NomClient, AdresseCourriel, AdresseDomicile, "
				+ "CarteDeCredit, CodeSecret, EstMembre, MontantDu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try { 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setInt(1, Integer.parseInt(membre.getNumeroTelephone()));
			statement.setString(2, membre.getNomClient());
			statement.setString(3, membre.getAdresseCourriel());
			statement.setString(4, membre.getAdresseDomicile());
			statement.setString(5, membre.getCarteDeCredit());
			statement.setBoolean(6, membre.getEstMembre());
			statement.setInt(7, membre.getCodeSecret());
			statement.setDouble(8, membre.getMontantDu());
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
		String query = "UPDATE Membres SET NomClient=?, AdresseCourriel=?, AdresseDomicile=?,"
				+ "CarteDeCredit=?, EstMembre=?, CodeSecret=?, MontantDu=? WHERE NumeroTelephone=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, membre.getNomClient());
			statement.setString(2, membre.getAdresseCourriel());
			statement.setString(3, membre.getAdresseDomicile());
			statement.setString(4, membre.getCarteDeCredit());
			statement.setBoolean(5, membre.getEstMembre());
			statement.setInt(6, membre.getCodeSecret());
			statement.setDouble(7, membre.getMontantDu());
			statement.setString(8, membre.getNumeroTelephone());
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
		String query = "DELETE FROM Membres WHERE NumeroTelephone=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, membre.getNumeroTelephone());
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
			    String numTel = result.getString("NumeroTelephone");
			    String nom = result.getString("NomClient");
			    String email = result.getString("AdresseCourriel");
			    String adresseDomicile = result.getString("AdresseDomicile");
			    String carteCredit = result.getString("CarteDeCredit");
			    Boolean estMembre = result.getBoolean("EstMembre");
			    int codeSecret = result.getInt("CodeSecret");
			    double montant = result.getDouble("MontantDu");
			    
			    listeMembres.add(new Membre(numTel, nom, email, adresseDomicile, estMembre, 
			    		carteCredit, codeSecret, montant));
			 
			    String output = "User #%d: %s - %s - %s - %s - %s - %s - %s";
			    System.out.println(String.format(output, ++count, numTel, nom, email, adresseDomicile,
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
		String data[][] = new String[listeMembres.size()][8];
		
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
			data[i][7] = Double.toString(m.getMontantDu());
		}
		
		return data;
	}
}
