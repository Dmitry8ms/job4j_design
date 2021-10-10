package ru.job4j.io.searchfile;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;

public class SearchFile {

    private String whatFind;
    private String howFind;

    public static void main(String[] args) throws IOException {
        GetArgs argsFromMain = GetArgs.of(args);
        SearchFile searchFile = new SearchFile();
        searchFile.checkArgs(argsFromMain);
        searchFile.whatFind = argsFromMain.get("n");
        searchFile.howFind = argsFromMain.get("t");
        SearchVisitor searchVisitor = new SearchVisitor(searchFile);
        Path startingDir = Path.of(argsFromMain.get("d"));

        Files.walkFileTree(startingDir, searchVisitor);
        Path storeFile = Path.of(argsFromMain.get("o"));
        Files.write(storeFile, searchVisitor.getFoundFiles());
    }

    public String getTValue() {
        return howFind;
    }

    PathMatcher getMatcher(String t) {

        switch (t) {
            case "regex":
                return FileSystems.getDefault().getPathMatcher("regex:" + this.whatFind);
            case "mask":
            case "name":
                return FileSystems.getDefault().getPathMatcher("glob:" + this.whatFind);
            default:
                throw new IllegalArgumentException("Wrong t argument value. Please choose -t=mask,"
                        + " -t=name or -t=regex.");
        }
    }

    private void checkArgs(GetArgs argsFromMain) {
        if (!argsFromMain.containsArgs("d", "n", "t", "o")) {
            throw new IllegalArgumentException("Wrong arguments number. Use all -d -n -t -o keys in the utility");
        }
        String d = argsFromMain.get("d");
        String n = argsFromMain.get("n");
        String t = argsFromMain.get("t");
        String o = argsFromMain.get("o");
        if (!Files.exists(Path.of(d)) || !Files.isDirectory(Path.of(d))) {
            throw new IllegalArgumentException("Wrong directory name. Please identify existing directory.");
        }
        if (n.equals("") || t.equals("") || o.equals("")) {
                    throw new IllegalArgumentException("Empty argument value. "
                    + "Use not empty value in argument format -KEY=VALUE");
        }
        if (!t.equals("mask") && !t.equals("name") && !t.equals("regex")) {
            throw new IllegalArgumentException("Wrong t argument value. Please choose -t=mask, -t=name or -t=regex.");
        }
    }

}
