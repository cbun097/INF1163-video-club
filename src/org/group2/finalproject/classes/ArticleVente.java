package org.group2.finalproject.classes;

public class ArticleVente {
	String CodeProduit;
	double Prix;
	int QuantiteDisponible;
	String DescriptionProduit;
	
	public ArticleVente(String codeProduit, double prix, int quantiteDisponible, String descriptionProduit) {
		super();
		CodeProduit = codeProduit;
		Prix = prix;
		QuantiteDisponible = quantiteDisponible;
		DescriptionProduit = descriptionProduit;
	}

	public String getCodeProduit() {
		return CodeProduit;
	}

	public void setCodeProduit(String codeProduit) {
		CodeProduit = codeProduit;
	}

	public double getPrix() {
		return Prix;
	}

	public void setPrix(double prix) {
		Prix = prix;
	}

	public int getQuantiteDisponible() {
		return QuantiteDisponible;
	}

	public void setQuantiteDisponible(int quantiteDisponible) {
		QuantiteDisponible = quantiteDisponible;
	}

	public String getDescriptionProduit() {
		return DescriptionProduit;
	}

	public void setDescriptionProduit(String descriptionProduit) {
		DescriptionProduit = descriptionProduit;
	}
}
