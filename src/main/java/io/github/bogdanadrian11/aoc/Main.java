package io.github.bogdanadrian11.aoc;

import io.github.bogdanadrian11.aoc.day01.Day01Challenge;
import io.github.bogdanadrian11.aoc.day02.Day02Challenge;
import io.github.bogdanadrian11.aoc.day03.Day03Challenge;
import io.github.bogdanadrian11.aoc.day04.Day04Challenge;
import io.github.bogdanadrian11.aoc.day05.Day05Challenge;
import io.github.bogdanadrian11.aoc.day06.Day06Challenge;
import io.github.bogdanadrian11.aoc.day07.Day07Challenge;

import java.util.List;

public class Main {
    private static final String FILE_PATH = "./src/main/resources/";
    private static final String FILE_EXT = ".input";
    private static final String DAY = "day";

    public static void main(String[] args) {
        var challenges = List.of(new Day01Challenge(),
                new Day02Challenge(),
                new Day03Challenge(),
                new Day04Challenge(),
                new Day05Challenge(),
                new Day06Challenge(),
                new Day07Challenge());
        for (int i = 0; i < challenges.size(); i++) {
            challenges.get(i).solve(getPath(i + 1));
        }
    }

    private static String getPath(int day) {
        return FILE_PATH +
                DAY +
                (day < 10 ? "0" + day : day) +
                FILE_EXT;
    }
}
