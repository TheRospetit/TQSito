package com.example.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class TestPlayerClass {

    Player player;
    @Test
    public void TestConstructor(){
        player = new Player();
    }

    @Test
    public void TestAtributes(){
        try {
            Player p = new Player();
            Class pl = p.getClass();
            Field name = pl.getDeclaredField("name");
            Field blocked = pl.getDeclaredField("blocked");
            Field winner = pl.getDeclaredField("winner");

            Field nonExistent = pl.getDeclaredField("nonExistent");

            System.out.println("Field = " + name);
            System.out.println("Field = " + blocked);
            System.out.println("Field = " + winner);
            System.out.println("Field = " + nonExistent); // Should throw an error of nonExistent
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
