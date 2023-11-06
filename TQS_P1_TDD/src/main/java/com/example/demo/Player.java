package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class Player {

    private String name;
    private boolean winner;
    private boolean blocked;

    private CardClass cardToPlay;

    private ArrayList<CardClass> hand = new ArrayList<>();

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
    public ArrayList<CardClass> getHand() {return hand; }
    public void giveCard(CardClass card) { this.hand.add(card); }
    public void setName(String name) {
        this.name = name;
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }


    /** Returns true if the current cardToPlay from the player can be played. */
    public Boolean testCardToPlay(CardClass cardToP, CardClass lcPlayed) { // Test if card can
        if (Objects.equals(cardToP.getColour(), lcPlayed.getColour()) // Same colour
                || Objects.equals(cardToP.getNumber(), lcPlayed.getNumber())
                || Objects.equals(cardToP.getAction(), Actions.COLOUR_SWAP) // Colour swap card available in hand
                || Objects.equals(cardToP.getAction(), Actions.PLUS_FOUR)  // Can play a +4
                || (Objects.equals(cardToP.getAction(), Actions.PLUS_TWO)
                && Objects.equals(lcPlayed.getAction(), Actions.PLUS_TWO))) // Last card played was a +2 and I have a +2 available.
        {
            return true;
        }
        return false;
    }

    /** Checks if a Player can Play any card from its hand. */
    public Boolean canPlayCard(CardClass lastCardPlayed) {

        if (Objects.equals(lastCardPlayed.getAction(), Actions.BLOCK)) {
            return false;
        }
        for (CardClass currentCard : hand) {
            if (testCardToPlay(currentCard, lastCardPlayed)) {
                return true;
            }
        }
        return false;
    }

    public CardClass playCard(CardClass lastCardPlayed){
        //Printing lastCardPlayed
        System.out.println("Last Card Played: " + lastCardPlayed.getColour() + " " + lastCardPlayed.getAction() + " " + lastCardPlayed.getNumber());


        //Printing hand cards
        int i = 1;
        for(CardClass carta : hand){
            System.out.println("Carta " + i + ": " + carta.getAction() +" " + carta.getColour() + " " + carta.getNumber());
            i++;
        }

        if (canPlayCard(lastCardPlayed)) { // Checks if the player can play any card
            Scanner scanner = new Scanner(System.in);
            while (true) {
                //waiting to input variables
                System.out.println("Select one card: ");
                String input = scanner.nextLine();
                int numPlayedCard = Integer.parseInt(input); //convert input into a integer, we should be care if its not correct value


                cardToPlay =  hand.get( numPlayedCard - 1);
                // if testCard(cardToPlay, lastCardPlayed) == true --> hand.remove();
                if (testCardToPlay(cardToPlay, lastCardPlayed)) { // Checks if the player selected a playable card
                    hand.remove(numPlayedCard - 1);
                    break;
                } else {
                  System.out.println("Please select a correct card to play.");
                }
            }
            //scanner.close();

            return cardToPlay;
        }
        return null;  // Player can't play card.
    }
}
