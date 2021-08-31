package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class Auto {
    private String model;
    private String color;
    private Owner owner;
    private boolean stolen;
    private String[] options;

    public Auto() {    }

    public Auto(String model, String color, Owner owner, boolean stolen, String... options) {
        this.model = model;
        this.color = color;
        this.owner = owner;
        this.stolen = stolen;
        this.options = options;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "model='" + model + '\''
                + ", color='" + color + '\''
                + ", owner=" + owner
                + ", stolen=" + stolen
                + ", options=" + Arrays.toString(options)
                + '}';
    }

    public static void main(String[] args) throws IOException {
        Auto vw = new Auto("Touareg", "Moon Light", new Owner("Ivan Ivanov", 40, "77AUM676"),
                false, "busyness interior", "glass roof", "sport engine");
        final Gson gson = new GsonBuilder().create();
        String vwString = gson.toJson(vw);
        System.out.println("JSON format: " + vwString);
        System.out.println("Auto object: " + vw);
        File tempFile = Files.createTempFile(null, null).toFile();
        PrintWriter toFile = new PrintWriter(tempFile);
        toFile.println(vwString);
        toFile.close();
        BufferedReader fromFile = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile)));
        String vwFromFile = fromFile.readLine();
        fromFile.close();
        System.out.println("JSON format from file: " + vwFromFile);
        Auto vw2 = gson.fromJson(vwFromFile, Auto.class);
        System.out.println("Auto object from file: " + vw2);
    }
}
