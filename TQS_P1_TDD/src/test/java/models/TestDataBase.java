package models;

import models.DataBase;
import models.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class TestDataBase {

    private static final String TEST_FILE_NAME = "test_stats.txt";
    private static final String PLAYER_NAME = "TestPlayer";
    private static final String PLAYER_LINE = "TestPlayer,0,0,0";

    private DataBase testDB;

    @BeforeEach
    void setUp() {
        testDB = new DataBase(TEST_FILE_NAME);
        testDB.createFile(); // Crear el archivo antes de cada prueba
    }

    @AfterEach
    void tearDown() {
        testDB = null;
        // Limpiar el archivo de prueba después de cada prueba
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEST_FILE_NAME));
            if (reader.readLine() != null) {
                // Si hay al menos una línea, el archivo no está vacío y puede ser eliminado
                reader.close();
                // Eliminar el archivo
                java.nio.file.Files.delete(java.nio.file.Paths.get(TEST_FILE_NAME));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testCreateFile() {
        assertTrue(java.nio.file.Files.exists(java.nio.file.Paths.get(TEST_FILE_NAME)));
    }

    @Test
    void testWriteToFile() {
        testDB.writeToFile(PLAYER_LINE, testDB);
        String lineInFile = testDB.searchString(PLAYER_NAME);
        assertEquals(PLAYER_LINE, lineInFile);
    }

    @Test
    void testWriteToFilePlayerExists() {
        // Intentar escribir la misma línea para un jugador existente
        testDB.writeToFile(PLAYER_LINE, testDB);
        testDB.writeToFile(PLAYER_LINE, testDB);
        String lineInFile = testDB.searchString(PLAYER_NAME);
        assertEquals(PLAYER_LINE, lineInFile); // La línea no debería haber cambiado
    }

    @Test
    void testSearchStringNotFound() {
        assertNull(testDB.searchString("NonExistentPlayer"));
    }

    @Test
    void testSearchStringFound() {
        testDB.writeToFile(PLAYER_LINE, testDB);
        assertNotNull(testDB.searchString(PLAYER_NAME));
    }

    @Test
    void testReplaceLineInFile() {
        testDB.writeToFile(PLAYER_LINE, testDB);
        String newLine = "TestPlayer,1,1,1";
        testDB.replaceLineInFile(PLAYER_LINE, newLine);
        String lineInFile = testDB.searchString(PLAYER_NAME);
        assertEquals(newLine, lineInFile);
    }

    @Test
    void testStringLineToStatistics() {
        Statistics stat = testDB.stringLineToStatistics(PLAYER_LINE);
        assertNotNull(stat);
        assertEquals(PLAYER_NAME, stat.getPlayer_name());
        assertEquals(0, stat.getNumGames());
        assertEquals(0, stat.getNumWins());
        assertEquals(0, stat.getNumCards());
    }

    @Test
    void testFormatStatLine() {
        Statistics stat = new Statistics(PLAYER_NAME, 1, 1, 1);
        String formattedLine = testDB.formatStatLine(stat);
        assertEquals("TestPlayer,1,1,1", formattedLine);
    }

    @Test
    void testViewAllStatistics() {
        // Verificar que la salida en la consola se realiza sin excepciones
        testDB.viewAllStatistics(testDB);
    }
}
