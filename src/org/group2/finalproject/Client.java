package org.group2.finalproject;

public class Client {
	String nom;
	String prenom;
	String courriel;
	String numeroTel; 
	String adresse;
	Number numCarteCredit;
	
	public Client(String nom, String prenom, String courriel,String numeroTel, String adresse, Number numCarteCredit) {
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.numeroTel = numeroTel;
		this.adresse = adresse;
		this.numCarteCredit = numCarteCredit;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Number getNumCarteCredit() {
		return numCarteCredit;
	}

	public void setNumCarteCredit(Number numCarteCredit) {
		this.numCarteCredit = numCarteCredit;
	}
	

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", courriel=" + courriel + ", numeroTel=" + numeroTel + ", adresse=" + adresse + ", numCarteCredit=" + numCarteCredit
				+ "]";
	}
}
