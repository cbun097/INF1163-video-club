package org.group2.finalproject.classes;

public class Film {
	
	String CodeFilm;
	String Nom; 
	String Genre;
	String DateSortie;
	Boolean EstNouveau;
	String Duree;
	String Pays;
	String Directeur;
	String Scenario;
	
	public Film(String codeFilm, String nom, String genre, String dateSortie, Boolean estNouveau, String duree,
			String pays, String directeur, String scenario) {
		super();
		CodeFilm = codeFilm;
		Nom = nom;
		Genre = genre;
		DateSortie = dateSortie;
		EstNouveau = estNouveau;
		Duree = duree;
		Pays = pays;
		Directeur = directeur;
		Scenario = scenario;
	}

	public String getCodeFilm() {
		return CodeFilm;
	}

	public void setCodeFilm(String codeFilm) {
		CodeFilm = codeFilm;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getGenre() {
		return Genre;
	}

	public void setGenre(String genre) {
		Genre = genre;
	}

	public String getDateSortie() {
		return DateSortie;
	}

	public void setDateSortie(String dateSortie) {
		DateSortie = dateSortie;
	}

	public Boolean getEstNouveau() {
		return EstNouveau;
	}

	public void setEstNouveau(Boolean estNouveau) {
		EstNouveau = estNouveau;
	}

	public String getDuree() {
		return Duree;
	}

	public void setDuree(String duree) {
		Duree = duree;
	}

	public String getPays() {
		return Pays;
	}

	public void setPays(String pays) {
		Pays = pays;
	}

	public String getDirecteur() {
		return Directeur;
	}

	public void setDirecteur(String directeur) {
		Directeur = directeur;
	}

	public String getScenario() {
		return Scenario;
	}

	public void setScenario(String scenario) {
		Scenario = scenario;
	}
}
