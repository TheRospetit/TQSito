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

public final class ViewPlayerStatus {

  public static void main(String[] args) {
    ArrayList<Player> playerNames = new ArrayList<>();
    playerNames.add(new Player("Hello", false));
    CardClass card = new CardClass(1,Colors.RED);
    ArrayList<CardClass> hand = new ArrayList<>();
    hand.add(card);
    playerNames.getFirst().setHand(hand);
    displayPlayerStatus(playerNames.getFirst().getName(), hand,card);
  }

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
    int terminalWidth = getIntelliJTerminalWidth();
    String playerNameString = "Turn: " + playerName;
    // Display current player's name
    //int centerPlayerName = (terminalWidth - (playerNameString.length() + 2)) / 2;
    int leftPaddingPlayerName = (terminalWidth - playerNameString.length()) / 2;
    int rightPaddingPlayerName = terminalWidth - playerNameString.length() - leftPaddingPlayerName - 2;
    System.out.println("╔" + fillWithCharacter('=', terminalWidth - 2) + "╗");
    System.out.println("║" + fillWithCharacter(' ', leftPaddingPlayerName) + playerNameString + fillWithCharacter(' ', rightPaddingPlayerName) + "║");
    System.out.println("╚" + fillWithCharacter('=', terminalWidth - 2) + "╝");

    // Display last played card
    String lastCardPlayedString = "Last card played: " + ViewPlayerStatus.createCardClassStringName(lastPlayedCard);
    int centerLastCard = (terminalWidth - (lastCardPlayedString.length() + 2)) / 2;
    //int leftPaddingLastCardPlayed = (terminalWidth - lastCardPlayedString.length()) / 2;
    //int rightPaddingLastCardPlayed = terminalWidth - lastCardPlayedString.length() - leftPaddingLastCardPlayed - 2;
    System.out.println(" ");
    System.out.println("┌" + fillWithCharacter('─', terminalWidth - 2) + "┐");
    System.out.println("│" + fillWithCharacter(' ', centerLastCard) + lastCardPlayedString + fillWithCharacter(' ', centerLastCard) + "│");
    System.out.println("└" + fillWithCharacter('─', terminalWidth - 2) + "┘");

    // Display player's hand in a table-like format
    System.out.println(" ");
    System.out.println("Player's Hand:");
    displayCardTable(playerHand);
    System.out.println("\n");
  }

  private static void displayCardTable(ArrayList<CardClass> playerHand) {
    int cardWidth = 15;
    int cardsPerRow = 5;
    char invisibleEmoticon = '\u200B'; // Carácter de espacio sin ancho

    int numRows = (int) Math.ceil((double) playerHand.size() / cardsPerRow);

    for (int row = 0; row < numRows; row++) {
      for (CardClass card : playerHand.subList(row * cardsPerRow, Math.min((row + 1) * cardsPerRow, playerHand.size()))) {
        System.out.print("┌" + fillWithCharacter('─', cardWidth - 2) + "┐ ");
      }
      System.out.println(); // Mueve a la siguiente línea

      for (CardClass card : playerHand.subList(row * cardsPerRow, Math.min((row + 1) * cardsPerRow, playerHand.size()))) {
        String cardName = ViewPlayerStatus.createCardClassStringName(card);
        int totalPadding = cardWidth - cardName.length();
        int leftPaddingCard = totalPadding / 2;
        int rightPaddingCard = totalPadding - leftPaddingCard;

        // Ajustar el espacio de relleno para alinear el nombre de la carta en el centro
        if (totalPadding % 2 != 0) {
          leftPaddingCard += 1;
        }

        String invisiblePadding = fillWithCharacter(invisibleEmoticon, 2); // Ajusta según sea necesario
        System.out.print("│" + fillWithCharacter(invisibleEmoticon, leftPaddingCard-2) + cardName + invisiblePadding + fillWithCharacter(invisibleEmoticon, rightPaddingCard-2) + "│ ");
      }
      System.out.println(); // Mueve a la siguiente línea

      for (CardClass card : playerHand.subList(row * cardsPerRow, Math.min((row + 1) * cardsPerRow, playerHand.size()))) {
        System.out.print("└" + fillWithCharacter('─', cardWidth - 2) + "┘ ");
      }
      System.out.println(); // Mueve a la siguiente línea
    }
  }



  // Método de utilidad para llenar una cadena con un carácter dado
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
