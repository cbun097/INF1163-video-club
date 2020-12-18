package org.group2.finalproject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.group2.finalproject.classes.Disque;
import org.group2.finalproject.classes.Location;
import org.group2.finalproject.controllers.LocationController;

//TODO: Ajouter client validation
@SuppressWarnings("serial")
public class LocationManagement extends JPanel {
	private JTable tblFilms;
	private LocationController controller;
	private JComboBox<String> modalCodeFieldSelect, modalClientFieldSelect;
	private JTextField modalRetardMontantDu;
	private JTextField modalDateLouerField, modalDateRetourField, modalDateDuField;
	
	/**
	 * Create the panel.d
	 */
	public LocationManagement() {
		setLayout(null);
		controller = new LocationController();

		tblFilms = new JTable();
		//tblClient.setBounds(142, 11, 298, 278);
		
		modalCodeFieldSelect = new JComboBox<>();
		modalClientFieldSelect = new JComboBox<>();
		modalDateLouerField = new JTextField(5);
		modalDateRetourField = new JTextField(5);
		modalDateDuField = new JTextField(5);
		modalRetardMontantDu = new JTextField(5);
		
		JScrollPane scrollPane = new JScrollPane(tblFilms);
		scrollPane.setBounds(142, 11, 600, 400);
		add(scrollPane);
		
		JButton btnRent = new JButton("Louer");
		btnRent.setBounds(10, 40, 122, 23);
		add(btnRent);
		btnRent.addActionListener(e -> louerDisqueDialog());
		
		JButton btnReturn = new JButton("Retourner");
		btnReturn.setBounds(10, 108, 122, 23);
		add(btnReturn);
		btnReturn.addActionListener(e -> retournerDisqueDialog());

		JButton btnMontant = new JButton("Mise a jour Montant");
		btnMontant.setBounds(10, 176, 122, 23);
		add(btnMontant);
		btnMontant.addActionListener(e -> modifMontantDu());
		
		controller.updateLocationListe();
		updateTableData();

	}

	public void louerDisqueDialog() {
		JPanel myPanel = locationPanel(true);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Louer un film", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
	    {
	    	Location location = new Location((String)modalClientFieldSelect.getSelectedItem(), (String)modalCodeFieldSelect.getSelectedItem(), modalDateLouerField.getText(), 
	    			"",
	    			modalDateDuField.getText(), 0);
	    	controller.louerFilm(location);
	    	System.out.print("Ajouter loc succ");
	    	updateTableData();
	    }
	}
	
	public void retournerDisqueDialog() {
		JPanel myPanel = locationPanel(false);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Retourner un film", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
	    {
	    	Location location = ListesUtil.LISTE_LOCATIONS.stream().filter(e -> 
	    			e.getNumeroTelephone().equals((String)modalClientFieldSelect.getSelectedItem()) && e.getCodeDisque().equals((String)modalCodeFieldSelect.getSelectedItem())).findAny().orElse(null);
	    	if(location != null)
	    	{
	    		location.setDateRetour(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
	    		controller.modifLocation(location, true);
		    	System.out.print("Ajouter loc succ");
		    	updateTableData();
	    	}
	    }
	}

	
	private void modifMontantDu() {
		
		ListesUtil.LISTE_LOCATIONS.forEach(e -> {
			try 
			{
				if(e.getDateRetour() != null)
				{
					Date start = new SimpleDateFormat("dd-MM-yyyy").parse(e.getDateDu());
					Date today = new Date();
					
					e.setMontantRetardDu(Math.max(0, TimeUnit.DAYS.convert(today.getTime() - start.getTime(), TimeUnit.MILLISECONDS)));

			    	controller.modifLocation(e, false);
				}
			} 
			catch (ParseException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		controller.updateLocationListe();
		
    	System.out.print("Ajouter loc succ");
    	updateTableData();
	}
	
	private void updateTableData()
	{
		tblFilms.setModel(new DefaultTableModel(controller.getListeLocationsData(), new String[]{"Code Client", "Code Disque","Date Louer", 
		  "Date Retour", "Date Du", "Montant Retard"}));
	}
	
	public JPanel locationPanel(boolean add) {
		JPanel myPanel = new JPanel();
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));

	    Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    modalDateLouerField.setText(new SimpleDateFormat("dd-MM-yyyy").format(c.getTime()));
	    c.add(Calendar.DATE, 7);
	    modalDateDuField.setText(new SimpleDateFormat("dd-MM-yyyy").format(c.getTime()));
	    
	    modalClientFieldSelect.removeAllItems();
	    modalCodeFieldSelect.removeAllItems();
	    
    	// Ajouter tous les membres à la liste disponible
	    ListesUtil.LISTE_MEMBRES.forEach(e -> modalClientFieldSelect.addItem(e.getNumeroTelephone()));
	    // Ajouter tous les disques disponibles
	    ListesUtil.LISTE_INVENTAIRE.forEach(e -> {
	    	if(e instanceof Disque)
	    		modalCodeFieldSelect.addItem(e.getCodeProduit());
	    });

	    // si c'est le modal Ajouter
	    if(add)
	    {
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("Numero Telephone Client:"));
		    myPanel.add(modalClientFieldSelect);

		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("Code Disque:"));
		    myPanel.add(modalCodeFieldSelect);

		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("Date louer(JJ-MM-AAAA):"));
		    myPanel.add(modalDateLouerField);

		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("Date maximal retour(JJ-MM-AAAA):"));
		    myPanel.add(modalDateDuField);
	    }
	    // Si c'est le modal de retour
	    else
	    {
		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("Numero Telephone Client:"));
		    myPanel.add(modalClientFieldSelect);

		    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		    myPanel.add(new JLabel("Code Disque:"));
		    myPanel.add(modalCodeFieldSelect);
	    }

	    return myPanel;
	}

}
