package org.group2.finalproject;

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

import org.group2.finalproject.classes.ArticleVente;
import org.group2.finalproject.controllers.InventoryController;

public class InventoryManagement extends JPanel{
	
	private JTable Inventory;
	private InventoryController controller;
	
	private JComboBox<String> codeFieldSelect;
	private JTextField modalNomField;
	private JTextField modalCodeField;
	private JTextField modalQtyField;
	private JTextField modalPrixField;
	private JTextField modalDescriptionField;
	
	public InventoryManagement() {
		
		setLayout(null);
		controller = new InventoryController();
		
		controller.updateListeArticle();
		Inventory = new JTable();
		updateTableData();
		//tblClient.setBounds(142, 11, 298, 278);
		
		JScrollPane scrollPane = new JScrollPane(Inventory);
		scrollPane.setBounds(142, 50, 600, 400);
		add(scrollPane);
		//add(tblClient);
		
		JButton btnAdd = new JButton("ajouter item");
		btnAdd.setBounds(10, 40, 122, 23);
		add(btnAdd);
		btnAdd.addActionListener(e -> ajouterDialogItem());
		
		JButton btnModItem = new JButton("modifier item");
		btnModItem.setBounds(10, 74, 122, 23);
		add(btnModItem);
		btnModItem.addActionListener(e -> modifierDialogItem());
		
		JButton btnRemove = new JButton("enlever item");
		btnRemove.setBounds(10, 108, 122, 23);
		add(btnRemove);
		btnRemove.addActionListener(e -> supprimerDialogArticle());
		
		JButton btnQty = new JButton("Ajuster Qty");
		btnQty.setBounds(10, 142, 122, 23);
		add(btnQty);
		btnQty.addActionListener(e -> ajusterQty());
		
		JTextField search = new JTextField("recherche item");
		search.setBounds(142, 11, 450, 25);
		add(search);
		
		JButton btnSearch = new JButton("recherche");
		btnSearch.setBounds(600, 12, 122, 25);
		add(btnSearch);
		
		modalNomField = new JTextField(5);
		modalCodeField = new JTextField(5);
		modalQtyField = new JTextField(5);
		modalPrixField = new JTextField(5);
		modalDescriptionField = new JTextField(5);
		
		codeFieldSelect = new JComboBox<>();
		codeFieldSelect.addActionListener(e -> {
			if(codeFieldSelect.getSelectedItem() != null) {
				ArticleVente article = controller.getListeArticle().stream()
						.filter((me) -> codeFieldSelect.getSelectedItem().equals(me.getCodeProduit()))
				    	.findAny()
				    	.orElse(null);
				modalNomField.setText(article.getNomProduit());
				modalQtyField.setText(String.valueOf(article.getQuantite()));
				modalPrixField.setText(String.valueOf(article.getPrix()));
				modalDescriptionField.setText(article.getDescriptionProduit());
			}
		});
		
		
		
	}
	private void ajouterDialogItem() {
		
		JPanel myPanel = ItemPanel(true);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Ajouter un item", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			
			
			int quantiteResult = Integer.parseInt(modalQtyField.getText());
			double prixResult = Double.parseDouble(modalPrixField.getText());
			ArticleVente article = new ArticleVente(modalNomField.getText(), modalCodeField.getText(), quantiteResult, prixResult, modalDescriptionField.getText());
			controller.ajouterArticle(article);
	    	updateTableData();
		}
	}
	private void modifierDialogItem() {
		
		JPanel myPanel = ItemPanel(false);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Modifier un item", JOptionPane.OK_CANCEL_OPTION);
		if(result == JOptionPane.OK_OPTION) {
			
			
			int quantiteResult = Integer.parseInt(modalQtyField.getText());
			double prixResult = Double.parseDouble(modalPrixField.getText());
			ArticleVente article = new ArticleVente(modalNomField.getText(), modalCodeField.getText(), quantiteResult, prixResult, modalDescriptionField.getText());
			controller.ajouterArticle(article);
	    	updateTableData();
		}
	}
	private void supprimerDialogArticle() {
		
		codeFieldSelect.removeAllItems();// = new JComboBox<>();
		  
		for (ArticleVente m : controller.getListeArticle()) 
			codeFieldSelect.addItem(m.getCodeProduit());

		if(codeFieldSelect.getItemCount() > 0)
			codeFieldSelect.setSelectedIndex(Inventory.getSelectedRow() >= 0 ? Inventory.getSelectedRow() : 0);

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Code Produit:"));
		myPanel.add(codeFieldSelect);
		myPanel.add(Box.createHorizontalStrut(15));
  	
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Entrer le code item a supprimer", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			ArticleVente selectArticle = controller.getListeArticle().stream()
			    	 .filter((me) -> codeFieldSelect.getSelectedItem().equals(me.getCodeProduit()))
			    	 .findAny()
			    	 .orElse(null);
			controller.supprimerArticle(selectArticle);
			updateTableData();
		}
	}
	private void ajusterQty() {
		
		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Code Produit:"));
		myPanel.add(codeFieldSelect);
		myPanel.add(Box.createHorizontalStrut(15));
		
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Quantite:"));
	    myPanel.add(modalQtyField);
  	
		int result = JOptionPane.showConfirmDialog(null, myPanel, "enter la nouvelle quantite", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			ArticleVente selectArticle = controller.getListeArticle().stream()
			    	 .filter((me) -> codeFieldSelect.getSelectedItem().equals(me.getCodeProduit()))
			    	 .findAny()
			    	 .orElse(null);
			controller.modifierQuantite(selectArticle);
			updateTableData();
		}
	}
	private void updateTableData()
	{
		Inventory.setModel(new DefaultTableModel(controller.getListeArticleData(), new String[]{"Nom du Produit", "Code du Produit","Qty", 
				"Prix", "Description du Protion"}));
	}
	public JPanel ItemPanel(boolean codeProduit) {
		JPanel myPanel = new JPanel();

	    // Reset the fields
		modalNomField.setText("");
		modalCodeField.setText("");
		modalQtyField.setText("");
		modalPrixField.setText("");
		modalDescriptionField.setText("");
		
		
	    myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Code du Produit:"));
	    if(codeProduit)
	    {
	    	modalCodeField.setText("");
	    	myPanel.add(modalCodeField);
	    }
	    else
	    {
	    	codeFieldSelect.removeAllItems();
	    	myPanel.add(codeFieldSelect);
	    	
	    	for (ArticleVente m : controller.getListeArticle())
	    		codeFieldSelect.addItem(m.getCodeProduit());
		    
			if(codeFieldSelect.getItemCount() > 0)
				codeFieldSelect.setSelectedIndex(Inventory.getSelectedRow() >= 0 ? Inventory.getSelectedRow() : 0);
	    }
	    
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Nom du Produit:"));
	    myPanel.add(modalNomField);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Code du Produit:"));
	    myPanel.add(modalCodeField);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Quantite:"));
	    myPanel.add(modalQtyField);
	    
	    // Display??
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Prix:"));
	    myPanel.add(modalPrixField);

	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("Description du Produit:"));
	    myPanel.add(modalDescriptionField);
	    
	    return myPanel;
		
	}

}
