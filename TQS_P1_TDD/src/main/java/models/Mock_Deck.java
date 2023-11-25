package models;

import java.util.ArrayList;
import java.util.Arrays;

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


}
