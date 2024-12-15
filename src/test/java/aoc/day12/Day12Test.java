package aoc.day12;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day12Test {

    @Test
    public void testPart1() {
        // Given
        String input = """
                    RRRRIICCFF
                    RRRRIICCCF
                    VVRRRCCFFF
                    VVRCCCJFFF
                    VVVVCJJCFE
                    VVIVCCJJEE
                    VVIIICJJEE
                    MIIIIIJJEE
                    MIIISIJEEE
                    MMMISSJEEE
                            """;

        // When
        String result = new Day12().part1(input);

        // Then
        assertEquals("1930", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = """
            AAAA
            BBCD
            BBCC
            EEEC
            """;
        // When
        String result = new Day12().part2(input);

        // Then
        assertEquals("80", result);
    }
}
