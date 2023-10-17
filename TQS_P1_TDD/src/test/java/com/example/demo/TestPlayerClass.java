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

        }catch(NoSuchFieldException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().toString().contains("Error"));
        }

        System.out.println("Testing if atribute types are correct");
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
        ArrayList<Parameter> parameters = new ArrayList<>(List.of(Player.class.getConstructor(String.class, boolean.class, boolean.class).getParameters()));

        assertEquals(String.class, parameters.get(0).getType());
        assertEquals(boolean.class, parameters.get(1).getType());
        assertEquals(boolean.class, parameters.get(2).getType());
    }
}
