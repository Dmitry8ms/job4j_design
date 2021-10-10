package ru.job4j.io.searchfile;

import java.util.HashMap;
import java.util.Map;

public class GetArgs {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] keyValue;
        if (args.length == 0) {
            throw new IllegalArgumentException(
                    "No arguments provided.");
        }
        for (String arg : args) {
                if (!arg.contains("=") || !arg.startsWith("-")) {
                    throw new IllegalArgumentException("Wrong argument format. Use argument format -KEY=VALUE");
                }
                keyValue = arg.split("=");
                if (keyValue.length != 2) {
                    throw new IllegalArgumentException("Wrong argument format. Use argument format -KEY=VALUE");
                }
                String key = keyValue[0].substring(1);
                String value = keyValue[1];
                values.put(key, value);
        }
    }
    boolean containsArgs(String... args) {
        for (String arg : args) {
            if (!values.containsKey(arg)) {
                return false;
            }
        }
        return true;
    }
    public static GetArgs of(String[] args) {
        GetArgs names = new GetArgs();
        names.parse(args);
        return names;
    }

}