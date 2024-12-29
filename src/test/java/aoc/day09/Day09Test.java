package aoc.day09;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day09Test {

    @Test
    public void testPart1() {
        // Given
        String input = "2333133121414131402";

        // When
        String result = new Day09().part1(input);

        // Then
        assertEquals("1928", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = "2333133121414131402";
        // When
        String result = new Day09().part2(input);

        // Then
        assertEquals("2858", result);
    }
}
