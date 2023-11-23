package view;

import models.DataBase;
import models.Player;
import models.Statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class ViewStatistics {

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


    public static void displayStatsIfInDDBB(DataBase myDatabase, ArrayList<Player> players){

        for (Player player : players) {
            String linePlayer = myDatabase.searchString(player.getName());
            if (linePlayer != null) {
                System.out.println("Searching to DDBB " + player.getName() + " -> " + linePlayer);
            }
            else {
                System.out.println("Searching to DDBB " + player.getName() + " -> Not found: ");
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {

        DataBase myDatabase = new DataBase("stats.txt");

        myDatabase.createFile();


        // TEST DE PRINTS:

        Statistics statPlayer500 = new Statistics("Player500", 500, 500, 500);
        String lineToWrite = myDatabase.formatStatLine(statPlayer500);
        myDatabase.writeToFile(lineToWrite, myDatabase);

        // Afegir un player al final per veure que funcioni bé el replace entre mig del fitxer i no només append:
        Statistics justAdd = new Statistics("Rage quit", 1, 0, 0);
        String justAddLine = myDatabase.formatStatLine(justAdd);
        myDatabase.writeToFile(justAddLine, myDatabase);

        myDatabase.viewAllStatistics(myDatabase); // S'hauria de veure un nou jugador, "Player500"

        // Suposem que es juga una partida i que Player500 acaba i suma algunes stats:
        Statistics newStatsPlayer500 = new Statistics("Player500", 1, 1, 55);

        String linePlayer500 = myDatabase.searchString("Player500");
        System.out.println("Linea trobada buscant 'Player500' -> " + linePlayer500);
        Statistics statsFromDBPlayer500 = myDatabase.stringLineToStatistics(linePlayer500);

        if (statPlayer500.equals(statsFromDBPlayer500)) {
            System.out.println("Les stats són iguals. Es formateja bé de BD a object Stats.");
        } else {
            System.out.println("Les stats NO són iguals. ALGO ESTÀ PASSANT, ESPAVILA.\n");
            System.out.println("statPlayer500: " + myDatabase.formatStatLine(statPlayer500));
            System.out.println("statsFromDBPlayer500: " + myDatabase.formatStatLine(statsFromDBPlayer500));

        }

        // Actualitzem les estadístiques amb les de la última partida i comprovem que siguin correctes:
        statsFromDBPlayer500.updateStatistics(newStatsPlayer500);
        String lineAct = myDatabase.formatStatLine(statsFromDBPlayer500);
        System.out.println("Estadistiques actualitzades: " + lineAct);

        // Replace del DataBase
        myDatabase.replaceLineInFile(linePlayer500, lineAct);


        myDatabase.viewAllStatistics(myDatabase); // S'hauria de veure el jugador "Player500" amb les estadístiques actualitzades.












        /*
        Thread.sleep(1000);
        // Setejar els valors del player que volem afegir:
        Statistics statistics = new Statistics();
        statistics.setPlayer_name("PlayerNumOne");
        statistics.setNumCards(127);
        statistics.setNumGames(66);
        statistics.setNumWins(2);

        String lineToAdd = myDatabase.formatStatLine(statistics);
        myDatabase.writeToFile(lineToAdd);
        myDatabase.searchCharacter('a');
        Thread.sleep(5000);
        statistics.setPlayer_name("BadPlayer");
        String lineToAdd1 = myDatabase.formatStatLine(statistics);
        myDatabase.replaceLineInFile(lineToAdd, lineToAdd1);

        String lineToAdd2 = myDatabase.formatStatLine(new Statistics("Player2", 2, 4, 3));
        String lineToAdd3 = myDatabase.formatStatLine(new Statistics("Player3", 20, 40, 30));
        String lineToAdd4 = myDatabase.formatStatLine(new Statistics("Player4", 4, 8, 6));
        String lineToAdd5 = myDatabase.formatStatLine(new Statistics("Player5", 200, 400, 300));


        myDatabase.writeToFile(lineToAdd2);
        myDatabase.writeToFile(lineToAdd3);
        myDatabase.writeToFile(lineToAdd4);
        myDatabase.writeToFile(lineToAdd5);


        // myDatabase.writeToFile(String.valueOf(LocalDateTime.now()));
        */

    }
}
