package fr.daniss.avaj.simulator.aircraft;

import fr.daniss.avaj.simulator.Coordinates;
import fr.daniss.avaj.simulator.Flyable;
import fr.daniss.avaj.simulator.exceptions.AircraftTypeException;

public class AircraftFactory {
    
    private static AircraftFactory instance = null;
    
    private AircraftFactory() {}
    
    public static AircraftFactory getInstance() {
        if (instance == null) {
            instance = new AircraftFactory();
        }
        return instance;
    }
    
    private static int id = 0;
    public Flyable newAircraft(String p_type, String p_name, Coordinates p_coordinates) throws AircraftTypeException {
        switch (p_type.toLowerCase()) {
            case "helicopter":
                return new Helicopter(id++, p_name, p_coordinates);
            case "jetplane":
                return new JetPlane(id++, p_name, p_coordinates);
            case "baloon":
                return new Baloon(id++, p_name, p_coordinates);
            default:
                throw new AircraftTypeException("Unknown aircraft type: " + p_type);
        }
    }
}