package view;

import models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// View to ask the number of players, ask the entry of each player's name and finally display all the player names.
public final class ViewAskPlayers {

    public static void displayAskNumPlayers(int minPlayers, int maxPlayers) {
        System.out.print("Enter the number of players ("+ minPlayers + "-" + maxPlayers + "): ");
    }

    public static void displayEnterPlayerN(int playerNum) {
        System.out.println("Enter the name of Player " + playerNum + ": ");
    }

    public static void displayPlayerNames(List<Player> players) {
        System.out.println("\nPlayer names:");
        for (Player player : players) {
            System.out.println("- " + player.getName());
        }
    }
}



