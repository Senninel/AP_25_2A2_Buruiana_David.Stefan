package org.example;

import java.util.*;

public class Bag {
    private List<Tile> tiles = new ArrayList<>();
    private Random rand = new Random();

    public Bag() {
        for(char c = 'A'; c <= 'Z'; c++) {
            for(int index = 0; index < 10; index++){
                int points = rand.nextInt(10) + 1;
                tiles.add(new Tile(c, points));
            }
        }

        Collections.shuffle(tiles);
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for(int index = 0; index < howMany && !tiles.isEmpty(); index++) {
            extracted.add(tiles.remove(0));
        }
        return extracted;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }
}
