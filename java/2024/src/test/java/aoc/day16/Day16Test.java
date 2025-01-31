package aoc.day16;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day16Test {


    @Test
    public void testPart1() {
        // Given
        String input = "";

        // When
        String result = new Day16().part1(input);

        // Then
        assertEquals("2028", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = "";
        String result = new Day16().part2(input);

        // Then
        assertEquals("105", result);
    }
}
