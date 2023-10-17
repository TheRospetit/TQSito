package com.example.demo;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStatistics {

    @Test
    public void TestAtributes() {
        // Test if the num of Attributes is correct.
        List<Field> allFields = Arrays.asList(Statistics.class.getDeclaredFields());
        assertEquals(4, allFields.size());

        // Check all fields of Statistics Class
        Field playerName = allFields.stream().filter(field -> field.getName().equals("player_name")).findFirst().orElseThrow(() -> new RuntimeException("Field 'player_name' not found"));
        assertEquals(String.class, playerName.getType());

        Field numCards = allFields.stream().filter(field -> field.getName().equals("numCards")).findFirst().orElseThrow(() -> new RuntimeException("Field 'numCards' not found"));
        assertEquals(int.class, numCards.getType());

        Field numGames = allFields.stream().filter(field -> field.getName().equals("numGames")).findFirst().orElseThrow(() -> new RuntimeException("Field 'numGames' not found"));
        assertEquals(int.class, numGames.getType());

        Field numWins = allFields.stream().filter(field -> field.getName().equals("numWins")).findFirst().orElseThrow(() -> new RuntimeException("Field 'numWins' not found"));
        assertEquals(int.class, numWins.getType());


    }

}
