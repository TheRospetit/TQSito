package models;

// CardBlock extends CardClassState, so it's a special state of the card. In this case it implements the Block Action.
// This card will have a color and an action and will skip the next player's turn.
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
