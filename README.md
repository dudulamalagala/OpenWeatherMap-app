# Consuming OpenWeatherMap REST services from a Java app

Application. Fetches weather data from a openweathermap.org and displays launch windows with location, datetime, and score.

To run the application, 
1. First input the city id
2. Put the API key in file **src/main/resources/config.properties**

Issue:
API call uri(api.openweathermap.org/data/2.5/forecast?id={city ID}) need appid parameter, which is unknown. I could not test this application for different cities. The full URL is,
https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=b6907d289e10d714a6e88b30761fae22

Use a JAX-RS 2.0 compatible server (~ Java EE 7 ) to deploy the app. Wildfly plugin is in the pom.xml, so an easy method is just to execute:

```
mvn MainApp:run
```
