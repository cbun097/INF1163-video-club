package org.group2.finalproject.classes;

public class InventaireDeVente {
	
	String codeProduit;
	int quantite;
	
	public InventaireDeVente(String codeProduit, int quantite) {
		super();
		this.codeProduit = codeProduit;
		this.quantite = quantite;
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

	@Override
	public String toString() {
		return "InventaireDeVente [codeProduit=" + codeProduit + ", quantite=" + quantite + "]";
	}
}
