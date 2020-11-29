package org.group2.finalproject.classes;

import java.util.Date;

public class Film {
	
	String codeFilm;
	String nom; 
	String genre;
	Date dateSortie;
	Boolean estNouveau;
	String duree;
	String pays;
	String affiche;
	String directeur;
	String scenariste;
	String scenario;
	int quantiteDVD;
	int quantiteBluray;
	double prixVente;
	double prixSemaine;
	double prixJournee;
	// manque les acteurs
	
	
	/**public Film(String codeFilm, String nom, String genre, Date dateSortie, String desription, Boolean estNouveau,
			String duree, String pays, String affiche, String director, String scenariste, String scenario,
			int quantiteDVD, int quantiteBluray, double prixVente, double prixSemaine, double prixJournee) {
		super();
		this.codeFilm = codeFilm;
		this.nom = nom;
		this.genre = genre;
		this.dateSortie = dateSortie;
		this.desription = desription;
		this.estNouveau = estNouveau;
		this.duree = duree;
		this.pays = pays;
		this.affiche = affiche;
		this.director = director;
		this.scenariste = scenariste;
		this.scenario = scenario;
		this.quantiteDVD = quantiteDVD;
		this.quantiteBluray = quantiteBluray;
		this.prixVente = prixVente;
		this.prixSemaine = prixSemaine;
		this.prixJournee = prixJournee;
	}*/
	
	// new contructor for testing purposes only
	public Film(String codeFilm, String nom, String duree) {
		this.codeFilm = codeFilm;
		this.nom = nom;
		this.duree = duree;
	}

	public String getCodeFilm() {
		return codeFilm;
	}

	public void setCodeFilm(String codeFilm) {
		this.codeFilm = codeFilm;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Boolean getEstNouveau() {
		return estNouveau;
	}

	public void setEstNouveau(Boolean estNouveau) {
		this.estNouveau = estNouveau;
	}

	public String getDuree() {
		return duree;
	}

	public void setDuree(String duree) {
		this.duree = duree;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getAffiche() {
		return affiche;
	}

	public void setAffiche(String affiche) {
		this.affiche = affiche;
	}

	public String getScenariste() {
		return scenariste;
	}

	public void setScenariste(String scenariste) {
		this.scenariste = scenariste;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}

	public int getQuantiteDVD() {
		return quantiteDVD;
	}

	public void setQuantiteDVD(int quantiteDVD) {
		this.quantiteDVD = quantiteDVD;
	}

	public int getQuantiteBluray() {
		return quantiteBluray;
	}

	public void setQuantiteBluray(int quantiteBluray) {
		this.quantiteBluray = quantiteBluray;
	}

	public double getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(double prixVente) {
		this.prixVente = prixVente;
	}

	public double getPrixSemaine() {
		return prixSemaine;
	}

	public void setPrixSemaine(double prixSemaine) {
		this.prixSemaine = prixSemaine;
	}

	public double getPrixJournee() {
		return prixJournee;
	}

	public void setPrixJournee(double prixJournee) {
		this.prixJournee = prixJournee;
	}

	@Override
	public String toString() {
		return "Film [codeFilm=" + codeFilm + ", nom=" + nom + ", genre=" + genre + ", dateSortie=" + dateSortie
				+ ", estNouveau=" + estNouveau + ", duree=" + duree + ", pays=" + pays + ", affiche=" + affiche
				+ ", directeur=" + directeur + ", scenariste=" + scenariste + ", scenario=" + scenario
				+ ", quantiteDVD=" + quantiteDVD + ", quantiteBluray=" + quantiteBluray + ", prixVente=" + prixVente
				+ ", prixSemaine=" + prixSemaine + ", prixJournee=" + prixJournee + "]";
	}
	
}
