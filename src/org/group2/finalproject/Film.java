package org.group2.finalproject;

public class Film {
	String nom; 
	String anneeSortie;
	String categorie;
	String acteursListe;
	
	public Film(String nom, String anneeSortie, String categorie, String acteursListe) {
		super();
		this.nom = nom;
		this.anneeSortie = anneeSortie;
		this.categorie = categorie;
		this.acteursListe = acteursListe;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAnneeSortie() {
		return anneeSortie;
	}

	public void setAnneeSortie(String anneeSortie) {
		this.anneeSortie = anneeSortie;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getActeursListe() {
		return acteursListe;
	}

	public void setActeursListe(String acteursListe) {
		this.acteursListe = acteursListe;
	}

	@Override
	public String toString() {
		return "Film [nom=" + nom + ", anneeSortie=" + anneeSortie + ", categorie=" + categorie + ", acteursListe="
				+ acteursListe + "]";
	}

}
