package models;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class TestDataBase {
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  private static final String TEST_FILE_NAME = "test_stats.txt";
  private static final String PLAYER_NAME = "TestPlayer";
  private static final String PLAYER_LINE = "TestPlayer,0,0,0";

  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  // private final PrintStream originalOut = System.out;

  private DataBase testDB;

  @Before
  public void setUpStreams() {
    // Redirect stdout and error output to ByteOutputStreams
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    // Restore original stdout and error output after testing
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @BeforeEach
  void setUp() {
    testDB = new DataBase(TEST_FILE_NAME);
    testDB.createFile();
    System.setOut(new PrintStream(outputStream));
  }

  @AfterEach
  void tearDown() {
    testDB = null;
    System.setOut(originalOut);
    try {
      Files.deleteIfExists(Paths.get(TEST_FILE_NAME));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testDatabaseConstructorNoParams(){
    DataBase testDBNoParams = new DataBase();
    assertEquals(testDBNoParams.getFileName(), "stats.txt");
  }
  @Test
  void testCreateFile() {
    String testFileName = "test_stats.txt";
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    System.setOut(new PrintStream(outputStream));

    DataBase testDB = new DataBase(testFileName);

    testDB.createFile();
    System.setOut(originalOut);

    File file = new File(testFileName);
    assertTrue(file.exists());

    // Check the output in the console
    String consoleOutput = outputStream.toString().trim();
    assertTrue(consoleOutput.contains("File created:") || consoleOutput.contains("File already exists"));
  }

  @Test
  void testWriteToFile() {
    testDB.writeToFile(PLAYER_LINE, testDB);
    String lineInFile = testDB.searchString(PLAYER_NAME);
    assertEquals(PLAYER_LINE, lineInFile);
  }

  @Test
  void testWriteToFilePlayerExists() {
    testDB.writeToFile(PLAYER_LINE, testDB);
    testDB.writeToFile(PLAYER_LINE, testDB);
    String lineInFile = testDB.searchString(PLAYER_NAME);
    assertEquals(PLAYER_LINE, lineInFile);
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
  void testStringLineToStatisticsInvalid() {
    assertNull(testDB.stringLineToStatistics("InvalidLine"));
  }

  @Test
  void testFormatStatLine() {
    Statistics stat = new Statistics(PLAYER_NAME, 1, 1, 1);
    String formattedLine = testDB.formatStatLine(stat);
    assertEquals("TestPlayer,1,1,1", formattedLine);
  }


  @Test
  public void testWriteAndSearchString() {
    DataBase dataBase = new DataBase(TEST_FILE_NAME);

    String lineToAdd = "Player1,10,5,20";
    dataBase.writeToFile(lineToAdd, dataBase);

    //Find the newly added line.
    String result = dataBase.searchString("Player1");

    assertEquals(lineToAdd, result);
  }


}