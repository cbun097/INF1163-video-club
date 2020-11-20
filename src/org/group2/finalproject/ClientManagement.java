package org.group2.finalproject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
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
		        //your actions
		    	ajouterDialogMembre();
		    }
		});
		
		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(10, 74, 122, 23);
		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				modifierDialogMembre();
			}
		});
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(10, 108, 122, 23);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				supprimerDialogMembre();
			}
		});
	}
	
	// Methode pour Ajouter un membre
	// TODO: Ajouter les fields
	private void ajouterDialogMembre() {
		// TODO change
	  JTextField codeField = new JTextField(5);
	  JTextField nomField = new JTextField(5);
      JTextField emailField = new JTextField(5);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Code:"));
      myPanel.add(codeField);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Nom:"));
      myPanel.add(nomField);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("Adresse courriel:"));
      myPanel.add(emailField);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Ajouter un nouveau membre", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
    	  Membre membre = new Membre(codeField.getText(), nomField.getText(), emailField.getText());
         System.out.println("nom value: " + nomField.getText());
         System.out.println("email value: " + emailField.getText());
         // TODO Ajouter la methode ajouter membre du controlleur
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
		  
		JTextField nomField = new JTextField(5);
	    JTextField emailField = new JTextField(5);

	    JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Code:"));
	    myPanel.add(codeField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom:"));
	    myPanel.add(nomField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Adresse courriel:"));
	    myPanel.add(emailField);

		codeField.addActionListener(e -> {
			Membre membre = controller.getListeMembres().stream()
		    	 .filter((me) -> codeField.getSelectedItem().equals(me.getCodeClient()))
		    	 .findAny()
		    	 .orElse(null);
			nomField.setText(membre.getNomClient());
			emailField.setText(membre.getAdresseCourriel());
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
		    System.out.println("nom value: " + selectMembre.getNomClient());
		    System.out.println("email value: " + selectMembre.getAdresseCourriel());
		    // TODO Ajouter la methode modifier membre du controlleur
	    	controller.modifierMembre(selectMembre);
	    	updateTableData();
	    }     
	}
	
	// Methode pour supprimer dialog membre
	private void supprimerDialogMembre() {
	  JTextField membreCodeSupprimer = new JTextField(5);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Code Membre:"));
      myPanel.add(membreCodeSupprimer);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Entrer le code membre à supprimer", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
    	  // TODO Ajouter la methode supprimer membre du controlleur
    	  controller.supprimerMembre(membreCodeSupprimer.getText());
         System.out.println("code membre value to delete: " + membreCodeSupprimer.getText());
         updateTableData();
      }
	}
	
	private void updateTableData()
	{
		tblClient.setModel(new DefaultTableModel(controller.getListeMembresData(), new String[]{"Code Client","Nom","Telephone","Courriel"}));
	}
}
