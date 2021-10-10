package ru.job4j.io.searchfile;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

public class SearchVisitor extends SimpleFileVisitor<Path> {
    private final PathMatcher matcher;
    private final List<String> pathList = new LinkedList<>();

    public SearchVisitor(SearchFile searchFile) {
        String howFind = searchFile.getTValue();
        this.matcher = searchFile.getMatcher(howFind);
    }
    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e) {
        System.err.printf("Visiting failed for %s\n", file);
        return FileVisitResult.SKIP_SUBTREE;
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        System.out.println("Visiting file: " + file);
        if (Files.isReadable(file) && matcher.matches(file.getFileName())) {
            System.out.println("Found match: " + file);
            pathList.add(file.toString());
        }
        return FileVisitResult.CONTINUE;
    }

    List<String> getFoundFiles() {
        return pathList;
    }

}
