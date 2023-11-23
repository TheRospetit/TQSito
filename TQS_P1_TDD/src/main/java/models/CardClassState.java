package models;

public abstract class CardClassState {
    //Variables que vamos a usar
    private CardClass card;
    private String state;

    // Constructor por defecto
    public CardClassState(CardClass card){
        this.card = card;
        state = card.getAction();
    }
    // Getter
    public String getState(){return state;}

    // Funciones abstractas que se usarán dependiendo del tipo de carta que tenemos
    protected abstract CardClass doAction(Deck deck, Game game);
}
