package com.example.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlayerClass {

    Player player;
    @Test
    public void TestConstructor(){
        player = new Player();
    }

    @Test
    public void TestAtributes(){
        Field name = null;
        Field blocked = null;
        Field winner = null;

        try {
            Player p = new Player();
            Class<? extends Player> pl = p.getClass();
            name = pl.getDeclaredField("name");
            blocked = pl.getDeclaredField("blocked");
            winner = pl.getDeclaredField("winner");
            //Field notDone = pl.getDeclaredField("nonExistent");

            System.out.println("Field = " + name);
            System.out.println("Field = " + blocked);
            System.out.println("Field = " + winner);

        } catch(NoSuchFieldException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().contains("Error"));
        }

        System.out.println("Testing if attribute types are correct");
        assert name != null; // This has also been done before
        assertEquals(String.class, name.getType());

        assert blocked != null; // This has also been done before
        assertEquals(boolean.class, blocked.getType());

        assert winner != null; // This has also been done before
        assertEquals(boolean.class, winner.getType());
        System.out.println("Atributes correct");

    }

    @Test
    public void TestPlayerConstructorParams()throws NoSuchMethodException, SecurityException {
        player = new Player("Paco", false, false);

        // TESTING IF PLAYER HAS BEEN GENERATED
        assert player != null : "Player has not been initialized correctly";
        // TESTING IF INITIALIZATION IS CORRECTLY DONE
        assert player.getName() != null : "Player name has not been initialized correctly";
        assert !player.getBlocked() : "Player blocked has not been initialized correctly";
        assert !player.getWinner() : "Player winner has not been initialized correctly";
    }

    @Test
    public void TestSettersAndGetters(){
        player = new Player();
        //TESTING SETTERS OF PLAYER
        player.setName("Juan");
        player.setBlocked(false);
        player.setWinner(false);

        // TESTING GETTERS OF PLAYER
        assert player.getName() != null: "Player has not been set correctly";
        assert !player.getBlocked() : "Player getBlocked did not work correctly";
        assert !player.getWinner() : "Player getBlocked did not work correctly";
    }
    @Test
    public void TestConstructorDefault() throws NoSuchMethodException, SecurityException{
        player = new Player();
        ArrayList<Parameter> parameters = new ArrayList<>(List.of(Player.class.getConstructor(String.class, boolean.class, boolean.class).getParameters()));

        // TESTING IF PARAMETERS HAVE THE CORRECT TYPE
        assert String.class == parameters.get(0).getType() : "First parameter is not String class";
        assert boolean.class == parameters.get(1).getType() : "Second parameter is not Boolean class";
        assert boolean.class == parameters.get(2).getType() : "Third parameter is not Boolean class";
    }
}
