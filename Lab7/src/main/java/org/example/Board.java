package org.example;

import java.util.*;

public class Board {

    private List<Word> words = new ArrayList<>();

    public synchronized void submitWord(Player player, Word word) {
        words.add(word);
        System.out.println(player.getName() + " submited " + word);
    }

    public synchronized List<Word> getWords() {
        return new ArrayList<>(words);
    }


}
