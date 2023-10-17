package com.example.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {
    private static final int NUM_PLAYERS = 4;
    private static final int CURRENT_PLAYER = 0;

    Game game;
    @Test
    public void TestConstructor() { game = new Game();}

    @Test
    public void TestAtributes() {
        //CHECKS IF THERE IS 4 ATTRIBUTE
        List<Field> allFields = Arrays.asList(Game.class.getDeclaredFields());
        assertEquals(4, allFields.size());

        //TEST TO VERIFY IF ATTRIBUTE numPlayers DOES EXISTS
        Field numPlayers = allFields.stream().filter(field -> field.getName().equals("numPlayers")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(int.class, numPlayers.getType());

        //TEST TO VERIFY IF ATRIBUTE currentPlayer DOES EXISTS
        Field currentPlayer = allFields.stream().filter(field -> field.getName().equals("currentPlayer")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(int.class, currentPlayer.getType());

        //TEST TO VERIFY IF ATTRIBUTE lastCardPlayed DOES EXISTS
        Field lastCardPlayed = allFields.stream().filter(field -> field.getName().equals("lastCardPlayed")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(CardClass.class, lastCardPlayed.getType());

        //TEST TO VERIFY IF ATTRIBUTE listPlayers DOES EXISTS
        Field listPlayer = allFields.stream().filter(field -> field.getName().equals("listPlayers")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(ArrayList.class, listPlayer.getType());




    }

    @Test
    public void TestConstructorWithParameters(){

        //CREATION OF A LIST WITH USERS THAT WILL USE IN THE CONSTRUCTOR
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false , false);
        Player pol = new Player("Pol", false, false);
        Player josias = new Player("Josias", false, false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);

        //FIRST WE CREATE THE GAME WITH GLOBAL VARIABLES AND OUR PLAYER LIST CREATED BEFORE
        Game myNewParamGame = new Game(NUM_PLAYERS, CURRENT_PLAYER, new CardClass(), myPlayerList );
        //CHECK IF HAS BEEN CREATED SUCESSFULLY
        assert myNewParamGame != null : "Game had not been initiliaze by constructor with params";
        //THEN WE CHECK IF NUMPLAYERS AND CURRENTPLAYERS HAVE BEEN INITIALIZE WITH THE CORRECT VALUES
        assert NUM_PLAYERS == myNewParamGame.getNumPlayers() : "Number players had not been initialize properly";
        assert CURRENT_PLAYER == myNewParamGame.getCurrentPlayer() : "Current player had not been initiliaze properly";
        //CHECK IF LIST AND CARD ARE NOT NULL
        assert myNewParamGame.getLastCardPlayed() != null : "Last Card had not been initiliaze properly";
        assert myNewParamGame.getListPlayers() != null : "List players had not been initiliaze properly";

        //NOW WE CHECK IF ALL VALUES ARE CORRECT AS THE ORIGINL LIST DOES.
        assert myNewParamGame.getListPlayers().get(0) == jan : "Jan had not been introduce in the list properly ";
        assert myNewParamGame.getListPlayers().get(1) == pol : "Pol had not been introduce in the list properly ";
        assert myNewParamGame.getListPlayers().get(2) == josias : "Josias had not been introduce in the list properly ";

        //REMAINS CHECK CARD IF ITS EQUAL, FALTA QUE LO PROGRAMEN xddd//



    }






}

