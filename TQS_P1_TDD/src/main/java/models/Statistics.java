package models;


import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Statistics {
    /** The player name shown on the Statistics DB file. */
    private String player_name;
    /** The total number of cards drawn because of a +4 or +2 of the player. */
    private int numCards;
    /** Total number of games played of the current player.  */
    private int numGames;
    /** Total number of wins of the current player. Using numGames, we can calculate the winRate. */
    private int numWins;

    /** Base constructor of Statistics. */
    public Statistics(){
        this.player_name = "";
        this.numCards = 0;
        this.numGames = 0;
        this.numWins = 0;
    }

    /** Params constructor of Statistics.
     *
     * @param pl_name <code>String</code> with the name of the player.
     * @param nCards  <code>int</code> with the number of drawn cards (because of a +4 or +2)
     * @param nGames  <code>int</code> with the number of games played by the current player.
     * @param nWins   <code>int</code> with the number of wins by the current player.
     */
    public Statistics(String pl_name, int nGames, int nWins, int nCards) {
        this.player_name = pl_name;
        this.numCards = nCards;
        this.numWins = nWins;
        // Check if the number of wins is lower than the number of games (as it should be)
        // If there was some mistake and the number of wins is higher than the number of games, we set the number of games = number of wins.
        this.numGames = (this.numWins <= nGames) ? nGames:this.numWins;

    }

    // ===============================================
    // ||           SETTERS and GETTERS             ||
    // ===============================================

    /** Sets the numCards attribute of the Statistics instance. numCards: The total number of cards drawn because of a +4 or +2 of the player.
     * @param numCards See also.
     * @see Statistics#numCards
     */
    public void setNumCards(int numCards) {
        this.numCards = numCards;
    }

    /** Sets the player name attribute of the Statistics instance.
     * @param player_name See also
     * @see Statistics#player_name
     */
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
    /** Sets the numGames attribute of the Statistics instance.
     * @param numGames See also
     * @see Statistics#numGames
     */
    public void setNumGames(int numGames) {
        this.numGames = numGames;
    }
    /** Sets the numWins attribute of the Statistics instance.
     * @param numWins See also
     * @see Statistics#numWins
     */
    public void setNumWins(int numWins) {
        this.numWins = numWins;
    }

    /**
     * @return <code>int</code> with the value of the numCards attribute of the Statistics instance.
     * @see Statistics#numCards
     */
    public int getNumCards() {
        return numCards;
    }

    /**
     * @return <code>int</code> with the value of the numGames attribute of the Statistics instance.
     * @see Statistics#numGames
     */
    public int getNumGames() {
        return numGames;
    }

    /**
     * @return <code>int</code> with the value of the numWins attribute of the Statistics instance.
     * @see Statistics#numWins
     */
    public int getNumWins() {
        return numWins;
    }

    /**
     * @return <code>String</code> containing the name of the Player in this Statistics instance.
     * @see Statistics#player_name
     */
    public String getPlayer_name() {
        return player_name;
    }



    public void updateStatistics(Statistics stats) {
        numGames += stats.getNumGames();
        numWins += stats.getNumWins();
        numCards += stats.getNumCards();
    }

    public static ArrayList<Statistics> getPlayerStatsListFromDDBB(DataBase myDatabase, ArrayList<Player> myPlayerList) {
        ArrayList<Statistics> myStatList = new ArrayList<>();
        Statistics stat;
        for (Player player : myPlayerList) {
            String linePlayer = myDatabase.searchString(player.getName());
            if (linePlayer != null) {
                stat = myDatabase.stringLineToStatistics(linePlayer);
            } else {
                stat = new Statistics(player.getName(), 0, 0, 0);
            }
            myStatList.add(stat);
        }

        return myStatList;
    }


}
