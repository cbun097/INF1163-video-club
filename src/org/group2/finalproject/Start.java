package org.group2.finalproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.group2.finalproject.classes.Film;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

public class Start
{
	
	public static void main(String[] args)
	{
		JSONParser parser = new JSONParser();
		JSONArray jsonArray;
		JSONObject jsonObject;
		String filePath = "resource/films.json";
		String movieDB;
		String json = null;
		
		JFrame f = new JFrame("Home");    
		f.setSize(800,800);

		JPanel home = new Home();
        f.setContentPane(home);
		f.setVisible(true);
		
		// Read json file
		try {
			movieDB = new File(filePath).getAbsolutePath();
			json = readFileAsString(movieDB);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				//System.out.println(json);
		// convert json file to object
        try
        {
            //convert JSONArray to JSONObject
        	ArrayList<Film> filmList = new ArrayList<>();
            jsonArray = new JSONArray(json);
            jsonObject = jsonArray.getJSONObject(0);
        	jsonArray = jsonObject.getJSONArray("films");
        	for(int i=0; i<jsonArray.length(); i++) {
        	    JSONObject jobject = jsonArray.getJSONObject(i);
        	    String name = (String) jobject.get("name");
        	    Number anneeSortie = (Number) jobject.get("year");
        	    // TODO: ajouter
        	    filmList.add(new Film(name, anneeSortie));
        	  //System.out.println(jobject);
        	  
        	}
        	System.out.println(filmList);
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
