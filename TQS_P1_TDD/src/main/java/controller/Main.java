package controller;

import java.util.ArrayList;
import java.util.Scanner;

import models.*;

import view.*;
// The game controller, part of the MVC pattern. It is the main loop (and main) of the game, and by running it you
// can play the game. Because the controller calls the views and models.
public class Main {
    // Global constants to change the game configs.
    public static final int TERMINAL_WIDTH = 160;
    public static final int MIN_PLAYERS = 2;
    public static final int MAX_PLAYERS = 6;
    public static final int EXIT_OPTION = 4; // Setting the exitOption value. In case we add more options, all
    // prints will change the range of correct values (and the While too). Same with MAX and MIN_PLAYERS


    public Main() { main(null);}

    public static void main(String[] args){


        // Initialize the database (stats.txt file)
        DataBase myDatabase = new DataBase("stats.txt");
        myDatabase.createFile();

        Scanner scanner = new Scanner(System.in);
        ScannerClass myScanner = new ScannerClass(scanner);

        int choice; // Switch case menu option(choice) by the user.
        do {
            ViewMenu.displayMenu();
            // Get user choice
            System.out.print("Enter your choice (1-" + EXIT_OPTION + "): ");;
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
                    while (numPlayers < MIN_PLAYERS || numPlayers > MAX_PLAYERS){
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
                    game.deck.setCardPlayed(game.deck.getPlayableCards().get(0)); // We set the first card

                    // Main loop. Play turns until there is a winner.
                    while (!game.gameEndedWinner()) {
                        Player actualPlayer = game.getListPlayers().get(game.getNextPlayerIndex());
                        ViewChangeTurn.showTurnChange(actualPlayer.getName());

                        ViewPlayerStatus.displayPlayerStatus(actualPlayer.getName(), actualPlayer.getHand(), game.getLastCardPlayed());

                        CardClass cardPlayed = game.getActualPlayerPlayedCard(actualPlayer);

                        if (game.getLastCardPlayed().getAction() != null){
                            if (game.getLastCardPlayed().getAction().equals(Actions.PLUS_TWO)){
                                myStatList.get(game.getNextPlayerIndex()).addDrawn2Stat();
                            } else if (game.getLastCardPlayed().getAction().equals(Actions.PLUS_FOUR)) {
                                myStatList.get(game.getNextPlayerIndex()).addDrawn4Stat();
                            }
                        }


                        if (game.playerRound(actualPlayer, cardPlayed)){
                            System.out.println("Card drawn: Showing hand again:");
                            ViewPlayerStatus.displayPlayerStatus(actualPlayer.getName(), actualPlayer.getHand(), game.getLastCardPlayed());
                            ViewChangeTurn.waitForKeypress();
                        }

                    }
                    String winnerName = "";
                    for (Player player : game.getListPlayers()){
                        if (player.getWinner()){
                            winnerName = player.getName();
                        }
                    }
                    ViewGameOver.displayEndGame(winnerName);

                    // Add the proper statistics to each Player and write them to the database in case the
                    // player wasn't there, or replace and rewrite the stats in case the player played before.
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

                    ViewStatistics.viewPlayerStatistics(myStatList);
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
                    // Option 4 (Exit)
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and " + EXIT_OPTION + ".");
            }


        }while (choice != EXIT_OPTION);

    }

    // Method to get the number of players being sure that the value is correct
    private static int getNumberOfPlayers() {
        Scanner scanner = new Scanner(System.in);
        int numPlayers = 0;

        // Input validation
        while (numPlayers <= 0) {
            try {
                ViewAskPlayers.displayAskNumPlayers(MIN_PLAYERS, MAX_PLAYERS);
                numPlayers = scanner.nextInt();

                if (numPlayers <= 1) {
                    System.out.println("Please enter a valid number greater than + " + (MIN_PLAYERS - 1) + ".");
                } else if (numPlayers > 6) {
                    System.out.println("Please enter a valid number lower than " + (MAX_PLAYERS + 1) + ".");
                }
            } catch (Exception e) {
                System.out.println("Input error. Please enter a valid number.");
                scanner.next();  // Clear the scanner buffer
            }
        }

        return numPlayers;
    }
    // Gets all the player names, asking the user to insert each player name (as much as players set before).
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

}


