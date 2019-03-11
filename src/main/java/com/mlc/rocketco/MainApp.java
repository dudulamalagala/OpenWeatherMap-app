package com.mlc.rocketco;

import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
* Main class to run the application
* @author Dudula Malagala
*/
public class MainApp {
    
    public static void main(String[] args) {
    	JSONParser parser = new JSONParser();
    	JsonService service = new JsonService();
    	ForecastDisplay display = new ForecastDisplay();
    	
    	// Getting City id input
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter city id: ");
    	int cityId = (int) input.nextFloat(); //524901
    	
    	try {
			display.setForecast((JSONObject) parser.parse(service.getForecast(cityId).toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}

