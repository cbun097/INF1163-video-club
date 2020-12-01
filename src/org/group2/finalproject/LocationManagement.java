package org.group2.finalproject;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.group2.finalproject.controllers.LocationController;

public class LocationManagement extends JPanel {
	private JTable tblFilms;
	private LocationController controller;

	/**
	 * Create the panel.d
	 */
	public LocationManagement() {
		setLayout(null);
		controller = new LocationController();

		tblFilms = new JTable();
		//tblClient.setBounds(142, 11, 298, 278);
		
		JScrollPane scrollPane = new JScrollPane(tblFilms);
		scrollPane.setBounds(142, 11, 600, 400);
		add(scrollPane);
		//add(tblClient);
		
		JButton btnRent = new JButton("Louer");
		btnRent.setBounds(10, 40, 122, 23);
		add(btnRent);
		
		JButton btnReserve = new JButton("R\u00E9server");
		btnReserve.setBounds(10, 74, 122, 23);
		add(btnReserve);
		
		JButton btnReturn = new JButton("Retourner");
		btnReturn.setBounds(10, 108, 122, 23);
		add(btnReturn);
		
		JButton btnShowAll = new JButton("Afficher tout");
		btnShowAll.setBounds(10, 142, 122, 23);
		add(btnShowAll);
	}

}
