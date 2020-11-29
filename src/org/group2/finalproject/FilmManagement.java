package org.group2.finalproject;

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
	private JTextField modalDureeField;

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
		//add(tblClient);
		
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
		
		// TODO add the rest
		modalCodeFilmField = new JTextField(5);
		modalNomField = new JTextField(5);
		modalDureeField = new JTextField(5);
		
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
	    	Film film = new Film(modalCodeFilmField.getText(),modalNomField.getText(), modalDureeField.getText());
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
			filmSelected.setDuree(modalDureeField.getText());
	    	controller.modifierFilm(filmSelected);
	    	updateTableFilms();
	    }     
	}
	
	public void supprimerDialogFilm() {
		modalCodeFieldSelect.removeAllItems();// = new JComboBox<>();
		  
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
		
	}
	
	private void updateTableFilms() {
		tblFilms.setModel(new DefaultTableModel(controller.getListeFilmsData(), new String[] {"Code Film", "Nom", "Duree"}));
	}
	
	public JPanel FilmsPanel() {
		System.out.print("inside panel creation");
		JPanel myPanel = new JPanel();
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("CodeFilm:"));
	    myPanel.add(modalCodeFilmField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom:"));
	    myPanel.add(modalNomField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Duree:"));
	    myPanel.add(modalDureeField);
	    
	    return myPanel;
	}

}
