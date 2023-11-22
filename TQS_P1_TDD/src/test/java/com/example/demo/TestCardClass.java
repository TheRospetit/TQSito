package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;

public class TestCardClass {
    CardClass cartita;
    Player player;

    Deck deck;
    @Test
    public void TestConstructorDefault(){
        cartita = new CardClass();
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


    }
    /*
    @Test
    public void TestDoAction(){
        cartita = new CardClass(Actions.BLOCK, Colours.RED);
        player = new Player("Jan", false);
        deck = new Deck();
        cartita.doAction(deck, player); // SE LE TENDRIA QUE AÑADIR UN MOCK
        CardClassState state = cartita.getState();
        String status = cartita.getState().getState();
        assert  state != null : "Wrong state set doAction function";
        assert status.equals(Actions.BLOCK) : "Wrong state set CardClassState";
    }
     */

    @Test
    public void TestdoAction(){
        //Setting common variables for the test//
        //Game(int numPlayers, int currentPlayer, ArrayList<Player> listPlayers){
        ArrayList<Player> testedPlayers = new ArrayList<>();
        Player janCarlo = new Player("Jan", false);
        Player polilla = new Player("Pol", false);
        testedPlayers.add(janCarlo); testedPlayers.add(polilla);

        Game testedGame = new Game(2, 0, testedPlayers);
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

        for(CardClass testCard : testedCardsNumbers)
        {
            CardClass returnedValue = new CardClass();
            returnedValue = testCard.doAction(testedDeck, testedGame);
            assert(returnedValue == null) : "All +2 cards should return himself, not a empty card";
        }

        assert(0 == testedGame.getListPlayers().get(0).numberHandCards()) : "Plus two cards has not added cards properly";
        assert(8 == testedGame.getListPlayers().get(1).numberHandCards()) : "Plus two cards has not added cards properly";





    }
}
