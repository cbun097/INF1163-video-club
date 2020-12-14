package org.group2.finalproject.classes;

public class ArticleVente {
<<<<<<< HEAD
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
=======
	
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
		return "InventaireDeVente [nomProduit=" + nomProduit + ", codeProduit=" + codeProduit + ", quantite=" + quantite + ", prix=" + prix + ", descriptionProduit=" + descriptionProduit + "]";
>>>>>>> connection front to back pour inventory ui
	}
}
