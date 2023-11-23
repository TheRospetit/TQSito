package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.*;

import view.ViewAskPlayers;
import view.ViewMenu;
import view.ViewStatistics;

public class Main {

    public Main() { main(null);}

    public static void main(String[] args){

        DataBase myDatabase = new DataBase("stats.txt");
        myDatabase.createFile();



        Scanner scanner = new Scanner(System.in);
        ScannerClass myScanner = new ScannerClass(scanner);


        int choice; // Switch case menu option(choice) by the user.
        do {

            ViewMenu.displayMenu();

            // Get user choice
            System.out.print("Enter your choice (1-3): ");
            while (!myScanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                myScanner.next(); // Consume the invalid input
            }
            choice = myScanner.nextInt();

                // Ask User the menu Option
            //choice = menu.askMenuOption();
            switch (choice) {
                case 1:
                    // Code for option 1 (Play)
                    int numPlayers = getNumberOfPlayers();

                    ArrayList<Player> myPlayerList = getPlayerNames(numPlayers);

                    // Display the player names
                    ViewAskPlayers.displayPlayerNames(myPlayerList);
                    ViewStatistics.displayStatsIfInDDBB(myDatabase, myPlayerList);

                    System.out.println("Starting the game...");
                    break;
                case 2:
                    // Add code for option 2 (View Statistics)
                    System.out.println("Viewing statistics...");
                    ViewStatistics.viewAllStatistics(myDatabase);
                    break;
                case 3:
                    // Option 3 (Exit)
                    System.out.println("Exiting the game. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }


        }while (choice != 3);


        System.out.println("THE END ENTRE COMILLAS");


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
            game.playerRound();
        }

    }
}


