package models;

public final class Colors {
    public static final String RED = "red";
    public static final String YELLOW = "yellow";
    public static final String BLUE = "blue";
    public static final String GREEN = "green";

    public static String getColorsStringName(String color){
        String colorString = null;
        if (color != null){
            if (color.equals(Colors.RED)){
                colorString = "\uD83D\uDD34"; // = "🔴"; o 🟥
            } else if (color.equals(Colors.YELLOW)){
                colorString = "\uD83D\uDFE1"; // = "🟡"; o 🟨
            } else if (color.equals(Colors.GREEN)){
                colorString = "\uD83D\uDFE2"; // = "🟢" o 🟩
            }  else if (color.equals(Colors.BLUE)){
                colorString = "\uD83D\uDD35"; // = "🔵"; o 🟦
            }
        }
        return colorString;
    }
}
