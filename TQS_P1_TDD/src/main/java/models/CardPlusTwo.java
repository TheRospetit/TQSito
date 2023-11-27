package models;

// CardPlusTwo will override the method doAction in order to add
// 2 cards to next player based in the iterator
public class CardPlusTwo extends CardClassState{
    // Constructor
    public CardPlusTwo(CardClass card) {
        super(card);
    }

    // Explicit implementation of the abstract method swap
    @Override
    protected CardClass doAction(Deck deck, Game game) {
        Player reciverPlayer;
        Integer number = 2;

        // Calculate the following player
        if (game.getIterator() == 1) {
            reciverPlayer  = game.getListPlayers().get((game.getCurrentPlayer() + game.getIterator()) % game.getNumPlayers() );
        } else {
            if (game.getCurrentPlayer() + game.getIterator() == -1) {
                reciverPlayer = game.getListPlayers().get((game.getNumPlayers() - 1));
            } else {
                reciverPlayer = game.getListPlayers().get((game.getCurrentPlayer() + game.getIterator()));
            }
        }

        for (int i = 0; i < number; i++) { // Give 2 cards to the following player
            deck.giveCardsToPlayer(reciverPlayer);
        }
        return null;

    }
}
