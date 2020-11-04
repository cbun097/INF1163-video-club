package org.group2.finalproject;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
		String filePath = "resource/movie_db.json";
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
            jsonArray = new JSONArray(json);
            jsonObject = jsonArray.getJSONObject(0);
        	jsonArray = jsonObject.getJSONArray("blueray");
        	for(int i=0; i<jsonArray.length(); i++) {
        	    JSONObject jobject = jsonArray.getJSONObject(i);
        	    String name = (String) jobject.get("name");
        	  System.out.println("Id:" + i + " Name: " + name);
        	  //System.out.println(jobject);
        	}
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
