package models;
//
// A class that will offer to a card all the possible colors that can have.
// It also has an associate icon in order to make view more attractive.
//
public final class Colors {
    public static final String RED = "red";
    public static final String YELLOW = "yellow";
    public static final String BLUE = "blue";
    public static final String GREEN = "green";

    // Used to visualize colors with emojis. (Sadly, an emoji uses one char space and a little bit more, so will make
    // all the view fail a little bit.
    public static String getColorsStringName(String color){
        String colorString = null;
        if (color != null){
            if (color.equals(Colors.RED)){
                colorString = "\uD83D\uDD34"; // = "🔴"; or 🟥
            } else if (color.equals(Colors.YELLOW)){
                colorString = "\uD83D\uDFE1"; // = "🟡"; or 🟨
            } else if (color.equals(Colors.GREEN)){
                colorString = "\uD83D\uDFE2"; // = "🟢" or 🟩
            }  else if (color.equals(Colors.BLUE)){
                colorString = "\uD83D\uDD35"; // = "🔵"; or 🟦
            }
        }
        return colorString;
    }
}
