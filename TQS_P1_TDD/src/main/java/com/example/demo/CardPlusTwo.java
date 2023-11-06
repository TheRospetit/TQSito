package com.example.demo;

public class CardPlusTwo extends CardClassState{

    public CardPlusTwo(CardClass card) {
        super(card);
    }

    @Override
    protected void doAction(Deck deck, Player player) {
        Integer number = 2;
        for (int i = 0; i < number; i++) {
            deck.giveCardsToPlayer(player);
        }
    }
}
