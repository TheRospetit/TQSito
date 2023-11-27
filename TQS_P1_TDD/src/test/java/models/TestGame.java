package models;

import org.junit.jupiter.api.Test;
//import static org.mockito.Mockito.*;


import java.awt.geom.RectangularShape;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;


public class TestGame {
    private static final int NUM_PLAYERS = 4;
    private static final int CURRENT_PLAYER = 0;

    @Test
    public void TestConstructor() {
        Game game = new Game();
        assert(game != null) : "This deck has not been instantiated properly";
    }

    @Test
    public void TestAtributes() {
        //CHECKS IF THERE ARE 9 ATTRIBUTES
        List<Field> allFields = Arrays.asList(Game.class.getDeclaredFields());
        assertEquals(9, allFields.size());

        //TEST TO VERIFY IF ATTRIBUTE numPlayers DOES EXIST
        Field numPlayers = allFields.stream().filter(field -> field.getName().equals("numPlayers")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(int.class, numPlayers.getType());

        //TEST TO VERIFY IF ATRIBUTE currentPlayer DOES EXIST
        Field currentPlayer = allFields.stream().filter(field -> field.getName().equals("currentPlayer")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(int.class, currentPlayer.getType());

        //TEST TO VERIFY IF ATRIBUTE iterator DOES EXIST
        Field iterator = allFields.stream().filter(field -> field.getName().equals("iterator")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(Integer.class, iterator.getType());

        //TEST TO VERIFY IF ATTRIBUTE listPlayers DOES EXIST
        Field listPlayer = allFields.stream().filter(field -> field.getName().equals("listPlayers")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(ArrayList.class, listPlayer.getType());

        Field deck = allFields.stream().filter(field -> field.getName().equals("deck")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(Deck.class, deck.getType());

        Field nextPlayerIndex = allFields.stream().filter(field -> field.getName().equals("nextPlayerIndex")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(Integer.class, nextPlayerIndex.getType());

        Field winner = allFields.stream().filter(field -> field.getName().equals("winner")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(boolean.class, winner.getType());

        Field stats = allFields.stream().filter(field -> field.getName().equals("stats")).findFirst().orElseThrow(()
                -> new RuntimeException("Field not found"));
        assertEquals(ArrayList.class, stats.getType());

    }

    @Test
    public void TestGetters(){
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);

        String input = "blue";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);

        Game gameTested = new Game(2, 0, myPlayerList,  scannerClass);

        assert gameTested.getListPlayers().getClass() == ArrayList.class : "Get List Player does not return an ArrayList";
        assert gameTested.getCurrentPlayer().getClass() == Integer.class : "Get Current Player does not return an Integer";
        assert gameTested.getNumPlayers().getClass() == Integer.class : "Get Num Player does not return an Integer";

        // Testing player index //
        Integer resultValue = gameTested.getNextPlayer();
        assert(resultValue == 0) : "The next player should be Pol (index = 1)";

        // Testing next Player index //
        assert (gameTested.getNextPlayerIndex() == 0) : "The next player index should be 0";




    }

    @Test
    public void TestConstructorWithParameters(){

        //CREATION OF A LIST WITH USERS THAT WILL USE IN THE CONSTRUCTOR
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);
        String input = "blue";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);

        //FIRST WE CREATE THE GAME WITH GLOBAL VARIABLES AND OUR PLAYER LIST CREATED BEFORE
        Game myNewParamGame = new Game(NUM_PLAYERS, CURRENT_PLAYER, myPlayerList, scannerClass);
        //CHECK IF HAS BEEN CREATED SUCESSFULLY
        assert myNewParamGame != null : "Game had not been initiliazed by constructor with params";
        //THEN WE CHECK IF NUMPLAYERS AND CURRENTPLAYERS HAVE BEEN INITIALIZE WITH THE CORRECT VALUES
        assert NUM_PLAYERS == myNewParamGame.getNumPlayers() : "Number players had not been initialized properly";
        assert CURRENT_PLAYER == myNewParamGame.getCurrentPlayer() : "Current player had not been initiliazed properly";
        //CHECK IF LIST IS NOT NULL
        assert myNewParamGame.getListPlayers() != null : "List players had not been initiliazed properly";

        //NOW WE CHECK IF ALL VALUES ARE CORRECT AS THE ORIGINL LIST DOES.
        assert myNewParamGame.getListPlayers().get(0) == jan : "Jan had not been introduced in the list properly ";
        assert myNewParamGame.getListPlayers().get(1) == pol : "Pol had not been introduced in the list properly ";
        assert myNewParamGame.getListPlayers().get(2) == josias : "Josias had not been introduced in the list properly ";

        //REMAINS CHECK CARD IF ITS EQUAL, FALTA QUE LO PROGRAMA JAN//

    }

    @Test
    public void TestChangeIteratorValue(){
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);
        String input = "blue";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);


        Game myGame = new Game(NUM_PLAYERS, CURRENT_PLAYER, myPlayerList, scannerClass);

        Integer inicial = myGame.getIterator();

        Game.reverseIterator();

        Integer end = myGame.getIterator();

        assert inicial != end : "Iterator remains the same " + end.toString();
    }

    @Test
    public void TestGetLastCardPlayed(){
        ArrayList<Player> testedPlayers = new ArrayList<>();
        Player janCarlo = new Player("Jan", false);
        Player polilla = new Player("Pol", false);
        testedPlayers.add(janCarlo); testedPlayers.add(polilla);
        ScannerClass scannerClass = new Mock_ScannerClass("R" );

        Game testedGame = new Game(2, 0, testedPlayers, scannerClass);
        testedGame.giveHand();

        CardClass inputTestCard = new CardClass(6, Colors.RED);
        testedGame.deck.setCardPlayed(inputTestCard);

        CardClass resultTest = testedGame.getLastCardPlayed();

        assert(resultTest == inputTestCard) : "The input a result card does not match";
    }

    @Test
    public void TestsetLastCardPlayed(){
        ArrayList<Player> testedPlayers = new ArrayList<>();
        Player janCarlo = new Player("Jan", false);
        Player polilla = new Player("Pol", false);
        testedPlayers.add(janCarlo); testedPlayers.add(polilla);
        ScannerClass scannerClass = new Mock_ScannerClass("R" );

        Game testedGame = new Game(2, 0, testedPlayers, scannerClass);
        testedGame.giveHand();
        CardClass inputTestCard = new CardClass(6, Colors.RED);
        testedGame.deck.setCardPlayed(inputTestCard);
        CardClass resultTest = testedGame.getLastCardPlayed();

        assert(inputTestCard == resultTest): "The lastCardPlayed does not match with the input";

    }

    @Test
    public void TestSetCurrentPlayer(){
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);


        String input = "8";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);
        Game gameTested = new Game(2, 0, myPlayerList,  scannerClass);
        assert(gameTested.getCurrentPlayer() == 0) :  "The initialize value is not correct";

        gameTested.setCurrentPlayer(2);
        assert(gameTested.getCurrentPlayer() == 2) :  "Set Current Player introduce a wrong value";
    }

    @Test
    public void getNextPlayerIndex(){
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);

        String input = "blue";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);

        Game gameTested = new Game(2, 0, myPlayerList,  scannerClass);
        int resultTest = gameTested.getNextPlayer();
        assert(resultTest == 0): "The next iterator should be 0";

    }

    @Test
    public void TestEndGameAndGameEnded(){
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);

        String input = "blue";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);

        Game gameTested = new Game(2, 0, myPlayerList,  scannerClass);
        for(Player currentPlayer : myPlayerList){
            gameTested.endGame(currentPlayer);
            assert (gameTested.gameEndedWinner() == false) : "For the moment all player should not be winners";
        }
        Player josias2 = new Player("Josias", true);myPlayerList.add(josias2);

        for(int i = 0; i<3; i++) {
            gameTested.endGame(myPlayerList.get(i));
            assert (gameTested.gameEndedWinner() == false) : "For the moment all player should not be winners";
        }
        gameTested.endGame(myPlayerList.get(3));
        assert(gameTested.gameEndedWinner() == true) : "The last player is supposed to be the winner";

    }

    @Test
    public void TestgetActualPlayerPlayedCard() {
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);


        String input = "8";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);
        Game gameTested = new Game(2, 0, myPlayerList,  scannerClass);
        gameTested.giveHand();

        CardClass expectedCard = new CardClass(2, Colors.RED);
        gameTested.deck.setCardPlayed(new CardClass(3, Colors.RED));
        josias.giveCard(new CardClass(2, Colors.RED));

        CardClass resultTest = new CardClass();


        resultTest = gameTested.getActualPlayerPlayedCard(josias);


        assert(expectedCard.getColour() == resultTest.getColour() && expectedCard.getNumber() == resultTest.getNumber()) : "The card class returned should be a 2 red";
    }
    
    @Test
    public void TestPlayerRound(){
        // Setting up variables for test //
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias",   false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);



        String input = "8";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner mockScanner = new Scanner(inputStream);
        ScannerClass scannerClass = new ScannerClass(mockScanner);
        Game gameTested = new Game(2, 0, myPlayerList,  scannerClass);
        gameTested.giveHand();

        // Testing the main path with a current card //

        CardClass testInput = new CardClass(2, Colors.BLUE);
        Boolean testOutput = gameTested.playerRound(josias, testInput);
        assert(testOutput == false) : "The card is not null and the the controller should not print nothing";


        // Testing the other path with a null card //
        testInput = null;
        testOutput = gameTested.playerRound(josias, testInput);
        assert(testOutput == true) : "The card is null and the the controller should print and check if the party is finished";


    }



}

