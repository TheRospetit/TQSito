package view;

import models.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public final class ViewAskPlayers {

    public static void displayAskNumPlayers() {
        System.out.println("Enter the number of players: ");
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



