package models;

// CardClassState is the abstract class that manages the actions of the different type of cards.
public abstract class CardClassState {
    private CardClass card;
    private String state;

    public CardClassState(CardClass card){
        this.card = card;
        state = card.getAction();
    }

    public String getState(){return state;}

    // Abstract functions that will be used depending on the card type.
    protected abstract CardClass doAction(Deck deck, Game game);
}
