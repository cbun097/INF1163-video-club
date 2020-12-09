package org.group2.finalproject;

import javax.swing.JPanel;
import javax.swing.JTable;

import org.group2.finalproject.classes.ArticleVente;
import org.group2.finalproject.controllers.ArticlesVenteController;
import org.group2.finalproject.controllers.MembreController;

//TODO: Ajouter client validation
@SuppressWarnings("serial")
public class ArticleVenteManagement extends JPanel {
	private JTable tblArticle;
	private ArticlesVenteController controller;

	public ArticleVenteManagement() {
		setLayout(null);
		controller = new ArticlesVenteController();

		tblArticle = new JTable();
	}
}
