package fr.daniss.avaj.simulator;

import fr.daniss.avaj.simulator.weather.WeatherTower;

public abstract class Flyable {

    protected WeatherTower weatherTower;

    public abstract void updateConditions();
    
    public abstract String getIdentifier();

    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        p_tower.register(this);
    }
}