package io.github.some_example_name;


public class Cards {
    private String rank;
    private String suit;

    public Cards(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank(String fileName) {
        int underscoreIndex = fileName.indexOf("_");
        String rank = fileName.substring(0, underscoreIndex);
        return rank;
    }

    public String getAssetFileName() {
        return rank + "_" + suit + ".png";
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

