package com.example.demo;

public class CardColourSwap extends CardClassState{

    public CardColourSwap(CardClass card) {
        super(card);
    }

    @Override
    protected void doAction(Deck deck, Player player) {
        System.out.println("A qué color quieres cambiar [azul, rojo, verde, amarillo]: ");
        // Falta hacer el in
    }
}
