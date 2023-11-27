package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private String fileName;

    public DataBase() {
        this.fileName = "stats.txt";
    }

    public DataBase(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void createFile() {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                //System.out.println("File created: " + file.getName());
            } else {
                //System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String text, DataBase myDB) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            // Check if the text to add is already written. (Same player Name)
            Statistics playerToCheck = myDB.stringLineToStatistics(text);
            if (myDB.searchString(playerToCheck.getPlayer_name()) == null) {
                try (FileWriter fileWriter = new FileWriter(fileName, true);
                     BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                    bufferedWriter.write(text);
                    bufferedWriter.newLine(); // Add a new line after adding a text.
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Line not added. Player already exists in DataBase.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String searchString(String player) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(String.valueOf(player))) {
                    return line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void replaceLineInFile(String lineToReplace, String replacementLine) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(lineToReplace)) {
                    lines.add(replacementLine); // Replace the line.
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (String newLine : lines) {
                bufferedWriter.write(newLine);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Statistics stringLineToStatistics(String line) {
        String[] parts = line.split(",");
        if (parts.length == 4) {
            String playerName = parts[0].trim();
            int numGames = Integer.parseInt(parts[1].trim());
            int numWins = Integer.parseInt(parts[2].trim());
            int numCards = Integer.parseInt(parts[3].trim());

            return new Statistics(playerName, numGames, numWins, numCards);
        } else {
            return null;
        }
    }

    public String formatStatLine(Statistics stat) {
        return stat.getPlayer_name() + "," + stat.getNumGames() + "," + stat.getNumWins() + "," + stat.getNumCards();
    }

    public void statToTableFormatPrintf(Statistics stat) {
        System.out.printf("\t| %-16s | %8d | %8d | %8d |%n", stat.getPlayer_name(),
                stat.getNumGames(), stat.getNumWins(), stat.getNumCards());
    }

    public void viewAllStatistics(DataBase myDB) {
        ArrayList<String> statMenuTitle = new ArrayList<>();
        statMenuTitle.add("=============================================================");
        statMenuTitle.add("\t\t\t\t\t VIEW ALL STATISTICS");
        statMenuTitle.add("=============================================================");

        for (String line : statMenuTitle) {
            System.out.println(line);
        }

        String separator = "\t-----------------------------------------------------%n";
        System.out.printf(separator);
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "PLAYER", "# Games", "# WINS", "#CARDS+2");
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "NAME", "Played", "", " DRAWN+4");
        System.out.printf(separator);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Statistics stat = myDB.stringLineToStatistics(line);
                statToTableFormatPrintf(stat);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf(separator);
    }
}