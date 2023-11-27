package view;

import controller.Main;

// We were using these functions in almost all the classes of the view, so we extracted them in an
// external class to not repeat so much code.
public class ViewUtils {

    public static String fillWithCharacter(char character, int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(character);
        }
        return result.toString();
    }

    public static int getIntelliJTerminalWidth() {
        // You can adjust this according to your needs
        return Main.TERMINAL_WIDTH;
    }
}
