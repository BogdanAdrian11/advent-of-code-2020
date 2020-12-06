package io.github.bogdanadrian11.aoc.day06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06ChallengeTest {
    private static final String input = """
            abc
                        
            a
            b
            c
                        
            ab
            ac
                        
            a
            a
            a
            a
                        
            b
            """;
    private Day06Challenge challenge;

    @BeforeEach
    public void beforeEach() {
        challenge = new Day06Challenge();
    }

    @Test
    public void testA() {
        assertEquals(11, challenge.partA(input));
    }

    @Test
    public void testB() {
        assertEquals(6, challenge.partB(input));
    }
}
