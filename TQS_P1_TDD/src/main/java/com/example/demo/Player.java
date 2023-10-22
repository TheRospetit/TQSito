package com.example.demo;

public class Player {

    private String name;
    private boolean winner;
    private boolean blocked;

    // Constructor
    public Player(){
        name = "";
        winner = false;
        blocked = false;
    }

    // Params constructor
    public Player(String nm, boolean wn, boolean blckd){
        name = nm;
        winner = wn;
        blocked = blckd;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public boolean getWinner() {
        return winner;
    }
    public boolean getBlocked() {
        return blocked;
    }

    public void addCards(int numCards){}

    public void setName(String name) {
        this.name = name;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
