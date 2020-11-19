package org.group2.finalproject;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.group2.finalproject.classes.Film;
import org.group2.finalproject.classes.Membre;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Start
{
	
	public static void main(String[] args)
	{
		JSONParser parser = new JSONParser();
		String movieFilePath = "resource/films.json";
		String membresFilePath = "resource/membres.json";
		
		// convert json file to object
        try
        {
            //convert JSONArray to JSONObject FILM
        	//ArrayList<Film> filmList = new ArrayList<>();
        	JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(Start.class.getClassLoader().getResource(movieFilePath).getFile()));
        	JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        	jsonArray =  (JSONArray) jsonObject.get("films");
        	for(int i=0; i<jsonArray.size(); i++) {
        	    JSONObject jobject = (JSONObject) jsonArray.get(i);
        	    String codeFilm = (String) jobject.get("CodeFilm");
        	    String nom = (String) jobject.get("Nom");
        	    //String genres = (String)jobject.get("Genres"); // array object
        	    // TODO: ajouters
        	    ResourcesUtil.LISTE_FILMS.add(new Film(codeFilm, nom));
        	  //System.out.println(jobject);
        	  
        	}
        	System.out.println(ResourcesUtil.LISTE_FILMS);
        	
        	// convert JSONArray to JSONObject Membre
        	JSONArray jaMembre = (JSONArray) parser.parse(new FileReader(Start.class.getClassLoader().getResource(membresFilePath).getFile()));
        	JSONObject joMembre = (JSONObject) jaMembre.get(0);
        	jaMembre = (JSONArray) joMembre.get("membres");
        	for(int i=0; i<jaMembre.size(); i++) {
        	    JSONObject jobject = (JSONObject) jaMembre.get(i);
        	    String codeClient = (String) jobject.get("CodeClient");
        	    String nomClient = (String) jobject.get("NomClient");
        	    String adresseCouriel = (String) jobject.get("AdresseCourriel");
        	    String adresseDomicile = (String) jobject.get("AdresseDomicile");
        	    String numTel = (String) jobject.get("NumeroTelephoneMaison");
        	    Boolean estMembre = (Boolean) jobject.get("EstMembre");
        	    String carteCredit = (String) jobject.get("CarteDeCredit");
        	    int codeSecret = ((Long)jobject.get("CodeSecret")).intValue();
        	    // revoir, accept valeur null
        	    double montantDu = (double) jobject.get("MontantDu");
        	    ResourcesUtil.LISTE_MEMBRES.add(new Membre(codeClient, nomClient, adresseCouriel,adresseDomicile,numTel, estMembre, carteCredit, codeSecret, montantDu));
        	}
        	System.out.println(ResourcesUtil.LISTE_MEMBRES);
        	

    		// Create & show after loading resources
    		JFrame f = new JFrame("Home");    
    		f.setSize(800,800);

    		JPanel home = new Home();
            f.setContentPane(home);
    		f.setVisible(true);
    		// Oops forgot to add that, now the process ends when you close the thing xD
    		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
}
