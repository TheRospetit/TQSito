package models;

public class CardBlock extends CardClassState{

    public CardBlock(CardClass card) {
        super(card);

    }

    @Override
    protected CardClass doAction(Deck deck, Game game) {
        game.nextPlayer();
        return null;
    }
}
