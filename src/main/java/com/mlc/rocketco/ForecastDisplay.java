package com.mlc.rocketco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * This class is process the JSON object data and generate the final response
 *
 * @author Dudula Malagala
 */
public class ForecastDisplay {
    
	@SuppressWarnings("unchecked")
    public void setForecast(JSONObject obj) {
    	
        try {        	
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray sortResponse = new JSONArray();
            
            JSONArray companyList = (JSONArray) jsonObject.get("list");
            Iterator<JSONObject> listIterator = companyList.iterator();
            
            while (listIterator.hasNext()) {
            	JSONObject objResp = new JSONObject();
            	
            	JSONObject windElement = (JSONObject) listIterator.next();
            	JSONObject windObject = (JSONObject) windElement.get("wind");
            	JSONObject mainObject = (JSONObject) windElement.get("main");
                       	
            	double deg; 
            	try {
            		deg = (Double) windObject.get("deg");
            	} catch ( Exception e) {
            		long i = (long) windObject.get("deg");
            		deg = i;
            	}
            	double speed = (Double) windObject.get("speed");
            	double score = java.lang.Math.abs(20 - ((Double) mainObject.get("temp"))) + speed + java.lang.Math.abs(220-deg) * 0.1;
                
                objResp.put("score", Integer.toString((int)score));
                objResp.put("datetime", (String) windElement.get("dt_txt"));
                objResp.put("location", ((JSONObject) jsonObject.get("city")).get("name"));
                sortResponse.add(objResp);
                
            }
            
            sortResponse = sort(sortResponse);
            JSONObject finalResponse = new JSONObject();
            finalResponse.put("launchWindows", sortResponse);
            System.out.println(finalResponse);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /*
     * Sort the array by score
     */
    @SuppressWarnings("unchecked")
	public static JSONArray sort(JSONArray jsonArr) {
    	
    	List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArr.size(); i++) {
            jsonValues.add((JSONObject)jsonArr.get(i));
        }
        
        Collections.sort( jsonValues, new Comparator<JSONObject>() {
            
        	private static final String KEY_NAME = "score";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                } 
                catch (Exception e) {
                    e.printStackTrace();
                }

                return valA.compareTo(valB);
            }
        });
        
        JSONArray sortedJsonArray = new JSONArray();

        for (int i = 0; i < jsonArr.size(); i++) {
            if (i == 5)
            	break;
        	sortedJsonArray.add(jsonValues.get(i));
        }
    	
    	return sortedJsonArray;
    }

}
