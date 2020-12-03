package main.java.day03;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static main.java.util.IO.readFileLines;

public class Main {
    private static final String MAP_FILE = "./src/main/resources/day03.input";
    private static final char TREE = '#';

    public static void main(String[] args) {
        try {
            List<String> map = readFileLines(MAP_FILE);

            System.out.println(traverse(map, 1, 3));
            System.out.println(new BigInteger(String.valueOf(traverse(map, 1, 1)))
                    .multiply(new BigInteger(String.valueOf(traverse(map, 1, 3))))
                    .multiply(new BigInteger(String.valueOf(traverse(map, 1, 5))))
                    .multiply(new BigInteger(String.valueOf(traverse(map, 1, 7))))
                    .multiply(new BigInteger(String.valueOf(traverse(map, 2, 1)))));
        } catch (IOException e) {
            System.out.printf("Error: %s", e.getMessage());
        }
    }

    private static int traverse(List<String> map, int moveDown, int moveRight) {
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
