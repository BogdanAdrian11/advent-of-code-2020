package main.java.day02;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static main.java.util.IO.readFileLines;

public class Main {
    private static final String VALUES_FILE = "./src/main/resources/day02.input";
    private static final Pattern NUMBER = Pattern.compile("[0-9]+");
    private static final Pattern LETTERS = Pattern.compile("[a-zA-Z]+");

    public static void main(String[] args) {
        try {
            List<PasswordLine> passwordLines = readFileLines(VALUES_FILE).stream()
                    .map(Main::deserialize)
                    .collect(Collectors.toList());
            System.out.println(passwordLines.stream()
                    .filter(Main::isValidA)
                    .count());
            System.out.println(passwordLines.stream()
                    .filter(Main::isValidB)
                    .count());
        } catch (Exception e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    private static PasswordLine deserialize(String line) {
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

    private static boolean isValidA(PasswordLine passwordLine) {
        long occurrences = passwordLine.password().chars()
                .filter(Integer.valueOf(passwordLine.policy().letter())::equals)
                .count();
        return occurrences >= passwordLine.policy().lowest() && occurrences <= passwordLine.policy().highest();
    }

    private static boolean isValidB(PasswordLine passwordLine) {
        String password = passwordLine.password();
        Character letter = passwordLine.policy().letter();
        char first = password.charAt(passwordLine.policy().lowest() - 1);
        char second = password.charAt(passwordLine.policy().highest() - 1);
        return letter.equals(first) ^ letter.equals(second);
    }
}
