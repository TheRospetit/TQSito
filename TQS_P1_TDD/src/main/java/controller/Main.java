package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.*;

import view.*;

public class Main {



    public Main() { main(null);}

    public static void main(String[] args){
        int minPlayers = 1;
        int maxPlayers = 6;
        int exitOption = 4; // Setting the exitOption value. In case we add more options, all
        // prints will change the range of correct values (and the While too).

        // Initialize the database (stats.txt file)
        DataBase myDatabase = new DataBase("stats.txt");
        myDatabase.createFile();


        Scanner scanner = new Scanner(System.in);
        ScannerClass myScanner = new ScannerClass(scanner);


        int choice; // Switch case menu option(choice) by the user.
        do {
            ViewMenu.displayMenu();

            // Get user choice
            System.out.print("Enter your choice (1-" + exitOption + "): ");;
            while (!myScanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                myScanner.next(); // Consume the invalid input
            }
            choice = myScanner.nextInt();

            // Ask User the menu Option
            switch (choice) {
                case 1:
                    // Code for option 1 (Play)
                    int numPlayers = -1;
                    while (numPlayers <= minPlayers || numPlayers > maxPlayers){
                        numPlayers = getNumberOfPlayers();
                    }


                    ArrayList<Player> myPlayerList = getPlayerNames(numPlayers);

                    // Display the player names
                    ViewAskPlayers.displayPlayerNames(myPlayerList);

                    // Create the Statistics list that will be in the same order as Player, so we can add the Stats
                    // of the game to each player.
                    ArrayList<Statistics> myStatList = Statistics.getPlayerStatsListFromDDBB(myDatabase, myPlayerList);

                    // GAME INNIT
                    Scanner Scanner = new Scanner(System.in);
                    ScannerClass myScannerPlayerInput = new ScannerClass(Scanner);

                    Game game = new Game(numPlayers,0, myPlayerList, myScannerPlayerInput);
                    game.giveHand(); // Initializes the deck and gives the hands of each player
                    game.setLastCardPlayed(game.deck.getPlayableCards().get(0)); // We set the first card

                    while (!game.gameEndedWinner()) {
                        Player actualPlayer = game.getListPlayers().get(Game.getNextPlayerIndex());
                        ViewChangeTurn.showTurnChange(actualPlayer.getName());

                        ViewPlayerStatus.displayPlayerStatus(actualPlayer.getName(), actualPlayer.getHand(), game.getLastCardPlayed());

                        CardClass cardPlayed = game.getActualPlayerPlayedCard(actualPlayer);

                        if (game.getLastCardPlayed().getAction() != null){
                            if (game.getLastCardPlayed().getAction().equals(Actions.PLUS_TWO)){
                                myStatList.get(Game.getNextPlayerIndex()).addDrawn2Stat();
                            } else if (game.getLastCardPlayed().getAction().equals(Actions.PLUS_FOUR)) {
                                myStatList.get(Game.getNextPlayerIndex()).addDrawn4Stat();
                            }
                        }


                        if (game.playerRound(actualPlayer, cardPlayed)){
                            System.out.println("Card drawn: Showing hand again:");
                            ViewPlayerStatus.displayPlayerStatus(actualPlayer.getName(), actualPlayer.getHand(), game.getLastCardPlayed());
                        }

                    }
                    String winnerName = "";
                    for (Player player : game.getListPlayers()){
                        if (player.getWinner()){
                            winnerName = player.getName();
                        }
                    }
                    ViewGameOver.displayEndGame(winnerName);

                    for (Statistics statAct : myStatList){
                        if (statAct.getPlayer_name().equals(winnerName)){
                            statAct.addWin();
                        }
                        statAct.addGame();

                        String actualString = myDatabase.formatStatLine(statAct);
                        String linePlayer = myDatabase.searchString(statAct.getPlayer_name());
                        if (linePlayer != null) {
                            myDatabase.replaceLineInFile(linePlayer, actualString);
                        }
                        else{
                            myDatabase.writeToFile(actualString, myDatabase);
                        }
                    }
                    break;
                case 2:
                    // Add code for option 2 (View Statistics)
                    System.out.println("Viewing statistics...");
                    ViewStatistics.viewAllStatistics(myDatabase);
                    break;
                case 3:
                    // Option 3 (How To Play)
                    ViewHowToPlay.displayHowToPlay();
                    ViewChangeTurn.waitForKeypress();
                    break;
                case 4:
                    // Option 3 (Exit)
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and " + exitOption + ".");
            }


        }while (choice != exitOption);





    }


    private static int getNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        int numPlayers = 0;

        // Input validation
        while (numPlayers <= 0) {
            try {
                System.out.print("Enter the number of players (2-6): ");
                numPlayers = scanner.nextInt();

                if (numPlayers <= 1) {
                    System.out.println("Please enter a valid number greater than one.");
                } else if (numPlayers > 6) {
                    System.out.println("Please enter a valid number lower than seven.");
                }
            } catch (Exception e) {
                System.out.println("Input error. Please enter a valid number.");
                scanner.next();  // Clear the scanner buffer
            }
        }

        return numPlayers;
    }
    private static ArrayList<Player> getPlayerNames(int numPlayers) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> playerNames = new ArrayList<>();

        // Ask for the names of the players
        for (int i = 1; i <= numPlayers; i++) {
            ViewAskPlayers.displayEnterPlayerN(i);
            String playerName = scanner.nextLine();
            playerNames.add(new Player(playerName, false));
        }

        return playerNames;
    }

    public void playGame() {
        //Setting the game
        ArrayList<Player> myPlayerList = new ArrayList<>();
        Player jan = new Player("Jan", false);
        Player pol = new Player("Pol", false);
        Player josias = new Player("Josias", false);
        myPlayerList.add(jan); myPlayerList.add(pol); myPlayerList.add(josias);

        Scanner Scanner = new Scanner(System.in);
        ScannerClass myScanner = new ScannerClass(Scanner);
        Game game = new Game(3,0, myPlayerList, myScanner );

        game.giveHand(); // Initializes the deck and gives the hands of each player
        game.setLastCardPlayed(game.deck.getPlayableCards().get(0)); // We set the first card

        while (!game.gameEndedWinner()) {
            Player actualPlayer = game.getListPlayers().get(Game.getNextPlayerIndex());
            //game.playerRound(actualPlayer);
        }

    }
}


