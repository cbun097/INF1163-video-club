package org.group2.finalproject;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.group2.finalproject.classes.Film;
import org.group2.finalproject.controllers.FilmController;

//TODO: Ajouter client validation
@SuppressWarnings("serial")
public class FilmManagement extends JPanel {
	private JTable tblFilms;
	private FilmController controller;
	
	// Champs des dialogs modals pour Ajouter/Modifier/Supprimer
	private JComboBox<String> modalCodeFieldSelect;
	private JTextField modalCodeFilmField;
	private JTextField modalNomField;
	private JTextField modalDateSortie;
	private JComboBox<String> modalGenreField;
	private JCheckBox modalEstNouveauField;
	private JTextField modalDureeField;
	private JTextField modalPaysField;
	private JTextField modalDirecteurField;
	private JTextField modalScenaristeField;
	private JTextArea modalScenarioField;
	private JTextField modalQuantiteDVDField;
	private JTextField modalQuantiteBlurayField;
	private JTextField modalPrixVenteField;
	private JTextField modalPrixSemaineField;
	private JTextField modalPrixJourneeField;

	/**
	 * Create the panel.
	 */
	public FilmManagement() {
		setLayout(null);
		controller = new FilmController();
		
		controller.updateFilmsListe();
		tblFilms = new JTable();
		updateTableFilms();
		//tblClient.setBounds(142, 11, 298, 278);
		
		JScrollPane scrollPane = new JScrollPane(tblFilms);
		scrollPane.setBounds(142, 11, 600, 400);
		add(scrollPane);
		
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setBounds(10, 40, 122, 23);
		add(btnAdd);
		btnAdd.addActionListener(e -> ajouterDialogFilm());
		
		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(10, 74, 122, 23);
		add(btnEdit);
		btnEdit.addActionListener(e -> modifierDialogFilm());
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(10, 108, 122, 23);
		add(btnDelete);
		btnDelete.addActionListener(e -> supprimerDialogFilm());
		
		JButton btnShowAll = new JButton("Afficher tout");
		btnShowAll.setBounds(10, 142, 122, 23);
		add(btnShowAll);
		btnShowAll.addActionListener(e -> affichierListeFilm());
		
		modalCodeFilmField = new JTextField(5);
		modalGenreField = new JComboBox<>();
		// TODO mettre dans une methode
		// Ajouter les types de genre pour les films
		modalGenreField.addItem("Action");
		modalGenreField.addItem("Comedie");
		modalDateSortie = new JTextField(5);
		modalEstNouveauField = new JCheckBox();
		modalNomField = new JTextField(5);
		modalDureeField = new JTextField(5);
		modalPaysField = new JTextField(5);
		modalDirecteurField = new JTextField(5);
		modalScenaristeField = new JTextField(5);
		modalScenarioField = new JTextArea(10,5);
		modalQuantiteDVDField = new JTextField(5); 
		modalQuantiteBlurayField = new JTextField(5);
		modalPrixVenteField = new JTextField(5); 
		modalPrixSemaineField = new JTextField(5);
		modalPrixJourneeField = new JTextField(5); 
		
		
		modalCodeFieldSelect = new JComboBox<>();
		modalCodeFieldSelect.addActionListener(e -> {
			if(modalCodeFieldSelect.getSelectedItem() != null)
			{
				Film film = controller.getListeFilm().stream()
				    	 .filter((me) -> modalCodeFieldSelect.getSelectedItem().equals(me.getCodeFilm()))
				    	 .findAny()
				    	 .orElse(null);
					modalNomField.setText(film.getNom());
					modalDureeField.setText(film.getDuree());
			}
		});
	}
	
	public void ajouterDialogFilm() {
		
		JPanel myPanel = FilmsPanel();
	      
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Ajouter un nouveau membre", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) 
	    {
	    	int quantiteDVDValue = Integer.parseInt(modalQuantiteDVDField.getText());
	    	int quantiteBlurayValue = Integer.parseInt(modalQuantiteBlurayField.getText());
	    	double prixVenteValue = Double.parseDouble(modalPrixVenteField.getText());
	    	double prixSemaineValue = Double.parseDouble(modalPrixSemaineField.getText());
	    	double prixJourneeValue = Double.parseDouble(modalPrixJourneeField.getText());
	    	// TODO add the rest
	    	// PAS Affiche
	    	Film film = new Film(modalCodeFilmField.getText(),modalNomField.getText(),
	    			modalGenreField.getSelectedItem().toString(), modalDateSortie.getText(), modalEstNouveauField.isSelected(),
	    			modalDureeField.getText(), modalPaysField.getText(), modalDirecteurField.getText(),
	    			modalScenaristeField.getText(), modalScenarioField.getText(),quantiteDVDValue, quantiteBlurayValue,
	    			 prixVenteValue, prixSemaineValue, prixJourneeValue);
	    	controller.ajouterFilm(film);
	    	updateTableFilms();
	    }     

	}
	
	public void modifierDialogFilm() {
		JPanel myPanel = FilmsPanel();

	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Modifier un membre", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) 
	    {
			Film filmSelected = controller.getListeFilm().stream()
			    	 .filter((me) -> modalCodeFieldSelect.getSelectedItem().equals(me.getCodeFilm()))
			    	 .findAny()
			    	 .orElse(null);
			filmSelected.setNom(modalNomField.getText());
			filmSelected.setGenre(modalGenreField.getName());
			filmSelected.setEstNouveau(modalEstNouveauField.isSelected());
			filmSelected.setDuree(modalDureeField.getText());
			filmSelected.setPays(modalPaysField.getText());
			filmSelected.setDirecteur(modalDirecteurField.getText());
			filmSelected.setScenariste(modalScenaristeField.getText());
			filmSelected.setScenario(modalScenarioField.getText());
			filmSelected.setQuantiteDVD(Integer.parseInt(modalQuantiteDVDField.getText()));
			filmSelected.setQuantiteBluray(Integer.parseInt(modalQuantiteBlurayField.getText()));
			filmSelected.setPrixVente(Double.parseDouble(modalPrixVenteField.getText()));
			filmSelected.setPrixSemaine(Double.parseDouble(modalPrixSemaineField.getText()));
			filmSelected.setPrixJournee(Double.parseDouble(modalPrixJourneeField.getText()));
	    	controller.modifierFilm(filmSelected);
	    	updateTableFilms();
	    }     
	}
	
	public void supprimerDialogFilm() {
		modalCodeFieldSelect.removeAllItems();
		  
		for (Film f : controller.getListeFilm()) 
			modalCodeFieldSelect.addItem(f.getCodeFilm());

		if(modalCodeFieldSelect.getItemCount() > 0)
			modalCodeFieldSelect.setSelectedIndex(tblFilms.getSelectedRow() >= 0 ? tblFilms.getSelectedRow() : 0);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Code Film:"));
		myPanel.add(modalCodeFieldSelect);
		myPanel.add(Box.createHorizontalStrut(15));
  	
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Entrer le code du film a supprimer", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			Film filmSelected = controller.getListeFilm().stream()
			    	 .filter((me) -> modalCodeFieldSelect.getSelectedItem().equals(me.getCodeFilm()))
			    	 .findAny()
			    	 .orElse(null);
			controller.supprimerFilm(filmSelected);
			updateTableFilms();
		}
	}
	
	public void affichierListeFilm() {
		tblFilms.setModel(new DefaultTableModel(controller.getListeFilmsData(), new String[]
				{"Code Film", "Nom", "Genre", "Date de sortie", "Est nouveau", "Durée", "Pays", "Directeur", "Scenario"
						, "Scenario", "QuantiteDVD", "QuantiteBluRay", "Prix Vente", "Prix Semaine", "Prix Journe"}));
	}
	
	private void updateTableFilms() {
		tblFilms.setModel(new DefaultTableModel(controller.getListeFilmsData(), new String[]
				{"Code Film", "Nom", "Genre", "Date de sortie", "Est nouveau", "Durée", "Pays", "Directeur", "Scenario"
						, "Scenario", "QuantiteDVD", "QuantiteBluRay", "Prix Vente", "Prix Semaine", "Prix Journe"}));
	}
	
	public JPanel FilmsPanel() {
		JPanel myPanel = new JPanel();
		// TODO Fix size of the dialog box
		//myPanel.setSize(new Dimension(500,800));
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    
		    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("CodeFilm:"));
	    myPanel.add(modalCodeFilmField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom:"));
	    myPanel.add(modalNomField);
	    
	    // TODO add combo box with populate values
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Genre:"));
	    myPanel.add(modalGenreField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Date de sortie:"));
	    myPanel.add(modalDateSortie);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("est nouveau:"));
	    myPanel.add(modalEstNouveauField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Duree:"));
	    myPanel.add(modalDureeField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Pays:"));
	    myPanel.add(modalPaysField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Directeur:"));
	    myPanel.add(modalDirecteurField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Senariste:"));
	    myPanel.add(modalScenaristeField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Scenario:"));
	    myPanel.add(modalScenarioField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Quantité DVD:"));
	    myPanel.add(modalQuantiteDVDField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Quantité Bluray:"));
	    myPanel.add(modalQuantiteBlurayField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Prix vente:"));
	    myPanel.add(modalPrixVenteField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Prix semaine:"));
	    myPanel.add(modalPrixSemaineField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Prix journée:"));
	    myPanel.add(modalPrixJourneeField);
	    
	    return myPanel;
	}

}
