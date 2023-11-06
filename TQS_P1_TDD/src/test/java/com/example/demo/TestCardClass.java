package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

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

    @Test
    public void TestDoAction(){
        cartita = new CardClass(Actions.BLOCK, Colours.RED);
        player = new Player("Jan", false, false);
        deck = new Deck();
        cartita.doAction(deck, player); // SE LE TENDRIA QUE AÑADIR UN MOCK
        CardClassState state = cartita.getState();
        String status = cartita.getState().getState();
        assert  state != null : "Wrong state set doAction function";
        assert status.equals(Actions.BLOCK) : "Wrong state set CardClassState";
    }
}
