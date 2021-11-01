package ru.job4j.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String line;
        String[] keyValue;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while ((line = read.readLine()) != null) {
                line = line.trim();
                if (!line.startsWith("#") && !line.isEmpty()) {
                    if (line.split("=").length < 2) {
                        throw new IllegalArgumentException();
                    }
                    keyValue = line.split("=");
                    values.put(keyValue[0].trim(), keyValue[1].trim());
                    System.out.printf("Loaded key = %s  Loaded value = %s %n", keyValue[0], keyValue[1]);
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
        Config config = new Config("app.properties");
        System.out.println(config);
        System.out.println();
        config.load();
        System.out.println(config.value("hibernate.dialect"));
    }

}
