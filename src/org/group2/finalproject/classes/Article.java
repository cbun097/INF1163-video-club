package org.group2.finalproject.classes;

public class Article {
	String codeProduit;
	String nomProduit;
	double prix;
	
	public Article(String codeProduit, String nomProduit, double prix) {
		super();
		this.codeProduit = codeProduit;
		this.nomProduit = nomProduit;
		this.prix = prix;
	}
	
	public String getCodeProduit() {
		return codeProduit;
	}
	
	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}
	
	public String getNomProduit() {
		return nomProduit;
	}
	
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}
	
	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Article [codeProduit=" + codeProduit + ", nomProduit=" + nomProduit + ", prix=" + prix + "]";
	}
}
