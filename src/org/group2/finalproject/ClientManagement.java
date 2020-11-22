package org.group2.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.group2.finalproject.classes.Membre;
import org.group2.finalproject.controllers.MembreController;

public class ClientManagement extends JPanel {
	private JTable tblClient;
	private TableModel tblMdlClient;
	private MembreController controller;

	/**
	 * Create the panel.
	 */
	public ClientManagement() {
		setLayout(null);
		controller = new MembreController();

		controller.updateMembreListe();
		tblClient = new JTable();
		updateTableData();
		//tblClient.setBounds(142, 11, 298, 278);
		
		JScrollPane scrollPane = new JScrollPane(tblClient);
		scrollPane.setBounds(142, 11, 600, 400);
		add(scrollPane);
		//add(tblClient);
		
		JButton btnAdd = new JButton("Ajouter");
		btnAdd.setBounds(10, 40, 122, 23);
		add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ajouterDialogMembre();
		    }
		});
		
		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(10, 74, 122, 23);
		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				modifierDialogMembre();
			}
		});
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(10, 108, 122, 23);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimerDialogMembre();
			}
		});
	}
	
	// Methode pour Ajouter un membre
	private void ajouterDialogMembre() {
	  JTextField codeField = new JTextField(5);
	  JTextField nomField = new JTextField(5);
      JTextField emailField = new JTextField(5);
      JTextField adresseField = new JTextField(5);
      JTextField numTel = new JTextField(5);
      JTextField carteCredit = new JTextField(5);
      JCheckBox estMembre = new JCheckBox();
      JTextField codeSecret = new JTextField(5);
      JTextField montantDu = new JTextField(5);
      

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Code:"));
      myPanel.add(codeField);
      
   // TODO create this to a common method for client management
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Nom:"));
      myPanel.add(nomField);
      
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Adresse courriel:"));
      myPanel.add(emailField);
      
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  myPanel.add(new JLabel("Adresse domicile:"));
	  myPanel.add(adresseField);
	    
	  myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  myPanel.add(new JLabel("Numéro de téléphone:"));
	  myPanel.add(numTel);
	    
	  // Display??
	  myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  myPanel.add(new JLabel("Carte de crédit:"));
	  myPanel.add(carteCredit);
	    
	  myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  myPanel.add(new JLabel("Membre:"));
	  myPanel.add(estMembre);
	 
	  // Display??
	  myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  myPanel.add(new JLabel("Code secret:"));
	  myPanel.add(codeSecret);

	    
	  myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	  myPanel.add(new JLabel("Montant Du:"));
	  myPanel.add(montantDu);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Ajouter un nouveau membre", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
    	  Boolean estMembreChecked = estMembre.isSelected();
    	  int codeSecretValeur = Integer.parseInt(codeSecret.getText());
    	  double montandDuValeur = Double.parseDouble(montantDu.getText());
    	  Membre membre = new Membre(codeField.getText(), nomField.getText(), emailField.getText(), adresseField.getText(),
    			  numTel.getText(),estMembreChecked, carteCredit.getText() , codeSecretValeur, montandDuValeur);
         controller.ajouterMembre(membre);
         updateTableData();
      }     
   }
	
	private void modifierDialogMembre() 
	{
		JComboBox<String> codeField = new JComboBox<>();
		
		for (Membre m : controller.getListeMembres()) {
			codeField.addItem(m.getCodeClient());
		}
		  
		// TODO create this to a common method for client management
		JTextField nomField = new JTextField(5);
	    JTextField emailField = new JTextField(5);
	    JTextField adresseField = new JTextField(5);
	    JTextField numTel = new JTextField(5);
	    JTextField carteCredit = new JTextField(5);
	    JCheckBox estMembre = new JCheckBox();
	    JTextField codeSecret = new JTextField(5);
	    JTextField montantDu = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Code:"));
	    myPanel.add(codeField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom:"));
	    myPanel.add(nomField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(emailField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse domicile:"));
	    myPanel.add(adresseField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Numéro de téléphone:"));
	    myPanel.add(numTel);
	    
	    // Display??
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Carte de crédit:"));
	    myPanel.add(carteCredit);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Membre:"));
	    myPanel.add(estMembre);
	    Boolean estMembreChecked = estMembre.isSelected();
	    
	    // Display??
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Code secret:"));
	    myPanel.add(codeSecret);
	    // TODO Fix this 
	    int codeSecretValeur = Integer.parseInt(codeSecret.getText());
	    
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Montant Du:"));
	    myPanel.add(montantDu);
	    double montandDuValeur = Double.parseDouble(montantDu.getText());

		codeField.addActionListener(e -> {
			Membre membre = controller.getListeMembres().stream()
		    	 .filter((me) -> codeField.getSelectedItem().equals(me.getCodeClient()))
		    	 .findAny()
		    	 .orElse(null);
			nomField.setText(membre.getNomClient());
			emailField.setText(membre.getAdresseCourriel());
			adresseField.setText(membre.getAdresseDomicile());
			numTel.setText(membre.getNumeroTelephoneMaison());
			carteCredit.setText(membre.getCarteDeCredit());
			estMembre.setSelected(membre.getEstMembre());
			codeSecret.setText(Integer.toString(membre.getCodeSecret()));
			montantDu.setText(Double.toString(membre.getMontantDu()));
		});
	    
		if(codeField.getItemCount() > 0)
			codeField.setSelectedIndex(0);

	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	             "Modifier un membre", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {

			Membre selectMembre = controller.getListeMembres().stream()
			    	 .filter((me) -> codeField.getSelectedItem().equals(me.getCodeClient()))
			    	 .findAny()
			    	 .orElse(null);
		    selectMembre.setNomClient(nomField.getText());
		    selectMembre.setAdresseCourriel(emailField.getText());
		    selectMembre.setAdresseDomicile(adresseField.getText());
		    selectMembre.setNumeroTelephoneMaison(numTel.getText());
		    selectMembre.setCarteDeCredit(carteCredit.getText());
		    selectMembre.setEstMembre(estMembreChecked);
		    selectMembre.setCodeSecret(codeSecretValeur);
		    selectMembre.setMontantDu(montandDuValeur);
	    	controller.modifierMembre(selectMembre);
	    	updateTableData();
	    }     
	}
	
	// Methode pour supprimer dialog membre
	private void supprimerDialogMembre() {	  
	  JComboBox<String> codeField = new JComboBox<>();
	  for (Membre m : controller.getListeMembres()) {
		  codeField.addItem(m.getCodeClient());
		}
	  
	  if(codeField.getItemCount() > 0)
			codeField.setSelectedIndex(0);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Code Membre:"));
      myPanel.add(codeField);
      myPanel.add(Box.createHorizontalStrut(15));

  	codeField.addActionListener(e -> {
		Membre membre = controller.getListeMembres().stream()
	    	 .filter((me) -> codeField.getSelectedItem().equals(me.getCodeClient()))
	    	 .findAny()
	    	 .orElse(null);
	});
    
  	
      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Entrer le code membre à supprimer", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
    		Membre selectMembre = controller.getListeMembres().stream()
			    	 .filter((me) -> codeField.getSelectedItem().equals(me.getCodeClient()))
			    	 .findAny()
			    	 .orElse(null);
    	  controller.supprimerMembre(selectMembre);
         updateTableData();
      }
	}
	
	private void updateTableData()
	{
		tblClient.setModel(new DefaultTableModel(controller.getListeMembresData(), new String[]{"Code Client","Nom","Courriel", 
				"Telephone","Adresse Domicile", "Carte de crédit","est Membre", "Code Secret", "Montant du"}));
	}
	
	public JPanel ClientJPanel() {
		// common remove code client
		// 
		JTextField nomField = new JTextField(5);
	    JTextField emailField = new JTextField(5);
	    JTextField adresseField = new JTextField(5);
	    JTextField numTel = new JTextField(5);
	    JTextField carteCredit = new JTextField(5);
	    JTextField estMembr = new JTextField(5);
	    JTextField codeSecret = new JTextField(5);
	    JTextField montantDu = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom:"));
	    myPanel.add(nomField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(emailField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Numéro de téléphone:"));
	    myPanel.add(numTel);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(emailField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(emailField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(emailField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(emailField);
	    
	    return myPanel;
	}
}
