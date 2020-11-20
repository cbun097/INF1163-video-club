package org.group2.finalproject.classes;

public class Membre {
	String codeClient;
	String nomClient;
	String adresseCourriel;
	String adresseDomicile;
	String numeroTelephoneMaison;
	Boolean estMembre; 
	String carteDeCredit;
	int codeSecret;
	double montantDu;
	
	// comment out to test
//	public Membre(String codeClient, String nomClient, String adresseCourriel, String adresseDomicile,
//			String numeroTelephoneMaison, Boolean estMembre, String carteDeCredit, int codeSecret, double montantDu) {
//		super();
//		this.codeClient = codeClient;
//		this.nomClient = nomClient;
//		this.adresseCourriel = adresseCourriel;
//		this.adresseDomicile = adresseDomicile;
//		this.numeroTelephoneMaison = numeroTelephoneMaison;
//		this.estMembre = estMembre;
//		this.carteDeCredit = carteDeCredit;
//		this.codeSecret = codeSecret;
//		this.montantDu = montantDu;
//	}
	
	public Membre(String nomClient, String adresseCourriel) {
		super();
		this.nomClient = nomClient;
		this.adresseCourriel = adresseCourriel;
	}

	public String getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresseCourriel() {
		return adresseCourriel;
	}

	public void setAdresseCourriel(String adresseCourriel) {
		this.adresseCourriel = adresseCourriel;
	}

	public String getAdresseDomicile() {
		return adresseDomicile;
	}

	public void setAdresseDomicile(String adresseDomicile) {
		this.adresseDomicile = adresseDomicile;
	}

	public String getNumeroTelephoneMaison() {
		return numeroTelephoneMaison;
	}

	public void setNumeroTelephoneMaison(String numeroTelephoneMaison) {
		this.numeroTelephoneMaison = numeroTelephoneMaison;
	}

	public Boolean getEstMembre() {
		return estMembre;
	}

	public void setEstMembre(Boolean estMembre) {
		this.estMembre = estMembre;
	}

	public String getCarteDeCredit() {
		return carteDeCredit;
	}

	public void setCarteDeCredit(String carteDeCredit) {
		this.carteDeCredit = carteDeCredit;
	}

	public int getCodeSecret() {
		return codeSecret;
	}

	public void setCodeSecret(int codeSecret) {
		this.codeSecret = codeSecret;
	}

	public double getMontantDu() {
		return montantDu;
	}

	public void setMontantDu(double montantDu) {
		this.montantDu = montantDu;
	}

	@Override
	public String toString() {
		return "Membre [codeClient=" + codeClient + ", nomClient=" + nomClient + ", adresseCourriel=" + adresseCourriel
				+ ", adresseDomicile=" + adresseDomicile + ", numeroTelephoneMaison=" + numeroTelephoneMaison
				+ ", estMembre=" + estMembre + ", carteDeCredit=" + carteDeCredit + ", codeSecret=" + codeSecret
				+ ", montantDu=" + montantDu + "]";
	}
	
	
}
