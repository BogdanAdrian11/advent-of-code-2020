package io.github.bogdanadrian11.aoc.day03;

import io.github.bogdanadrian11.aoc.Challenge;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static io.github.bogdanadrian11.aoc.util.IO.readFileLines;

public class Day03Challenge implements Challenge {
    private static final char TREE = '#';

    @Override
    public void solve(String inputPath) {
        try {
            List<String> map = readFileLines(inputPath);
            System.out.println(partA(map));
            System.out.println(partB(map));
        } catch (IOException e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    public BigInteger partA(List<String> map) {
        return new BigInteger(String.valueOf(traverse(map, 1, 3)));
    }

    public BigInteger partB(List<String> map) {
        return new BigInteger(String.valueOf(traverse(map, 1, 1)))
                .multiply(new BigInteger(String.valueOf(traverse(map, 1, 3))))
                .multiply(new BigInteger(String.valueOf(traverse(map, 1, 5))))
                .multiply(new BigInteger(String.valueOf(traverse(map, 1, 7))))
                .multiply(new BigInteger(String.valueOf(traverse(map, 2, 1))));
    }

    private int traverse(List<String> map, int moveDown, int moveRight) {
        int trees = 0;
        int rowPos = 0;
        for (int i = 0; i < map.size(); i+= moveDown) {
            if (TREE == map.get(i).charAt(rowPos)) {
                trees++;
            }
            rowPos = (rowPos + moveRight) % map.get(i).length();
        }
        return trees;
    }

}
