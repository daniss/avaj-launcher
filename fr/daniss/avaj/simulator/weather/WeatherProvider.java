package fr.daniss.avaj.simulator.weather;

import fr.daniss.avaj.simulator.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};
    
    private WeatherProvider() {}
    
    public static WeatherProvider getInstance() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }
    
    public String getCurrentWeather(Coordinates p_coordinates) {
        int sum = p_coordinates.getLongitude()
                + p_coordinates.getLatitude()
                + p_coordinates.getHeight();

        int index = Math.abs(sum) % weather.length;
        return weather[index];
    }
}