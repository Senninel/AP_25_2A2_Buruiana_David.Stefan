package org.example;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Airport airport = new Airport(3);


        Airliner airliner1 = new Airliner("Boeing 737", "AAL123", 180);
        Freighter freighter1 = new Freighter("Boeing 747", "UPS987", 10000);
        Drone drone1 = new Drone("DJI Phantom", "DRN001", 2.5);

        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight(airliner1, 1, LocalTime.of(10, 0), LocalTime.of(10, 30)));
        flightList.add(new Flight(freighter1, 2, LocalTime.of(10, 15), LocalTime.of(10, 45)));
        flightList.add(new Flight(drone1, 3, LocalTime.of(10, 50), LocalTime.of(11, 10)));

        scheduleSolver solver = new scheduleSolver(airport, flightList);
        solver.assignFlights();

       /// System.out.println(solver.toString());

        solver.assignFlightFinalBoss();
    }
}