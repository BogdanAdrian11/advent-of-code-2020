package main.java.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class IO {

    public static List<String> readFileLines(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName));
    }
}
