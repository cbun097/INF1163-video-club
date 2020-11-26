package org.group2.finalproject.classes;

public class Membre {
	String numeroTelephone;
	String nomClient;
	String adresseCourriel;
	String adresseDomicile;
	Boolean estMembre; 
	String carteDeCredit;
	int codeSecret;
	double montantDu;
	
	public Membre(String numeroTelephone, String nomClient, String adresseCourriel, String adresseDomicile,
			 Boolean estMembre, String carteDeCredit, int codeSecret, double montantDu) {
		super();
		this.numeroTelephone = numeroTelephone;
		this.nomClient = nomClient;
		this.adresseCourriel = adresseCourriel;
		this.adresseDomicile = adresseDomicile;
		this.estMembre = estMembre;
		this.carteDeCredit = carteDeCredit;
		this.codeSecret = codeSecret;
		this.montantDu = montantDu;
	}
	
	public String getNumeroTelephone() {
		return numeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		this.numeroTelephone = numeroTelephone;
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
		return "Membre [numeroTelephone=" + numeroTelephone + ", nomClient=" + nomClient + ", adresseCourriel=" + adresseCourriel
				+ ", adresseDomicile=" + adresseDomicile
				+ ", estMembre=" + estMembre + ", carteDeCredit=" + carteDeCredit + ", codeSecret=" + codeSecret
				+ ", montantDu=" + montantDu + "]";
	}
	
	
}
