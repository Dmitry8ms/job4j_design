package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        for (Path source : sources) {
            packSingleFile(source, target);
        }
    }

    public static void packSingleFile(Path source, File target) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zipOut.putNextEntry(new ZipEntry(source.toString()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toString()))) {
                zipOut.write(in.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName arguments = ArgsName.of(args);
        Path root = Path.of(arguments.get("d"));
        String extension = arguments.get("e");
        File target = new File(arguments.get("o"));
        List<Path> sources = Search.search(root, path -> !path.toFile().getName().endsWith(extension));
        packFiles(sources, target);
    }

}