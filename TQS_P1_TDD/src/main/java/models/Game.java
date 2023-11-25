package models;
import java.util.ArrayList;

public class Game {

    private ScannerClass myScanner;
    private int numPlayers;
    private int currentPlayer;
    private static Integer iterator;
    private ArrayList<Player> listPlayers = new ArrayList<>();

    public Deck deck;
    private static Integer nextPlayerIndex = 0;
    public boolean winner = false;
    private ArrayList<Statistics> stats = new ArrayList<>();     // TODO. Remains to test when Pol's part finished.

    // CONSTRUCTORS
    public Game(){}
    public Game(int numPlayers, int currentPlayer, ArrayList<Player> listPlayers, ScannerClass inputScanner){
        myScanner = inputScanner;
        this.numPlayers = numPlayers;
        this.currentPlayer = currentPlayer;
        this.listPlayers = listPlayers;
        iterator = 1;
    }

    // GET & SET METHODS
    public ScannerClass getMyScanner() {return myScanner;}
    public Integer getNumPlayers(){return numPlayers;}
    public Integer getCurrentPlayer() { return currentPlayer;}
    public CardClass getLastCardPlayed() {return deck.getPlayedCards().get(deck.getPlayedCards().size()-1);}
    public ArrayList<Player> getListPlayers() { return listPlayers;}
    public Integer getIterator(){return iterator;}
    public void setLastCardPlayed(CardClass card) { deck.getPlayedCards().add(card); }
    public void setCurrentPlayer(int i) { currentPlayer = i;}

    public static Integer getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    // OTHER METHODS
    public void giveHand() {
        this.deck = new Deck();
        deck.giveStarterCardsToPlayers(listPlayers);
    }
    public static void reverseIterator(){ iterator *= -1; }
    public void nextPlayer() {
        nextPlayerIndex = (nextPlayerIndex + iterator) ;

        if (nextPlayerIndex == -1) nextPlayerIndex = this.numPlayers-1; // We are going counterclockwise (reverse)
        else nextPlayerIndex = nextPlayerIndex % this.numPlayers; // Clockwise
    }

    public Integer getNextPlayer(){ return nextPlayerIndex; }

    public CardClass getActualPlayerPlayedCard(Player actualPlayer){
        CardClass cardPlayed = actualPlayer.playCard(this.getLastCardPlayed(), this.deck, this );
        return cardPlayed;
    }
    public boolean playerRound(Player actualPlayer, CardClass cardPlayed){
        //Player actualPlayer = listPlayers.get(nextPlayerIndex);
        boolean draw = false; // Just to check if the player draws a card, so in the controller we can display the playerCards again.
        //System.out.println("Your turn " + actualPlayer.getName());
        //CardClass cardPlayed = actualPlayer.playCard(this.getLastCardPlayed(), this.deck, this );
        if (cardPlayed == null) {
            this.deck.giveCardsToPlayer(actualPlayer);
            draw = true;
        } else {
            this.setLastCardPlayed(cardPlayed); // Whatever the card is it will also do its action (+2, +4, reverse...)
            this.deck.setCardPlayed(cardPlayed);
            this.endGame(actualPlayer);
        }
        this.setCurrentPlayer((this.getCurrentPlayer()+1) % this.getNumPlayers());
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
