package io.github.some_example_name;

public class GameRules {
    private int playerScore;
    private int dealerScore;
    private int playerWins;
    private int dealerWins;

    public GameRules() {
        this.playerScore = 0;
        this.dealerScore = 0;
        this.playerWins = 0;
        this.dealerWins = 0;
    }

    public void updateScores(int playerCardValue, int dealerCardValue) {
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
        } else



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
}
