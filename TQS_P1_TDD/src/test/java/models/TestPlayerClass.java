package models;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlayerClass {

    Player player;
    @Test
    public void TestConstructor(){
        player = new Player();
        assert (player != null) : "User not created properly";
    }

    @Test
    public void TestAtributes(){
        Field name = null;
        Field winner = null;

        try {
            Player p = new Player();
            Class<? extends Player> pl = p.getClass();
            name = pl.getDeclaredField("name");
            winner = pl.getDeclaredField("winner");
            //Field notDone = pl.getDeclaredField("nonExistent");

            System.out.println("Field = " + name);
            System.out.println("Field = " + winner);

        } catch(NoSuchFieldException ex){
            System.out.println(ex);
            assertTrue(ex.getMessage().contains("Error"));
        }

        System.out.println("Testing if attribute types are correct");
        assert name != null; // This has also been done before
        assertEquals(String.class, name.getType());


        assert winner != null; // This has also been done before
        assertEquals(boolean.class, winner.getType());
        System.out.println("Atributes correct");

    }

    @Test
    public void TestPlayerConstructorParams()throws NoSuchMethodException, SecurityException {
        player = new Player("Paco", false);

        // TESTING IF PLAYER HAS BEEN GENERATED
        assert player != null : "Player has not been initialized correctly";
        // TESTING IF INITIALIZATION IS CORRECTLY DONE
        assert player.getName() != null : "Player name has not been initialized correctly";
        assert !player.getWinner() : "Player winner has not been initialized correctly";
    }

    @Test
    public void TestSettersAndGetters(){
        player = new Player();
        //TESTING SETTERS OF PLAYER
        player.setName("Juan");
        player.setWinner(false);

        // TESTING GETTERS OF PLAYER
        assert player.getName() != null: "Player has not been set correctly";
        assert !player.getWinner() : "Player getWinner did not work correctly";
    }
    @Test
    public void TestConstructorDefault() throws NoSuchMethodException, SecurityException{
        player = new Player();
        ArrayList<Parameter> parameters = new ArrayList<>(List.of(Player.class.getConstructor(String.class, boolean.class).getParameters()));

        // TESTING IF PARAMETERS HAVE THE CORRECT TYPE
        assert String.class == parameters.get(0).getType() : "First parameter is not String class";
        assert boolean.class == parameters.get(1).getType() : "Second parameter is not Boolean class";
    }

    @Test
    public void TestCardToPlay() {
        Player testedPlayer = new Player();

        // Test with void cards
        CardClass topTested = new CardClass();
        CardClass playedTested = new CardClass();

        Boolean resultTest = testedPlayer.testCardToPlay(topTested, playedTested);
        assert (resultTest == false) : "This should be false";

        // Test with different colours, same number
        topTested = new CardClass(1, "blue");
        playedTested = new CardClass(1, "green");

        resultTest = testedPlayer.testCardToPlay(topTested, playedTested);
        assert (resultTest == true) : "This should be correct";

        // Test with diferents numbers, same colours
        topTested = new CardClass(1, "blue");
        playedTested = new CardClass(3, "blue");

        resultTest = testedPlayer.testCardToPlay(topTested, playedTested);
        assert (resultTest == true) : "This should be correct";

        // Test with swap colour
        topTested = new CardClass(1, "blue");
        playedTested = new CardClass(Actions.COLOR_SWAP, null);

        resultTest = testedPlayer.testCardToPlay(playedTested , topTested);
        assert (resultTest == true) : "This should be correct";

        // Test with +4
        topTested = new CardClass(1, "blue");
        playedTested = new CardClass(Actions.PLUS_FOUR, null);

        resultTest = testedPlayer.testCardToPlay(playedTested , topTested);
        assert (resultTest == true) : "This should be correct";

        // Test with +2 and the last card is +2
        topTested = new CardClass(Actions.PLUS_TWO, "blue");
        playedTested = new CardClass(Actions.PLUS_TWO, "blue");

        resultTest = testedPlayer.testCardToPlay(topTested, playedTested);
        assert (resultTest == true) : "This should be correct";

        //Test with block
        topTested = new CardClass(4, "red");
        playedTested = new CardClass(Actions.BLOCK, "red");

        resultTest = testedPlayer.testCardToPlay(playedTested, topTested);
        assert (resultTest == true ) : "This should be correct";

        //Test with reverse
        topTested = new CardClass(4, "red");
        playedTested = new CardClass(Actions.REVERSE, "red");

        resultTest = testedPlayer.testCardToPlay(playedTested, topTested);
        assert (resultTest == true ) : "This should be correct";

        // Test different numbers, different colours
        topTested = new CardClass(1, "green");
        playedTested = new CardClass(3, "blue");

        resultTest = testedPlayer.testCardToPlay(topTested, playedTested);
        assert (resultTest == false) : "This should be false";

        // Test with +2 and the last card is not a +2
        topTested = new CardClass(Actions.PLUS_TWO, "blue");
        playedTested = new CardClass(3, "blue");

        resultTest = testedPlayer.testCardToPlay(topTested, playedTested);
        assert (resultTest == true) : "This should be false";

        //Test two different action cards
        topTested = new CardClass(Actions.PLUS_TWO, "blue");
        playedTested = new CardClass(Actions.BLOCK, "red");

        resultTest = testedPlayer.testCardToPlay(playedTested, topTested);
        assert (resultTest == false) : "This should be false";

        //Test color swap with another action
        topTested = new CardClass(Actions.COLOR_SWAP, null);
        playedTested = new CardClass(Actions.PLUS_TWO, "blue");

        resultTest = testedPlayer.testCardToPlay(topTested, playedTested);
        assert (resultTest == true) : "This should be true";

    }

    @Test
    public void TestCanPlayCard() {
        Player testedPlayer = new Player();
        for(int i = 0; i < 5; i++) {
            testedPlayer.giveCard( new CardClass(i, Colors.RED));
        }
        boolean resultTest = testedPlayer.canPlayCard(new CardClass(0, Colors.RED));
        assert (resultTest == true) : "Hand is all red and last card too, it should be true";

        resultTest = testedPlayer.canPlayCard(new CardClass(0, Colors.GREEN));
        assert (resultTest == true) : "Hand is all red [0-5] and last card is 0 Green, it should be true";

        resultTest = testedPlayer.canPlayCard(new CardClass(7, Colors.GREEN));
        assert (resultTest == false) : "Hand is all red and last card is 7 green, it should be false" ;
    }

    @Test
    public void TestPlayCard() {
        // Setting all params for the test
        Player testedPlayer1 = new Player("Josias", false);
        Player testedPlayer2 = new Player("Pol", false);
        Player testedPlayer3 = new Player("Jan", false);
        ArrayList<Player> testedArrayPlayer = new ArrayList<>();
        testedArrayPlayer.add(testedPlayer1);testedArrayPlayer.add(testedPlayer2);
        testedArrayPlayer.add(testedPlayer3);

        CardClass resultTested = new CardClass();
        ScannerClass scannerClass = new Mock_ScannerClass("3" );
        Game testedGame = new Game(3, 0, testedArrayPlayer, scannerClass);
        //Deck testedDeck = new Deck();
        Deck testedDeck2 = new Mock_Deck();


        List<CardClass> subList = testedDeck2.getPlayableCards().subList(12, 19);
        ArrayList<CardClass> onlyNumbersHand = new ArrayList<>(subList);
        testedPlayer1.setHand(onlyNumbersHand);

        CardClass lastCardNumber = new CardClass(7, Colors.RED);
        CardClass lastCardAction = new CardClass(Actions.BLOCK, Colors.GREEN);


        // Testing throw a card, no winner, no void hand
        CardClass selectedCardByPlayer = onlyNumbersHand.get(2);

        CardClass resultTested1 = new CardClass();
        resultTested1 = testedPlayer1.playCard(lastCardNumber,testedDeck2, testedGame);

        assert(resultTested1 == selectedCardByPlayer ) : "The last card played does not correspond the chosen one";
        assert(testedPlayer1.getHand().size() == 6) : "The last card played has not been removed from the player hand";
        assert(testedPlayer1.getWinner() == false) : "The player should not be a winner because his/her hand is not void";

        // Testing throw a card, winner, void hand
        testedGame.getMyScanner().setInputsList("1");

        subList = testedDeck2.getPlayableCards().subList(14,15);
        onlyNumbersHand = new ArrayList<>(subList);
        testedPlayer1.setHand(onlyNumbersHand);
        selectedCardByPlayer = onlyNumbersHand.get(0);

        resultTested1 = testedPlayer1.playCard(lastCardNumber,testedDeck2, testedGame);
        assert(resultTested1 == selectedCardByPlayer ) : "The last card played does not correspond the chosen one";
        assert(testedPlayer1.getHand().size() == 0) : "The last card played has not been removed from the player hand";
        assert(testedPlayer1.getWinner() == true) : "The player should be a winner because his/her hand is void";

        // Testing throw an invalid card
        testedGame.getMyScanner().setInputsList("2","1");
        subList.clear();
        subList.add(new CardClass(2, Colors.RED));  subList.add(new CardClass(5, Colors.BLUE));
        onlyNumbersHand = new ArrayList<>(subList);
        testedPlayer1.setHand(onlyNumbersHand);
        testedPlayer1.setWinner(false);
        selectedCardByPlayer = onlyNumbersHand.get(0);

        resultTested1 = testedPlayer1.playCard(lastCardNumber,testedDeck2, testedGame);
        assert(resultTested1 == selectedCardByPlayer ) : "The last card played does not correspond the chosen one";
        assert(testedPlayer1.getHand().size() == 1) : "The last card played has not been removed from the player hand";
        assert(testedPlayer1.getWinner() == false) : "The player should not be a winner because his/her hand is not void";


        // Hand size: 9 cards. Possible values [1-9]
        testedGame.getMyScanner().setInputsList("0", "1", "2", "4", "8", "9", "10");
        subList.clear();
        subList.add(new CardClass(Actions.PLUS_TWO, Colors.RED));  subList.add(new CardClass(5, Colors.BLUE));
        onlyNumbersHand = new ArrayList<>(subList);
        testedPlayer1.setHand(onlyNumbersHand);
        testedPlayer1.setWinner(false);
        selectedCardByPlayer = onlyNumbersHand.get(0);
        resultTested1 = testedPlayer1.playCard(lastCardNumber,testedDeck2, testedGame);
        assert(resultTested1 == selectedCardByPlayer ) : "The last card played does not correspond the chosen one";
        assert(testedPlayer1.getHand().size() == 1) : "The last card played has not been removed from the player hand";
        assert(testedPlayer1.getWinner() == false) : "The player should not be a winner because his/her hand is not void";

        // Testing that user does not have any card available to play //
        ArrayList<CardClass> inputHand = new ArrayList<>();
        inputHand.add(new CardClass(6, Colors.YELLOW));
        testedPlayer1.setHand(inputHand);
        resultTested1 = testedPlayer1.playCard(lastCardNumber,testedDeck2, testedGame);
        assert(resultTested1 == null) : "The user does not have any card to throw, so he has to pick one card";


        //Testing that user throws a +4 a switch color to red //
        testedGame.getMyScanner().setInputsList("1","R");
        inputHand = new ArrayList<>();
        inputHand.add(new CardClass(Actions.PLUS_FOUR, null));
        testedPlayer1.setHand(inputHand);

        CardClass exepectedValue = new CardClass(Actions.PLUS_FOUR, Colors.RED);
        resultTested1 = testedPlayer1.playCard(lastCardNumber,testedDeck2, testedGame);
        assert(resultTested1 != null) : "The user played has throw a plus four and changed to red";
        assert(resultTested1.getAction() == exepectedValue.getAction() && resultTested1.getColour() == exepectedValue.getColour()) : "The returned value should be a +4 red";

    }

    @Test
    public void PairwiseTestingTestCardToPlayFirst() {
        // We do pairwise testing of the first if of Player.testCardToPlay() and its return
        Player player1 = new Player("jan", false);
        CardClass cardPlayed = new CardClass();
        CardClass lastCardPlayed = new CardClass();

        // Case 1
        /*
        * cardToP.getAction(): null
        * lcPlayed.getAction(): null
        * cardToP.getNumber(): not null
        * lcPlayed.getNumber(): not null
        * cardToP.getColour(): any value
        * lcPlayed.getColour(): any value
        * */
        cardPlayed = new CardClass(1, Colors.RED);
        lastCardPlayed = new CardClass(1, Colors.RED);
        //player1.testCardToPlay();
    }
}
