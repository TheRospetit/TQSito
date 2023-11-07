package com.example.demo;

public class CardBlock extends CardClassState{

    public CardBlock(CardClass card) {
        super(card);

    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        Player punishPlayer = game.getListPlayers().get((game.getCurrentPlayer() + 1) % game.getNumPlayers() );
        punishPlayer.setBlocked(true);
        return null;
    }
}
