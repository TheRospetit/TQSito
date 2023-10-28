package com.example.demo;
import java.util.ArrayList;

public class Game {
    private int numPlayers;
    private int currentPlayer;
    private CardClass lastCardPlayed;
    private ArrayList<Player> listPlayers = new ArrayList<>();

    //Remains to test when Pol's part finished.
    private ArrayList<Statistics> stats = new ArrayList<>();
    //

    public Game(){}
    public Game(int nPlayers, int turnPlayer, CardClass LCP, ArrayList<Player> list){
        numPlayers = nPlayers;
        currentPlayer = turnPlayer;
        lastCardPlayed = LCP;
        listPlayers = list;
    }

    public Integer getNumPlayers(){return numPlayers;}
    public Integer getCurrentPlayer() { return currentPlayer;}
    public CardClass getLastCardPlayed() {return lastCardPlayed;}
    public ArrayList<Player> getListPlayers() { return listPlayers;}
}
