package org.group2.finalproject.controllers;

public class FilmController {

	// Ajouter un nouveau film
	public void ajouterFilm() {
		// TODO Ajouter le reste
		String query = "INSERT INTO Films (Nom, Duree) VALUES (?, ?)";
	}
	
	// Modifier un film
	public void modifierFilm(String CodeFilm) {
		// TODO Ajouter le reste
		String query = "UPDATE Users SET Nom=?, Duree=? WHERE CodeFilm=?";
		
	}
	
	
	// Supprimer un film 
	public void supprimerFilm(String CodeFilm) {
		String query = "DELETE FROM Films WHERE CodeFilm=?";
		
	}
	
	// Afficher la liste complete
	public void afficherFilmListe() {
		String query = "SELECT * FROM Films";
		
	}
}
