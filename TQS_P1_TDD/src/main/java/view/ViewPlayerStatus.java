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

import models.Actions;
import models.CardClass;
import models.Colors;
import models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

// The most complex view class. Shows the Player Status, so shows the actual turn player name, the
// last card played and the player's hand. We decided to show the cards with emojis to make it more
// visual due the fact that the use of emojis moves all the display a little bit (because an emoji uses a
// little bit more than one terminal char slot (but less than 2)) and it's really difficult to fix that (if possible).
// We tried a lot of different options but this one is the best one so far.
// We also decided to show only 5 cards per row, so it will fit in smaller terminals.
public final class ViewPlayerStatus {

  // Creates the CardClass name that will be shown on the terminal based on the actions, color or number of the card.
  // Parsing the name of each action/color to an emoji with `Actions.getActionsStringName()`
  // and `Colors.getColorsStringName()`
  public static String createCardClassStringName(CardClass card) {
    String strAction = Actions.getActionsStringName(card.getAction());
    String strNumb = String.valueOf(card.getNumber());
    String strCol = Colors.getColorsStringName(card.getColour());
    String cardStringName = null;

    if (strCol != null && !Objects.equals(strNumb, "null") && strAction == null){
      cardStringName = strCol + strNumb; // Color + Number = Normal card
    } else if (strCol != null && Objects.equals(strNumb, "null") && strAction != null){
      cardStringName = strCol + strAction; // Color + Action = Special Color Card
    } else if (strCol == null && Objects.equals(strNumb, "null") && strAction != null){
      cardStringName = strAction;  // Action = Special colorless Card (+4, change color)
    } else if (strCol != null && Objects.equals(strNumb, "null") && strAction == null){
      cardStringName = strCol; // Special case: When a special card is played, it returns a color card,
      // which is the color that will be played on the next round.
    }
    return cardStringName;
  }
  public static void displayPlayerStatus(String playerName, ArrayList<CardClass> playerHand, CardClass lastPlayedCard) {
    int terminalWidth = ViewUtils.getIntelliJTerminalWidth();
    String playerNameString = "Turn: " + playerName;
    // Display current player's name
    //int centerPlayerName = (terminalWidth - (playerNameString.length() + 2)) / 2;
    int leftPaddingPlayerName = (terminalWidth - playerNameString.length()) / 2;
    int rightPaddingPlayerName = terminalWidth - playerNameString.length() - leftPaddingPlayerName - 2;
    System.out.println("╔" + ViewUtils.fillWithCharacter('=', terminalWidth - 2) + "╗");
    System.out.println("║" + ViewUtils.fillWithCharacter(' ', leftPaddingPlayerName) + playerNameString + ViewUtils.fillWithCharacter(' ', rightPaddingPlayerName) + "║");
    System.out.println("╚" + ViewUtils.fillWithCharacter('=', terminalWidth - 2) + "╝");

    // Display last played card
    String lastCardPlayedString = "Last card played: " + ViewPlayerStatus.createCardClassStringName(lastPlayedCard);
    int centerLastCard = (terminalWidth - (lastCardPlayedString.length() + 2)) / 2;
    //int leftPaddingLastCardPlayed = (terminalWidth - lastCardPlayedString.length()) / 2;
    //int rightPaddingLastCardPlayed = terminalWidth - lastCardPlayedString.length() - leftPaddingLastCardPlayed - 2;
    System.out.println(" ");
    System.out.println("┌" + ViewUtils.fillWithCharacter('─', terminalWidth - 2) + "┐");
    System.out.println("│" + ViewUtils.fillWithCharacter(' ', centerLastCard) + lastCardPlayedString + ViewUtils.fillWithCharacter(' ', centerLastCard) + "│");
    System.out.println("└" + ViewUtils.fillWithCharacter('─', terminalWidth - 2) + "┘");

    // Display player's hand in a table-like format
    System.out.println(" ");
    System.out.println("Player's Hand:");
    displayCardTable(playerHand);
    System.out.println("\n");
  }

  private static void displayCardTable(ArrayList<CardClass> playerHand) {
    int cardWidth = 15;
    int cardsPerRow = 5;
    char invisibleEmoticon = '\u200B'; // Wide space char

    int numRows = (int) Math.ceil((double) playerHand.size() / cardsPerRow);

    // Create a row with 5 cards (cardsPerRow as MAX)
    for (int row = 0; row < numRows; row++) {
      // Creating the top part of a card.
      for (CardClass card : playerHand.subList(row * cardsPerRow, Math.min((row + 1) * cardsPerRow, playerHand.size()))) {
        System.out.print("┌" + ViewUtils.fillWithCharacter('─', cardWidth - 2) + "┐ ");
      }
      System.out.println();
      // Creating the middle part of a card. Trying to center properly the name of the card.
      for (CardClass card : playerHand.subList(row * cardsPerRow, Math.min((row + 1) * cardsPerRow, playerHand.size()))) {
        String cardName = ViewPlayerStatus.createCardClassStringName(card);
        int totalPadding = cardWidth - cardName.length();
        int leftPaddingCard = totalPadding / 2;
        int rightPaddingCard = totalPadding - leftPaddingCard;

        // Adjust the padding space to align the card name in the center
        if (totalPadding % 2 != 0) {
          leftPaddingCard += 1;
        }

        String invisiblePadding = ViewUtils.fillWithCharacter(invisibleEmoticon, 2);
        System.out.print("│" + ViewUtils.fillWithCharacter(invisibleEmoticon, leftPaddingCard-2) + cardName + invisiblePadding + ViewUtils.fillWithCharacter(invisibleEmoticon, rightPaddingCard-2) + "│ ");
      }
      System.out.println();
      // Bottom part of the card.
      for (CardClass card : playerHand.subList(row * cardsPerRow, Math.min((row + 1) * cardsPerRow, playerHand.size()))) {
        System.out.print("└" + ViewUtils.fillWithCharacter('─', cardWidth - 2) + "┘ ");
      }
      System.out.println();
    }
  }

  
}
