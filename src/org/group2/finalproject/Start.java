package org.group2.finalproject;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Start
{
	
	public static void main(String[] args)
	{
		// Create & show after loading resources
		JFrame f = new JFrame("Home");    
		f.setSize(800,800);

		ClientManagement client = new ClientManagement();
		FilmManagement film = new FilmManagement();
		LocationManagement location = new LocationManagement();
		ArticleVenteManagement article = new ArticleVenteManagement();
		
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Clients", client);
		tabbedPane.addTab("Films", film);
		tabbedPane.addTab("Article", article);
		tabbedPane.addTab("Locations", location);
        f.setContentPane(tabbedPane);
		f.setVisible(true);
		// Oops forgot to add that, now the process ends when you close the thing xD
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
