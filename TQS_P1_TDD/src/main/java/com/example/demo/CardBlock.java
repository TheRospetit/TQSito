package com.example.demo;

public class CardBlock extends CardClassState{

    public CardBlock(CardClass card) {
        super(card);

    }

    @Override
    protected void doAction(Deck deck, Player player) {
        // Next player can't play
    }
}