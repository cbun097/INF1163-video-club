package org.group2.finalproject;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTable;

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
		
		JButton btnEdit = new JButton("Modifier");
		btnEdit.setBounds(10, 74, 122, 23);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Supprimer");
		btnDelete.setBounds(10, 108, 122, 23);
		add(btnDelete);
	}
}
