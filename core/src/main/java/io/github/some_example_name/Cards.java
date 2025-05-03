package io.github.some_example_name;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cards {
    private String rank;
    private String suit;

    public Cards(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }
    public String getAssetFileName() {
        return rank + "_" + suit + ".png";
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

