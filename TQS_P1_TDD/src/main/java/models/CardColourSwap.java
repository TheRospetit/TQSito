package models;

// CardColourSwap will override the method doAction in order to return a value
// color selected by the user.

public class CardColourSwap extends CardClassState{
    // Constructor
    public CardColourSwap(CardClass card) {
        super(card);
    }

    // Explicit implementation of the abstract method swap
    @Override
    protected CardClass doAction(Deck deck, Game game) {
        while(true)
        {
            System.out.println("Which color you desire to change [B, R, G, Y]: ");
            String input = game.getMyScanner().nextLine();


            switch (input){
                case("B"): return new CardClass(Actions.COLOR_SWAP, Colors.BLUE) ;
                case("R"): return new CardClass(Actions.COLOR_SWAP, Colors.RED);
                case("G"): return new CardClass(Actions.COLOR_SWAP, Colors.GREEN);
                case("Y"): return new CardClass(Actions.COLOR_SWAP, Colors.YELLOW);
                default: System.out.println("Incorrect value, introduce a new one !");
            }
        }
    }
}
