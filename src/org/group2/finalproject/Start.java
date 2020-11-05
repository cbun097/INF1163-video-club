package org.group2.finalproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.group2.finalproject.classes.Film;
import org.group2.finalproject.classes.Membre;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class Start
{
	
	public static void main(String[] args)
	{
		JSONParser parser = new JSONParser();
		String movieFilePath = "resource/films.json";
		String membresFilePath = "resource/membres.json";
		String movieDB;
		String membreDB;
		String movieJSON = null;
		String membreJSON = null;
		
		JFrame f = new JFrame("Home");    
		f.setSize(800,800);

		JPanel home = new Home();
        f.setContentPane(home);
		f.setVisible(true);
		
		// Read json file
		try {
			movieDB = new File(movieFilePath).getAbsolutePath();
			membreDB = new File(membresFilePath).getAbsolutePath();
			movieJSON = readFileAsString(movieDB);
			membreJSON = readFileAsString(membreDB);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				//System.out.println(membreJSON);
		
		// convert json file to object
        try
        {
            //convert JSONArray to JSONObject FILM
        	ArrayList<Film> filmList = new ArrayList<>();
        	JSONArray jsonArray = new JSONArray(movieJSON);
        	JSONObject jsonObject = jsonArray.getJSONObject(0);
        	jsonArray = jsonObject.getJSONArray("films");
        	for(int i=0; i<jsonArray.length(); i++) {
        	    JSONObject jobject = jsonArray.getJSONObject(i);
        	    String codeFilm = (String) jobject.get("CodeFilm");
        	    String nom = (String) jobject.get("Nom");
        	    //String genres = (String)jobject.get("Genres"); // array object
        	    // TODO: ajouters
        	    filmList.add(new Film(codeFilm, nom));
        	  //System.out.println(jobject);
        	  
        	}
        	System.out.println(filmList);
        	
        	// convert JSONArray to JSONObject Membre
        	ArrayList<Membre> membresListe = new ArrayList<>();
        	JSONArray jaMembre = new JSONArray(membreJSON);
        	JSONObject joMembre = jaMembre.getJSONObject(0);
        	jaMembre = joMembre.getJSONArray("membres");
        	for(int i=0; i<jaMembre.length(); i++) {
        	    JSONObject jobject = jaMembre.getJSONObject(i);
        	    String codeClient = (String) jobject.get("CodeClient");
        	    String nomClient = (String) jobject.get("NomClient");
        	    String adresseCouriel = (String) jobject.get("AdresseCourriel");
        	    String adresseDomicile = (String) jobject.get("AdresseDomicile");
        	    String numTel = (String) jobject.get("NumeroTelephoneMaison");
        	    Boolean estMembre = (Boolean) jobject.get("EstMembre");
        	    String carteCredit = (String) jobject.get("CarteDeCredit");
        	    int codeSecret = (int) jobject.get("CodeSecret");
        	    // revoir, accept valeur null
        	    double montantDu = (double) jobject.get("MontantDu");
        	    membresListe.add(new Membre(codeClient, nomClient, adresseCouriel,adresseDomicile,numTel, estMembre, carteCredit, codeSecret, montantDu));
        	}
        	System.out.println(membresListe);
        	
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
	
	 public static String readFileAsString(String file)throws Exception
	    {
	        return new String(Files.readAllBytes(Paths.get(file)));
	    }
}
