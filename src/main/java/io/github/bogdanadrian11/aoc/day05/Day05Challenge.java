package io.github.bogdanadrian11.aoc.day05;

import io.github.bogdanadrian11.aoc.Challenge;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.github.bogdanadrian11.aoc.util.IO.readFileLines;

public class Day05Challenge implements Challenge<List<String>, Integer> {
    @Override
    public void solve(String inputPath) {
        try {
            List<String> input = readFileLines(inputPath);
            System.out.println(partA(input));
            System.out.println(partB(input));
        } catch (IOException e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    public Integer decodeID(String seat) {
        return Integer.valueOf(seat
                        .replace('F', '0')
                        .replace('B', '1')
                        .replace('L', '0')
                        .replace('R', '1')
                , 2);
    }

    @Override
    public Integer partA(List<String> input) {
        return input.stream()
                .map(this::decodeID)
                .max(Comparator.comparingInt(id -> id))
                .orElseThrow();
    }

    @Override
    public Integer partB(List<String> input) {
        List<Integer> ids = input.stream()
                .map(this::decodeID)
                .sorted()
                .collect(Collectors.toList());
        return IntStream.range(0, ids.size() - 2)
                .filter(pos -> ids.get(pos + 1) - ids.get(pos) == 2)
                .map(ids::get)
                .map(id -> id + 1)
                .findFirst()
                .orElseThrow();
    }
}
