package models;

public class CardPlusFour extends CardClassState{

    public CardPlusFour (CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        //Add five cards to the next player

        Integer number = 4;
        Player reciverPlayer = game.getListPlayers().get((game.getCurrentPlayer() + 1) % game.getNumPlayers() );
        for (int i = 0; i < number; i++) {
            deck.giveCardsToPlayer(reciverPlayer);
        }

        //Specify which colour is desired
        boolean color_correcto = false;
        while(!color_correcto)
        {
            System.out.println("A qué color quieres cambiar [azul, rojo, verde, amarillo]: ");
            String input = game.getMyScanner().nextLine();

            switch (input){
                case("B"): deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true;  return new CardClass(Actions.PLUS_FOUR, Colors.BLUE) ;
                case("R"):  deck.setCardPlayed(new CardClass((Integer) null, input));color_correcto = true; return new CardClass(Actions.PLUS_FOUR, Colors.RED);
                case("G"):  deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true ;return new CardClass(Actions.PLUS_FOUR, Colors.GREEN);
                case("Y"):  deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true ;return new CardClass(Actions.PLUS_FOUR, Colors.YELLOW);
                default: System.out.println("Color no correcto! Introduce otro de nuevo");
            }
        }
        return null;
    }
}
