package org.example;

public class Main {
    public static void main(String[] args) {
        Aircraft[] aircrafts = { new Freighter("Model1","callSign1", 1300),
                new Freighter("Model2","callSign2", 1500),
                new Airliner("Model3","callSign3", 1500),
                new Airliner("Model4","callSign4", 1700),
                new Drone("Model5", "callSign5", 150),
                new Drone("Model6","callSign6", 150 )
        };
        for(Aircraft aircraft : aircrafts) {
            System.out.println(aircraft.toString());
        }
    }
}