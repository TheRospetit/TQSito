package com.example.demo;

import org.junit.jupiter.api.Test;

public class TestPlayerClass {

    Player player;
    @Test
    public void TestConstructor(){
        player = new Player();
    }

    @Test
    public void TestAtributes(){
        String playerName = player.name;
        boolean blocked = player.blocked;
        boolean winner = player.winner;
    }
}
