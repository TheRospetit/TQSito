package models;

import java.util.ArrayList;
import java.util.Objects;
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

    public ArrayList<CardClass> getHand() {
        return hand;
    }

    /** Returns true if the current cardToPlay from the player can be played. */
    public Boolean testCardToPlay(CardClass cardToP, CardClass lcPlayed) { // Test if card can
        /*
        if (Objects.equals(cardToP.getColour(), lcPlayed.getColour()) // Mismo color
                || cardToP.getNumber() != null && lcPlayed.getNumber() != null
                || Objects.equals(cardToP.getNumber(), lcPlayed.getNumber())
                || Objects.equals(cardToP.getAction(), Actions.COLOUR_SWAP) // Carta de intercambio de color disponible en la mano
                || Objects.equals(cardToP.getAction(), Actions.PLUS_FOUR)  // Puede jugar un +4
                || (Objects.equals(cardToP.getAction(), Actions.PLUS_TWO)
                && Objects.equals(lcPlayed.getAction(), Actions.PLUS_TWO)) // Última carta jugada fue un +2 y tengo un +2 disponible.
                || !Objects.equals(cardToP.getAction(), lcPlayed.getAction())) // Last card played was a +2 and I have a +2 available.
        {
            return true;
        }
        */

        if(cardToP.getAction()== null && lcPlayed.getAction() == null && cardToP.getNumber()!= null && lcPlayed.getNumber() != null){
            if(cardToP.getColour() == lcPlayed.getColour() || cardToP.getNumber() == lcPlayed.getNumber() ||
                    (cardToP.getNumber() == lcPlayed.getNumber() && cardToP.getColour() == lcPlayed.getColour())){
                return true;
            }
            else{
                return false;
            }
        }
        if(cardToP.getNumber()== null && lcPlayed.getNumber() == null && cardToP.getAction()!= null && lcPlayed.getAction() != null){
            if( (cardToP.getColour() == lcPlayed.getColour() && cardToP.getAction() == lcPlayed.getAction()) ||
                    cardToP.getColour() == lcPlayed.getColour() || cardToP.getAction() == lcPlayed.getAction() ||
                    cardToP.getAction() == Actions.PLUS_FOUR || cardToP.getAction() == Actions.COLOUR_SWAP ) {
                return true;
            }
            else{
                return false;
            }
        }

        if(cardToP.getAction() == Actions.COLOUR_SWAP || cardToP.getAction() == Actions.PLUS_FOUR){
            return true;
        }








        return false;





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

        //Printing lastCardPlayed
        System.out.println("Last Card Played: " + lastCardPlayed.getAction() + " " + lastCardPlayed.getColour() + " " + lastCardPlayed.getNumber());
        //Printing hand cards
        int i = 1;
        for(CardClass carta : hand){
            System.out.println("Carta " + i + ": " + carta.getAction() +" " + carta.getColour() + " " + carta.getNumber());
            i++;
        }
        CardClass returnedCard = new CardClass();
        if (canPlayCard(lastCardPlayed)) { // Checks if the player can play any card
            Scanner scanner = new Scanner(System.in);
            while (true) {
                //waiting to input variables
                System.out.println("Select one card: ");
                String input = scanner.nextLine();
                int numPlayedCard = Integer.parseInt(input); //convert input into an integer, we should be care if its not correct value

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //cuidado con la carta que se devuelve, hay que modificarlo para los casos de cartas con acciones/////////////////
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
            else{
                return returnedCard;
            }
        }
        return null;  // Player can't play card.
    }
}
