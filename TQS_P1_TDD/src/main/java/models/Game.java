package models;
import java.util.ArrayList;

public class Game {

    private ScannerClass myScanner;
    private int numPlayers;
    private int currentPlayer;
    private static Integer iterator;
    private ArrayList<Player> listPlayers = new ArrayList<>();
    public Deck deck;
    private Integer nextPlayerIndex = 0;
    public boolean winner = false;
    private ArrayList<Statistics> stats = new ArrayList<>();     // TODO. Remains to test when Pol's part finished.


    // CONSTRUCTORS //
    public Game(){}
    public Game(int numPlayers, int currentPlayer, ArrayList<Player> listPlayers, ScannerClass inputScanner){
        myScanner = inputScanner;
        this.numPlayers = numPlayers;
        this.currentPlayer = currentPlayer;
        this.listPlayers = listPlayers;
        iterator = 1;
    }

    //// GET & SET METHODS ////
    public ScannerClass getMyScanner() {return myScanner;}
    public Integer getNumPlayers(){return numPlayers;}
    public Integer getCurrentPlayer() { return currentPlayer;}
    public CardClass getLastCardPlayed() {return deck.getPlayedCards().get(deck.getPlayedCards().size()-1);}
    public ArrayList<Player> getListPlayers() { return listPlayers;}
    public Integer getIterator(){return iterator;}
    public Integer getNextPlayer(){ return nextPlayerIndex; }
    public Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }
    public void setCurrentPlayer(int i) { currentPlayer = i;}



    // OTHER METHODS
    public void giveHand() {
        this.deck = new Deck();
        deck.giveStarterCardsToPlayers(listPlayers);
    }

    public static void reverseIterator(){ iterator *= -1; }
    public void nextPlayer() {
        // Calculate the nextPlayer based on the iterator and actualPlayer
        nextPlayerIndex = (nextPlayerIndex + iterator) ;

        if (nextPlayerIndex == -1) nextPlayerIndex = this.numPlayers-1; // We are going counterclockwise (reverse)
        else nextPlayerIndex = nextPlayerIndex % this.numPlayers; // Clockwise
    }
    public CardClass getActualPlayerPlayedCard(Player actualPlayer){
        CardClass cardPlayed = actualPlayer.playCard(this.getLastCardPlayed(), this.deck, this );
        return cardPlayed;
    }

    public boolean playerRound(Player actualPlayer, CardClass cardPlayed){
        boolean draw = false; // Just to check if the player draws a card, so in the controller we can display the playerCards again.

        if (cardPlayed == null) {
            this.deck.giveCardsToPlayer(actualPlayer);
            if (this.deck.getNumCards() == 0) {
                this.deck.refillPlayableCards();
            }
            draw = true;
        } else {
            this.deck.setCardPlayed(cardPlayed);
            this.endGame(actualPlayer);
        }
        this.nextPlayer();
        this.setCurrentPlayer(this.getNextPlayer());
        System.out.println("");

        // Control te iterator and the next player that will play
        return draw;
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
