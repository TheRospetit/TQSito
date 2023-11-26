package models;

import java.util.ArrayList;
import java.util.Scanner;


public class Player {

    private String name;
    private boolean winner;
    //private boolean blocked; NOT MORE REQUIRED FOR THE MOMENT
    private CardClass cardToPlay;
    private ArrayList<CardClass> hand = new ArrayList<>();

    // Constructor
    public Player(){
        name = "";
        winner = false;
    }

    // Params constructor
    public Player(String name, boolean winner){
        this.name = name;
        this.winner = winner;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public boolean getWinner() {
        return winner;
    }
    public void giveCard(CardClass card) { this.hand.add(card); }
    public void setName(String name) {
        this.name = name;
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    public int numberHandCards(){return hand.size();}

    public void setHand(ArrayList<CardClass> newHand) { this.hand = newHand; }

    public ArrayList<CardClass> getHand() {
        return hand;
    }

    /** Returns true if the current cardToPlay from the player can be played. */
    public Boolean testCardToPlay(CardClass cardToP, CardClass lcPlayed) { // Test if card can be played

        if(cardToP.getAction()== null && lcPlayed.getAction() == null && cardToP.getNumber()!= null && lcPlayed.getNumber() != null){ // Cards have number
            return (cardToP.getColour().equals(lcPlayed.getColour()) || cardToP.getNumber().equals(lcPlayed.getNumber()) ||
                    (cardToP.getNumber().equals(lcPlayed.getNumber()) && cardToP.getColour().equals(lcPlayed.getColour())));
        }

        if (cardToP.getAction() != null) {
            if(cardToP.getAction().equals(Actions.COLOR_SWAP) || cardToP.getAction().equals(Actions.PLUS_FOUR)){
                return true;
            }
        }

        if(cardToP.getNumber()== null && lcPlayed.getNumber() == null && cardToP.getAction() != null && lcPlayed.getAction() != null){ // Cards have action
            if (cardToP.getColour() != null && lcPlayed.getAction() != null){
                return ((cardToP.getColour().equals(lcPlayed.getColour()) && cardToP.getAction().equals(lcPlayed.getAction())) ||
                        cardToP.getColour().equals(lcPlayed.getColour()) || cardToP.getAction().equals(lcPlayed.getAction()));
            }
        }

        // If they have the same color but not both have action or number (ej: 4 red & color_swap red)
        return(cardToP.getColour() != null && lcPlayed.getColour() != null && cardToP.getColour().equals(lcPlayed.getColour()));

    }

    /** Checks if a Player can Play any card from its hand. */
    public Boolean canPlayCard(CardClass lastCardPlayed) {

        for (CardClass currentCard : hand) {
            if (testCardToPlay(currentCard, lastCardPlayed)) {
                return true;
            }
        }
        return false;
    }

    public CardClass playCard(CardClass lastCardPlayed, Deck deck, Game game){
        CardClass returnedCard ;
        if (canPlayCard(lastCardPlayed)) { // Checks if the player can play any card
            while (true) {
                int numPlayedCard = hand.size() + 2;
                while (numPlayedCard <= 0 || numPlayedCard > hand.size()){
                    //waiting to input variables
                    System.out.println("Select one card (Position of the card [ 1 - " + hand.size() + " ]): ");
                    String number = game.getMyScanner().nextLine();
                    try {
                        numPlayedCard = Integer.parseInt(number);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Insert a number, not anything else, thanks");
                    }
                }
                //convert input into an integer, we should be care if its not correct value

                cardToPlay =  hand.get( numPlayedCard - 1);

                // if testCard(cardToPlay, lastCardPlayed) == true --> hand.remove();
                if (testCardToPlay(cardToPlay, lastCardPlayed)) { // Checks if the player selected a playable card
                    returnedCard = cardToPlay.doAction(deck, game);
                    hand.remove(numPlayedCard - 1);
                    if (hand.isEmpty()){
                        this.winner = true;
                    }
                    break;
                } else {
                  System.out.println("Please select a correct card to play.");
                }
            }
            //scanner.close();
            if(returnedCard == null){
                return cardToPlay;
            }
        }
        return null;  // Player can't play card.
    }
}
