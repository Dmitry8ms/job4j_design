package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileReader;

public class ReadFile {
    public static void main(String[] args) {
        try (FileReader in = new FileReader("input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
