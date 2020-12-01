package main.java.day1;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static main.java.util.IO.readFileLines;

public class Main {
    private static final int SUM = 2020;
    private static final String VALUES_FILE = "./src/main/resources/day01.input";

    public static void main(String[] args) {
        try {
            List<Integer> values = readFileLines(VALUES_FILE).stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            System.out.println(tryA(values));
            System.out.println(tryB(values));
        } catch (IOException e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }

    private static Integer tryA(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                if (values.get(i) + values.get(j) == SUM) {
                    return values.get(i) * values.get(j);
                }
            }
        }
        throw new IllegalArgumentException("Not possible");
    }

    private static Integer tryB(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                for (int k = j + 1; k < values.size(); k++) {
                    if (values.get(i) + values.get(j) + values.get(k) == SUM) {
                        return values.get(i) * values.get(j) * values.get(k);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Not possible");
    }

}
