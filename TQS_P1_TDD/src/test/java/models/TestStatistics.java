package models;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.ArrayList;
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


    @Test
    public void testDefaultConstructor() {
        // Arrange
        Statistics statistics = new Statistics();

        // Act & Assert
        assert "".equals(statistics.getPlayer_name());
        assert 0 == statistics.getNumGames();
        assert 0 == statistics.getNumWins();
        assert 0 == statistics.getNumCards();
    }

    @Test
    public void testParamConstructor() {
        // Arrange
        Statistics statistics = new Statistics("Player1", 5, 3, 10);

        // Act & Assert
        assert "Player1".equals(statistics.getPlayer_name());
        assert 5 == statistics.getNumGames();
        assert 3 == statistics.getNumWins();
        assert 10 == statistics.getNumCards();
    }

    @Test
    public void testParamConstructor_WinsGreaterThanGames() {
        // Arrange
        Statistics statistics = new Statistics("Player1", 3, 5, 8);

        // Act & Assert
        assert "Player1".equals(statistics.getPlayer_name());
        assert 5 == statistics.getNumGames();
        assert 5 == statistics.getNumWins();
        assert 8 == statistics.getNumCards();
    }

    @Test
    public void testGetters() {
        // Arrange
        Statistics statistics = new Statistics("Player1", 5, 3, 10);

        // Act & Assert
        assert "Player1".equals(statistics.getPlayer_name());
        assert 5 == statistics.getNumGames();
        assert 3 == statistics.getNumWins();
        assert 10 == statistics.getNumCards();
    }

    @Test
    public void testAddDrawn2Stat() {
        // Arrange
        Statistics statistics = new Statistics();

        // Act
        statistics.addDrawn2Stat();

        // Assert
        assert 2 == statistics.getNumCards();
    }

    @Test
    public void testAddDrawn4Stat() {
        // Arrange
        Statistics statistics = new Statistics();

        // Act
        statistics.addDrawn4Stat();

        // Assert
        assert 4 == statistics.getNumCards();
    }

    @Test
    public void testAddWin() {
        // Arrange
        Statistics statistics = new Statistics();

        // Act
        statistics.addWin();

        // Assert
        assert 1 == statistics.getNumWins();
    }

    @Test
    public void testAddGame() {
        // Arrange
        Statistics statistics = new Statistics();

        // Act
        statistics.addGame();

        // Assert
        assert 1 == statistics.getNumGames();
    }

    @Test
    public void testGetPlayerStatsListFromDDBB() {
        // Arrange
        ArrayList<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Player1", false));
        playerList.add(new Player("Player2", false));
        playerList.add(new Player("Player3", false));

        DataBase myDatabase = new DataBase("test.txt");
        myDatabase.createFile();
        myDatabase.writeToFile("Player1,5,3,10", myDatabase);
        myDatabase.writeToFile("Player2,8,4,15", myDatabase);

        // Act
        ArrayList<Statistics> statList = Statistics.getPlayerStatsListFromDDBB(myDatabase, playerList);

        // Assert
        assert 3 == statList.size();

        // Player1 stats
        Statistics statsPlayer1 = statList.get(0);
        assert "Player1".equals(statsPlayer1.getPlayer_name());
        assert 5 == statsPlayer1.getNumGames();
        assert 3 == statsPlayer1.getNumWins();
        assert 10 == statsPlayer1.getNumCards();

        // Player2 stats
        Statistics statsPlayer2 = statList.get(1);
        assert "Player2".equals(statsPlayer2.getPlayer_name());
        assert 8 == statsPlayer2.getNumGames();
        assert 4 == statsPlayer2.getNumWins();
        assert 15 == statsPlayer2.getNumCards();

        // Player2 stats
        Statistics statsPlayer3 = statList.get(2);
        assert "Player3".equals(statsPlayer3.getPlayer_name());
        assert 0 == statsPlayer3.getNumGames();
        assert 0 == statsPlayer3.getNumWins();
        assert 0 == statsPlayer3.getNumCards();
    }

}
