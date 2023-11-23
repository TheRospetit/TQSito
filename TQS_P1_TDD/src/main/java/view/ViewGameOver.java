package view;

public final class ViewGameOver {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public static void main(String[] args) {
        String winner = "Player1";
        displayEndGame(winner);
    }

    public static void displayEndGame(String winner) {
        int terminalWidth = getIntelliJTerminalWidth();

        // End of game message
        String[] endGameMessage = {
                "Game Over!",
                "The winning player is: " + winner,
                "Congratulations!"
        };


        // Display the end game message
        System.out.println("╔" + fillWithCharacter('=', terminalWidth - 2) + "╗");
        for (String line : endGameMessage) {
            int leftPadding = (terminalWidth - line.length()) / 2;
            int rightPadding = terminalWidth - line.length() - leftPadding - 2;
            System.out.println("║" + fillWithCharacter(' ', leftPadding) +ANSI_RED+  line +ANSI_RESET+  fillWithCharacter(' ', rightPadding) + "║");
        }
        System.out.println("╚" + fillWithCharacter('=', terminalWidth - 2) + "╝");
    }

    // Method to get the width of the terminal in IntelliJ IDEA
    private static int getIntelliJTerminalWidth() {
        return 80; // Adjust this value as needed
    }

    // Method to fill a string with a given character
    private static String fillWithCharacter(char character, int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(character);
        }
        return result.toString();
    }
}
