package com.example.demo;

public class CardPlusTwo extends CardClassState{

    public CardPlusTwo(CardClass card) {
        super(card);
    }

    @Override
    protected void doAction() {
        Integer number = 2;
        Player.addCards(number);
    }
}
