package org.example;

public class Word {
    public String text;
    private int score;

    public Word(String text, int score) {
        this.text = text;
        this.score = score;
    }

    public String getText() {
        return text;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Word{" +
                "text='" + text + '\'' +
                ", score=" + score +
                '}';
    }
}
