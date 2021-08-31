package ru.job4j.serialization.xml;

import ru.job4j.serialization.json.Owner;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

public class Auto {
    private final String model;
    private final String color;
    private final Owner owner;
    private final boolean stolen;
    private final String[] options;

    public Auto(String model, String color, Owner owner, boolean stolen, String... options) {
        this.model = model;
        this.color = color;
        this.owner = owner;
        this.stolen = stolen;
        this.options = options;
    }

    public static void main(String[] args) {
        ru.job4j.serialization.json.Auto vw = new ru.job4j.serialization.json.Auto("Touareg",
                "Moon Light", new Owner("Ivan Ivanov", 40, "77AUM676"),
                false, "busyness interior", "glass roof", "sport engine");
    }
}