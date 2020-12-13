package org.group2.finalproject.classes;

public class Disque {
	
	Film CodeFilm;
	String TypeDeDisque;
	double PrixLocationSemaine;
	double PrixLocationJournee;
	Boolean AVendre;
	
	public Disque(Film CodeFilm, String typeDeDisque, double prixLocationSemaine, double prixLocationJournee,
			Boolean aVendre) {
		super();
		this.CodeFilm = CodeFilm;
		TypeDeDisque = typeDeDisque;
		PrixLocationSemaine = prixLocationSemaine;
		PrixLocationJournee = prixLocationJournee;
		AVendre = aVendre;
	}

	public Film getCodeFilm() {
		return CodeFilm;
	}

	public void setCodeFilm(Film CodeFilm) {
		this.CodeFilm = CodeFilm;
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

	public Boolean getAVendre() {
		return AVendre;
	}

	public void setAVendre(Boolean aVendre) {
		AVendre = aVendre;
	}
	
	
}
