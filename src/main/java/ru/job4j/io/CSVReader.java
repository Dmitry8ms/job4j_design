package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {
    private final File pathToTable;
    private final String delimiter;
    private final String toOut;
    private final Set<String> cols;
    private PrintStream output;
    private boolean validate = false;

    public CSVReader(String pathToTable, String delimiter, String toOut, String[] cols) {
        this.pathToTable = new File(pathToTable);
        this.delimiter = delimiter;
        this.toOut = toOut;
        this.cols = Set.of(cols);
    }
    public void readAndOutput() {
        validate = validate();
        if (validate) {
            List<Integer> columns = getColNumbers(cols);
            List<String> stringsToWrite = readTable(columns);
            try {
                if (!toOut.equals("stdout")) {
                    output = new PrintStream(toOut);
                } else {
                    output = System.out;
                }
                outputFiltered(stringsToWrite, output);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean validate() {
        if (!pathToTable.exists()) {
            throw new IllegalArgumentException(String.format("File %s does not exist", pathToTable.getAbsoluteFile()));
        }
        Set<String> header = new HashSet<>();
        try (Scanner table = new Scanner(pathToTable, StandardCharsets.UTF_8)) {
            if (table.hasNextLine()) {
                header = Set.of(table.nextLine().split(delimiter));
            }
            for (String col : cols) {
                if (!header.contains(col)) {
                    throw new IllegalArgumentException(String.format("%s column does not exist", col));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private void outputFiltered(List<String> stringsToWrite, PrintStream output) {
            for (String filteredLine : stringsToWrite) {
                output.println(filteredLine);
            }
            output.close();
    }

    private List<String> readTable(List<Integer> columns) {

        List<String> result = new ArrayList<>();
        String filterLine = "";
        if (!columns.isEmpty()) {
            try (Scanner table = new Scanner(pathToTable)) {
                while (table.hasNextLine()) {
                    String[] elements = table.nextLine().split(delimiter);
                    for (int col : columns) {
                        filterLine += elements[col] + delimiter;
                    }
                    filterLine = filterLine.substring(0, filterLine.length() - 1);
                    result.add(filterLine);
                    filterLine = "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private List<Integer> getColNumbers(Set<String> cols) {
        List<Integer> result = new ArrayList<>();
        String[] header;
        try (Scanner table = new Scanner(pathToTable, StandardCharsets.UTF_8)) {
            if (table.hasNextLine()) {
               header = table.nextLine().split(delimiter);
                for (int i = 0; i < header.length; i++) {
                    String head = header[i];
                    if (cols.contains(head)) {
                        result.add(i);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static void main(String[] args) {
        ArgsName a = ArgsName.of(args);
        new CSVReader(a.get("path"), a.get("delimiter"), a.get("out"), a.get("filter").split(","))
                .readAndOutput();
    }
}
