package fr.daniss.avaj.simulator.weather;

import fr.daniss.avaj.simulator.Coordinates;
import fr.daniss.avaj.simulator.Tower;

public class WeatherTower extends Tower {
    
    public String getWeather(Coordinates p_coordinates) {
        return WeatherProvider.getInstance().getCurrentWeather(p_coordinates);
    }
    
    public void changeWeather() {
        this.conditionsChanged();
    }
}