package models;

// CardClass that will manage cards used in the game. The attributes will be used according
// the type of card created {Number , Action}
public class CardClass {
    // Card attributes will be used depending on the type of card
    private Integer number;
    private String colour;
    private String action;
    private CardClassState state;

    // Constructors
    public CardClass(){}
    public CardClass(Integer number, String colour){ // Constructor in number card case
        this.number = number;
        this.colour = colour;
    }

    public CardClass(String action, String colour){ // Constructor in action card case
        this.action = action;
        this.colour = colour;

        // Switch to set up the action state according to the string
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

            case Actions.COLOR_SWAP:
                this.state = new CardColourSwap(this);
                break;

            default:
                assert true : "Unknown action " + action;
                //System.exit(-1);
                break;
        }
    }

    // Getters and Setters
    public Integer getNumber(){return number;}
    public String getColour(){return colour;}
    public String getAction(){return action;}
    public CardClassState getState(){return state;}
    public void setState(CardClassState newState) {
        if(newState == null && number != null) {
            this.state = newState;
        }
        else{
            if(newState != null && number == null){
                this.state = newState;
            }
        }
    }


    // Other methods
    public void nullifyColour() {
        this.colour = null;
    }

    public CardClass doAction(Deck deck, Game game){
        // Just used by those cards which has an associated action
        if (this.action != null){
            CardClass myCard = new CardClass();
            myCard = state.doAction(deck, game);
            return myCard;
        }
        return null;
    }

}
