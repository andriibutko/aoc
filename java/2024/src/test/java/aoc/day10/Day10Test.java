package aoc.day10;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day10Test {

    @Test
    public void testPart1() {
        // Given
        String input = """
                    89010123
                    78121874
                    87430965
                    96549874
                    45678903
                    32019012
                    01329801
                    10456732
                            """;

        // When
        String result = new Day10().part1(input);

        // Then
        assertEquals("36", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732
        """;
        // When
        String result = new Day10().part2(input);

        // Then
        assertEquals("81", result);
    }
}
