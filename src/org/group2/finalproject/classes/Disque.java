package org.group2.finalproject.classes;

public class Disque extends ArticleVente{
	
	Film film;
	String TypeDeDisque;
	double PrixLocationSemaine;
	double PrixLocationJournee;
	boolean AVendre;
	
	public Disque(String codeDisque, Film film, String typeDeDisque, double prixLocationSemaine, double prixLocationJournee,
			boolean aVendre, int qte, double prixVente) {
		super(film != null ? film.Nom : "Disque vide???", codeDisque, qte, prixVente, String.format("Disque du film %s", film != null ? film.CodeFilm : "INVALID"));
		this.film = film;
		TypeDeDisque = typeDeDisque;
		PrixLocationSemaine = prixLocationSemaine;
		PrixLocationJournee = prixLocationJournee;
		AVendre = aVendre;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String getTypeDeDisque() {
		return TypeDeDisque;
	}

	public void setTypeDeDisque(String typeDeDisque) {
		TypeDeDisque = typeDeDisque;
	}

	public double getPrixLocationSemaine() {
		return PrixLocationSemaine;
	}

	public void setPrixLocationSemaine(double prixLocationSemaine) {
		PrixLocationSemaine = prixLocationSemaine;
	}

	public double getPrixLocationJournee() {
		return PrixLocationJournee;
	}

	public void setPrixLocationJournee(double prixLocationJournee) {
		PrixLocationJournee = prixLocationJournee;
	}

	public boolean getAVendre() {
		return AVendre;
	}

	public void setAVendre(boolean aVendre) {
		AVendre = aVendre;
	}
	
	
}
