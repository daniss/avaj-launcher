package fr.daniss.avaj.simulator.aircraft;

import fr.daniss.avaj.simulator.Coordinates;

public class Helicopter extends Aircraft {
    
    public Helicopter(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        switch (weather) {
            case "SUN":
                longitude += 10;
                height = Math.min(height + 2, 100);
                System.out.println(getIdentifier() + ": This sunny weather is perfect for a ride!");
                break;
            case "RAIN":
                longitude += 5;
                System.out.println(getIdentifier() + ": It's raining. Better stay alert.");
                break;
            case "FOG":
                longitude += 1;
                System.out.println(getIdentifier() + ": Fog detected, proceeding with caution.");
                break;
            case "SNOW":
                height = Math.max(height - 12, 0);
                System.out.println(getIdentifier() + ": Snow is causing some turbulence.");
                break;
        }

        coordinates = updateCoordinates(longitude, latitude, height);
        
        if (coordinates.getHeight() == 0) {
            System.out.println(getIdentifier() + " landing.");
            weatherTower.unregister(this);
        }
    }
}