package models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestCardClass {
    CardClass cartita;

    Deck deck;
    @Test
    public void TestConstructorDefault(){
        cartita = new CardClass();
        assert(cartita != null) : "The Card has not been created";
    }
    @Test
    public void TestConstructorNumberColour(){
        cartita = new CardClass(4,Colours.RED);
        assert !cartita.equals(new CardClass(4, Colours.RED)) : "Inicializacion con numero errónea";
    }
    @Test
    public void TestConstructorActionColour(){
        cartita = new CardClass(Actions.BLOCK, Colours.RED);
        assert !cartita.equals(new CardClass(Actions.BLOCK, Colours.RED)) : "Inicialitzación BLOCK incorrecto";

        cartita = new CardClass(Actions.REVERSE, Colours.YELLOW);
        assert !cartita.equals(new CardClass(Actions.REVERSE, Colours.YELLOW)) : "Inicialitzación REVERSE incorrecto";

        cartita = new CardClass(Actions.COLOUR_SWAP, Colours.BLUE);
        assert !cartita.equals(new CardClass(Actions.COLOUR_SWAP, Colours.BLUE)) : "Inicialitzación COLOUR_SWAP incorrecto";

        cartita = new CardClass(Actions.PLUS_TWO, Colours.GREEN);
        assert !cartita.equals(new CardClass(Actions.PLUS_TWO, Colours.GREEN)) : "Inicialitzación PLUS_TWO incorrecto";

        cartita = new CardClass(Actions.PLUS_FOUR, Colours.BLUE);
        assert !cartita.equals(new CardClass(Actions.PLUS_FOUR, Colours.BLUE)) : "Inicialitzación PLUS_FOUR incorrecto";

        cartita = new CardClass("NON_EXISTENT", Colours.RED);
        assert !cartita.equals(new CardClass("NON_EXISTENT", Colours.RED)) : "Inicialitzación NON_EXISTENT incorrecto";

    }

    @Test
    public void TestGetters(){
        cartita = new CardClass(7,Colours.RED);
        Integer number = cartita.getNumber();
        assert number != null : "Wrong number initialization or wrong get";

        cartita = new CardClass(Actions.BLOCK, Colours.RED);
        String action = cartita.getAction();
        assert action != null : "Wrong action initialization or wrong get";

        String colour = cartita.getColour();
        assert colour != null : "Wrong colour initialization or wrong get";

        cartita = new CardClass(Actions.BLOCK, Colours.RED);
        CardClassState checkClassState = cartita.getState();
        assert (checkClassState != null ) : "The card has not been given a correct ClassState";

        cartita = new CardClass(7, Colours.RED);
        checkClassState = cartita.getState();
        assert (checkClassState == null ) : "It's a number card, it should not have a ClassState";




    }

    @Test
    public void TestdoAction(){
        // Setting common variables for the test //

        ArrayList<Player> testedPlayers = new ArrayList<>();
        Player janCarlo = new Player("Jan", false);
        Player polilla = new Player("Pol", false);
        testedPlayers.add(janCarlo); testedPlayers.add(polilla);

        ScannerClass scannerClass = new Mock_ScannerClass("azul", "verde","amarillo", "agucate" ,"rojo", "azul", "verde","amarillo","agucate", "rojo" );

        Game testedGame = new Game(2, 0, testedPlayers, scannerClass);
        Deck testedDeck = new Deck();

        //Testing number cards//
        CardClass testedCard1 = new CardClass(1, "green");
        CardClass testedCard2 = new CardClass(1, "blue");
        CardClass testedCard3 = new CardClass(1, "yellow");
        CardClass testedCard4 = new CardClass(1, "red");

        ArrayList<CardClass> testedCardsNumbers = new ArrayList<>();
        testedCardsNumbers.add(testedCard1);
        testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);
        testedCardsNumbers.add(testedCard4);

        // As they are number cards, none of them has the possibility to execute //
        // method doAction, then they should return a null value //
        for(CardClass testCard : testedCardsNumbers)
        {
            CardClass returnedValue = new CardClass();
            returnedValue = testCard.doAction(testedDeck, testedGame);
            assert(returnedValue == returnedValue) :"All number cards should return null once execute doAction " ;
        }

        // Testing plus2 card //
        testedCard1 = new CardClass("plus_two", "green");
        testedCard2 = new CardClass("plus_two", "blue");
        testedCard3 = new CardClass("plus_two", "yellow");
        testedCard4 = new CardClass("plus_two", "red");

        testedCardsNumbers.clear();
        testedCardsNumbers.add(testedCard1);testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);testedCardsNumbers.add(testedCard4);

        for(CardClass testCard : testedCardsNumbers)
        {
            CardClass returnedValue = new CardClass();
            returnedValue = testCard.doAction(testedDeck, testedGame);
            assert(returnedValue == null) : "All +2 cards should return himself, not a empty card";
        }

        assert(0 == testedGame.getListPlayers().get(0).numberHandCards()) : "Plus two cards has not added cards properly";
        assert(8 == testedGame.getListPlayers().get(1).numberHandCards()) : "Plus two cards has not added cards properly";

        // Testing plus4 card //
        testedCard1 = new CardClass("plus_four", "green");
        testedCard2 = new CardClass("plus_four", "blue");
        testedCard3 = new CardClass("plus_four", "yellow");
        testedCard4 = new CardClass("plus_four", "red");

        testedCardsNumbers.clear();
        testedCardsNumbers.add(testedCard1);testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);testedCardsNumbers.add(testedCard4);

        CardClass returnedValue = new CardClass();

        returnedValue = testedCardsNumbers.get(0).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.BLUE ) : "doAction mock test is not returning colour blue card selected"; // Check for BLUE option

        returnedValue = testedCardsNumbers.get(1).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.GREEN ) : "doAction mock test is not returning colour blue card selected"; // Check for GREEN option

        returnedValue = testedCardsNumbers.get(2).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.YELLOW ) : "doAction mock test is not returning colour blue card selected"; // Check for YELLOW option

        returnedValue = testedCardsNumbers.get(3).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.RED ) : "doAction mock test is not returning colour blue card selected"; // Check for RED option


        assert(0 == testedGame.getListPlayers().get(0).numberHandCards()) : "Plus four cards has not added cards properly";
        assert(24 == testedGame.getListPlayers().get(1).numberHandCards()) : "Plus four cards has not added cards properly";

        // Testing swap colour //
        testedCard1 = new CardClass("colour_swap", "green");
        testedCard2 = new CardClass("colour_swap", "blue");
        testedCard3 = new CardClass("colour_swap", "yellow");
        testedCard4 = new CardClass("colour_swap", "red");

        testedCardsNumbers.clear();
        testedCardsNumbers.add(testedCard1);testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);testedCardsNumbers.add(testedCard4);

        returnedValue = testedCardsNumbers.get(0).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.BLUE ) : "doAction mock test is not returning colour blue card selected"; // Check for BLUE option

        returnedValue = testedCardsNumbers.get(1).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.GREEN ) : "doAction mock test is not returning colour blue card selected"; // Check for GREEN option

        returnedValue = testedCardsNumbers.get(2).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.YELLOW ) : "doAction mock test is not returning colour blue card selected"; // Check for YELLOW option

        returnedValue = testedCardsNumbers.get(3).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colours.RED ) : "doAction mock test is not returning colour blue card selected"; // Check for RED option



    }

    @Test
    public void TestSetState(){
        CardClass card = new CardClass(6, Colours.GREEN);
        CardClassState newState = new CardBlock(card);

        card.setState(null);
        assert (null == card.getState()): "The getState it's not correct";

        card = new CardClass(Actions.BLOCK, Colours.GREEN);
        card.setState(newState);
        assert ( newState == card.getState()): "The getState it's not correct";



    }

}
