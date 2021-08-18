package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

public class Analizy {
    public void unavailable(String source, String target) {
        String downTimeBegin = null;
        String downTimeEnd;
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target));
             BufferedReader in = new BufferedReader(new FileReader(source))) {
            String lineFromLog;
            while ((lineFromLog = in.readLine()) != null) {
                if (lineFromLog.startsWith("400 ") || lineFromLog.startsWith("500 ")) {
                    if (downTimeBegin == null) {
                        downTimeBegin = lineFromLog.substring(4);
                        out.print(downTimeBegin + ";");
                    }
                } else {
                    if (downTimeBegin != null) {
                        downTimeEnd = lineFromLog.substring(4);
                        out.println(downTimeEnd + ";");
                        downTimeBegin = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<String> readFile(String file) {
        String line;
        List<String> linesFromFile = new LinkedList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            while ((line = in.readLine()) != null) {
                linesFromFile.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linesFromFile;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String line : readFile("unavailable.csv")) {
            System.out.println(line);
        }
        System.out.println();
        new Analizy().unavailable("server.log", "unavailable.csv");
        for (String line : readFile("unavailable.csv")) {
            System.out.println(line);
        }
    }


}
