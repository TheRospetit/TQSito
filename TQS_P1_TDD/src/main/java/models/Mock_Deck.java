package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Mock_Deck extends Deck {
  public Mock_Deck(){

    //super(true, 108, new);
    super();
  }

  @Override
  public void shuffleCardsDeck(){
    //The father method shuffle the deck, but we don't want that, so we
    // override the method so we the method does nothing
  }

  // TODO. Check for removal: This does not have any type of useful implementation

  public Mock_Deck(Boolean thereIsCards, int nCards, ArrayList<CardClass> myCards, ArrayList<CardClass> lastCard){
    mock_notMoreCards = thereIsCards;
    mock_numCards= nCards;
    mock_playableCards = myCards;
    mock_playedCards = lastCard;
  }
  @Override
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
    ArrayList<String> colours = new ArrayList<>(Arrays.asList(Colours.BLUE, Colours.RED, Colours.GREEN, Colours.YELLOW));
    ArrayList<String> actions_twice = new ArrayList<>(Arrays.asList(Actions.BLOCK, Actions.PLUS_TWO, Actions.REVERSE));
    // We only have a Zero card for each color
    // Initialize card with 0, and specail card +4 and colour swap
    for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
      mock_playableCards.add(new CardClass(0, colours.get(i))); // Initialize a card and add it to the deck.
      mock_playableCards.add(new CardClass(Actions.PLUS_FOUR,null)); //Initialize a plus four card but without a colour
      mock_playableCards.add(new CardClass(Actions.COLOUR_SWAP,null)); //Initialize a plus four card but without a colour

    }

    for (int j = 1; j <= 9; j++){ // Iterate through the rest of colours
      for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
        mock_playableCards.add(new CardClass(j, colours.get(i))); // Initialize a card and add it to the deck.
        mock_playableCards.add(new CardClass(j, colours.get(i))); // Initialize a card and add it to the deck.
      }
    }
    // Initialize Special Cards of each colour (Block, Reverse, +2).
    for (int j = 0; j < actions_twice.size(); j++) { // Iterate through actions_twice.
      for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
        mock_playableCards.add(new CardClass(actions_twice.get(j), colours.get(i))); // Initialize a card and add it to the deck.
        mock_playableCards.add(new CardClass(actions_twice.get(j), colours.get(i))); // Initialize a card and add it to the deck.
      }
    }



  }
  @Override
  public void giveCardsToPlayer(Player player){
    player.giveCard(mock_playableCards.get(0));
    mock_playableCards.remove(0);
    mock_numCards--;
  }
  @Override
  public void giveStarterCardsToPlayers(ArrayList<Player> players) {
    // Each player Starts with 7 cards.
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < players.size(); j++) {
        giveCardsToPlayer(players.get(j));
      }
    }
  }
  @Override
  public Boolean getNotMoreCards() {return mock_notMoreCards;}
  @Override
  public ArrayList<CardClass> getPlayableCards() {return mock_playableCards;}
  @Override
  public Integer getNumCards() {return mock_numCards;}
  @Override
  public ArrayList<CardClass> getPlayedCards() {return mock_playedCards;}
  @Override
  public void setCardPlayed(CardClass card) { mock_playedCards.add(card); }
}
