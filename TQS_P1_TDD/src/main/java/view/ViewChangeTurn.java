package view;

import java.util.Scanner;

public final class ViewChangeTurn {

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 5; i++) {
      showTurnChange("Player 2");
      waitForKeypress();
      clearScreen();
      //Thread.sleep(2000);
    }
  }

  /*
  public static void clearScreen() {
    // Uses ANSI escape sequence to clear the screen
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  */

  // For the moment the "visual" solution in IntelliJ due to the
  // fact that we can't use any other option.
  public static void clearScreen() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
  }

  public static void waitForKeypress() {
    try {
      System.out.println("Press 'ENTER' to continue.");
      // Use Scanner to wait for the user to press a key
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void showTurnChange(String playerName) {
    // Get the width of the terminal in IntelliJ
    int terminalWidth = getIntelliJTerminalWidth();

    String nextPlayerString = "Next turn. Player name:";
    String pressEnterString = "Press ENTER to continue . . .";
    // Calculate the position to center the player name
    int centerAt = (terminalWidth - (playerName.length() + 2)) / 2;
    int centerTitle = (terminalWidth - (nextPlayerString.length() + 2)) / 2;
    int centerPressEnter = (terminalWidth - (pressEnterString.length() + 2)) / 2;

    // Print the turn change with the player name centered
    System.out.println("╔" + fillWithCharacter('=', terminalWidth - 2) + "╗");
    System.out.println("║" + fillWithCharacter(' ', centerTitle) + nextPlayerString + fillWithCharacter(' ', centerTitle) + " ║");
    System.out.println("║" + fillWithCharacter(' ', centerAt) + playerName + fillWithCharacter(' ', centerAt) + " ║");
    System.out.println("╚" + fillWithCharacter('=', terminalWidth - 2) + "╝");

    System.out.println(" "); // New line (could be \n but is more visual now).
    System.out.println("┌" + fillWithCharacter('─', terminalWidth - 2) + "┐");
    System.out.println("│" + fillWithCharacter(' ', centerPressEnter) + pressEnterString + fillWithCharacter(' ', centerPressEnter) + " │");
    System.out.println("└" + fillWithCharacter('─', terminalWidth - 2) + "┘");
  }

  public static String fillWithCharacter(char character, int length) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; i++) {
      result.append(character);
    }
    return result.toString();
  }

  public static int getIntelliJTerminalWidth() {
    // You can adjust this according to your needs
    return 160;
  }
}
