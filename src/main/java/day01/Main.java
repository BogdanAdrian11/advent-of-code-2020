package main.java.day01;

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
        } catch (Exception e) {
            System.out.println(String.format("Error: %s", e.getMessage()));
        }
    }

    private static Integer tryA(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            if (values.contains(SUM - values.get(i))) {
                return values.get(i) * (SUM - values.get(i));
            }
        }
        throw new IllegalArgumentException("Solution not possible");
    }

    private static Integer tryB(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                if (values.contains(SUM - values.get(i) - values.get(j))) {
                    return values.get(i) * values.get(j) * (SUM - values.get(i) - values.get(j));
                }
            }
        }
        throw new IllegalArgumentException("Solution not possible");
    }

}
