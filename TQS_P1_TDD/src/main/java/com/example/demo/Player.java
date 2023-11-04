package com.example.demo;

import java.util.ArrayList;

public class Player {

    private String name;
    private boolean winner;
    private boolean blocked;

    private ArrayList<CardClass> hand;

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
    public static void addCards(Integer numCards){} // Robar cartas del mazo
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
