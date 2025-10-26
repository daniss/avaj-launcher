package fr.daniss.avaj.simulator.aircraft;

import fr.daniss.avaj.simulator.Coordinates;

public class Baloon extends Aircraft {
    
    public Baloon(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        switch (weather) {
            case "SUN":
                longitude += 2;
                height = Math.min(height + 4, 100);
                System.out.println(getIdentifier() + ": Beautiful weather for ballooning!");
                break;
            case "RAIN":
                height = Math.max(height - 5, 0);
                System.out.println(getIdentifier() + ": Rain is making the balloon heavier.");
                break;
            case "FOG":
                height = Math.max(height - 3, 0);
                System.out.println(getIdentifier() + ": Fog is limiting visibility.");
                break;
            case "SNOW":
                height = Math.max(height - 15, 0);
                System.out.println(getIdentifier() + ": Snow is accumulating on the balloon!");
                break;
        }

        coordinates = updateCoordinates(longitude, latitude, height);
        
        if (coordinates.getHeight() == 0) {
            System.out.println(getIdentifier() + " landing.");
            weatherTower.unregister(this);
        }
    }
}