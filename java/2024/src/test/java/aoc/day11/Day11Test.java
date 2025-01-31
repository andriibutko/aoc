package aoc.day11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day11Test {

    @Test
    public void testPart1() {
        // Given
        String input = "125 17";

        // When
        String result = new Day11().part1(input);

        // Then
        assertEquals("55312", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = "125 17";
        // When
        String result = new Day11().part2(input);

        // Then
        assertEquals("65601038650482", result);
    }
}
