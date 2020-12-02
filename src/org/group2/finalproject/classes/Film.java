package org.group2.finalproject.classes;

public class Film {
	
	String codeFilm;
	String nom; 
	String genre;
	// TODO String dans la BD changer pour DATE
	String dateSortie;
	Boolean estNouveau;
	String duree;
	String pays;
	String affiche; // did not display
	String directeur;
	String scenariste;
	String scenario;
	int quantiteDVD;
	int quantiteBluray;
	double prixVente;
	double prixSemaine;
	double prixJournee;
	
	// remove affiche
	public Film(String codeFilm, String nom, String genre, String dateSortie, Boolean estNouveau,
			String duree, String pays, String directeur, String scenariste, String scenario,
			int quantiteDVD, int quantiteBluray, double prixVente, double prixSemaine, double prixJournee) {
		super();
		this.codeFilm = codeFilm;
		this.nom = nom;
		this.genre = genre;
		this.dateSortie = dateSortie;
		this.estNouveau = estNouveau;
		this.duree = duree;
		this.pays = pays;
		this.directeur = directeur;
		this.scenariste = scenariste;
		this.scenario = scenario;
		this.quantiteDVD = quantiteDVD;
		this.quantiteBluray = quantiteBluray;
		this.prixVente = prixVente;
		this.prixSemaine = prixSemaine;
		this.prixJournee = prixJournee;
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

	public String getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(String dateSortie) {
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
	
	public String getDirecteur() {
		return directeur;
	}

	public void setDirecteur(String directeur) {
		this.directeur = directeur;
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
