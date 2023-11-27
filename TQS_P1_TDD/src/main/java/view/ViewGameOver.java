package view;

// View for the GAME OVER part of the game. Shows the winner in red.
public final class ViewGameOver {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";

    public static void displayEndGame(String winner) {
        int terminalWidth = ViewUtils.getIntelliJTerminalWidth();

        // End of game message
        String[] endGameMessage = {
                "Game Over!",
                "The winning player is: " + winner,
                "Congratulations!"
        };


        // Display the end game message
        System.out.println("╔" + ViewUtils.fillWithCharacter('=', terminalWidth - 2) + "╗");
        for (String line : endGameMessage) {
            int leftPadding = (terminalWidth - line.length()) / 2;
            int rightPadding = terminalWidth - line.length() - leftPadding - 2;
            System.out.println("║" + ViewUtils.fillWithCharacter(' ', leftPadding) +ANSI_RED+  line +ANSI_RESET+  ViewUtils.fillWithCharacter(' ', rightPadding) + "║");
        }
        System.out.println("╚" + ViewUtils.fillWithCharacter('=', terminalWidth - 2) + "╝");
    }

}
