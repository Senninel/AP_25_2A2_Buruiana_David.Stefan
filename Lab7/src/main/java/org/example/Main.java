package org.example;

public class Main {
    public static void main(String[] args) {
        Bag bag = new Bag();
        Board board = new Board();

        Player p1 = new Player("John", bag, board);
        Player p2 = new Player("Adam", bag, board);

        p1.start();
        p2.start();

        try {
            p1.join();
            p2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Game over.");
    }
}