/*
package view;


import java.util.Arrays;
import java.util.List;

public class ViewPlayerStatus {

  public static void main(String[] args) {
    // Sample data for testing
    String playerName = "Player 1";
    List<String> playerHand = Arrays.asList("Red 3", "Blue Skip", "Wild Draw Four", "Green 7");
    String lastPlayedCard = "Yellow Reverse";

    // Display player status
    displayPlayerStatus(playerName, playerHand, lastPlayedCard);
  }

  public static void displayPlayerStatus(String playerName, List<String> playerHand, String lastPlayedCard) {
    int terminalWidth = getIntelliJTerminalWidth();

    // Display current player's name
    int centerPlayerName = (terminalWidth - (playerName.length() + 2)) / 2;
    System.out.println("╔" + fillWithCharacter('=', terminalWidth - 2) + "╗");
    System.out.println("║" + fillWithCharacter(' ', centerPlayerName) + playerName + fillWithCharacter(' ', centerPlayerName) + " ║");
    System.out.println("╚" + fillWithCharacter('=', terminalWidth - 2) + "╝");

    // Display last played card
    int centerLastCard = (terminalWidth - (lastPlayedCard.length() + 2)) / 2;
    System.out.println(" ");
    System.out.println("┌" + fillWithCharacter('─', terminalWidth - 2) + "┐");
    System.out.println("│" + fillWithCharacter(' ', centerLastCard) + lastPlayedCard + fillWithCharacter(' ', centerLastCard) + " │");
    System.out.println("└" + fillWithCharacter('─', terminalWidth - 2) + "┘");

    // Display player's hand with card shapes
    System.out.println(" ");
    System.out.println("Player's Hand:");
    for (String card : playerHand) {
      displayCardShape(card);
    }
    System.out.println("\n");
  }

  private static void displayCardShape(String card) {
    int cardWidth = card.length() + 4; // Adjust according to your needs

    System.out.println("╔" + fillWithCharacter('═', cardWidth - 2) + "╗");
    System.out.println("║ " + card + " ║");
    System.out.println("╚" + fillWithCharacter('═', cardWidth - 2) + "╝");
  }

  private static String fillWithCharacter(char character, int length) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; i++) {
      result.append(character);
    }
    return result.toString();
  }

  private static int getIntelliJTerminalWidth() {
    // You can adjust this according to your needs
    return 160;
  }
}
*/


package view;

import java.util.Arrays;
import java.util.List;

public class ViewPlayerStatus {

  public static void main(String[] args) {
    // Sample data for testing
    String playerName = "Player 1";
    List<String> playerHand = Arrays.asList("Red 3", "Blue Skip", "Draw Four", "Green 7");
    String lastPlayedCard = "Yellow Reverse";

    // Display player status
    displayPlayerStatus(playerName, playerHand, lastPlayedCard);
  }

  public static void displayPlayerStatus(String playerName, List<String> playerHand, String lastPlayedCard) {
    int terminalWidth = getIntelliJTerminalWidth();

    // Display current player's name
    int centerPlayerName = (terminalWidth - (playerName.length() + 2)) / 2;
    System.out.println("╔" + fillWithCharacter('=', terminalWidth - 2) + "╗");
    System.out.println("║" + fillWithCharacter(' ', centerPlayerName) + playerName + fillWithCharacter(' ', centerPlayerName) + "║");
    System.out.println("╚" + fillWithCharacter('=', terminalWidth - 2) + "╝");

    // Display last played card
    int centerLastCard = (terminalWidth - (lastPlayedCard.length() + 2)) / 2;
    System.out.println(" ");
    System.out.println("┌" + fillWithCharacter('─', terminalWidth - 2) + "┐");
    System.out.println("│" + fillWithCharacter(' ', centerLastCard) + lastPlayedCard + fillWithCharacter(' ', centerLastCard) + "│");
    System.out.println("└" + fillWithCharacter('─', terminalWidth - 2) + "┘");

    // Display player's hand in a table-like format
    System.out.println(" ");
    System.out.println("Player's Hand:");
    displayCardTable(playerHand);
    System.out.println("\n");
  }

  private static void displayCardTable(List<String> playerHand) {
    int cardWidth = 15; // Adjust according to your needs

    for (String card : playerHand) {
      System.out.print("┌" + fillWithCharacter('─', cardWidth - 2) + "┐ ");
    }
    System.out.println(); // Move to the next line

    for (String card : playerHand) {
      int padding = cardWidth - card.length() - 4; // Calculate padding for centering
      System.out.print("│ " + fillWithCharacter(' ', padding / 2) + " " + card + fillWithCharacter(' ', padding - padding / 2) + "│ ");
    }
    System.out.println(); // Move to the next line

    for (String card : playerHand) {
      System.out.print("└" + fillWithCharacter('─', cardWidth - 2) + "┘ ");
    }
    System.out.println(); // Move to the next line
  }

  private static String fillWithCharacter(char character, int length) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; i++) {
      result.append(character);
    }
    return result.toString();
  }

  private static int getIntelliJTerminalWidth() {
    // You can adjust this according to your needs
    return 160;
  }
}