package fr.daniss.avaj.simulator.aircraft;

import fr.daniss.avaj.simulator.Coordinates;
import fr.daniss.avaj.simulator.Flyable;

public class Aircraft extends Flyable {

    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long p_id, String p_name, Coordinates p_coordinate) {
        this.id = p_id;
        this.name = p_name;
        this.coordinates = p_coordinate;
    }

    public void updateConditions() {
        throw new UnsupportedOperationException("Method not implemented");
    }
    
    Coordinates updateCoordinates(int longitude, int latitude, int height) {
        return Coordinates.create(longitude, latitude, height);
    }
}