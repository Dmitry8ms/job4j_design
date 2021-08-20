package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zipOut.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    //System.out.println("zipping " + source);
                    zipOut.write(in.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        if (args.length < 3) {
            throw new IllegalArgumentException(
                    "Folder to archive, file exclusion, or archive name is null. "
                            + "Usage java -jar pack.jar -d=ROOT_FOLDER -e=EXCLUSION_EXTENSION -o=SOURCE_ARCHIVE_NAME.");
        }
        ArgsName arguments = ArgsName.of(args);
        Path root = Path.of(arguments.get("d"));
        if (!Files.exists(root)) {
            throw new IllegalArgumentException(
                    "Specified root folder does not exist. Please, specify an existing folder");
        }
        String extension = arguments.get("e");
        File target = new File(arguments.get("o"));
        List<Path> sources = Search.search(root, path -> !path.toFile().getName().endsWith(extension));
        packFiles(sources, target);
    }

}