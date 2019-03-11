package com.mlc.rocketco;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;


/**
* This class is calling the REST API web service and return the JSON object
* 
* @author Dudula Malagala
*/
@ApplicationScoped
public class JsonService {
    
   
    public String getForecast(int cityId) {
    	String sb = "";
    	
    	try {
    		URL url = new URL("https://samples.openweathermap.org/data/2.5/forecast?id="+cityId+"&appid="+getPropValues("weathermap.apikey"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				sb = sb + output;
			}

			conn.disconnect();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }
    	return sb;
    }
    
    public String getPropValues(String key) throws IOException {
    	String result = "";
    	InputStream inputStream = null;
    	
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			result = prop.getProperty(key);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
   
}
