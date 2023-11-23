package view;

import java.util.Scanner;

public class ViewMenu {

  public static void main(String[] args) {
    // Sample usage
    displayMenu();
  }

  public static void displayMenu() {
    int terminalWidth = getIntelliJTerminalWidth();

    // Display the game title
    String[] gameTitle1 = {
        " _   _ _   _  ___",
        "| | | | \\ | |/ _ \\",
        "| | | |  \\| | | | |",
        "| |_| | |\\  | |_| |",
        " \\___/|_| \\_|\\___/ ",
    };
    String[] gameTitle2 = {
        "   __     __ _____ _   _ _   _",
        "  |   \\ /   | ____| \\ | | | | |",
        "  | |\\ V /| |  _| |  \\| | | | |",
        "  | | \\_/ | | |___| |\\  | |_| |",
        "  |_|     |_|_____|_| \\_|\\___/ "
    };
  // Showing UNO
    int titleWidth = gameTitle1[0].length();
    int leftPadding = (terminalWidth - titleWidth) / 2;

    System.out.println("╔" + fillWithCharacter('=', terminalWidth - 2) + "╗");
    for (String line : gameTitle1) {
      System.out.println("║" + fillWithCharacter(' ', leftPadding) + line + fillWithCharacter(' ', leftPadding) + "║");
    }
    // Showing MENU
    int titleWidth2 = gameTitle2[0].length();
    int leftPadding2 = (terminalWidth - titleWidth2) / 2;
    for (String line : gameTitle2) {
      System.out.println("║" + fillWithCharacter(' ', leftPadding2) + line + fillWithCharacter(' ', leftPadding2) + "║");
    }
    System.out.println("╚" + fillWithCharacter('=', terminalWidth - 2) + "╝");


    // Display menu options
    System.out.println("\nSelect an option:");
    System.out.println("1. Play");
    System.out.println("2. View Statistics");
    System.out.println("3. Exit");
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
    return 80;
  }
}
