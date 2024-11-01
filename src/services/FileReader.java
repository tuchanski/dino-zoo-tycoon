package services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String path;

    public FileReader(String path){
        this.path = path;
    }

    public void readFile() throws FileNotFoundException {
        File file = new File(path);
        Scanner sc = new Scanner(file);

        String header = sc.nextLine();
        System.out.println(header);

        while (sc.hasNextLine()){
            String data = sc.nextLine();
            Scanner dividedData = new Scanner(data).useDelimiter(",");
            System.out.println("ID " + dividedData.nextInt());
            System.out.println("Name " + dividedData.next());
            System.out.println("Age " + dividedData.nextInt());
            System.out.println(" ");

            dividedData.close();

        }

        sc.close();
    }
}
