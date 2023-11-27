package models;

// CardColourSwap will override the method doAction in order to return a value
// color selected by the user and adding plus 4 cards to the next player based
// on the iterator.
public class CardPlusFour extends CardClassState{

    // Constructor
    public CardPlusFour (CardClass card) {
        super(card);
    }

    // Explicit implementation of the abstract method swap
    @Override
    protected CardClass doAction(Deck deck, Game game) {

        Player reciverPlayer;
        Integer number = 4;

        // Calculate the next player that will receive the 4 cards
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

        // Specify which colour is desired
        while(true)
        {
            System.out.println("A qué color quieres cambiar [B, R, G, Y]: ");
            String input = game.getMyScanner().nextLine();

            switch (input){
                case("B"):  return new CardClass(Actions.PLUS_FOUR, Colors.BLUE) ;
                case("R"):  return new CardClass(Actions.PLUS_FOUR, Colors.RED);
                case("G"):  return new CardClass(Actions.PLUS_FOUR, Colors.GREEN);
                case("Y"):  return new CardClass(Actions.PLUS_FOUR, Colors.YELLOW);
                default: System.out.println("Color no correcto! Introduce otro de nuevo");
            }
        }
    }
}
