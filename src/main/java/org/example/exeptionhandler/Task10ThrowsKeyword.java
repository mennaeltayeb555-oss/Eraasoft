package org.example.exeptionhandler;

import java.io.FileReader;
import java.io.IOException;

public class Task10ThrowsKeyword {
    public static void readFile() throws IOException {
        FileReader file = new FileReader("test.txt");
        System.out.println("File opened successfully.");
        file.close();
    }
    //الميثود أعلنت بس إنها ممكن ترمي exception
    //وبعدين ال main تمسكه

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("Error: file problem occurred.");
        }
    }
}
