package models;

// CardReverse will override the method doAction in order invert
// the flow of the game [P1, P2 , P3] => [P3, P2, P1].
public class CardReverse extends CardClassState{
    // Constructor
    public CardReverse(CardClass card) {
        super(card);
    }

    // Explicit implementation of the abstract method swap
    @Override
    protected CardClass doAction(Deck deck, Game game) {
        Game.reverseIterator();
        return null;
    }
}
