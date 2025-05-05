package io.github.some_example_name;

public class GameRules {
    private static int playerScore;

    public GameRules() {
        playerScore = 0;
    }

    public static String winCondition(int playerScore, int dealerScore)
    {

        if (playerScore > 21) {
            return "Dealer Wins";
        } else if (dealerScore > 21) {
            return "Player Wins";
        } else if (playerScore == 21) {
            return "Player Wins";
        } else if (dealerScore == 21) {
            return "Dealer Wins";
        } else if (playerScore > dealerScore) {
            return "Player Wins";
        } else if (dealerScore > playerScore) {
            return "Dealer Wins";
        } else {
            return "Draw";
        }
    }


    public static int getCardValue(String rank) {
        switch (rank) {
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "10":
            case "Jack":
            case "Queen":
            case "King":
                return 10;
            case "Ace":
                if (playerScore + 11 > 21) {
                    return 1;
                } else {
                    return 11;
                }
        }
        return 0;
    }
}
