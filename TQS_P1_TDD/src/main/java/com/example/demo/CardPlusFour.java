package com.example.demo;

public class CardPlusFour extends CardClassState{

    public CardPlusFour (CardClass card) {
        super(card);
    }

    @Override
    protected void doAction() {
        Integer number = 4;
        Player.addCards(number);
    }
}
