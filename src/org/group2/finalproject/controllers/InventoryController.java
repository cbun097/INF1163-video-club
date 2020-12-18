package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.ListesUtil;
import org.group2.finalproject.classes.ArticleVente;
import org.group2.finalproject.classes.Disque;
import org.group2.finalproject.classes.Film;

public class InventoryController {
	
	// add new item to inventory
	public void ajouterArticle(ArticleVente article) {

		String query = "INSERT INTO ArticleVente (NomProduit, CodeProduit, QuantiteDisponible, Prix, DescriptionProduit) VALUES (?, ?, ?, ?, ?)";

		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setString(1, article.getNomProduit());
			statement.setString(2, article.getCodeProduit());
			statement.setInt(3, article.getQuantite());
			statement.setDouble(4, article.getPrix());
			statement.setString(5, article.getDescriptionProduit());
			statement.executeUpdate();
			System.out.println("Article ajoute avec succes!");
			
			// Ajouter dans la table disque (leurs ID seront toujours pareille dans les deux tables)
			if(article instanceof Disque)
			{
				query = "INSERT INTO Disque (CodeDisque, CodeFilm, TypeDeDisque, PrixLocationSemaine, PrixLocationJournee, AVendre) VALUES (?, ?, ?, ?, ?, ?)";
				statement = ConnexionDB.getConnexion().prepareStatement(query);	
				statement.setString(1, ((Disque)article).getFilm().getCodeFilm());
				statement.setString(2, ((Disque)article).getCodeProduit());
				statement.setString(3, ((Disque)article).getTypeDeDisque());
				statement.setDouble(4, ((Disque)article).getPrixLocationSemaine());
				statement.setDouble(5, ((Disque)article).getPrixLocationJournee());
				statement.setBoolean(6, ((Disque)article).getAVendre());
				statement.executeUpdate();
			}
			
			updateListeArticle("");
		}
		catch(SQLException e) 
		{
			System.out.println("Ajouter article erreur: " + e);
		}
		finally
		{
			ConnexionDB.closeConnection();
		}
	}
	
	// modify an existing item in the inventory
	public void modifierArticle(ArticleVente article) {
		String query = "UPDATE ArticleVente SET NomProduit=?, QuantiteDisponible=?, Prix=?, DescriptionProduit=? WHERE CodeProduit=?";
		
		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setString(1, article.getNomProduit());
			statement.setString(2, article.getCodeProduit());
			statement.setInt(3, article.getQuantite());
			statement.setDouble(4, article.getPrix());
			statement.setString(5, article.getDescriptionProduit());
			statement.executeUpdate();
			System.out.println("Article ajoute avec succes!");
			updateListeArticle("");
		}
		catch(SQLException e) 
		{
			System.out.println("Ajouter article erreur: " + e);
		}
		finally
		{
			ConnexionDB.closeConnection();
		}
	}
	
	// remove an item from the inventory
	public void supprimerArticle(ArticleVente article) {
		String query = "DELETE FROM ArticleVente WHERE CodeProduit=?";

		try 
		{
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, article.getCodeProduit());
			statement.executeUpdate();

			if(article instanceof Disque)
			{
				statement = ConnexionDB.getConnexion().prepareStatement("DELETE FROM Disque WHERE CodeDisque=?");
				statement.setString(1, article.getCodeProduit());
				statement.executeUpdate();
			}
				
			System.out.println("Membre supprimï¿½ avec succï¿½s!");
			updateListeArticle("");
		}
		catch(SQLException e)
		{
			System.out.println("Supprimer article erreur: " + e);
		}
		finally 
		{
			ConnexionDB.closeConnection();
		}
	}
	// adjust inventory quantity for a specific item
	public void modifierQuantite(ArticleVente article) {

		String query = "UPDATE ArticleVente (WHERE CodeProduit=?, QuantiteDisponible=?) ";


		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);	
			statement.setString(2, article.getCodeProduit());
			statement.setInt(3, article.getQuantite());
			statement.executeUpdate();
			System.out.println("Article ajoute avec succes!");
			updateListeArticle("");
		}
		catch(SQLException e) 
		{
			System.out.println("Ajouter article erreur: " + e);
		}
		finally
		{
			ConnexionDB.closeConnection();
		}
	}
	
	public void updateListeArticle(String para) {

		String query = "SELECT * FROM ArticleVente LEFT JOIN Disque ON ArticleVente.CodeProduit = Disque.CodeDisque";
		if (para!=null && !para.isEmpty()) 
			query += " where NomProduit like '%" + para + "%'";

		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			ListesUtil.LISTE_INVENTAIRE.clear();
			while (result.next())
			{
			    String nomProduit = result.getString("NomProduit");
			    String codeProduit = result.getString("CodeProduit");

			    int quantite = result.getInt("QuantiteDisponible");

			    double prix = result.getDouble("Prix");
			    String descriptionProduit = result.getString("DescriptionProduit");
			    
			    String codeDisque = result.getString("CodeDisque");
			    String codeFilm = result.getString("CodeFilm");
			    String typeDisque = result.getString("TypeDeDisque");
			    double prixJ = result.getDouble("PrixLocationJournee");
			    double prixS = result.getDouble("PrixLocationSemaine");
			    boolean vente = result.getBoolean("AVendre");
			    
			    if(codeDisque != null)
			    {
					Film f = ListesUtil.LISTE_FILMS.stream().filter(e -> e.getCodeFilm().equals(codeFilm)).findAny().orElse(null);
				    ListesUtil.LISTE_INVENTAIRE.add(new Disque(codeProduit, f, typeDisque, prixS, prixJ, vente, quantite, prix));
			    }
			    else
			    {
				    ListesUtil.LISTE_INVENTAIRE.add(new ArticleVente(nomProduit, codeProduit, quantite, prix, descriptionProduit));
			    }
			}
			
			System.out.println("Liste des articles mise a jour");
		}
		catch(SQLException e)
		{
			System.out.print("Afficher liste erreur: " + e);
		}
		finally 
		{
			ConnexionDB.closeConnection();
		}
	}

	// Données de la liste pour tableau
	public String[][] getListeArticleData()
	{
		String data[][] = new String[ListesUtil.LISTE_INVENTAIRE.size()][10];
		
		for(int i = 0; i < ListesUtil.LISTE_INVENTAIRE.size(); i++)
		{
			ArticleVente m = ListesUtil.LISTE_INVENTAIRE.get(i);
			data[i][0] = m.getNomProduit();
			data[i][1] = m.getCodeProduit();
			data[i][2] = String.valueOf(m.getQuantite());
			data[i][3] = String.valueOf(m.getPrix());
			data[i][4] = m.getDescriptionProduit();
			
			if(ListesUtil.LISTE_INVENTAIRE.get(i) instanceof Disque)
			{
				Disque d = (Disque) ListesUtil.LISTE_INVENTAIRE.get(i);

				if(d.getFilm() != null)
					data[i][5] = d.getFilm().getCodeFilm();
				data[i][6] = d.getTypeDeDisque();
				data[i][7] = String.valueOf(d.getPrixLocationSemaine());
				data[i][8] = String.valueOf(d.getPrixLocationJournee());
				data[i][9] = String.valueOf(d.getAVendre());
			}
		}
		
		return data;
	}
}
