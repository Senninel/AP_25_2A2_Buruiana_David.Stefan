package org.example;
import lombok.AllArgsConstructor;

import java.util.*;
@AllArgsConstructor
public class scheduleSolver{
    private Airport airport;
    private List<Flight> flightList;
    private PriorityQueue<List<Flight>> runways;

    scheduleSolver(Airport airport, List<Flight> flightList){
        this.airport = airport;
        this.flightList = flightList;
        this.runways = new PriorityQueue<>(Comparator.comparingInt(List::size));
    }
    public void assignFlights(){
        for(Flight flight:flightList){
            boolean found = false;

            List<List<Flight>> runways =  airport.getRunways();

            for(int index = 0; index < runways.size(); index++){
                List<Flight> runway = runways.get(index);
                boolean foundRunway = true;

                for(Flight runwayFlight:runway){
                    if(runwayFlight.intersectFlight(flight)){
                        foundRunway = false;
                        break;
                    }
                }
                if(foundRunway){
                    airport.addFlightToRunway(index, flight);
                    found = true;
                    break;
                }
            }
            if(!found){
                System.out.println("Cant assign flight" + flight + " to runways");
                return;
            }
            }
    }
    public void assignFlightFinalBoss(){
        boolean found = false;
        while(!found){
            found = assignFlightSmarter();
            if(!found) airport.addRunway();
        }
        this.printPriorityQueue();
    }
    private void printPriorityQueue() {

        for (List<Flight> runway : this.runways) {
            System.out.println("Pista cu " + runway.size() + " zboruri:");
            for (Flight flight : runway) {
                System.out.println(flight.toString());
            }
        }
    }
    private boolean assignFlightSmarter(){

        for(int i=0; i< airport.getRunways().size(); i++){
            runways.add(new  ArrayList<>());
        }

        boolean allAssigned = true;

        for(Flight flight:flightList){
            List<Flight> bestRunway = runways.poll();

            if(bestRunway != null){
                boolean canAssign = true;
                for(Flight runwayFlight:bestRunway){
                    if(runwayFlight.intersectFlight(flight)){
                        canAssign = false;
                        break;
                    }
                }
                if(canAssign){
                    bestRunway.add(flight);
                    runways.add(bestRunway);
                }
                else{
                    allAssigned = false;
                }
                }
            }
        return allAssigned;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<List<Flight>> runways =  airport.getRunways();
        int index = 0;
        for(List<Flight> runway:runways){
            sb.append("Pista numarul: " + index + " are zborurile: \n");
            for(Flight flight:runway){
                sb.append("Zborul: " + flight.toString() + "\n");
            }
            index++;
        }
        return sb.toString();
    }
}
