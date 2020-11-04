package org.group2.finalproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Start
{
	public static void main(String[] args)
	{
		//Test test = new Test();
		//test.setVisible(true); 

		JFrame f = new JFrame("Home");    
		f.setSize(800,800);

		JPanel home = new Home();
        f.setContentPane(home);
        
		f.setVisible(true);
		
		/** ConnectionDB db = new ConnectionDB();
		db.connectDB(); */
	}
}
