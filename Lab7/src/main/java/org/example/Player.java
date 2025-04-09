package org.example;

import java.util.*;

public class Player extends Thread {
    private String name;
    private Bag bag;
    private Board board;
    private Random rand = new Random();
    private int score;

    public Player(String name, Bag bag, Board board) {
        this.name = name;
        this.bag = bag;
        this.board = board;
    }

    @Override
    public void run(){
        while(!bag.isEmpty()){
            List<Tile> tiles = bag.extractTiles(7);
            if(tiles.isEmpty()){ break;}

            StringBuilder wordBuilder = new StringBuilder();
            int wordScore = 0;

            for(Tile tile : tiles){
                wordBuilder.append(tile.getLetter());
                wordScore += tile.getPoints();
            }

            Word word = new Word(wordBuilder.toString(), wordScore);
            board.submitWord(this, word);
            score += wordScore;

            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(name + " finished with score: " + score);
    }


}

