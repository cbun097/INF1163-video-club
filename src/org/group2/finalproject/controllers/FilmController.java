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
		String query = "INSERT INTO Film (CodeFilm, Nom, Genre, DateSortie, EstNouveau, Duree, Pays, Directeur,"
				+ "Scenariste, Scenario, QuantiteDVD, QuantiteBluRay, PrixVente, PrixSemaine, PrixJournee) VALUES (?, ?, ?,"
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, f.getCodeFilm());
			statement.setString(2, f.getNom());
			statement.setString(3, f.getGenre());
			// TODO String dans la BD changer pour DATE
			statement.setString(4, f.getDateSortie());
			statement.setBoolean(5, f.getEstNouveau());
			statement.setString(6, f.getDuree());
			statement.setString(7, f.getPays());
			statement.setString(8, f.getDirecteur());
			statement.setString(9, f.getScenariste());
			statement.setString(10, f.getScenario());
			statement.setInt(11, f.getQuantiteDVD());
			statement.setInt(12, f.getQuantiteBluray());
			statement.setDouble(13, f.getPrixVente());
			statement.setDouble(14, f.getPrixSemaine());
			statement.setDouble(15, f.getPrixJournee());
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
				+ "Directeur=?, Scenariste=?, Scenario=?, QuantiteDVD=?, QuantiteBluRay=?, PrixVente=?,"
				+ "PrixSemaine=?, PrixJournee=? WHERE CodeFilm=?";
		try {
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, f.getNom());
			statement.setString(2, f.getGenre());
			// TODO String dans la BD changer pour DATE
			statement.setString(3, f.getDateSortie());
			statement.setBoolean(4, f.getEstNouveau());
			statement.setString(5, f.getDuree());
			statement.setString(6, f.getPays());
			statement.setString(7, f.getDirecteur());
			statement.setString(8, f.getScenariste());
			statement.setString(9, f.getScenario());
			statement.setInt(10, f.getQuantiteDVD());
			statement.setInt(11, f.getQuantiteBluray());
			statement.setDouble(12, f.getPrixVente());
			statement.setDouble(13, f.getPrixSemaine());
			statement.setDouble(14, f.getPrixJournee());
			statement.setString(15, f.getCodeFilm());
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
			
			listeFilm.clear();
			
			while(result.next()) {
				String codeFilm = result.getString("CodeFilm");
				String nom = result.getString("Nom");
				String genre = result.getString("Genre");
				// TODO String dans la BD changer pour DATE
				String dateSorite = result.getString("DateSortie");
				Boolean estNouveau = result.getBoolean("EstNouveau");
				String duree  = result.getString("Duree");
				String pays = result.getString("Pays"); 
				String directeur = result.getString("Directeur");
				String scenariste = result.getString("Scenariste");
				String scenario = result.getString("Scenario"); 
				int quantiteDVD = result.getInt("QuantiteDVD"); 
				int quantiteBluRay = result.getInt("QuantiteBluRay");
				double prixVente = result.getDouble("PrixVente");
				double prixSemaine = result.getDouble("PrixSemaine");
				double prixJournee = result.getDouble("PrixJournee");
				
				listeFilm.add(new Film(codeFilm,nom,genre,dateSorite,estNouveau, duree, pays, directeur,scenario,
						scenariste, quantiteDVD, quantiteBluRay, prixVente, prixSemaine, prixJournee));
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
	
	public ArrayList<Film> getListeFilm() {
		return listeFilm;
	}
	
	public String[][]  getListeFilmsData() {
		String data[][] = new String[listeFilm.size()][15];
		
		for(int i = 0; i< listeFilm.size(); i++) {
			Film f = listeFilm.get(i);
			data[i][0] = f.getCodeFilm();
			data[i][1] = f.getNom();
			data[i][2] = f.getGenre();
			// TODO String dans la BD changer pour DATE
			data[i][3] = f.getDateSortie();
			data[i][4] = Boolean.toString(f.getEstNouveau());
			data[i][5] = f.getDuree();
			data[i][6] = f.getPays();
			data[i][7] = f.getDirecteur();
			data[i][8] = f.getScenariste();
			data[i][9] = f.getScenario();
			data[i][10] = Integer.toString(f.getQuantiteDVD());
			data[i][11] = Integer.toString(f.getQuantiteBluray());
			data[i][12] = Double.toString(f.getPrixVente());
			data[i][13] = Double.toString(f.getPrixSemaine());
			data[i][14] = Double.toString(f.getPrixJournee());
		}
		
		return data;
	}
}
