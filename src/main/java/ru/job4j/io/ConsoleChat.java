package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> log = new LinkedList<>();

        try (BufferedReader console = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            List<String> botAnswers = readPhrases();
            String phraseFromConsole;
            String startStop = CONTINUE;
            String botAnswer;
            phraseFromConsole = console.readLine();
            while (!OUT.equals(phraseFromConsole)) {
                log.add(phraseFromConsole);
                if (STOP.equals(phraseFromConsole)) {
                    startStop = STOP;
                } else if (CONTINUE.equals(phraseFromConsole)) {
                    startStop = CONTINUE;
                }
                if (CONTINUE.equals(startStop)) {
                    botAnswer = botAnswers.get((int) (Math.random() * botAnswers.size()));
                    System.out.println(botAnswer);
                    log.add(botAnswer);
                }
                phraseFromConsole = console.readLine();
            }
            saveLog(log);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private List<String> readPhrases() throws IOException {
        return Files.lines(Path.of(botAnswers), StandardCharsets.UTF_8)
                .collect(Collectors.toList());
    }

    private void saveLog(List<String> log) throws IOException {
        Files.write(Path.of(path), log, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("log.txt", "botAnswers.txt");
        cc.run();
    }
}
