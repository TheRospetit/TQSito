package models;

// Is inheritance of Deck, it just override the method that shuffles
// the cards inside the deck. It is important to notify that is just
// for testing.


import java.util.ArrayList;
import java.util.Arrays;


public class Mock_Deck extends Deck {
  // Constructor
  public Mock_Deck(){
    super();
  }

  // Explicit implementation of the abstract method shuffle
  @Override
  public void shuffleCardsDeck(){
    //The father method shuffle the deck, but we don't want that, so we
    // override the method so we the method does nothing
  }


}
