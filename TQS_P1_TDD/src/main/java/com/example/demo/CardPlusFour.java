package com.example.demo;

public class CardPlusFour extends CardClassState{

    public CardPlusFour (CardClass card) {
        super(card);
    }

    @Override
    protected void doAction(Deck deck, Player player) {
        Integer number = 4;
        for (int i = 0; i < number; i++) {
            deck.giveCardsToPlayer(player);
        }
    }
}
