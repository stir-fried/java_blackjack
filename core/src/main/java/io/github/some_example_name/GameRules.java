package io.github.some_example_name;

public class GameRules {
    private int playerScore;
    private int dealerScore;
    private int playerWins;
    private int dealerWins;
    private int draw;

    public GameRules() {
        this.playerScore = 0;
        this.dealerScore = 0;
        this.playerWins = 0;
        this.dealerWins = 0;
        this.draw = 0;
    }

    public void updateScores(int playerCardValue, int dealerCardValue)
    {
        playerScore += playerCardValue;
        dealerScore += dealerCardValue;

        if (playerScore == 21) {
            playerWins++;
        } else if (dealerScore == 21) {
            dealerWins++;
        } else if (playerScore > 21) {
            dealerWins++;
        } else if (dealerScore > 21) {
            playerWins++;
        } else if (playerScore == dealerScore) {
            draw++;
        }
    }

    public int getPlayerScore() {
        return playerScore;
    }

    public int getDealerScore() {
        return dealerScore;
    }

    public int getPlayerWins() {
        return playerWins;
    }

    public int getDealerWins() {
        return dealerWins;
    }

    public int getDraw() {
        return draw;
    }

    public void resetScores() {
        playerScore = 0;
        dealerScore = 0;
    }

    public int getCardValue(String rank) {
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
