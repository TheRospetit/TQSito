package models;

public class CardReverse extends CardClassState{

    public CardReverse(CardClass card) {
        super(card);
    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        Game.reverseIterator();
        return null;
    }
}
