package com.example.demo;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class DataBase {

    private String fileName;


    public DataBase(){
        this.fileName = "stats.txt";
    }
    public DataBase(String fileName) {
        this.fileName = fileName;
    }

    // Método para crear un archivo de texto si no existe
    public void createFile() {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para escribir en el archivo
    public void writeToFile(String text) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true); // true para añadir al archivo existente
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.newLine(); // Nueva línea después de cada texto
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para buscar un carácter en el archivo
    public void searchCharacter(char character) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int lineNumber = 1;
            boolean found = false;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(String.valueOf(character))) {
                    System.out.println("Character '" + character + "' found in line " + lineNumber + ": " + line);
                    found = true;
                }
                lineNumber++;
            }

            if (!found) {
                System.out.println("Character '" + character + "' not found in the file.");
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para reemplazar una línea en el archivo
    public void replaceLineInFile(String lineToReplace, String replacementLine) {
        List<String> lines = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(lineToReplace)) {
                    lines.add(replacementLine); // Reemplazar la línea
                } else {
                    lines.add(line);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String newLine : lines) {
                bufferedWriter.write(newLine);
                bufferedWriter.newLine(); // Nueva línea después de cada línea
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
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
    public void statToTableFormatPrintf(Statistics stat) {
        System.out.printf("\t| %-16s | %8d | %8d | %8d |%n", stat.getPlayer_name(),
                stat.getNumGames(), stat.getNumWins(), stat.getNumCards());
    }

    /**
     * Method to print all the rows of the DataBase with a table format
     */
    public void viewAllStatistics() {
        ArrayList<String> statMenuTitle = new ArrayList<>();
        statMenuTitle.add("=============================================================");
        statMenuTitle.add("\t\t\t\t\t VIEW ALL STATISTICS");
        statMenuTitle.add("=============================================================");

        for (String line : statMenuTitle) {
            System.out.println(line);
        }

        // Printing title:

        System.out.printf("\t-----------------------------------------------------%n");
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "PLAYER", "# Games", "# WINS", "#CARDS+2");
        System.out.printf("\t| %-16s | %-8s | %-8s | %-8s |%n", "NAME", "Played", "", " DRAWN+4");
        System.out.printf("\t-----------------------------------------------------%n");

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line; // Line to read

            while ((line = bufferedReader.readLine()) != null) {
                String[] stringStats = line.split(",");
                Statistics stat = new Statistics(stringStats[0], Integer.parseInt(stringStats[1]),
                        Integer.parseInt(stringStats[2]), Integer.parseInt(stringStats[3]));
                statToTableFormatPrintf(stat);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("\t-----------------------------------------------------%n");
    }




    public static void main(String[] args) throws InterruptedException {

        DataBase myDatabase = new DataBase("stats.txt");

        myDatabase.createFile();
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
        myDatabase.viewAllStatistics();

    }







}