package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();
    public static void foo() {
        System.out.println("in foo - " + FIELD);
    }
    static {
        System.out.println("Static block initialized");
        foo();
    }
    public Config(final String path) {
        this.path = path;
    }
    public static final String FIELD = "changed";
    public void load() {
        String line;
        String[] keyValue;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while ((line = read.readLine()) != null) {
                line = line.trim();
                if (!line.startsWith("#") && !line.isEmpty()) {
                    if (!line.contains("=") || line.startsWith("=") || line.endsWith("=")) {
                        throw new IllegalArgumentException();
                    }
                    keyValue = line.split("=");
                    values.put(keyValue[0], keyValue[1]);
                    System.out.print("Loaded key = " + keyValue[0] + " - loaded value = ");
                    System.out.println(keyValue[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println("in main - " + FIELD);
        Config config = new Config("app.properties");
        System.out.println(config);
        System.out.println();
        config.load();
        System.out.format("Привет - %s! Как дела %s?", "Саша", "на работе")
                    .format("%nПривет, %s, хорошо!%n", "Дима");
    }

}
