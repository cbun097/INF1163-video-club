package org.group2.finalproject.classes;

import java.util.Date;

public class DisqueLouer {
	// TODO ajouter id de la reservation
	String codeClient;
	String codeDisque;
	Date dateLouer;
	Date dateRetour; 
	Date dateDu;
	
	public DisqueLouer(String codeClient, String codeDisque, Date dateLouer, Date dateRetour, Date dateDu) {
		super();
		this.codeClient = codeClient;
		this.codeDisque = codeDisque;
		this.dateLouer = dateLouer;
		this.dateRetour = dateRetour;
		this.dateDu = dateDu;
	}
	
	public String getCodeClient() {
		return codeClient;
	}

	public void setCodeClient(String codeClient) {
		this.codeClient = codeClient;
	}
	
	public String getCodeDisque() {
		return codeDisque;
	}
	
	public void setCodeDisque(String codeDisque) {
		this.codeDisque = codeDisque;
	}
	
	public Date getDateLouer() {
		return dateLouer;
	}
	
	public void setDateLouer(Date dateLouer) {
		this.dateLouer = dateLouer;
	}
	
	public Date getDateRetour() {
		return dateRetour;
	}
	
	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	public Date getDateDu() {
		return dateDu;
	}
	
	public void setDateDu(Date dateDu) {
		this.dateDu = dateDu;
	} 

	@Override
	public String toString() {
		return "DisqueLouer [codeClient=" + codeClient + ", codeDisque=" + codeDisque + ", dateLouer=" + dateLouer
				+ ", dateRetour=" + dateRetour + ", dateDu=" + dateDu + "]";
	}

}
