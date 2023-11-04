package com.example.demo;

public class CardReverse extends CardClassState{

    public CardReverse(CardClass card) {
        super(card);
    }

    @Override
    protected void doAction() {
        Game.reverseIterator();
    }
}
