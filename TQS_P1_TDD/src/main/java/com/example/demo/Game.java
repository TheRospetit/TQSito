package com.example.demo;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int numPlayers;
    private int currentPlayer;
    private static Integer iterator;
    private ArrayList<Player> listPlayers = new ArrayList<>();

    public Deck deck;
    private Integer nextPlayerIndex = 0;
    public boolean winner = false;
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
    public CardClass getLastCardPlayed() {return deck.getPlayedCards().get(deck.getPlayedCards().size()-1);}
    public ArrayList<Player> getListPlayers() { return listPlayers;}
    public void giveHand() {
        this.deck = new Deck();
        deck.giveStarterCardsToPlayers(listPlayers);
    }

    public void setLastCardPlayed(CardClass card) { deck.getPlayedCards().add(card); }
    public Integer getIterator(){return iterator;}
    public static void reverseIterator(){
        iterator *= -1; }
    public void setCurrentPlayer(int i) { currentPlayer = i;}
    public void playerRound(){
        Player actualPlayer = listPlayers.get(nextPlayerIndex);

        System.out.println("TU TURNO " + actualPlayer.getName());
        CardClass cardPlayed = actualPlayer.playCard(this.getLastCardPlayed(), this.deck, this );
        if (cardPlayed == null) {
            this.deck.giveCardsToPlayer(actualPlayer);
        } else {
            this.setLastCardPlayed(cardPlayed); // Whatever the card is it will also do its action (+2, +4, reverse...)
            this.deck.setCardPlayed(cardPlayed);
            this.endGame(actualPlayer);
        }
        this.setCurrentPlayer((this.getCurrentPlayer()+1) % this.getNumPlayers());
        System.out.println("");

        // Control te iterator and the next player that will play
        //System.out.println(iterator.toString());
        nextPlayerIndex = (nextPlayerIndex + iterator) ;

        if (nextPlayerIndex == -1) nextPlayerIndex = this.numPlayers-1; // We are going counterclockwise (reverse)
        else nextPlayerIndex = nextPlayerIndex % this.numPlayers; // Clockwise


    }

    public void endGame(Player player){
        if (player.getWinner()) {
            this.winner = true;
        }
    }

    public boolean gameEndedWinner(){
        return this.winner;
    }
}
