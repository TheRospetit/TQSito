package com.example.demo;

public class Mock_CardPlusFour extends CardPlusFour {

  public Mock_CardPlusFour(CardClass card) {
    super(card);
  }

  @Override
  protected CardClass doAction(Deck deck, Game game){
    Integer number = 4;
    Player reciverPlayer = game.getListPlayers().get((game.getCurrentPlayer() + 1) % game.getNumPlayers() );
    for (int i = 0; i < number; i++) {
      deck.giveCardsToPlayer(reciverPlayer);
    }
    deck.setCardPlayed(new CardClass((Integer) null, Colours.BLUE));
    return new CardClass((Integer) null, Colours.BLUE) ;

  }


}
