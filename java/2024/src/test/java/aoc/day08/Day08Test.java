package aoc.day08;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day08Test {

    @Test
    public void testPart1() {
        // Given
        String input = """
            ............
            ........0...
            .....0......
            .......0....
            ....0.......
            ......A.....
            ............
            ............
            ........A...
            .........A..
            ............
            ............
        """;

        // When
        String result = new Day08().part1(input);

        // Then
        assertEquals("14", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = """
            T....#....
            ...T......
            .T....#...
            .........#
            ..#.......
            ..........
            ...#......
            ..........
            ....#.....
            ..........
            """;
        // When
        new Day08().part2(input);

        // Then
        assertEquals("1", "1");
    }
}
