package models;

import java.util.ArrayList;
import java.util.Scanner;

// The player class manages all the features that a player can do on the game. Each player has a name, a boolean
// value to determine if they won the game or not, a hand of cards to play the game and a card to play to manage if
// the card that is trying to play can be played.
public class Player {

    private String name;
    private boolean winner;
    private CardClass cardToPlay;
    private ArrayList<CardClass> hand = new ArrayList<>();

    // Constructors
    public Player(){
        name = "";
        winner = false;
    }
    public Player(String name, boolean winner){
        this.name = name;
        this.winner = winner;
    }

    // Getters & Setters
    public String getName() {
        return name;
    }
    public boolean getWinner() {
        return winner;
    }
    public void giveCard(CardClass card) {
        this.hand.add(card);
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWinner(boolean winner) {
        this.winner = winner;
    }
    public void setHand(ArrayList<CardClass> newHand) {
        this.hand = newHand;
    }
    public ArrayList<CardClass> getHand() {
        return hand;
    }

    // Other methods
    public int numberHandCards(){
        return hand.size();
    }

    /** Returns true if the current cardToPlay from the player can be played. */
    public Boolean testCardToPlay(CardClass cardToP, CardClass lcPlayed) {
        // Cards with number => true if same color, same number or both
        if(cardToP.getAction()== null && lcPlayed.getAction() == null && cardToP.getNumber()!= null && lcPlayed.getNumber() != null){
            return (cardToP.getColour().equals(lcPlayed.getColour()) || cardToP.getNumber().equals(lcPlayed.getNumber()) ||
                    (cardToP.getNumber().equals(lcPlayed.getNumber()) && cardToP.getColour().equals(lcPlayed.getColour())));
        }

        // If it has an action it checks if it is a "special" one (COLOR_SWAP or PLUS_FOUR)
        if (cardToP.getAction() != null) {
            if(cardToP.getAction().equals(Actions.COLOR_SWAP) || cardToP.getAction().equals(Actions.PLUS_FOUR)){
                return true;
            }
        }

        // Cards with action (they do not have number) | True => true if same color, same action or both
        if(cardToP.getNumber()== null && lcPlayed.getNumber() == null && cardToP.getAction() != null && lcPlayed.getAction() != null){
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
        System.out.println(this.name + " can't play a card, drawing a card");
        return false;
    }

    public CardClass playCard(CardClass lastCardPlayed, Deck deck, Game game){
        CardClass returnedCard; // Card that is going to get set form PLUS_FOUR ot COLOR_SWAP
        if (canPlayCard(lastCardPlayed)) { // Checks if the player can play any card
            while (true) {
                int numPlayedCard = hand.size() + 2;
                while (numPlayedCard <= 0 || numPlayedCard > hand.size()){
                    //waiting to input variables
                    System.out.println("Select one card (Position of the card [ 1 - " + hand.size() + " ]): ");
                    String number = game.getMyScanner().nextLine();
                    try { // Checks that the input is an Integer
                        numPlayedCard = Integer.parseInt(number);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Insert a number, not anything else, thanks");
                    }
                }

                cardToPlay =  hand.get(numPlayedCard - 1); // sets the card chosen

                if (testCardToPlay(cardToPlay, lastCardPlayed)) { // Checks if the player selected a playable card
                    returnedCard = cardToPlay.doAction(deck, game); // Card does its action if it has one
                    hand.remove(numPlayedCard - 1); // Remove card from hand
                    if (hand.isEmpty()){
                        this.winner = true;
                    }
                    break;
                } else {
                  System.out.println("Please select a correct card to play.");
                }
            }

            if(returnedCard == null){
                return cardToPlay;
            } else {
                return returnedCard; // For the plus four and colour swap
            }
        }
        return null;  // Player can't play card.
    }
}
