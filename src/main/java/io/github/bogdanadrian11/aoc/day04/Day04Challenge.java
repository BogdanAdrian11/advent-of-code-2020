package io.github.bogdanadrian11.aoc.day04;

import io.github.bogdanadrian11.aoc.Challenge;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.github.bogdanadrian11.aoc.util.IO.readFile;

public class Day04Challenge implements Challenge<String, Long> {
    public static final String DIGITS_REGEX = "[0-9]+";
    public static final String PASSWORD_ID_REGEX = "[0-9]{9}";
    public static final String HAIR_COLOR_REGEX = "#[0-9a-f]{6}";
    public static final String BIRTH_YEAR = "byr";
    public static final String ISSUE_YEAR = "iyr";
    public static final String EXPIRATION_YEAR = "eyr";
    public static final String HEIGHT = "hgt";
    public static final String HAIR_COLOR = "hcl";
    public static final String EYE_COLOR = "ecl";
    public static final String PASSPORT_ID = "pid";

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

    @Override
    public Long partA(String input) {
        return Arrays.stream(input
                .split("\n\n"))
                .filter(line -> line.contains(BIRTH_YEAR))
                .filter(line -> line.contains(ISSUE_YEAR))
                .filter(line -> line.contains(EXPIRATION_YEAR))
                .filter(line -> line.contains(HEIGHT))
                .filter(line -> line.contains(HAIR_COLOR))
                .filter(line -> line.contains(EYE_COLOR))
                .filter(line -> line.contains(PASSPORT_ID))
                .count();
    }

    @Override
    public Long partB(String input) {
        return Arrays.stream(input
                .split("\n\n"))
                .map(line -> line.split("[ \\n]"))
                .map(this::toPassportMap)
                .filter(passport -> isByrValid(passport.get(BIRTH_YEAR)))
                .filter(passport -> isIyrValid(passport.get(ISSUE_YEAR)))
                .filter(passport -> isEyrValid(passport.get(EXPIRATION_YEAR)))
                .filter(passport -> isHgtValid(passport.get(HEIGHT)))
                .filter(passport -> isHclValid(passport.get(HAIR_COLOR)))
                .filter(passport -> isEclValid(passport.get(EYE_COLOR)))
                .filter(passport -> isPidValid(passport.get(PASSPORT_ID)))
                .count();
    }

    private boolean isPidValid(String pid) {
        return pid != null &&
                pid.matches(PASSWORD_ID_REGEX);
    }

    private boolean isEclValid(String ecl) {
        return ecl != null &&
                Stream.of(EyeColor.values())
                        .map(EyeColor::getColor)
                        .anyMatch(color -> color.contains(ecl));
    }

    private boolean isHclValid(String hcl) {
        return hcl != null &&
                hcl.matches(HAIR_COLOR_REGEX);
    }

    private boolean isHgtValid(String hgt) {
        if (hgt == null) {
            return false;
        }
        if (hgt.contains("cm")) {
            String hgtValue = hgt.replace("cm", "");
            return hgtValue.matches(DIGITS_REGEX) &&
                    Integer.parseInt(hgtValue) >= 150 &&
                    Integer.parseInt(hgtValue) <= 193;
        }
        if (hgt.contains("in")) {
            String hgtValue = hgt.replace("in", "");
            return hgtValue.matches(DIGITS_REGEX) &&
                    Integer.parseInt(hgtValue) >= 59 &&
                    Integer.parseInt(hgtValue) <= 76;
        }
        return false;
    }

    private boolean isEyrValid(String eyr) {
        return eyr != null &&
                eyr.matches(DIGITS_REGEX) &&
                Integer.parseInt(eyr) >= 2020 &&
                Integer.parseInt(eyr) <= 2030;
    }

    private boolean isIyrValid(String iyr) {
        return iyr != null &&
                iyr.matches(DIGITS_REGEX) &&
                Integer.parseInt(iyr) >= 2010 &&
                Integer.parseInt(iyr) <= 2020;
    }

    private boolean isByrValid(String byr) {
        return byr != null &&
                byr.matches(DIGITS_REGEX) &&
                Integer.parseInt(byr) >= 1920 &&
                Integer.parseInt(byr) <= 2002;
    }

    private Map<String, String> toPassportMap(String[] attributes) {
        Map<String, String> passport = new HashMap<>();
        for (String attribute : attributes) {
            String[] entry = attribute.split(":");
            passport.put(entry[0], entry[1]);
        }
        return passport;
    }
}
