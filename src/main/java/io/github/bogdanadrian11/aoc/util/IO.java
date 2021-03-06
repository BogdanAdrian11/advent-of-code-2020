package io.github.bogdanadrian11.aoc.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class IO {
    public static String readFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }

    public static List<String> readFileLines(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName));
    }
}
