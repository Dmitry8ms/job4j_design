package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**Метод filter должен прочитать файл и вернуть строки, где предпоследнее число - это 404.*/

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> lines = new LinkedList<>();
        String line;
        try (BufferedReader in = new BufferedReader(new FileReader("log.txt"))) {
            while ((line = in.readLine()) != null) {
                if (line.contains(" 404 ") && correctPosition(line)) {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static boolean correctPosition(String line) {
        boolean rsl = false;
        String[] words = line.split("\\s");
        if (isNumeric(words[words.length - 1]) && words[words.length - 2].equals("404")) {
            rsl = true;
        }
        return rsl;
    }

    private static boolean isNumeric(String word) {
        for (char c : word.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String line : log) {
                out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        save(log, "404.txt");
    }
}
