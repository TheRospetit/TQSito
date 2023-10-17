package com.example.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {
    private static final int NUM_PLAYERS = 4;



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

    }

}

