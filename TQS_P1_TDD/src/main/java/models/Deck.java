package models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Deck {
  /*Attributes*/
  private int numCards;
  private ArrayList<CardClass> playableCards = new ArrayList<>();
  private ArrayList<CardClass> playedCards = new ArrayList<>();

  // Constructors //
  public Deck(){
    initializeDeck(); // Initialize the deck
    shuffleCardsDeck();
    numCards = playableCards.size();
    playedCards = new ArrayList<CardClass>();
  }
  public Deck(int nCards, ArrayList<CardClass> myCards, ArrayList<CardClass> lastCard){
    numCards= nCards;
    playableCards = myCards;
    playedCards = lastCard;
  }

  // Setters & Getters //
  public ArrayList<CardClass> getPlayableCards() {return playableCards;}
  public ArrayList<CardClass> getPlayedCards() {return playedCards;}
  public Integer getNumCards() {return numCards;}
  public void setPlayableCards(ArrayList<CardClass> inputTest){
        playableCards = inputTest;
        this.numCards = inputTest.size();
    }
  public void setCardPlayed(CardClass card) { playedCards.add(card); }

  // Other methods //
  public void shuffleCardsDeck(){
    Collections.shuffle(playableCards);
  }
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
    // Initialize card with 0, and special card +4 and colour swap
      for (String string : colours) { // Iterate through colours.
          playableCards.add(new CardClass(0, string)); // Initialize a card and add it to the deck.
          playableCards.add(new CardClass(Actions.PLUS_FOUR, null)); //Initialize a plus four card but without a colour
          playableCards.add(new CardClass(Actions.COLOR_SWAP, null)); //Initialize a plus four card but without a colour

      }
    // Color + number cards
    for (int j = 1; j <= 9; j++){ // Iterate through the rest of colours
        for (String colour : colours) { // Iterate through colours.
            playableCards.add(new CardClass(j, colour)); // Initialize a card and add it to the deck.
            playableCards.add(new CardClass(j, colour)); // Initialize a card and add it to the deck.
        }
    }
    // Initialize Special Cards of each colour (Block, Reverse, +2).
      for (String s : actions_twice) { // Iterate through actions_twice.
          for (String colour : colours) { // Iterate through colours.
              playableCards.add(new CardClass(s, colour)); // Initialize a card and add it to the deck.
              playableCards.add(new CardClass(s, colour)); // Initialize a card and add it to the deck.
          }
      }



  }
  public void giveCardsToPlayer(Player player){
    player.giveCard(playableCards.get(0));
    playableCards.remove(0); // Delete from playableCard the card that was given
    numCards--;
  }
  public void giveStarterCardsToPlayers(ArrayList<Player> players) {
    // Each player Starts with 7 cards.
    for (int i = 0; i < 7; i++) {
        for (Player player : players) {
            giveCardsToPlayer(player);
        }
    }
  }
  public void refillPlayableCards() {
    // Once the playableCards is empty, we reassign and shuffle the playedCards to playableCards
    for (CardClass card : playedCards) {
      if (card.getAction() != null) {
        if (card.getAction().equals(Actions.PLUS_FOUR) || card.getAction().equals(Actions.COLOR_SWAP)) {
          card.nullifyColour();
        }
      }
      playableCards.add(card);
    }
    playedCards.clear();
  }
}
