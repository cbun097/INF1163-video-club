package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.ListesUtil;
import org.group2.finalproject.classes.Film;

public class FilmController {

	// Ajouter un nouveau film
	public void ajouterFilm(Film f) {
		String query = "INSERT INTO Film (CodeFilm, Nom, Genre, DateSortie, EstNouveau, Duree, Pays, Directeur,"
				+ "Scenario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, f.getCodeFilm());
			statement.setString(2, f.getNom());
			statement.setString(3, f.getGenre());
			statement.setString(4, f.getDateSortie());
			statement.setBoolean(5, f.getEstNouveau());
			statement.setString(6, f.getDuree());
			statement.setString(7, f.getPays());
			statement.setString(8, f.getDirecteur());
			statement.setString(9, f.getScenario());
			statement.executeUpdate();
			updateFilmsListe();
		}
		catch(SQLException e) {
			System.out.println("Ajouter film erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
	}
	
	// Modifier un film
	public void modifierFilm(Film f) {
		String query = "UPDATE Film SET Nom=?,Genre=?, DateSortie=?, EstNouveau=?, Duree=?, Pays=?,"
				+ "Directeur=?, Scenario=? WHERE CodeFilm=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, f.getNom());
			statement.setString(2, f.getGenre());
			statement.setString(3, f.getDateSortie());
			statement.setBoolean(4, f.getEstNouveau());
			statement.setString(5, f.getDuree());
			statement.setString(6, f.getPays());
			statement.setString(7, f.getDirecteur());
			statement.setString(8, f.getScenario());
			statement.setString(9, f.getCodeFilm());
			System.out.println("Film modifie avec succes!");
			statement.executeUpdate();
			updateFilmsListe();
		}
		catch(SQLException e) {
			System.out.println("Ajouter film erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
	}
	
	
	// Supprimer un film 
	public void supprimerFilm(Film f) {
		String query = "DELETE FROM Film WHERE CodeFilm=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, f.getCodeFilm());
			statement.executeUpdate();
			updateFilmsListe();
		}
		catch(SQLException e) {
			System.out.println("Ajouter film erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
		
	}
	
	// Afficher la liste complete
	public void updateFilmsListe() {
		String query = "SELECT * FROM Film";
		
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			ListesUtil.LISTE_FILMS.clear();
			
			while(result.next()) {
				String codeFilm = result.getString("CodeFilm");
				String nom = result.getString("Nom");
				String genre = result.getString("Genre");
				String dateSorite = result.getString("DateSortie");
				Boolean estNouveau = result.getBoolean("EstNouveau");
				String duree  = result.getString("Duree");
				String pays = result.getString("Pays"); 
				String directeur = result.getString("Directeur");
				String scenario = result.getString("Scenario");
				
				ListesUtil.LISTE_FILMS.add(new Film(codeFilm,nom,genre,dateSorite,estNouveau, duree, pays, directeur, scenario));
			}
			System.out.println("Liste des films mise a jour");
		}
		catch(SQLException e){
			System.out.println("Afficher la liste des films erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
		
	}
	
	// Données de la liste pour tableau
	public String[][]  getListeFilmsData() {
		String data[][] = new String[ListesUtil.LISTE_FILMS.size()][9];
		
		for(int i = 0; i< ListesUtil.LISTE_FILMS.size(); i++) {
			Film f = ListesUtil.LISTE_FILMS.get(i);
			data[i][0] = f.getCodeFilm();
			data[i][1] = f.getNom();
			data[i][2] = f.getGenre();
			data[i][3] = f.getDateSortie();
			data[i][4] = Boolean.toString(f.getEstNouveau());
			data[i][5] = f.getDuree();
			data[i][6] = f.getPays();
			data[i][7] = f.getDirecteur();
			data[i][8] = f.getScenario();
		}
		
		return data;
	}
}
