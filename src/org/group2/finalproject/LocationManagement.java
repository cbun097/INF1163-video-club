package org.group2.finalproject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.group2.finalproject.classes.Location;
import org.group2.finalproject.controllers.LocationController;

//TODO: Ajouter client validation
@SuppressWarnings("serial")
public class LocationManagement extends JPanel {
	private JTable tblFilms;
	private LocationController controller;
	private JComboBox<String> modalCodeFieldSelect, modalClientFieldSelect;
	private JTextField modalCodeDisque, modalNumeroTel, modalRetardMontantDu;
	private JTextField modalDateLouerField, modalDateRetourField, modalDateDuField;
	
	/**
	 * Create the panel.
	 */
	public LocationManagement() {
		setLayout(null);
		controller = new LocationController();

		tblFilms = new JTable();
		//tblClient.setBounds(142, 11, 298, 278);
		
		modalCodeDisque = new JTextField(5);
		modalNumeroTel = new JTextField(5);
		modalDateLouerField = new JTextField(5);
		modalDateRetourField = new JTextField(5);
		modalDateDuField = new JTextField(5);
		modalRetardMontantDu = new JTextField(5);
		
		JScrollPane scrollPane = new JScrollPane(tblFilms);
		scrollPane.setBounds(142, 11, 600, 400);
		add(scrollPane);
		//add(tblClient);
		
		JButton btnRent = new JButton("Louer");
		btnRent.setBounds(10, 40, 122, 23);
		add(btnRent);
		btnRent.addActionListener(e -> louerDisqueDialog());
		
		JButton btnReturn = new JButton("Retourner");
		btnReturn.setBounds(10, 108, 122, 23);
		add(btnReturn);
		btnReturn.addActionListener(e -> retournerDisqueDialog());
		
		JButton btnShowAll = new JButton("Afficher tout");
		btnShowAll.setBounds(10, 142, 122, 23);
		add(btnShowAll);
		btnShowAll.addActionListener(e -> updateTableData());
	}
	
	public void louerDisqueDialog() {
		JPanel myPanel = LocationPanel();

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Louer un film", JOptionPane.OK_CANCEL_OPTION);
		 if (result == JOptionPane.OK_OPTION) 
		    {
		    	Location location = new Location(modalNumeroTel.getText(), modalCodeDisque.getText(), modalDateLouerField.getText(), 
		    			modalDateRetourField.getText(),
		    			modalDateDuField.getText(), Double.parseDouble(modalRetardMontantDu.getText()));
		    	controller.louerFilm(location);
		    	System.out.print("Ajouter loc succ");
		    	updateTableData();
		    }
	}
	
	public void retournerDisqueDialog() {
		
	}
	
	private void updateTableData()
	{
		tblFilms.setModel(new DefaultTableModel(controller.getListeLocationsData(), new String[]{"Code Client", "Code Disque","Date Louer", 
		  "Date Retour", "Date Du"}));
	}
	
	public JPanel LocationPanel() {
		JPanel myPanel = new JPanel();
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Numero Telephone Clientt:"));
	    myPanel.add(modalNumeroTel);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Code Disque:"));
	    myPanel.add(modalCodeDisque);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Date louer(JJ-MM-AAAA):"));
	    myPanel.add(modalDateLouerField);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Date de retour(JJ-MM-AAAA):"));
	    myPanel.add(modalDateRetourField);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Date de du(JJ-MM-AAAA):"));
	    myPanel.add(modalDateDuField);
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Montant Retard Du:"));
	    myPanel.add(modalRetardMontantDu);

	    return myPanel;
	}

}
