package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder numbersBlock = new StringBuilder();
        try (FileInputStream input = new FileInputStream("even.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                numbersBlock.append((char) read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] numbers = numbersBlock.toString().split(System.lineSeparator());
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            boolean rsl = num % 2 == 0;
            System.out.println("Number " + number + " is even - " + rsl);
        }
    }
}
