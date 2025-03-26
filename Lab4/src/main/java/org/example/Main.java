package org.example;

import java.util.*;
import java.util.stream.Collectors;
import com.github.javafaker.Faker;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

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

        Location[] randomLocations = new Location[10];

        Faker fakeLocation = new Faker();
        Random randNumber = new Random();
        locationType typeOfLocation = locationType.NEUTRAL;

        for(int i=0;i < randomLocations.length;i++){
            String name = fakeLocation.address().cityName();
            typeOfLocation = locationType.values()[randNumber.nextInt(3)];
            randomLocations[i] = Location.getInstanceWithName(name,typeOfLocation);
        }

        for (Location l : randomLocations) {
            System.out.println(l);
        }

        Graph<Location, DefaultWeightedEdge> graphRoute = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        for(Location l:randomLocations){
            graphRoute.addVertex(l);
        }

        for(Location l:randomLocations){
            for(Location r:randomLocations){
                if(!l.equals(r) && randNumber.nextInt(2) == 1){
                    DefaultWeightedEdge edge = graphRoute.addEdge(l,r);
                    if(edge != null){
                        double weight = 1 + randNumber.nextInt(10);
                        graphRoute.setEdgeWeight(edge,weight);
                    }
                }
            }
        }

        Location start = randomLocations[0];
        DijkstraShortestPath<Location,DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graphRoute);
        System.out.println("Rutele de la " + start.getName() + " pana la: ");
        for(Location l:randomLocations){
            if(!start.equals(l)){
                var path = dijkstra.getPath(start, l);
                if(path!=null){
                    System.out.println(l.getName() + " are costul: " + path.getWeight() + " si trece prin: " + path.getVertexList());
                }
            }
        }

        Map<locationType, List<Location>> groupedType = Arrays.stream(randomLocations).filter(Objects::nonNull).collect(Collectors.groupingBy(Location::getLocationType));

        List<Location> friendly = groupedType.getOrDefault(locationType.FRIENDLY,new ArrayList<>());
        List<Location> neutral =  groupedType.getOrDefault(locationType.NEUTRAL,new ArrayList<>());
        List<Location> enemy = groupedType.getOrDefault(locationType.ENEMY, new ArrayList<>());
        System.out.println("Friendly: ");
        friendly.forEach(dest -> printPath(start, dest, dijkstra));

        System.out.println("Neutral: ");
        neutral.forEach(dest -> printPath(start, dest, dijkstra));

        System.out.println("ENEMY: ");
        enemy.forEach(dest -> printPath(start, dest, dijkstra));

        System.out.println("\n\n\n\n");

        List<RouteInfo> routes = new ArrayList<>();

        for(Location l : randomLocations){
            for(Location r : randomLocations){
                if(!r.equals(l)){
                    var path = dijkstra.getPath(l, r);
                    if(path!=null){
                        List<Location> pathList = path.getVertexList();

                        Map<locationType, Long> allTypesCount = pathList.stream().collect(Collectors.groupingBy(Location::getLocationType, Collectors.counting()));

                        RouteInfo info = new RouteInfo(l,r, path.getWeight(), allTypesCount);
                        routes.add(info);
                    }
                }
            }
        }


        routes.stream().sorted((r1,r2) -> Long.compare(r2.typeOfLocations.getOrDefault(locationType.FRIENDLY, 0L), r1.typeOfLocations.getOrDefault(locationType.FRIENDLY, 0L))).limit(5).forEach(System.out::println);

        double avgCost = routes.stream()
                .mapToDouble(r -> r.cost)
                .average()
                .orElse(0);

        long totalRoutes = routes.size();

        System.out.println("Numar de rute: " + totalRoutes);
        System.out.println("Avg cost: " + avgCost);

        System.out.println("\n\n\n\n\n");
        randomInstance(100);

    }
    private static void randomInstance(int numberOfVertex){
        Location[] randomLocations = new Location[numberOfVertex];

        Faker fakeLocation = new Faker();
        Random randNumber = new Random();
        locationType typeOfLocation = locationType.NEUTRAL;

        for(int i=0;i < numberOfVertex;i++){
            String name = fakeLocation.address().cityName();
            typeOfLocation = locationType.values()[randNumber.nextInt(3)];
            randomLocations[i] = Location.getInstanceWithName(name,typeOfLocation);
        }

        Graph<Location, DefaultWeightedEdge> graphRoute = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        for(Location l:randomLocations){
            graphRoute.addVertex(l);
        }

        for(Location l:randomLocations){
            for(Location r:randomLocations){
                if(!l.equals(r) && randNumber.nextInt(2) == 1){
                    DefaultWeightedEdge edge = graphRoute.addEdge(l,r);
                    if(edge != null){
                        double weight = 1 + randNumber.nextInt(10);
                        graphRoute.setEdgeWeight(edge,weight);
                    }
                }
            }
        }

        Location start = randomLocations[0];
        DijkstraShortestPath<Location,DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(graphRoute);
        System.out.println("Rutele de la " + start.getName() + " pana la: ");
        for(Location l:randomLocations){
            if(!start.equals(l)){
                var path = dijkstra.getPath(start, l);
                if(path!=null){
                    System.out.println(l.getName() + " are costul: " + path.getWeight() + " si trece prin: " + path.getVertexList());
                }
            }
        }
        Map<locationType, List<Location>> groupedType = Arrays.stream(randomLocations).filter(Objects::nonNull).collect(Collectors.groupingBy(Location::getLocationType));

        List<Location> friendly = groupedType.getOrDefault(locationType.FRIENDLY,new ArrayList<>());
        List<Location> neutral =  groupedType.getOrDefault(locationType.NEUTRAL,new ArrayList<>());
        List<Location> enemy = groupedType.getOrDefault(locationType.ENEMY, new ArrayList<>());
        System.out.println("Friendly: ");
        friendly.forEach(dest -> printPath(start, dest, dijkstra));

        System.out.println("Neutral: ");
        neutral.forEach(dest -> printPath(start, dest, dijkstra));

        System.out.println("ENEMY: ");
        enemy.forEach(dest -> printPath(start, dest, dijkstra));

        System.out.println("\n\n\n\n");

        List<RouteInfo> routes = new ArrayList<>();

        for(Location l : randomLocations){
            for(Location r : randomLocations){
                if(!r.equals(l)){
                    var path = dijkstra.getPath(l, r);
                    if(path!=null){
                        List<Location> pathList = path.getVertexList();

                        Map<locationType, Long> allTypesCount = pathList.stream().collect(Collectors.groupingBy(Location::getLocationType, Collectors.counting()));

                        RouteInfo info = new RouteInfo(l,r, path.getWeight(), allTypesCount);
                        routes.add(info);
                    }
                }
            }
        }

        routes.stream().sorted((r1,r2) -> Long.compare(r2.typeOfLocations.getOrDefault(locationType.FRIENDLY, 0L), r1.typeOfLocations.getOrDefault(locationType.FRIENDLY, 0L))).limit(5).forEach(System.out::println);

        double avgCost = routes.stream()
                .mapToDouble(r -> r.cost)
                .average()
                .orElse(0);

        long totalRoutes = routes.size();

        System.out.println("Numar de rute: " + totalRoutes);
        System.out.println("Avg cost: " + avgCost);




    }
    private static void printPath(Location start, Location dest, DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstra){
        if(!start.equals(dest)){
            var path = dijkstra.getPath(start,dest);
            if(path!=null){
                System.out.println(dest.getName() + " Cost: " + path.getWeight() + " prin nodurile: " + path.getVertexList());
            }
        }
    }


}