package models;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

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
        cartita = new CardClass(4, Colors.RED);
        assert !cartita.equals(new CardClass(4, Colors.RED)) : "Inicializacion con numero errónea";
    }
    @Test
    public void TestConstructorActionColour(){
        cartita = new CardClass(Actions.BLOCK, Colors.RED);
        assert !cartita.equals(new CardClass(Actions.BLOCK, Colors.RED)) : "Inicialitzación BLOCK incorrecto";

        cartita = new CardClass(Actions.REVERSE, Colors.YELLOW);
        assert !cartita.equals(new CardClass(Actions.REVERSE, Colors.YELLOW)) : "Inicialitzación REVERSE incorrecto";

        cartita = new CardClass(Actions.COLOR_SWAP, Colors.BLUE);
        assert !cartita.equals(new CardClass(Actions.COLOR_SWAP, Colors.BLUE)) : "Inicialitzación COLOUR_SWAP incorrecto";

        cartita = new CardClass(Actions.PLUS_TWO, Colors.GREEN);
        assert !cartita.equals(new CardClass(Actions.PLUS_TWO, Colors.GREEN)) : "Inicialitzación PLUS_TWO incorrecto";

        cartita = new CardClass(Actions.PLUS_FOUR, Colors.BLUE);
        assert !cartita.equals(new CardClass(Actions.PLUS_FOUR, Colors.BLUE)) : "Inicialitzación PLUS_FOUR incorrecto";

        cartita = new CardClass("NON_EXISTENT", Colors.RED);
        assert !cartita.equals(new CardClass("NON_EXISTENT", Colors.RED)) : "Inicialitzación NON_EXISTENT incorrecto";

    }

    @Test
    public void TestGetters(){
        cartita = new CardClass(7, Colors.RED);
        Integer number = cartita.getNumber();
        assert number != null : "Wrong number initialization or wrong get";

        cartita = new CardClass(Actions.BLOCK, Colors.RED);
        String action = cartita.getAction();
        assert action != null : "Wrong action initialization or wrong get";

        String colour = cartita.getColour();
        assert colour != null : "Wrong colour initialization or wrong get";

        cartita = new CardClass(Actions.BLOCK, Colors.RED);
        CardClassState checkClassState = cartita.getState();
        assert (checkClassState != null ) : "The card has not been given a correct ClassState";

        cartita = new CardClass(7, Colors.RED);
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

        ScannerClass scannerClass = new Mock_ScannerClass("B", "G","Y", "agucate" ,"R", "G" , "B","Y","agucate", "R" );

        Game testedGame = new Game(2, 0, testedPlayers, scannerClass);
        Deck testedDeck = new Deck();

        //Testing number cards//
        CardClass testedCard1 = new CardClass(1, Colors.GREEN);
        CardClass testedCard2 = new CardClass(1, Colors.BLUE);
        CardClass testedCard3 = new CardClass(1, Colors.YELLOW);
        CardClass testedCard4 = new CardClass(1, Colors.RED);

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

        // Testing plus 2 card with all the colors//
        testedCard1 = new CardClass(Actions.PLUS_TWO, Colors.GREEN);
        testedCard2 = new CardClass(Actions.PLUS_TWO, Colors.BLUE);
        testedCard3 = new CardClass(Actions.PLUS_TWO, Colors.YELLOW);
        testedCard4 = new CardClass(Actions.PLUS_TWO, Colors.RED);

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

        // Testing plus 4 card with all the colors//
        testedCard1 = new CardClass(Actions.PLUS_FOUR, Colors.GREEN);
        testedCard2 = new CardClass(Actions.PLUS_FOUR, Colors.BLUE);
        testedCard3 = new CardClass(Actions.PLUS_FOUR, Colors.YELLOW);
        testedCard4 = new CardClass(Actions.PLUS_FOUR, Colors.RED);

        testedCardsNumbers.clear();
        testedCardsNumbers.add(testedCard1);testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);testedCardsNumbers.add(testedCard4);

        CardClass returnedValue = new CardClass();

        returnedValue = testedCardsNumbers.get(0).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colors.BLUE ) : "doAction mock test is not returning colour blue card selected"; // Check for BLUE option

        returnedValue = testedCardsNumbers.get(1).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colors.GREEN ) : "doAction mock test is not returning colour blue card selected"; // Check for GREEN option

        returnedValue = testedCardsNumbers.get(2).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colors.YELLOW ) : "doAction mock test is not returning colour blue card selected"; // Check for YELLOW option

        returnedValue = testedCardsNumbers.get(3).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colors.RED ) : "doAction mock test is not returning colour blue card selected"; // Check for RED option


        assert(0 == testedGame.getListPlayers().get(0).numberHandCards()) : "Plus four cards has not added cards properly";
        assert(24 == testedGame.getListPlayers().get(1).numberHandCards()) : "Plus four cards has not added cards properly";

        // Testing swap colour with all the colors//
        testedCard1 = new CardClass(Actions.COLOR_SWAP, Colors.GREEN);
        testedCard2 = new CardClass(Actions.COLOR_SWAP, Colors.BLUE);
        testedCard3 = new CardClass(Actions.COLOR_SWAP, Colors.YELLOW);
        testedCard4 = new CardClass(Actions.COLOR_SWAP, Colors.RED);

        testedCardsNumbers.clear();
        testedCardsNumbers.add(testedCard1);testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);testedCardsNumbers.add(testedCard4);

        returnedValue = testedCardsNumbers.get(0).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new green card";
        assert(returnedValue.getColour() == Colors.GREEN ) : "doAction mock test is not returning colour green card selected"; // Check for GREEN option

        returnedValue = testedCardsNumbers.get(1).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new blue card";
        assert(returnedValue.getColour() == Colors.BLUE ) : "doAction mock test is not returning colour blue card selected"; // Check for blue option

        returnedValue = testedCardsNumbers.get(2).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new yellow card";
        assert(returnedValue.getColour() == Colors.YELLOW ) : "doAction mock test is not returning colour yellow card selected"; // Check for YELLOW option

        returnedValue = testedCardsNumbers.get(3).doAction(testedDeck, testedGame);
        assert(returnedValue != null) : "doAction is returning null, it should return a new red card";
        assert(returnedValue.getColour() == Colors.RED ) : "doAction mock test is not returning colour red card selected"; // Check for RED option

        // Testing reverse colour with all the colors//
        testedCard1 = new CardClass(Actions.REVERSE, Colors.GREEN);
        testedCard2 = new CardClass(Actions.REVERSE, Colors.BLUE);
        testedCard3 = new CardClass(Actions.REVERSE, Colors.YELLOW);
        testedCard4 = new CardClass(Actions.REVERSE, Colors.RED);

        testedCardsNumbers.clear();
        testedCardsNumbers.add(testedCard1);testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);testedCardsNumbers.add(testedCard4);

        Integer iterTest = testedGame.getIterator();
        for(CardClass testCard : testedCardsNumbers)
        {
            iterTest*=-1; // Change the iterTest to match the iterator of the game
            returnedValue = testCard.doAction(testedDeck, testedGame);
            assert(returnedValue == null) : "All reverse cards should return himself, not a empty card";
            assert (testedGame.getIterator().equals(iterTest)) : "doAction Reverse is not changing the iterator order";
        }

        // Testing block colour with all the colors//
        testedCard1 = new CardClass(Actions.BLOCK, Colors.GREEN);
        testedCard2 = new CardClass(Actions.BLOCK, Colors.BLUE);
        testedCard3 = new CardClass(Actions.BLOCK, Colors.YELLOW);
        testedCard4 = new CardClass(Actions.BLOCK, Colors.RED);

        testedCardsNumbers.clear();
        testedCardsNumbers.add(testedCard1);testedCardsNumbers.add(testedCard2);
        testedCardsNumbers.add(testedCard3);testedCardsNumbers.add(testedCard4);

        Integer currentPlayer = testedGame.getCurrentPlayer();
        for(CardClass testCard : testedCardsNumbers)
        {
            returnedValue = testCard.doAction(testedDeck, testedGame);
            assert(returnedValue == null) : "All block cards should return himself, not a empty card";
            // We have only two players, so we return to the same player all the time
            assert (!testedGame.getNextPlayer().equals(currentPlayer)) : "doAction Block is not changing the iterator order";
            currentPlayer = testedGame.getNextPlayer();
        }
    }

    @Test
    public void TestSetState(){
        CardClass card = new CardClass(6, Colors.GREEN);
        CardClassState newState = new CardBlock(card);

        card.setState(null);
        assert (null == card.getState()): "The getState it's not correct";

        card = new CardClass(Actions.BLOCK, Colors.GREEN);
        card.setState(newState);
        assert ( newState == card.getState()): "The getState it's not correct";



    }

}
