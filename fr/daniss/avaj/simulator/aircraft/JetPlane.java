package fr.daniss.avaj.simulator.aircraft;

import fr.daniss.avaj.simulator.Coordinates;

public class JetPlane extends Aircraft{
    
    public JetPlane(long p_id, String p_name, Coordinates p_coordinates) {
        super(p_id, p_name, p_coordinates);
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
                System.out.println("JetPlane#" + name + "(" + id + "): Clear skies ahead!");
                break;
            case "RAIN":
                latitude += 5;
                System.out.println("JetPlane#" + name + "(" + id + "): Rain droplets on the windshield.");
                break;
            case "FOG":
                latitude += 1;
                System.out.println("JetPlane#" + name + "(" + id + "): Flying through fog, visibility reduced.");
                break;
            case "SNOW":
                height = Math.max(height - 7, 0);
                System.out.println("JetPlane#" + name + "(" + id + "): Encountering snow at high altitude.");
                break;
        }

        coordinates = updateCoordinates(longitude, latitude, height);
        
        if (coordinates.getHeight() == 0) {
            System.out.println("JetPlane#" + name + "(" + id + "): Landing at coordinates (" 
                + coordinates.getLongitude() + ", " + coordinates.getLatitude() + ").");
            weatherTower.unregister(this);
        }
    }
}