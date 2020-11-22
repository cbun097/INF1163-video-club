package org.group2.finalproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.group2.finalproject.classes.Membre;
import org.group2.finalproject.controllers.MembreController;

//TODO: Ajouter client validation
@SuppressWarnings("serial")
public class ClientManagement extends JPanel 
{
	private JTable tblClient;
	private TableModel tblMdlClient;
	private MembreController controller;
	
	// Champs des dialog modals pour Ajouter/Modifier/Supprimer
	private JComboBox<String> modalCodeFieldSelect;
	private JTextField modalCodeField;
	private JTextField modalNomField;
	private JTextField modalEmailField;
	private JTextField modalAdresseField;
	private JTextField modalNumTel;
	private JTextField modalCarteCredit;
	private JCheckBox modalEstMembre = new JCheckBox();
	private JTextField modalCodeSecret;
	private JTextField modalMontantDu;

	/**
	 * Create the panel.
	 */
	public ClientManagement() 
	{
		setLayout(null);
		controller = new MembreController();

		controller.updateMembreListe();
		tblClient = new JTable();
		updateTableData();
		
		JScrollPane scrollPane = new JScrollPane(tblClient);
		scrollPane.setBounds(142, 11, 600, 400);
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
		

		modalCodeField = new JTextField(5);
		modalNomField = new JTextField(5);
		modalEmailField = new JTextField(5);
		modalAdresseField = new JTextField(5);
		modalNumTel = new JTextField(5);
		modalCarteCredit = new JTextField(5);
		modalEstMembre = new JCheckBox();
		modalCodeSecret = new JTextField(5);
		modalMontantDu = new JTextField(5);
		
		modalCodeFieldSelect = new JComboBox<>();
		modalCodeFieldSelect.addActionListener(e -> {
			if(modalCodeFieldSelect.getSelectedItem() != null)
			{
				Membre membre = controller.getListeMembres().stream()
				    	 .filter((me) -> modalCodeFieldSelect.getSelectedItem().equals(me.getCodeClient()))
				    	 .findAny()
				    	 .orElse(null);
					modalNomField.setText(membre.getNomClient());
					modalEmailField.setText(membre.getAdresseCourriel());
					modalAdresseField.setText(membre.getAdresseDomicile());
					modalNumTel.setText(membre.getNumeroTelephoneMaison());
					modalCarteCredit.setText(membre.getCarteDeCredit());
					modalEstMembre.setSelected(membre.getEstMembre());
					modalCodeSecret.setText(Integer.toString(membre.getCodeSecret()));
					modalMontantDu.setText(Double.toString(membre.getMontantDu()));
			}
		});
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
	    	double montandDuValeur = Double.parseDouble(modalMontantDu.getText());
	    	Membre membre = new Membre(modalCodeField.getText(), modalNomField.getText(), modalEmailField.getText(), modalAdresseField.getText(),
			modalNumTel.getText(),estMembreChecked, modalCarteCredit.getText() , codeSecretValeur, montandDuValeur);
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
			Membre selectMembre = controller.getListeMembres().stream()
			    	 .filter((me) -> modalCodeFieldSelect.getSelectedItem().equals(me.getCodeClient()))
			    	 .findAny()
			    	 .orElse(null);
		    selectMembre.setNomClient(modalNomField.getText());
		    selectMembre.setAdresseCourriel(modalEmailField.getText());
		    selectMembre.setAdresseDomicile(modalAdresseField.getText());
		    selectMembre.setNumeroTelephoneMaison(modalNumTel.getText());
		    selectMembre.setCarteDeCredit(modalCarteCredit.getText());
		    selectMembre.setEstMembre(modalEstMembre.isSelected());
		    selectMembre.setCodeSecret(Integer.parseInt(modalCodeSecret.getText()));
		    selectMembre.setMontantDu(Double.parseDouble(modalMontantDu.getText()));
	    	controller.modifierMembre(selectMembre);
	    	updateTableData();
	    }     
	}
	
	// Methode pour supprimer dialog membre
	private void supprimerDialogMembre() 
	{
		modalCodeFieldSelect.removeAllItems();// = new JComboBox<>();
	  
		for (Membre m : controller.getListeMembres()) 
			modalCodeFieldSelect.addItem(m.getCodeClient());

		if(modalCodeFieldSelect.getItemCount() > 0)
			modalCodeFieldSelect.setSelectedIndex(tblClient.getSelectedRow() >= 0 ? tblClient.getSelectedRow() : 0);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Code Membre:"));
		myPanel.add(modalCodeFieldSelect);
		myPanel.add(Box.createHorizontalStrut(15));
  	
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Entrer le code membre a supprimer", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			Membre selectMembre = controller.getListeMembres().stream()
			    	 .filter((me) -> modalCodeFieldSelect.getSelectedItem().equals(me.getCodeClient()))
			    	 .findAny()
			    	 .orElse(null);
			controller.supprimerMembre(selectMembre);
			updateTableData();
		}
	}
	
	private void updateTableData()
	{
		tblClient.setModel(new DefaultTableModel(controller.getListeMembresData(), new String[]{"Code Client","Nom","Courriel", 
				"Telephone","Adresse Domicile", "Carte de credit","Est Membre", "Code Secret", "Montant du"}));
	}
	
	public JPanel ClientJPanel(boolean codeTextField) 
	{
	    JPanel myPanel = new JPanel();

	    // Reset the fields
		modalNomField.setText("");
		modalEmailField.setText("");
		modalAdresseField.setText("");
		modalNumTel.setText("");
		modalCarteCredit.setText("");
		modalEstMembre = new JCheckBox();
		modalCodeSecret.setText("");
		modalMontantDu.setText("");
		
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Code:"));
	    if(codeTextField)
	    {
	    	modalCodeField.setText("");
	    	myPanel.add(modalCodeField);
	    }
	    else
	    {
			modalCodeFieldSelect.removeAllItems();
	    	myPanel.add(modalCodeFieldSelect);
	    	
	    	for (Membre m : controller.getListeMembres())
	    		modalCodeFieldSelect.addItem(m.getCodeClient());
		    
			if(modalCodeFieldSelect.getItemCount() > 0)
				modalCodeFieldSelect.setSelectedIndex(tblClient.getSelectedRow() >= 0 ? tblClient.getSelectedRow() : 0);
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
	    myPanel.add(new JLabel("Numero de telephone:"));
	    myPanel.add(modalNumTel);
	    
	    // Display??
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Carte de credit:"));
	    myPanel.add(modalCarteCredit);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Membre:"));
	    myPanel.add(modalEstMembre);
	    
	    // Display??
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Code secret:"));
	    myPanel.add(modalCodeSecret);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Montant Du:"));
	    myPanel.add(modalMontantDu);
	    
	    return myPanel;
	}
}
