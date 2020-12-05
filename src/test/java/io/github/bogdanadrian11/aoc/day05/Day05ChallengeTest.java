package io.github.bogdanadrian11.aoc.day05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Day05ChallengeTest {
    private Day05Challenge challenge;

    @BeforeEach
    public void beforeEach() {
        challenge = new Day05Challenge();
    }

    @Test
    public void testDecodeID() {
        assertEquals(357, challenge.decodeID("FBFBBFFRLR"));
        assertEquals(567, challenge.decodeID("BFFFBBFRRR"));
        assertEquals(119, challenge.decodeID("FFFBBBFRRR"));
        assertEquals(820, challenge.decodeID("BBFFBBFRLL"));
    }

    @Test
    public void testA() {
        assertEquals(820, challenge.partA(
                List.of("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")));
    }

    @Test
    public void testB() {
        assertThrows(RuntimeException.class,
                () -> challenge.partB(List.of("FBFBBFFRLR", "BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")));
    }

}
