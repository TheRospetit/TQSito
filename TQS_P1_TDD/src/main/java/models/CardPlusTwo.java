package models;

public class CardPlusTwo extends CardClassState{

    public CardPlusTwo(CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        //CUIDADO QUE SE LO ESTAMOS DANDO AL MISMO JUGADOR, NO AL SIGUIENTE
        // Puede ser que les estamos dando más de 2 cartas, falta debugar un poco más
        Player reciverPlayer;
        Integer number = 2;

        if (game.getIterator() == 1) {
            reciverPlayer  = game.getListPlayers().get((game.getCurrentPlayer() + game.getIterator()) % game.getNumPlayers() );
        } else {
            if (game.getCurrentPlayer() + game.getIterator() == -1) {
                reciverPlayer = game.getListPlayers().get((game.getNumPlayers() - 1));
            } else {
                reciverPlayer = game.getListPlayers().get((game.getCurrentPlayer() + game.getIterator()));
            }
        }

        for (int i = 0; i < number; i++) {
            deck.giveCardsToPlayer(reciverPlayer);
        }
        return null;

    }
}
