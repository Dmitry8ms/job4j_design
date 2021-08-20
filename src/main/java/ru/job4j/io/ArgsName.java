package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] keyValue;
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "No arguments provided. Please provide array with arguments");
        }
        for (String arg : args) {
                if (!arg.contains("=") || arg.startsWith("=") || arg.endsWith("=")) {
                    throw new IllegalArgumentException("Wrong argument format. Use argument format KEY=VALUE");
                }
                keyValue = arg.split("=");
                String key = keyValue[0].substring(1);
                String value = keyValue[1];
                values.put(key, value);
                //System.out.printf("Loaded key = %s - loaded value = %s%n", key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}