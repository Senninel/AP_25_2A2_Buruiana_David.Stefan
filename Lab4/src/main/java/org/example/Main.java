package org.example;

import java.util.*;
import java.util.stream.Collectors;

enum locationType{
    FRIENDLY,
    NEUTRAL,
    ENEMY;
}

public class Main {
    public static void main(String[] args) {
            Location[]  locations = new Location[10];
            Location l1 = Location.getInstanceWithName("Iasi",locationType.ENEMY);
            Location l2 = Location.getInstanceWithName("Cluj",locationType.NEUTRAL);
            Location l3 = Location.getInstanceWithName("Bucuresti",locationType.FRIENDLY);
            Location l4 = Location.getInstanceWithName("Buru",locationType.FRIENDLY);
            Location l5 = Location.getInstanceWithName("Cuz",locationType.NEUTRAL);
            Location l6 = Location.getInstanceWithName("Oradea",locationType.ENEMY);
            locations[0]=l6;
            locations[1]=l2;
            locations[2]=l3;
            locations[3]=l4;
            locations[4]=l5;
            locations[5]=l1;
            for(Location l:locations){
                if(l!=null) {
                    System.out.println(l.toString());
                }
                }

            System.out.println();
            System.out.println();

        TreeSet<Location> friendlyLocations = Arrays.stream(locations).filter(Objects::nonNull)
                .filter(loc -> loc.getLocationType() == locationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        friendlyLocations.forEach(System.out::println);

        LinkedList<Location> enemyLocations = Arrays.stream(locations).filter(Objects::nonNull)
                .filter(loc -> loc.getLocationType() == locationType.ENEMY)
                .sorted(Comparator.comparing(Location::getLocationType)
                        .thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        enemyLocations.forEach(System.out::println);
    }
}