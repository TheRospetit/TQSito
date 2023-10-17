package com.example.demo;


public class Statistics {
    String player_name;
    int numCards;
    int numGames;
    int numWins;

    // Constructor base
    public void Statistics(){
        this.player_name = "";
        this.numCards = 0;
        this.numGames = 0;
        this.numWins = 0;
    }

    // Params constructor
    public void Statistics(String pl_name, int nCards, int nGames, int nWins){
        this.player_name = pl_name;
        this.numCards = nCards;
        this.numGames = nGames;
        this.numWins = nWins;
    }

    // Setters and Getters
    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }
    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }
    public int getNumCards() {
        return numCards;
    }
    public int getNumGames() {
        return numGames;
    }
    public int getNumWins() {
        return numWins;
    }
    public String getPlayer_name() {
        return player_name;
    }


}
