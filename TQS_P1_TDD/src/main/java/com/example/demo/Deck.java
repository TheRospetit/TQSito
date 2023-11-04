package com.example.demo;
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
    Collections.shuffle(playableCards); // Shuffle the deck
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
    ArrayList<String> actions_once = new ArrayList<>(Arrays.asList(Actions.COLOUR_SWAP, Actions.PLUS_FOUR));
    // We only have a Zero card for each color
    // Initialize card with 0
    for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
      playableCards.add(new CardClass(0, colours.get(i))); // Initialize a card and add it to the deck.
    }

    for (int j = 1; j < 9; j++){ // Iterate through the rest of colours
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

    // Initialize Special Cards of each colour (Block, Reverse, +2).
    for (int j = 0; j < actions_once.size(); j++) { // Iterate through actions_once.
      for (int i = 0; i < colours.size(); i++) { // Iterate through colours.
        playableCards.add(new CardClass(actions_once.get(j), colours.get(i))); // Initialize a card and add it to the deck.
        playableCards.add(new CardClass(actions_once.get(j), colours.get(i))); // Initialize a card and add it to the deck.
      }
    }
  }

  public void giveCardsToPlayer(Player player){
    player.giveCard(playableCards.getFirst());
    playableCards.remove(0);
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
