package models;

public class CardClass {
    // VARIABLES QUE USAMOS DEPENDIENDO DE LA CARTA
    private Integer number;
    private String colour;
    private String action;
    private CardClassState state;

    // CONSTRUCTORES
    public CardClass(){}
    public CardClass(Integer number, String colour){ // Constructor en caso de que nos llegue una carta normal
        this.number = number;
        this.colour = colour;
    }

    public CardClass(String action, String colour){ // Constructor en caso de que nos llegue una carta especial
        this.action = action;
        this.colour = colour;

        // SWITCH CASE PARA DECLARAR EL STATE CON EL HIJO QUE LE TOCA
        switch (action){
            case Actions.REVERSE:
                this.state = new CardReverse(this);
                break;

            case Actions.BLOCK:
                this.state = new CardBlock(this);
                break;

            case Actions.PLUS_TWO:
                this.state = new CardPlusTwo(this);
                break;

            case Actions.PLUS_FOUR:
                this.state = new CardPlusFour(this);
                break;

            case Actions.COLOUR_SWAP:
                this.state = new CardColourSwap(this);
                break;

            default:
                assert true : "Unknown action " + action;
                //System.exit(-1);
                break;
        }
    }
    public Integer getNumber(){return number;}
    public String getColour(){return colour;}
    public String getAction(){return action;}
    public CardClassState getState(){return state;}
    public void setState(CardClassState newState) {
        if(newState == null && number != null) {
            this.state =newState;
        }
        else{
            if(newState != null && number == null){
                this.state = newState;
            }
        }
    }
    public CardClass doAction(Deck deck, Game game){

        if (this.action != null){
            CardClass myCard = new CardClass();
            myCard = state.doAction(deck, game);
            return myCard;
        }
        return null;
    }

}
