package view;

import java.util.Scanner;

public final class ViewMenu {


  public static void displayMenu() {
    int terminalWidth = getIntelliJTerminalWidth();

    // Display the game title
    String[] gameTitle1 = {
            " _   _ _   _  ___   ",
            "| | | | \\ | |/ _ \\ ",
            "| | | |  \\| | | | | ",
            "| |_| | |\\  | |_| |  ",
            " \\___/|_| \\_|\\___/ ",
    };
    String[] gameTitle2 = {
            "   __     __ _____ _   _ _   _   ",
            "  |   \\ /   | ____| \\ | | | | |",
            "  | |\\ V /| |  _| |  \\| | | | |",
            "  | | \\_/ | | |___| |\\  | |_| |",
            "  |_|     |_|_____|_| \\_|\\___/ "
    };

    // Determine the maximum width between the two game titles
    int maxTitleWidth = Math.max(gameTitle1[0].length(), gameTitle2[0].length());

    // Calculate left padding for the first game title
    int leftPadding1 = (terminalWidth - gameTitle1[0].length()) / 2;

    // Display the first game title
    System.out.println("╔" + fillWithCharacter('=', terminalWidth - 2) + "╗");
    for (String line : gameTitle1) {
      int rightPadding = terminalWidth - line.length() - leftPadding1 - 2;
      System.out.println("║" + fillWithCharacter(' ', leftPadding1) + line + fillWithCharacter(' ', rightPadding) + "║");
    }

    // Calculate left padding for the second game title
    int leftPadding2 = (terminalWidth - maxTitleWidth) / 2;

    // Display the second game title
    for (String line : gameTitle2) {
      int rightPadding = terminalWidth - line.length() - leftPadding2 - 2;
      System.out.println("║" + fillWithCharacter(' ', leftPadding2) + line + fillWithCharacter(' ', rightPadding) + "║");
    }

    // Display the closing line of the box
    System.out.println("╚" + fillWithCharacter('=', terminalWidth - 2) + "╝");

    // Display menu options
    System.out.println("\nSelect an option:");
    System.out.println("1. Play");
    System.out.println("2. View Statistics");
    System.out.println("3. How To Play");
    System.out.println("4. Exit");
  }


  private static String fillWithCharacter(char character, int length) {
    return String.valueOf(character).repeat(Math.max(0, length));
  }

  private static int getIntelliJTerminalWidth() {
    // You can adjust this according to your needs
    return 80;
  }
}
