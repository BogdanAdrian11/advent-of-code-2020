package io.github.bogdanadrian11.aoc.day02;

import io.github.bogdanadrian11.aoc.Challenge;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.bogdanadrian11.aoc.util.IO.readFileLines;

public class Day02Challenge implements Challenge {
    private static final Pattern NUMBER = Pattern.compile("[0-9]+");
    private static final Pattern LETTERS = Pattern.compile("[a-zA-Z]+");

    @Override
    public void solve(String inputPath) {
        try {
            List<PasswordLine> passwordLines = readFileLines(inputPath).stream()
                    .map(this::deserialize)
                    .collect(Collectors.toList());
            System.out.println(partA(passwordLines));
            System.out.println(partB(passwordLines));
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    public Long partA(List<PasswordLine> passwordLineStream) {
        return passwordLineStream.stream()
                .filter(this::isValidA)
                .count();
    }

    public Long partB(List<PasswordLine> passwordLines) {
        return passwordLines.stream()
                .filter(this::isValidB)
                .count();
    }

    private PasswordLine deserialize(String line) {
        List<Integer> numberMatches = NUMBER.matcher(line).results()
                .map(MatchResult::group)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        List<String> lettersMatches = LETTERS.matcher(line).results()
                .map(MatchResult::group)
                .collect(Collectors.toList());

        return new PasswordLine(new PasswordPolicy(numberMatches.get(0),
                numberMatches.get(1), lettersMatches.get(0).charAt(0)), lettersMatches.get(1));
    }

    private boolean isValidA(PasswordLine passwordLine) {
        long occurrences = passwordLine.password().chars()
                .filter(Integer.valueOf(passwordLine.policy().letter())::equals)
                .count();
        return occurrences >= passwordLine.policy().lowest() && occurrences <= passwordLine.policy().highest();
    }

    private boolean isValidB(PasswordLine passwordLine) {
        String password = passwordLine.password();
        Character letter = passwordLine.policy().letter();
        char first = password.charAt(passwordLine.policy().lowest() - 1);
        char second = password.charAt(passwordLine.policy().highest() - 1);
        return letter.equals(first) ^ letter.equals(second);
    }

}
