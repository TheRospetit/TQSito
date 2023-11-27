package models;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class TestDeck {
  Deck deck;
  @Test
  public void TestConstructor() {
    deck = new Deck();
    assert deck != null : "This deck has not been instantiated properly";

  }

  @Test
  public void TestConstructorWithParamaters(){
    ArrayList<CardClass> cards = new ArrayList<>();
    CardClass card1 = new CardClass();
    CardClass card2 = new CardClass();
    cards.add(card1); cards.add(card2);

    ArrayList<CardClass> cardsPlayed = new ArrayList<>();
    CardClass card3 = new CardClass();
    CardClass card4 = new CardClass();
    cardsPlayed.add(card3); cardsPlayed.add(card4);

    Deck testedDeck = new Deck(10, cards, cardsPlayed );

    assert testedDeck != null : "Deck has not been initialize properly";
    assert testedDeck.getNumCards() == 10 : "Num Cards has not been set properly";
    assert testedDeck.getPlayableCards().get(0) == card1 : "Playable Cards has not been set properly";
    assert testedDeck.getPlayedCards().get(0) == card3 : "Played cards has not been set properly";

  }

  @Test
  public void TestAtributes(){
    List<Field> allFields = Arrays.asList(Deck.class.getDeclaredFields());
    assertEquals(3, allFields.size());



    Field nCards = allFields.stream().filter(field -> field.getName().equals("numCards")).findFirst().orElseThrow(()
        -> new RuntimeException("Field not found"));
    assertEquals(int.class, nCards.getType());
  }

  @Test
  public void TestGetters(){
    ArrayList<CardClass> cards = new ArrayList<>();
    CardClass card1 = new CardClass();
    CardClass card2 = new CardClass();
    cards.add(card1); cards.add(card2);

    ArrayList<CardClass> cardsPlayed = new ArrayList<>();
    CardClass card3 = new CardClass();
    CardClass card4 = new CardClass();
    cardsPlayed.add(card3); cardsPlayed.add(card4);

    Deck testedDeck = new Deck(10, cards, cardsPlayed );

    assert testedDeck.getPlayedCards().getClass() == ArrayList.class : "Get Played Cards list does not return an arraylist";
    assert testedDeck.getPlayableCards().getClass() == ArrayList.class : "Get Playable Cards list does not return an array";
    assert testedDeck.getNumCards().getClass() == Integer.class : "Get Num Cards does not return a integer";

    assert testedDeck.getPlayableCards() == cards : "Deck getPlayableCards incorrect";
    assert testedDeck.getPlayedCards() == cardsPlayed : "Deck getPlayedCards incorrect";
    assert testedDeck.getNumCards() == 10 : "Deck getNumCards incorrect";
  }

  @Test
  public void TestRefillPlayableCards() {
    ArrayList<CardClass> playable = new ArrayList<>();
    ArrayList<CardClass> played = new ArrayList<>();
    CardClass card1 = new CardClass(Actions.PLUS_FOUR, Colors.RED);
    CardClass card2 = new CardClass(Actions.COLOR_SWAP, Colors.YELLOW);
    CardClass card3 = new CardClass(7, Colors.BLUE);
    CardClass card4 = new CardClass(Actions.PLUS_TWO, Colors.GREEN);
    played.add(card1); played.add(card2); played.add(card3); played.add(card4);

    Deck testDeck = new Deck(4, playable, played);

    testDeck.refillPlayableCards();
    assert(playable.size() == 4) : "refillPlayableCards incorrect playable cards";
    assert(played.isEmpty()) : "refillPlayableCards incorrect played cards";
  }

}
