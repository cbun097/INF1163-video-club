package org.group2.finalproject.classes;

import java.sql.Date;

public class Location {
	
	String NumeroTelephone;
	String CodeDisque;
	Date DateLouer;
	Date DateRetour;
	Date DateDu;
	double MontantRetardDu;
	
	public Location(String numeroTelephone, String codeDisque, Date dateLouer, Date dateRetour, Date dateDu, double montantRetardDu) {
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

	public Date getDateLouer() {
		return DateLouer;
	}

	public void setDateLouer(Date dateLouer) {
		DateLouer = dateLouer;
	}
	
	public Date getDateRetour() {
		return DateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		DateRetour = dateRetour;
	}

	public Date getDateDu() {
		return DateDu;
	}

	public void setDateDu(Date dateDu) {
		DateDu = dateDu;
	}

	public double getMontantRetardDu() {
		return MontantRetardDu;
	}

	public void setMontantRetardDu(double montantRetardDu) {
		MontantRetardDu = montantRetardDu;
	}
}
