package fr.daniss.avaj.simulator;

import fr.daniss.avaj.simulator.aircraft.AircraftFactory;
import fr.daniss.avaj.simulator.exceptions.CoordinatesException;
import fr.daniss.avaj.simulator.exceptions.ScenarioFileException;
import fr.daniss.avaj.simulator.exceptions.SimulatorException;
import fr.daniss.avaj.simulator.weather.WeatherTower;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Simulator {
    
    private static WeatherTower weatherTower;
    private static PrintWriter writer;
    private static PrintStream originalOut;
    
    public static void main(String[] args) {
        try {            
            if (args.length != 1) {
                throw new ScenarioFileException("Usage: java simulator.Simulator <scenario_file>");
            }
            
            File file = new File(args[0]);
            if (!file.exists() || !file.isFile()) {
                throw new ScenarioFileException("Scenario file does not exist: " + args[0]);
            }
            
            writer = new PrintWriter(new FileWriter("simulation.txt"));
            originalOut = System.out;

            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) throws IOException {
                    writer.write(b);
                    writer.flush();
                }
                
                @Override
                public void write(byte[] b) throws IOException {
                    writer.write(new String(b));
                    writer.flush();
                }
                
                @Override
                public void write(byte[] b, int off, int len) throws IOException {
                    writer.write(new String(b, off, len));
                    writer.flush();
                }
            }));
            
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty()) {
                reader.close();
                throw new ScenarioFileException("Empty scenario file");
            }
            
            int simulationCycles;
            try {
                simulationCycles = Integer.parseInt(line.trim());
                if (simulationCycles <= 0) {
                    reader.close();
                    throw new ScenarioFileException("Simulation cycles must be positive");
                }
            } catch (NumberFormatException e) {
                throw new ScenarioFileException("First line must be a valid integer (number of simulation cycles)");
            }
            
            weatherTower = new WeatherTower();
            AircraftFactory aircraftFactory = AircraftFactory.getInstance();
            int lineNumber = 1;
            
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) {
                    reader.close();
                    throw new ScenarioFileException("Empty line found in the scenario file at line " + lineNumber);
                }
                
                String[] params = line.split("\\s+");
                if (params.length != 5) {
                    reader.close();
                    throw new ScenarioFileException("Invalid aircraft definition on line " + lineNumber + ": " + line);
                }
                
                try {
                    String type = params[0];
                    String name = params[1];
                    int longitude, latitude, height;
                    
                    try {
                        longitude = Integer.parseInt(params[2]);
                        latitude = Integer.parseInt(params[3]);
                        height = Integer.parseInt(params[4]);
                    } catch (NumberFormatException e) {
                        throw new CoordinatesException("Invalid coordinates on line " + lineNumber + ": " + line);
                    }
                    if (longitude <= 0 || latitude <= 0 || height <= 0) {
                        throw new CoordinatesException("Coordinates must be positive on line " + lineNumber + ": " + line);
                    }
                    Coordinates coordinates = new Coordinates(longitude, latitude, height);
                    Flyable aircraft = aircraftFactory.newAircraft(type, name, coordinates);
                    
                    aircraft.registerTower(weatherTower);
                } catch (SimulatorException e) {
                    System.setOut(originalOut);
                    System.out.println("Error: " + e.getMessage());
                    reader.close();
                    System.exit(1);
                }
            }
            
            reader.close();

            for (int i = 0; i < simulationCycles; i++) {
                weatherTower.changeWeather();
            }
            
        } catch (SimulatorException e) {
            if (originalOut != null) {
                System.setOut(originalOut);
            }
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            if (originalOut != null) {
                System.setOut(originalOut);
            }
            System.out.println("Error: I/O error occurred - " + e.getMessage());
            System.exit(1);
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (originalOut != null) {
                System.setOut(originalOut);
            }
        }
    }
}