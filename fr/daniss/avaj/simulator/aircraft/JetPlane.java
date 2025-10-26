package fr.daniss.avaj.simulator.aircraft;

import fr.daniss.avaj.simulator.Coordinates;

public class JetPlane extends Aircraft{
    
    public JetPlane(long p_id, String p_name, Coordinates p_coordinate) {
        super(p_id, p_name, p_coordinate);
    }

    public void updateConditions() {
        String weather = weatherTower.getWeather(coordinates);
        int longitude = coordinates.getLongitude();
        int latitude = coordinates.getLatitude();
        int height = coordinates.getHeight();

        switch (weather) {
            case "SUN":
                latitude += 10;
                height = Math.min(height + 2, 100);
                System.out.println(getIdentifier() + ": Clear skies ahead!");
                break;
            case "RAIN":
                latitude += 5;
                System.out.println(getIdentifier() + ": Rain droplets on the windshield.");
                break;
            case "FOG":
                latitude += 1;
                System.out.println(getIdentifier() + ": Flying through fog, visibility reduced.");
                break;
            case "SNOW":
                height = Math.max(height - 7, 0);
                System.out.println(getIdentifier() + ": Encountering snow at high altitude.");
                break;
        }

        coordinates = updateCoordinates(longitude, latitude, height);
        
        if (coordinates.getHeight() == 0) {
            System.out.println(getIdentifier() + " landing.");
            weatherTower.unregister(this);
        }
    }
}