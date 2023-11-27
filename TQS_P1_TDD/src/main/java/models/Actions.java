package models;

public final class Actions {
    // POSSIBLE ACTIONS OF THE CARDS
    public static final String BLOCK = "block";
    public static final String REVERSE = "reverse";
    public static final String PLUS_TWO = "plus_two";
    public static final String PLUS_FOUR = "plus_four";
    public static final String COLOR_SWAP = "color_swap";

    // Function to view the Actions with another name.
    public static String getActionsStringName(String action){
        String actionString = null;
        if (action != null){
            if (action.equals(Actions.BLOCK)){
                actionString = String.valueOf('\u26D4');
            } else if (action.equals(Actions.REVERSE)){
                actionString = String.valueOf('⭾') ; //String.valueOf(''); // ⇄ U+1D996 ⭾
            } else if (action.equals(Actions.PLUS_TWO)){
                actionString = "+2";
            } else if (action.equals(Actions.PLUS_FOUR)){
                actionString = "+4\uD83C\uDFB2";
            } else if (action.equals(Actions.COLOR_SWAP)){
                actionString = "\uD83C\uDFA8\uD83D\uDD04";
            }
        }
        return actionString;
    }
}
