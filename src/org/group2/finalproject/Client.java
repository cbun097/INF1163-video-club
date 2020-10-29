package org.group2.finalproject;

public class Client {
	String nom;
	String prenom;
	String courriel;
	String numeroTel; 
	
	public Client(String nom, String prenom, String courriel,String numeroTel) {
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.numeroTel = numeroTel;
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

	@Override
	public String toString() {
		return "Client [nom=" + nom + ", prenom=" + prenom + ", courriel=" + courriel + ", numeroTel=" + numeroTel
				+ "]";
	}
}
