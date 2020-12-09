package org.group2.finalproject.classes;

public class Panier {
	String CodePanier;
	double MontantTotal;
	ArticleVente Articles;
	
	public Panier(String codePanier, double montantTotal, ArticleVente articles) {
		super();
		CodePanier = codePanier;
		MontantTotal = montantTotal;
		Articles = articles;
	}

	public String getCodePanier() {
		return CodePanier;
	}

	public void setCodePanier(String codePanier) {
		CodePanier = codePanier;
	}

	public double getMontantTotal() {
		return MontantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		MontantTotal = montantTotal;
	}

	public ArticleVente getArticles() {
		return Articles;
	}

	public void setArticles(ArticleVente articles) {
		Articles = articles;
	}
}
