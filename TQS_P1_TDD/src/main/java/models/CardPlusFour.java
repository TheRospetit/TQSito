package models;

public class CardPlusFour extends CardClassState{

    public CardPlusFour (CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        //Add five cards to the next player
        Player reciverPlayer;

        Integer number = 4;

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

        //Specify which colour is desired
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
