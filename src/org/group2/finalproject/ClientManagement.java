package org.group2.finalproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.group2.finalproject.classes.Membre;
import org.group2.finalproject.controllers.MembreController;

//TODO: Ajouter client validation
@SuppressWarnings("serial")
public class ClientManagement extends JPanel 
{
	private JTable tblClient;
	private MembreController controller;
	
	// Champs des dialog modals pour Ajouter/Modifier/Supprimer
	private JComboBox<String> modalTelFieldSelect;
	private JTextField modalNumTel;
	private JTextField modalNomField;
	private JTextField modalEmailField;
	private JTextField modalAdresseField;
	private JTextField modalCarteCredit;
	private JCheckBox modalEstMembre;
	private JTextField modalCodeSecret;
	private JTextField search;
	/**
	 * Create the panel.
	 */
	public ClientManagement() 
	{
		setLayout(null);
		controller = new MembreController();

		controller.updateMembreListe("");
		tblClient = new JTable();
		updateTableModel();
		
		JScrollPane scrollPane = new JScrollPane(tblClient);
		scrollPane.setBounds(142, 50, 600, 400);
		add(scrollPane);
		
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setBounds(10, 40, 122, 23);
		add(btnAdd);
		btnAdd.addActionListener(e -> ajouterDialogMembre());
		
		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(10, 74, 122, 23);
		add(btnEdit);
		btnEdit.addActionListener(e -> modifierDialogMembre());
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(10, 108, 122, 23);
		add(btnDelete);
		btnDelete.addActionListener(e -> supprimerDialogMembre());

		modalNumTel = new JTextField(5);
		modalNomField = new JTextField(5);
		modalEmailField = new JTextField(5);
		modalAdresseField = new JTextField(5);
		modalCarteCredit = new JTextField(5);
		modalEstMembre = new JCheckBox();
		modalCodeSecret = new JTextField(5);
		
		modalTelFieldSelect = new JComboBox<>();
		modalTelFieldSelect.addActionListener(e -> {
			if(modalTelFieldSelect.getSelectedItem() != null)
			{
				Membre membre = ListesUtil.LISTE_MEMBRES.stream()
				    	 .filter((me) -> modalTelFieldSelect.getSelectedItem().equals(me.getNumeroTelephone()))
				    	 .findAny()
				    	 .orElse(null);
					modalNomField.setText(membre.getNomClient());
					modalEmailField.setText(membre.getAdresseCourriel());
					modalAdresseField.setText(membre.getAdresseDomicile());
					modalCarteCredit.setText(membre.getCarteDeCredit());
					modalEstMembre.setSelected(membre.getEstMembre());
					modalCodeSecret.setText(Integer.toString(membre.getCodeSecret()));
			}
		});
		
		// search to find film in db
				search = new JTextField("");
				search.setBounds(142, 11, 450, 25);
				add(search);
				
				JButton btnSearch = new JButton("recherche");
				btnSearch.setBounds(600, 12, 122, 25);
				add(btnSearch);
				btnSearch.addActionListener(e -> rechercheDialogMembre());
				
	}
	
	private void rechercheDialogMembre() {
		updateTableData();
	}
	
	// Methode pour Ajouter un membre
	private void ajouterDialogMembre() 
	{
		JPanel myPanel = ClientJPanel(true);
      
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Ajouter un nouveau membre", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) 
	    {
	    	Boolean estMembreChecked = modalEstMembre.isSelected();
	    	int codeSecretValeur = Integer.parseInt(modalCodeSecret.getText());
	    	Membre membre = new Membre(modalNumTel.getText(),modalNomField.getText(), modalEmailField.getText(), modalAdresseField.getText(),
			estMembreChecked, modalCarteCredit.getText() , codeSecretValeur);
	    	controller.ajouterMembre(membre);
	    	updateTableData();
	    }     
   }
	
	private void modifierDialogMembre() 
	{
	    JPanel myPanel = ClientJPanel(false);

	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Modifier un membre", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) 
	    {
			Membre selectMembre = ListesUtil.LISTE_MEMBRES.stream()
			    	 .filter((me) -> modalTelFieldSelect.getSelectedItem().equals(me.getNumeroTelephone()))
			    	 .findAny()
			    	 .orElse(null);
		    selectMembre.setNomClient(modalNomField.getText());
		    selectMembre.setAdresseCourriel(modalEmailField.getText());
		    selectMembre.setAdresseDomicile(modalAdresseField.getText());
		    selectMembre.setCarteDeCredit(modalCarteCredit.getText());
		    selectMembre.setEstMembre(modalEstMembre.isSelected());
		    selectMembre.setCodeSecret(Integer.parseInt(modalCodeSecret.getText()));
	    	controller.modifierMembre(selectMembre);
	    	updateTableData();
	    }     
	}
	
	// Methode pour supprimer dialog membre
	private void supprimerDialogMembre() 
	{
		modalTelFieldSelect.removeAllItems();// = new JComboBox<>();
	  
		for (Membre m : ListesUtil.LISTE_MEMBRES) 
			modalTelFieldSelect.addItem(m.getNumeroTelephone());

		if(modalTelFieldSelect.getItemCount() > 0)
			modalTelFieldSelect.setSelectedIndex(tblClient.getSelectedRow() >= 0 ? tblClient.getSelectedRow() : 0);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Code Membre:"));
		myPanel.add(modalTelFieldSelect);
		myPanel.add(Box.createHorizontalStrut(15));
  	
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Entrer le code membre a supprimer", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			Membre selectMembre = ListesUtil.LISTE_MEMBRES.stream()
			    	 .filter((me) -> modalTelFieldSelect.getSelectedItem().equals(me.getNumeroTelephone()))
			    	 .findAny()
			    	 .orElse(null);
			controller.supprimerMembre(selectMembre);
			updateTableData();
		}
	}
	
	private void updateTableModel()
	{
		tblClient.setModel(new DefaultTableModel(controller.getListeMembresData(), new String[]{"Telephone", "Nom","Courriel", 
				"Adresse Domicile", "Carte de credit","Est Membre", "Code Secret"}));
	}
	
	private void updateTableData()
	{
		String item = search.getText();
		controller.updateMembreListe(item);
		
		updateTableModel();
	}
	
	public JPanel ClientJPanel(boolean telTextField) 
	{
	    JPanel myPanel = new JPanel();

	    // Reset the fields
		modalNumTel.setText("");
		modalNomField.setText("");
		modalEmailField.setText("");
		modalAdresseField.setText("");
		modalCarteCredit.setText("");
		modalEstMembre = new JCheckBox();
		modalCodeSecret.setText("");
		
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Numero de telephone:"));
	    if(telTextField)
	    {
	    	modalNumTel.setText("");
	    	myPanel.add(modalNumTel);
	    }
	    else
	    {
	    	modalTelFieldSelect.removeAllItems();
	    	myPanel.add(modalTelFieldSelect);
	    	
	    	for (Membre m : ListesUtil.LISTE_MEMBRES)
	    		modalTelFieldSelect.addItem(m.getNumeroTelephone());
		    
			if(modalTelFieldSelect.getItemCount() > 0)
				modalTelFieldSelect.setSelectedIndex(tblClient.getSelectedRow() >= 0 ? tblClient.getSelectedRow() : 0);
	    }
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom:"));
	    myPanel.add(modalNomField);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(modalEmailField);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse domicile:"));
	    myPanel.add(modalAdresseField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Carte de credit:"));
	    myPanel.add(modalCarteCredit);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Membre:"));
	    myPanel.add(modalEstMembre);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Code secret:"));
	    myPanel.add(modalCodeSecret);
	    
	    return myPanel;
	}
}
