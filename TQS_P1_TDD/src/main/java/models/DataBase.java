package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// The DataBase manages the file that contains the statistics of players previous games and its access.
// With this class we can create a file, write to the file, read it and search a string, and even replace it.
// Very useful to manage the Statistics DDBB.
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
                System.out.println("File created: " + file.getName() + ". Statistics for the next games will be saved here.");
            } else {
                System.out.println("File already exists. Using an existing DataBase, previous game statistics will be saved.");
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



}