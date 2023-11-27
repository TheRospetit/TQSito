package models;

public class CardColourSwap extends CardClassState{

    public CardColourSwap(CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        while(true)
        {
            System.out.println("A qué color quieres cambiar [B, R, G, Y]: ");
            // Falta hacer el in
            String input = game.getMyScanner().nextLine();


            switch (input){
                case("B"): return new CardClass(Actions.COLOR_SWAP, Colors.BLUE) ;
                case("R"): return new CardClass(Actions.COLOR_SWAP, Colors.RED);
                case("G"): return new CardClass(Actions.COLOR_SWAP, Colors.GREEN);
                case("Y"): return new CardClass(Actions.COLOR_SWAP, Colors.YELLOW);
                default: System.out.println("Color no correcto! Introduce otro de nuevo");
            }
        }
    }
}
