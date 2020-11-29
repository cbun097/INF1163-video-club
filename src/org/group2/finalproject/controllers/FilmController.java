package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.classes.Film;

public class FilmController {
	
	private ArrayList<Film> listeFilm = new ArrayList<>();

	// Ajouter un nouveau film
	public void ajouterFilm(Film f) {
		// TODO Ajouter le reste
		String query = "INSERT INTO Film (CodeFilm, Nom, Durée) VALUES (?, ?, ?)";
		
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, f.getCodeFilm());
			statement.setString(2, f.getNom());
			statement.setString(3, f.getDuree());
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
	// TODO fix this - not working
	public void modifierFilm(Film f) {
		// TODO Ajouter le reste
		String query = "UPDATE Film SET Nom=?, Durée=? WHERE CodeFilm=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, f.getNom());
			statement.setString(2, f.getDuree());
			statement.setString(3, f.getCodeFilm());
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
			
			listeFilm.clear();
			
			while(result.next()) {
				String codeFilm = result.getString("CodeFilm");
				String nom = result.getString("Nom");
				String duree  = result.getString("Durée");
				
				listeFilm.add(new Film(codeFilm, nom, duree));
			}
		}
		catch(SQLException e){
			System.out.println("Afficher la liste des films erreur: " + e);
		}
		finally {
			ConnexionDB.closeConnection();
		}
		
	}
	
	public ArrayList<Film> getListeFilm() {
		return listeFilm;
	}
	
	public String[][]  getListeFilmsData() {
		String data[][] = new String[listeFilm.size()][3];
		
		// TODO add the rest
		for(int i = 0; i< listeFilm.size(); i++) {
			Film f = listeFilm.get(i);
			data[i][0] = f.getCodeFilm();
			data[i][1] = f.getNom();
			data[i][2] = f.getDuree();
		}
		
		return data;
	}
}
