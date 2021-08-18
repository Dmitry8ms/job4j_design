package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, Integer> dupCount = new HashMap<>();
    private int count = 1;
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        dupCount.computeIfPresent(new FileProperty(file.toFile().getName(), file.toFile().length()),
                (k, v) -> v = v + 1);
        dupCount.putIfAbsent(new FileProperty(file.toFile().getName(), file.toFile().length()),
                1);
        return super.visitFile(file, attrs);
    }
    public List<FileProperty> getDups() {
        return dupCount.entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }

}
