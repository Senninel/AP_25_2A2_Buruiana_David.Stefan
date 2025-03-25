package org.example;

import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RouteInfo{
    Location from;
    Location to;
    double cost;
    Map<locationType, Long> typeOfLocations;


    @Override
    public String toString(){
        return "De la:" + from.getName() + " to: " + to.getName() +
                " Cost : " + cost + " tipuri: " + typeOfLocations;
    }
}
