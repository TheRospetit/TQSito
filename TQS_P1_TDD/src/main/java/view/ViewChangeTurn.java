package view;

import java.util.Scanner;

// View of the change turn part of the main loop. Shows the next turn's player name. Also asks to press ENTER key
// each time to make sure that only the current player is looking at the screen.
public final class ViewChangeTurn {

  /*
  public static void clearScreen() {
    // Uses ANSI escape sequence to clear the screen
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
  */

  // For the moment the " visual" solution in IntelliJ due to the
  // fact that we can't use any other option.
  public static void clearScreen() {
    for (int i = 0; i < 50; i++) {
      System.out.println();
    }
    // Just in case it works on the terminal used for the execution.
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void waitForKeypress() {
    try {
      int terminalWidth = ViewUtils.getIntelliJTerminalWidth();
      String pressEnterString = "Press ENTER to continue . . .";
      int centerPressEnter = (terminalWidth - (pressEnterString.length() + 2)) / 2;
      System.out.println(" "); // New line (could be \n but is more visual now).
      System.out.println("┌" + ViewUtils.fillWithCharacter('─', terminalWidth - 2) + "┐");
      System.out.println("│" + ViewUtils.fillWithCharacter(' ', centerPressEnter) + pressEnterString + ViewUtils.fillWithCharacter(' ', centerPressEnter) + " │");
      System.out.println("└" + ViewUtils.fillWithCharacter('─', terminalWidth - 2) + "┘");

      // Use Scanner to wait for the user to press a key
      Scanner scanner = new Scanner(System.in);
      scanner.nextLine();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void showTurnChange(String playerName) {
    ViewChangeTurn.clearScreen();
    // Get the width of the terminal in IntelliJ
    int terminalWidth = ViewUtils.getIntelliJTerminalWidth();

    String nextPlayerString = "Next turn. Player name:";

    // Calculate the position to center the player name
    int centerAt = (terminalWidth - (playerName.length() + 2)) / 2;
    int centerTitle = (terminalWidth - (nextPlayerString.length() + 2)) / 2;


    // Print the turn change with the player name centered
    System.out.println("╔" + ViewUtils.fillWithCharacter('=', terminalWidth - 2) + "╗");
    System.out.println("║" + ViewUtils.fillWithCharacter(' ', centerTitle) + nextPlayerString + ViewUtils.fillWithCharacter(' ', centerTitle) + " ║");
    System.out.println("║" + ViewUtils.fillWithCharacter(' ', centerAt) + playerName + ViewUtils.fillWithCharacter(' ', centerAt) + " ║");
    System.out.println("╚" + ViewUtils.fillWithCharacter('=', terminalWidth - 2) + "╝");

    waitForKeypress();
  }

}
