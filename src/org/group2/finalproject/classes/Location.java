package org.group2.finalproject.classes;

public class Location {
	
	String NumeroTelephone;
	String CodeDisque;
	String DateLouer;
	String DateRetour;
	String DateDu;
	double MontantRetardDu;
	
	public Location(String numeroTelephone, String codeDisque, String dateLouer, String dateRetour, String dateDu, double montantRetardDu) {
		super();
		NumeroTelephone = numeroTelephone;
		CodeDisque = codeDisque;
		DateLouer = dateLouer;
		DateRetour = dateRetour;
		DateDu = dateDu;
		MontantRetardDu = montantRetardDu;
	}

	public String getNumeroTelephone() {
		return NumeroTelephone;
	}

	public void setNumeroTelephone(String numeroTelephone) {
		NumeroTelephone = numeroTelephone;
	}

	public String getCodeDisque() {
		return CodeDisque;
	}

	public void setCodeDisque(String codeDisque) {
		CodeDisque = codeDisque;
	}

	public String getDateLouer() {
		return DateLouer;
	}

	public void setDateLouer(String dateLouer) {
		DateLouer = dateLouer;
	}
	
	public String getDateRetour() {
		return DateRetour;
	}

	public void setDateRetour(String dateRetour) {
		DateRetour = dateRetour;
	}

	public String getDateDu() {
		return DateDu;
	}

	public void setDateDu(String dateDu) {
		DateDu = dateDu;
	}

	public double getMontantRetardDu() {
		return MontantRetardDu;
	}

	public void setMontantRetardDu(double montantRetardDu) {
		MontantRetardDu = montantRetardDu;
	}
}
