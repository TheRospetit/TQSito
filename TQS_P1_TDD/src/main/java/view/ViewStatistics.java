package view;

import models.DataBase;
import models.Player;
import models.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class ViewStatistics {
    // TODO. Valorar si es queda o es borra el mètode.
    public Statistics stringLineToStatistics(String line) {
        String[] parts = line.split(",");

        if (parts.length == 4) {
            String playerName = parts[0];
            int numGames = Integer.parseInt(parts[1]);
            int numWins = Integer.parseInt(parts[2]);
            int numCards = Integer.parseInt(parts[3]);

            return new Statistics(playerName, numGames, numWins, numCards);
        } else {
            // The input string is not in the expected format.
            return null;
        }
    }
    /**
     * Simple method to format all the Statistics attributes in a single String line.
     * @param stat -> A Statistics Instance
     * @return -> Returns a String with all the attributes of a Statistics instance.
     */
    public String formatStatLine(Statistics stat) {
        return stat.getPlayer_name() + "," + stat.getNumGames() + "," + stat.getNumWins() + "," + stat.getNumCards();
    }

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

}
