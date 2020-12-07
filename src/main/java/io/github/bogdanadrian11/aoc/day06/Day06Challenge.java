package io.github.bogdanadrian11.aoc.day06;

import io.github.bogdanadrian11.aoc.Challenge;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static io.github.bogdanadrian11.aoc.util.IO.readFile;

public class Day06Challenge implements Challenge {
    @Override
    public void solve(String inputPath) {
        try {
            String input = readFile(inputPath);
            System.out.println(partA(input));
            System.out.println(partB(input));
        } catch (IOException e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }


    public Integer partA(String input) {
        return Arrays.stream(input.split("\n\n"))
                .map(group -> group.replace("\n", ""))
                .map(group -> group.chars()
                        .mapToObj(c -> (char) c)
                        .collect(Collectors.toSet()))
                .map(Set::size)
                .reduce(0, Integer::sum);
    }


    public Integer partB(String input) {
        return Arrays.stream(input.split("\n\n"))
                .map(group -> group.split("\n"))
                .map(group -> Arrays.stream(group)
                        .map(person -> person.chars()
                                .mapToObj(c -> (char) c)
                                .collect(Collectors.toSet()))
                        .collect(Collectors.toList()))
                .filter(group -> !group.isEmpty())
                .map(group -> group.stream()
                            .skip(1)
                            .peek(set -> group.get(0).retainAll(set))
                            .reduce(group.get(0), (a, b) -> a))
                .map(Set::size)
                .reduce(0, Integer::sum);
    }
}
