package org.group2.finalproject.classes;

public class ArticleVente {

	
	String nomProduit;
	String codeProduit;
	int quantite;
	double prix;
	String descriptionProduit;
	
	public ArticleVente(String nomProduit, String codeProduit, int quantite, double prix, String descriptionProduit) {
		super();
		
		this.nomProduit = nomProduit;
		this.codeProduit = codeProduit;
		this.quantite = quantite;
		this.prix = prix;
		this.descriptionProduit = descriptionProduit;
	}
	public String getNomProduit() {
		return nomProduit;
	}
	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public String getCodeProduit() {
		return codeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		this.codeProduit = codeProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public String getDescriptionProduit() {
		return descriptionProduit;
	}
	
	public void setDescriptionProduit(String descriptionProduit) {
		this.descriptionProduit = descriptionProduit;
	}

	@Override
	public String toString() {

		return "ArticleVente [nomProduit=" + nomProduit + ", codeProduit=" + codeProduit + ", quantite=" + quantite + ", prix=" + prix + ", descriptionProduit=" + descriptionProduit + "]";

	}
}
