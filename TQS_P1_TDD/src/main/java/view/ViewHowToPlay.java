package view;
// View for the "How to Play" section of the UNO game
public class ViewHowToPlay {
    // Method to display instructions on how to play
    public static void displayHowToPlay() {
        System.out.println("=== How to Play UNO ===");
        System.out.println("UNO is a card game played with a special deck. The goal is to get rid of all your cards before other players.");
        System.out.println("The deck includes numbered cards in four colors: Red 🔴, Yellow 🟡, Green 🟢, Blue 🔵.");
        System.out.println("Additionally, there are special cards with unique effects:");

        // Numbered cards
        displayCardExplanation("Numbered Cards", "Represent the majority of the deck. Play a card with the same number or color as the top card on the discard pile.");

        // Wild cards
        displayCardExplanation("Wild Cards", "Allow you to change the current color. You can play them at any time, even if you have a card in the current color.");

        // +2 cards
        displayCardExplanation("+2 Cards", "Force the next player to draw two cards and lose their turn. You can play them on cards of the same color or another +2 card.");

        // Skip cards
        displayCardExplanation("Skip Cards", "Make the next player lose their turn. You can play them on cards with the same number or color.");

        // Reverse cards
        displayCardExplanation("Reverse Cards", "Change the direction of play. You can play them on cards with the same number or color.");

        // +4 cards
        displayCardExplanation("+4 Cards", "Force the next player to draw four cards, change the color, and lose their turn. You can play them at any time, even if you have a card in the current color.");
    }

    // Method to display an explanation of a card category
    private static void displayCardExplanation(String category, String explanation) {
        System.out.println("\n" + category + ": " + explanation);
    }
}

