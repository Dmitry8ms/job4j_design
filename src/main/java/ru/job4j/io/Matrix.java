package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }

    public static void main(String[] args) {
        int[][] table = multiple(9);
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            for (int i = 0; i < table.length; i++) {
                for (int j = 0; j < table.length; j++) {
                    out.write((table[i][j] + "\t").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
