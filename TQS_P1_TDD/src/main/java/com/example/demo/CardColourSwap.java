package com.example.demo;

public class CardColourSwap extends CardClassState{

    public CardColourSwap(CardClass card) {
        super(card);
    }

    @Override
    protected void doAction() {
        System.out.println("A qué color quieres cambiar [azul, rojo, verde, amarillo]: ");
        // Falta hacer el in
    }
}
