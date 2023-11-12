package com.example.demo;

public class CardPlusTwo extends CardClassState{

    public CardPlusTwo(CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        //CUIDADO QUE SE LO ESTAMOS DANDO AL MISMO JUGADOR, NO AL SIGUIENTE
        // Puede ser que les estamos dando más de 2 cartas, falta debugar un poco más
        Integer number = 2;
        Integer nextPlayer = game.getCurrentPlayer() + 1;
        nextPlayer = nextPlayer % game.getNumPlayers();
        Player reciverPlayer = game.getListPlayers().get(nextPlayer);
        for (int i = 0; i < number; i++) {
            deck.giveCardsToPlayer(reciverPlayer);
        }
        return null;

    }
}
