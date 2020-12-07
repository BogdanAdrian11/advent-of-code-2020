package io.github.bogdanadrian11.aoc.day01;

import io.github.bogdanadrian11.aoc.Challenge;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.bogdanadrian11.aoc.util.IO.readFileLines;

public class Day01Challenge implements Challenge {
    private static final int SUM = 2020;

    @Override
    public void solve(String inputPath) {
        try {
            List<Integer> values = readFileLines(inputPath).stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
            System.out.println(partA(values));
            System.out.println(partB(values));
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    public Integer partA(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            int remainValue = SUM - values.get(i);
            if (values.contains(remainValue) && values.indexOf(remainValue) != i) {
                return values.get(i) * (remainValue);
            }
        }
        throw new IllegalArgumentException("Solution not possible");
    }

    public Integer partB(List<Integer> values) {
        for (int i = 0; i < values.size(); i++) {
            for (int j = i + 1; j < values.size(); j++) {
                int remainValue = SUM - values.get(i) - values.get(j);
                if (values.contains(remainValue) && values.indexOf(remainValue) != i
                        && values.indexOf(remainValue) != j) {
                    return values.get(i) * values.get(j) * remainValue;
                }
            }
        }
        throw new IllegalArgumentException("Solution not possible");
    }

}
