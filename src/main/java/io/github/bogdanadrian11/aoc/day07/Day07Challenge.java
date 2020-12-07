package io.github.bogdanadrian11.aoc.day07;

import io.github.bogdanadrian11.aoc.Challenge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static io.github.bogdanadrian11.aoc.util.IO.readFileLines;

public class Day07Challenge implements Challenge{
    private static final Pattern NUMBER_REGEX = Pattern.compile("[0-9]+");

    @Override
    public void solve(String inputPath) {
        Map<String, Map<String, Integer>> bags = new HashMap<>();
        Map<String, List<String>> containedBy = new HashMap<>();
        try {
            List<String> rules = readFileLines(inputPath);
            for (String rule : rules) {
                Map<String, Integer> nrOfContained = new HashMap<>();
                String[] parts = rule.split(" bags contain ");
                String[] containedParts = parts[1].split("[,.]");
                if (!containedParts[0].contains("no other bags")) {
                    for (String containedPart : containedParts) {
                        Integer numberMatch = NUMBER_REGEX.matcher(containedPart).results()
                                .map(MatchResult::group)
                                .map(Integer::valueOf)
                                .findFirst()
                                .orElseThrow();
                        String color = containedPart.replace(String.valueOf(numberMatch), "")
                                .replace("bags", "")
                                .replace("bag", "")
                                .trim();
                        nrOfContained.put(color, numberMatch);
                        if (containedBy.get(color) != null) {
                            containedBy.get(color).add(parts[0]);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(parts[0]);
                            containedBy.put(color, list);
                        }
                    }
                }
                bags.put(parts[0], nrOfContained);
            }

            System.out.println(partA(containedBy));
            System.out.println(partB(bags));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer partA(Map<String, List<String>> input) {
        return match(input, new ArrayList<>(), "shiny gold");
    }

    private Integer match(Map<String, List<String>> input, List<String> visited, String color) {
        if (input.get(color) == null) {
            return 0;
        }
        int sum = 0;
        for (String c : input.get(color)) {
            if (visited.contains(c)) {
                continue;
            }
            visited.add(c);
            sum = sum + 1 + match(input, visited, c);
        }
        return sum;
    }

    public Integer partB(Map<String, Map<String, Integer>> input) {
        return contains(input, "shiny gold");
    }

    private Integer contains(Map<String, Map<String, Integer>> input, String color) {
        Map<String, Integer> bags = input.get(color);
        if (bags == null || bags.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Map.Entry<String, Integer> entry : bags.entrySet()) {
            String c = entry.getKey();
            sum = sum + entry.getValue() + (entry.getValue() * contains(input, c));
        }
        return sum;

    }
}
