package org.group2.finalproject;


import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


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
	private JTextArea modalScenarioField;

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
		scrollPane.setBounds(142, 50, 600, 400);
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
		modalGenreField.addItem("Comedie");
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
		modalScenarioField = new JTextArea(10,5);
		
		
		modalCodeFieldSelect = new JComboBox<>();
		modalCodeFieldSelect.addActionListener(e -> {
			if(modalCodeFieldSelect.getSelectedItem() != null)
			{
				Film film = controller.getListeFilm().stream()
				    	 .filter((me) -> modalCodeFieldSelect.getSelectedItem().equals(me.getCodeFilm()))
				    	 .findAny()
				    	 .orElse(null);
					modalNomField.setText(film.getNom());
					modalGenreField.setSelectedItem(film.getGenre());
					modalDateSortie.setText(film.getDateSortie());
					modalDureeField.setText(film.getDuree());
					modalPaysField.setText(film.getPays());
					modalDirecteurField.setText(film.getDirecteur());
					modalScenarioField.setText(film.getScenario());
			}
		});
	}
	
	public void ajouterDialogFilm() {
		
		JScrollPane scrollPane = new JScrollPane(FilmsPanel(true)); 
		scrollPane.setPreferredSize(new Dimension(300,450));
		int result = JOptionPane.showConfirmDialog(null,scrollPane, "Ajouter un nouveau film", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) 
	    {
	    	// TODO: Fix date format
	    	Film film = new Film(modalCodeFilmField.getText(),modalNomField.getText(),
	    			modalGenreField.getSelectedItem().toString(), modalDateSortie.getText(), modalEstNouveauField.isSelected(),
	    			modalDureeField.getText(), modalPaysField.getText(), modalDirecteurField.getText(),
	    			modalScenarioField.getText());
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
			filmSelected.setGenre((String) modalGenreField.getSelectedItem());
			filmSelected.setDateSortie(modalDateSortie.getText());
			filmSelected.setEstNouveau(modalEstNouveauField.isSelected());
			filmSelected.setDuree(modalDureeField.getText());
			filmSelected.setPays(modalPaysField.getText());
			filmSelected.setDirecteur(modalDirecteurField.getText());
			filmSelected.setScenario(modalScenarioField.getText());
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
				{"Code Film", "Nom", "Genre", "Date de sortie", "Est nouveau", "Duree", "Pays", "Directeur", "Scenario"}));
	}
	
	public JPanel FilmsPanel(boolean textField) {
		JPanel myPanel = new JPanel();
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    
	    modalNomField.setText("");
	    modalGenreField.setSelectedIndex(0);
		modalDateSortie.setText("");
		modalDureeField.setText("");
		modalPaysField.setText("");
		modalDirecteurField.setText("");
		modalScenarioField.setText("");
		    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("CodeFilm:"));
	    if(textField)
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
	    myPanel.add(new JLabel("Scenario:"));
	    myPanel.add(modalScenarioField);
	    
	 // search to find film in db
 		JTextField search = new JTextField("recherche film");
 		search.setBounds(142, 11, 450, 25);
 		add(search);
 		
 		JButton btnSearch = new JButton("recherche");
 		btnSearch.setBounds(600, 12, 122, 25);
 		add(btnSearch);
	    
	    return myPanel;

	}

}
