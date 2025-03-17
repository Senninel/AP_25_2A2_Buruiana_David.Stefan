package org.example;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
@Getter
@Setter
public class Airport{
    private List<List<Flight>> runways;

    public Airport(int numberOfRunways){
        runways = new ArrayList<>();
        for(int i=0;i<numberOfRunways;i++){
            runways.add(new ArrayList<>());
        }
    }
    public void addFlightToRunway(int index, Flight flight)
    {
        runways.get(index).add(flight);
    }
    public void addRunway(){
        runways.add(new ArrayList<>());
    }
}
