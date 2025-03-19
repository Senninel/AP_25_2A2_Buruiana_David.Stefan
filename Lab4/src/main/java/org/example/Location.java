package org.example;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class Location implements Comparable<Location>{
    private static final Map<String,Location> locations = new HashMap<>();
    private String name;
    private locationType  locationType;

    private Location(String name,locationType locationType){
        this.name=name;
        this.locationType=locationType;
    }

    public static Location getInstanceWithName(String name, locationType type) {
        return locations.computeIfAbsent(name, k -> new Location(name, type));
    }

    public int compareTo(Location other){
        return this.name.compareTo(other.name);
    }
    @Override
    public String toString() {
        return this.name + " " +  this.locationType.toString();
    }
}
