package models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Deck {
  /*Attributes*/
  private Boolean notMoreCards;
  private int numCards;
  private ArrayList<CardClass> playableCards = new ArrayList<>();
  private ArrayList<CardClass> playedCards = new ArrayList<>();


  public Deck(){
    initializeDeck(); // Initialize the deck
    shuffleCardsDeck();
    notMoreCards = false;
    numCards = playableCards.size();
    playedCards = new ArrayList<CardClass>();
  }

  // TODO. Check for removal: This does not have any type of useful implementation
  public Deck(Boolean thereIsCards, int nCards, ArrayList<CardClass> myCards, ArrayList<CardClass> lastCard){
    notMoreCards = thereIsCards;
    numCards= nCards;
    playableCards = myCards;
    playedCards = lastCard;
  }

  //TODO TRYNG TO IMPLEMENT IF MOCK COULD WORK PROPERLY
  public void shuffleCardsDeck(){
    Collections.shuffle(playableCards);
  }



  //TODO
  public void initializeDeck() {
    /*
    SUMMARY of Cards:
    - 2 cards of each colour from 1 to 9.     -- Total: 72
    - 1 Zero Card for each colour.            -- Total:  4
    - 2 reverse cards for each colour.        -- Total:  8
    - 2 Block turn cards for each colour.     -- Total:  8
    - 2 PLUS_TWO (+2) cards for each colour.  -- Total:  8
    - 4 ColorSwaps.                           -- Total:  4
    - 4 PLUS_FOUR (+4).                       -- Total:  4
                                                DECK = 108 Cards
     */
    ArrayList<String> colours = new ArrayList<>(Arrays.asList(Colors.BLUE, Colors.RED, Colors.GREEN, Colors.YELLOW));
    ArrayList<String> actions_twice = new ArrayList<>(Arrays.asList(Actions.BLOCK, Actions.PLUS_TWO, Actions.REVERSE));
    // We only have a Zero card for each color
    // Initialize card with 0, and specail card +4 and colour swap
    for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
      playableCards.add(new CardClass(0, colours.get(i))); // Initialize a card and add it to the deck.
      playableCards.add(new CardClass(Actions.PLUS_FOUR,null)); //Initialize a plus four card but without a colour
      playableCards.add(new CardClass(Actions.COLOR_SWAP,null)); //Initialize a plus four card but without a colour

    }

    for (int j = 1; j <= 9; j++){ // Iterate through the rest of colours
      for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
        playableCards.add(new CardClass(j, colours.get(i))); // Initialize a card and add it to the deck.
        playableCards.add(new CardClass(j, colours.get(i))); // Initialize a card and add it to the deck.
      }
    }
    // Initialize Special Cards of each colour (Block, Reverse, +2).
    for (int j = 0; j < actions_twice.size(); j++) { // Iterate through actions_twice.
      for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
        playableCards.add(new CardClass(actions_twice.get(j), colours.get(i))); // Initialize a card and add it to the deck.
        playableCards.add(new CardClass(actions_twice.get(j), colours.get(i))); // Initialize a card and add it to the deck.
      }
    }



  }

  public void giveCardsToPlayer(Player player){
    player.giveCard(playableCards.get(0));
    playableCards.remove(0);
    numCards--;
  }

  public void giveStarterCardsToPlayers(ArrayList<Player> players) {
    // Each player Starts with 7 cards.
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < players.size(); j++) {
        giveCardsToPlayer(players.get(j));
      }
    }
  }

  public Boolean getNotMoreCards() {return notMoreCards;}
  public ArrayList<CardClass> getPlayableCards() {return playableCards;}
  public Integer getNumCards() {return numCards;}
  public ArrayList<CardClass> getPlayedCards() {return playedCards;}

  public void setCardPlayed(CardClass card) { playedCards.add(card); }
}
