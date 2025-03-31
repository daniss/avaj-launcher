package fr.daniss.avaj.simulator.aircraft;

import fr.daniss.avaj.simulator.Coordinates;
import fr.daniss.avaj.simulator.Flyable;
import fr.daniss.avaj.simulator.weather.WeatherTower;

public class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    public void updateConditions() {
        throw new UnsupportedOperationException("Method not implemented");
    }
    
    public void registerTower(WeatherTower p_tower) {
        this.weatherTower = p_tower;
        weatherTower.register(this);
    }
    
    protected Coordinates updateCoordinates(int longitude, int latitude, int height) {
        return new Coordinates(longitude, latitude, height);
    }
}