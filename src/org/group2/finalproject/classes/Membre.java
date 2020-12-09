package org.group2.finalproject.classes;

public class Membre {
	String NomClient;
	String AdresseCourriel;
	String AdresseDomicile;
	String NumeroTelephone;
	Boolean EstMembre;
	String CarteDeCredit;
	int CodeSecret;
	
	public Membre(String nomClient, String adresseCourriel, String adresseDomicile, String numeroTelephone,
			Boolean estMembre, String carteDeCredit, int codeSecret) {
		super();
		NomClient = nomClient;
		AdresseCourriel = adresseCourriel;
		AdresseDomicile = adresseDomicile;
		NumeroTelephone = numeroTelephone;
		EstMembre = estMembre;
		CarteDeCredit = carteDeCredit;
		CodeSecret = codeSecret;
	}

	public String getNomClient() {
		return NomClient;
	}

	public void setNomClient(String nomClient) {
		NomClient = nomClient;
	}

	public String getAdresseCourriel() {
		return AdresseCourriel;
	}

	public void setAdresseCourriel(String adresseCourriel) {
		AdresseCourriel = adresseCourriel;
	}

	public String getAdresseDomicile() {
		return AdresseDomicile;
	}

	public void setAdresseDomicile(String adresseDomicile) {
		AdresseDomicile = adresseDomicile;
	}

	public String getNumeroTelephone() {
		return NumeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		NumeroTelephone = numeroTelephone;
	}

	public Boolean getEstMembre() {
		return EstMembre;
	}

	public void setEstMembre(Boolean estMembre) {
		EstMembre = estMembre;
	}

	public String getCarteDeCredit() {
		return CarteDeCredit;
	}

	public void setCarteDeCredit(String carteDeCredit) {
		CarteDeCredit = carteDeCredit;
	}

	public int getCodeSecret() {
		return CodeSecret;
	}

	public void setCodeSecret(int codeSecret) {
		CodeSecret = codeSecret;
	}
}
