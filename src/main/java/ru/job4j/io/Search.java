package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException(
                    "Root folder or file extension is null. Usage java -jar search.jar ROOT_FOLDER EXTENSION.");
        }
        Path start = Paths.get(args[0]);
        if (!Files.exists(start)) {
            throw new IllegalArgumentException(
                    "Specified root folder does not exist. Please, specify an existing folder");
        }
        String ext = args[1];
        search(start, p -> p.toFile().getName().endsWith(ext))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
