package application;

import service.FileReader;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileReader fr = new FileReader("./database/dinosaur.csv");
        fr.readFile();
    }
}