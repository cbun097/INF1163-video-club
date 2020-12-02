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
		btnShowAll.addActionListener(e -> updateTableFilms());
		
		modalCodeFilmField = new JTextField(5);
		modalGenreField = new JComboBox<>();
		modalGenreField.addItem("Action");
		modalGenreField.addItem("Aventure");
		modalGenreField.addItem("Comédie");
		modalGenreField.addItem("Crime");
		modalGenreField.addItem("Drama");
		modalGenreField.addItem("Horreur");
		modalGenreField.addItem("Science Fiction");
		modalGenreField.addItem("Romantique");
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
					// TODO Add methode to get the index
					//modalGenreField.setSelectedIndex(film.getGenre());
					modalDateSortie.setText(film.getDateSortie());
					modalDureeField.setText(film.getDuree());
					modalPaysField.setText(film.getPays());
					modalDirecteurField.setText(film.getDirecteur());
					modalScenaristeField.setText(film.getScenariste());
					modalScenarioField.setText(film.getScenario());
					modalQuantiteDVDField.setText(Integer.toString(film.getQuantiteDVD()));
					modalQuantiteBlurayField.setText(Integer.toString(film.getQuantiteBluray()));
					modalPrixVenteField.setText(Double.toString(film.getPrixVente()));
					modalPrixSemaineField.setText(Double.toString(film.getPrixSemaine()));
					modalPrixVenteField.setText(Double.toString(film.getPrixVente()));
			}
		});
	}
	
	public void ajouterDialogFilm() {
		
		JScrollPane scrollPane = new JScrollPane(FilmsPanel(true)); 
		scrollPane.setPreferredSize(new Dimension(300,450));
		int result = JOptionPane.showConfirmDialog(null,scrollPane, "Ajouter un nouveau film", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) 
	    {
	    	int quantiteDVDValue = Integer.parseInt(modalQuantiteDVDField.getText());
	    	int quantiteBlurayValue = Integer.parseInt(modalQuantiteBlurayField.getText());
	    	double prixVenteValue = Double.parseDouble(modalPrixVenteField.getText());
	    	double prixSemaineValue = Double.parseDouble(modalPrixSemaineField.getText());
	    	double prixJourneeValue = Double.parseDouble(modalPrixJourneeField.getText());
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
		JScrollPane scrollPane = new JScrollPane(FilmsPanel(false)); 
		scrollPane.setPreferredSize(new Dimension(300,450));
		
	    int result = JOptionPane.showConfirmDialog(null, scrollPane, "Modifier un film", JOptionPane.OK_CANCEL_OPTION);
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
		
	private void updateTableFilms() {
		tblFilms.setModel(new DefaultTableModel(controller.getListeFilmsData(), new String[]
				{"Code Film", "Nom", "Genre", "Date de sortie", "Est nouveau", "Durée", "Pays", "Directeur", "Scenario"
						, "Scenario", "QuantiteDVD", "QuantiteBluRay", "Prix Vente", "Prix Semaine", "Prix Journée"}));
	}
	
	public JPanel FilmsPanel(boolean telTextField) {
		JPanel myPanel = new JPanel();
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("CodeFilm:"));
	    if(telTextField)
	    {
	    	modalCodeFilmField.setText("");
	    	myPanel.add(modalCodeFilmField);
	    }
	    else
	    {
	    	modalCodeFieldSelect.removeAllItems();
	    	myPanel.add(modalCodeFieldSelect);
	    	
	    	for (Film f : controller.getListeFilm())
	    		modalCodeFieldSelect.addItem(f.getCodeFilm());
		    
			if(modalCodeFieldSelect.getItemCount() > 0)
				modalCodeFieldSelect.setSelectedIndex(tblFilms.getSelectedRow() >= 0 ? tblFilms.getSelectedRow() : 0);
	    }
	    
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom:"));
	    myPanel.add(modalNomField);
	    
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
