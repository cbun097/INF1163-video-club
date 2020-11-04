package org.group2.finalproject;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Home extends JPanel {
	
	JFrame f;
	JButton ClientBtn;
	JButton filmBtn;
	JButton locationBtn;
	JTable table_1;
	JScrollPane sp;

	/**
	 * Create the panel.
	 */
	public Home() {
		
	    f = new JFrame("Home");    
        JPanel panel=new JPanel();  
        panel.setBounds(40,80,200,200);  
		
        ClientBtn = new JButton("Clients");
		ClientBtn.setBounds(50,100,80,30); 
		 ClientBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// redirect to the clients UI
				JButton ajouterClient = new JButton("Ajouter client");
				ajouterClient.setBounds(175,100,180,30);
			}
		}); 
		f.getContentPane().add(ClientBtn);
		
		filmBtn = new JButton("Films");
		filmBtn.setBounds(150,100,80,30); 
		/**filmBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				// redirect to the films UI
			}
		}); */
		f.getContentPane().add(filmBtn);
		
		locationBtn = new JButton("Location");
		locationBtn.setBounds(250,100,180,30); 
		locationBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// redirect to the location UI
			}
		});
		f.getContentPane().add(locationBtn);
		
		// Does not show up
		String data[][]={ {"101","Amit","670000"},    
                {"102","Jai","780000"},    
                {"101","Sachin","700000"}};    
		String column[]={"ID","NAME","SALARY"};     
		table_1 = new JTable(data, column);
        sp  = new JScrollPane(table_1);
        sp.setBounds(50, 172, 492, 275);
        f.getContentPane().add(sp);
         
         f.setVisible(true);   
         f.setSize(800,800);    
         f.getContentPane().setLayout(null);    
		
	}
}
