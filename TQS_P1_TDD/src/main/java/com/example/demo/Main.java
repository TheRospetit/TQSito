package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public Main() { main(null);}
    public static void main(String[] args){
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false , false);
        Player pol = new Player("Pol", false, false);
        Player josias = new Player("Josias", false, false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);

        Game game = new Game(3,0, myPlayerList);
        game.giveHand(); // Initializes the deck and gives the hands of each player
        game.setLastCardPlayed(game.deck.getPlayableCards().get(0)); // We set the first card
        for (int i = 0; i < myPlayerList.size(); i++) {
            CardClass cardPlayed = myPlayerList.get(i).playCard(game.getLastCardPlayed());
            if (cardPlayed == null) {
                game.deck.giveCardsToPlayer(myPlayerList.get(i));
            } else {
                game.setLastCardPlayed(cardPlayed);
                game.deck.setCardPlayed(cardPlayed);
            }
        }
    }
}


