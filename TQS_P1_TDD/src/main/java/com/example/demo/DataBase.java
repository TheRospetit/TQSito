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

    public static void main(String[] args) throws InterruptedException {
        DataBase myDatabase = new DataBase("DataBase.txt");

        myDatabase.createFile();

        Thread.sleep(1000);
        // Setejar els valors del player que volem afegir:
        Statistics statistics = new Statistics();
        statistics.setPlayer_name("PlayerNumOne");
        statistics.setNumCards(127);
        statistics.setNumGames(66);
        statistics.setNumWins(2);

        String lineToAdd = statistics.getPlayer_name() + "," + statistics.getNumGames() + "," + statistics.getNumWins() + "," + statistics.getNumCards();
        myDatabase.writeToFile(lineToAdd);
        myDatabase.searchCharacter('a');
        Thread.sleep(5000);
        statistics.setPlayer_name("BadPlayer");
        String lineToAdd2 = statistics.getPlayer_name() + "," + statistics.getNumGames() + "," + statistics.getNumWins() + "," + statistics.getNumCards();
        myDatabase.replaceLineInFile(lineToAdd, lineToAdd2);

        // myDatabase.writeToFile(String.valueOf(LocalDateTime.now()));

    }
}