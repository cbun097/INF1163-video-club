package org.group2.finalproject.controllers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.group2.finalproject.ConnexionDB;
import org.group2.finalproject.classes.ArticleVente;

public class InventoryController {
	
	private ArrayList<ArticleVente> listeArticle = new ArrayList<>(); 
	
	// add new item to inventory
	public void ajouterArticle(ArticleVente article) {
		//TODO add rest
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
			System.out.println("Article ajout� avec succ�s!");
			updateListeArticle();
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
		//TODO add rest
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
			System.out.println("Article ajout� avec succ�s!");
			updateListeArticle();
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
		//TODO add rest
		String query = "DELETE FROM ArticleVente WHERE CodeProduit=?";
		try 
		{
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			statement.setString(1, article.getCodeProduit());
			statement.executeUpdate();
			System.out.println("Membre supprim� avec succ�s!");
			updateListeArticle();
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
			System.out.println("Article ajout� avec succ�s!");
			updateListeArticle();
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
	public void updateListeArticle() {
		String query = "SELECT * FROM ArticleVente";
		try
		{ 
			ConnexionDB.initConnexion();
			PreparedStatement statement = ConnexionDB.getConnexion().prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			listeArticle.clear();
			while (result.next())
			{
			    String nomProduit = result.getString("NomProduit");
			    String codeProduit = result.getString("CodeProduit");
			    int quantite = result.getInt("QuantiteDisponible");
			    double prix = result.getDouble("Prix");
			    String descriptionProduit = result.getString("DescriptionProduit");
			    
			    listeArticle.add(new ArticleVente(nomProduit, codeProduit, quantite, prix, descriptionProduit));
			}
			
			System.out.println("Liste des articles mise � jour");
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
	public ArrayList<ArticleVente> getListeArticle()
	{
		return listeArticle;
	}
	
	public String[][] getListeArticleData()
	{
		String data[][] = new String[listeArticle.size()][5];
		
		for(int i = 0; i < listeArticle.size(); i++)
		{
			ArticleVente m = listeArticle.get(i);
			data[i][0] = m.getNomProduit();
			data[i][1] = m.getCodeProduit();
			data[i][2] = String.valueOf(m.getQuantite());
			data[i][3] = String.valueOf(m.getPrix());
			data[i][4] = m.getDescriptionProduit();
		}
		
		return data;
	}
	
}
