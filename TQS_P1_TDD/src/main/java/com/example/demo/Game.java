package com.example.demo;
import java.util.ArrayList;

public class Game {
    private int numPlayers;
    private int currentPlayer;
    private static Integer iterator;
    private ArrayList<Player> listPlayers = new ArrayList<>();
    public Deck deck;

    //Remains to test when Pol's part finished.
    private ArrayList<Statistics> stats = new ArrayList<>();
    //

    public Game(){}
    public Game(int numPlayers, int currentPlayer, ArrayList<Player> listPlayers){
        this.numPlayers = numPlayers;
        this.currentPlayer = currentPlayer;
        this.listPlayers = listPlayers;
        iterator = 1;
    }

    public Integer getNumPlayers(){return numPlayers;}
    public Integer getCurrentPlayer() { return currentPlayer;}
    public CardClass getLastCardPlayed() {return deck.getPlayedCards().getLast();}
    public ArrayList<Player> getListPlayers() { return listPlayers;}
    public void giveHand() {
        this.deck = new Deck();
        deck.giveStarterCardsToPlayers(listPlayers);
    }

    public void setLastCardPlayed(CardClass card) { deck.getPlayedCards().add(card); }
    public Integer getIterator(){return iterator;}
    public static void reverseIterator(){ iterator *= -1; }
}
