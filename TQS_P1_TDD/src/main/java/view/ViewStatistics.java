package view;

import models.DataBase;
import models.Player;
import models.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

// View part to show/view the Statistics, parsing and formatting a string to stats or vice-versa.
public final class ViewStatistics {


    /**
     * Simple method to make the print of a row of the All Statistics table (With Format)
     * @param stat -> A Statistics instance which attributes will be printed.
     */
    public static void statToTableFormatPrintf(Statistics stat) {
        System.out.printf("\t| %-16s | %8d | %8d | %8d |%n", stat.getPlayer_name(),
                stat.getNumGames(), stat.getNumWins(), stat.getNumCards());
    }

    /**
     * Method to print all the rows of the DataBase with a table format
     */
    public static void viewAllStatistics(DataBase myDB) {
        ArrayList<String> statMenuTitle = new ArrayList<>();
        statMenuTitle.add("=============================================================");
        statMenuTitle.add("\t\t\t\t\t VIEW ALL STATISTICS");
        statMenuTitle.add("=============================================================");

        for (String line : statMenuTitle) {
            System.out.println(line);
        }

        /* Printing title:
         * For format purposes, we use these special characters:
         * %-16s: Will use 16 string type characters with left-align.
         * %-8s: Will use 8 string type characters with left-align.
         * %n: New line.
         */
        String separator = "\t-----------------------------------------------------%n";
        System.out.printf(separator);
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "PLAYER", "# Games", "# WINS", "#CARDS+2");
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "NAME", "Played", "", " DRAWN+4");
        System.out.printf(separator);

        try {
            FileReader fileReader = new FileReader(myDB.getFileName());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line; // Line to read

            while ((line = bufferedReader.readLine()) != null) {
                Statistics stat = myDB.stringLineToStatistics(line);
                statToTableFormatPrintf(stat);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf(separator);
        ViewChangeTurn.waitForKeypress();
    }

    // Extra function to show the stats of the players at the end of a game (Only the players that were on the game).
    public static void viewPlayerStatistics(ArrayList<Statistics> myStatsList) {
        ArrayList<String> statMenuTitle = new ArrayList<>();
        statMenuTitle.add("=============================================================");
        statMenuTitle.add("\t\t\t\t\t VIEW GAME STATISTICS");
        statMenuTitle.add("=============================================================");

        for (String line : statMenuTitle) {
            System.out.println(line);
        }

        /* Printing title:
         * For format purposes, we use these special characters:
         * %-16s: Will use 16 string type characters with left-align.
         * %-8s: Will use 8 string type characters with left-align.
         * %n: New line.
         */
        String separator = "\t-----------------------------------------------------%n";
        System.out.printf(separator);
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "PLAYER", "# Games", "# WINS", "#CARDS+2");
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "NAME", "Played", "", " DRAWN+4");
        System.out.printf(separator);
        for (Statistics statistics:myStatsList){
            statToTableFormatPrintf(statistics);
        }
        ViewChangeTurn.waitForKeypress();
    }
}
