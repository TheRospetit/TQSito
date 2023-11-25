package models;

public class CardColourSwap extends CardClassState{

    public CardColourSwap(CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        boolean color_correcto = false;
        while(!color_correcto)
        {
            System.out.println("A qué color quieres cambiar [B, R, G, Y]: ");
            // Falta hacer el in
            String input = game.getMyScanner().nextLine();


            switch (input){
                case("B"): deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true;  return new CardClass(Actions.COLOR_SWAP, Colors.BLUE) ;
                case("R"):  deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true; return new CardClass(Actions.COLOR_SWAP, Colors.RED);
                case("G"):  deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true ;return new CardClass(Actions.COLOR_SWAP, Colors.GREEN);
                case("Y"):  deck.setCardPlayed(new CardClass((Integer) null, input)); color_correcto = true ;return new CardClass(Actions.COLOR_SWAP, Colors.YELLOW);
                default: System.out.println("Color no correcto! Introduce otro de nuevo");
            }
        }
        return null;
    }
}
