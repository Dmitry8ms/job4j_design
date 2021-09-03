package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isStolen() {
        return stolen;
    }

    public String[] getOptions() {
        return options;
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
        try (PrintWriter toFile = new PrintWriter(tempFile)) {
            toFile.println(vwString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader fromFile = new BufferedReader(
                                            new InputStreamReader(
                                                new FileInputStream(tempFile)))) {

            String vwFromFile = fromFile.readLine();
            System.out.println("JSON format from file: " + vwFromFile);
            Auto vw2 = gson.fromJson(vwFromFile, Auto.class);
            System.out.println("Auto object from file: " + vw2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* JSONObject из json-строки строки */
        JSONObject jsonOwner = new JSONObject("{\"name\":\"Ivan Ivanov\",\"age\":40,\"license\":\"77AUM676\"}");
        System.out.println("JSON string from JSONObject: " + jsonOwner);

        /* JSONArray из ArrayList */
        List<String> optionsList = new ArrayList<>();
        optionsList.addAll(List.of("busyness interior", "glass roof", "sport engine"));
        /*optionsList.add("busyness interior");
        optionsList.add("glass roof");
        optionsList.add("sport engine");*/
        JSONArray jsonOptions = new JSONArray(optionsList);
        System.out.println("JSONArray: " + jsonOptions);
        System.out.println("JSONArray works as List: " + jsonOptions.get(0));

        /* JSONObject напрямую методом put */
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", vw.getModel());
        jsonObject.put("color", vw.getColor());
        jsonObject.put("owner", jsonOwner);
        jsonObject.put("stolen", vw.isStolen());
        jsonObject.put("options", jsonOptions);

        /* Выведем результат в консоль */
        System.out.println("String of JSONObject: " + jsonObject);
        /* Преобразуем объект vw в json-строку */
        System.out.println("String of JSONObject directly: " + new JSONObject(vw));
    }
}
