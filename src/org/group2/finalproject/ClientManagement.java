package org.group2.finalproject;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.group2.finalproject.controllers.MembreController;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientManagement extends JPanel {
	private JTable tblClient;
	private MembreController controller;

	/**
	 * Create the panel.
	 */
	public ClientManagement() {
		setLayout(null);
		controller = new MembreController();

		// TEMP before BD
		String[][] data = controller.getJsonListTemp();
		String[] column={"Code Client","Nom","Telephone","Courriel"};
		
		tblClient = new JTable(data, column);
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
		    	AjouterDialogMembre();
		    }
		});
		
		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(10, 74, 122, 23);
		add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ModifierDialogMembre();
			}
		});
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(10, 108, 122, 23);
		add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SupprimerDialogMembre();
			}
		});
	}
	
	// Methode pour Ajouter un membre
	// TODO: Ajouter les fields
	private void AjouterDialogMembre() {
		// TODO change
	  JTextField xField = new JTextField(5);
      JTextField yField = new JTextField(5);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("x:"));
      myPanel.add(xField);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      myPanel.add(new JLabel("y:"));
      myPanel.add(yField);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Ajouter un nouveau membre", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
         System.out.println("x value: " + xField.getText());
         System.out.println("y value: " + yField.getText());
         // TODO Ajouter la methode ajouter membre du controlleur
         // controller.ajouterMembre();
      }     
   }
	
	private void ModifierDialogMembre() {
		// TODO ajouter la methode pour modifier un membre du controlleur
		// controller.modifierMembre();
			
	}
	
	// Methode pour supprimer dialog membre
	private void SupprimerDialogMembre() {
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
      }
	}
}
