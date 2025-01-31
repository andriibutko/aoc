package aoc.day06;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day06Test {

    String txt = """
                ....#.....
                .........#
                ..........
                ..#.......
                .......#..
                ..........
                .#..^.....
                ........#.
                #.........
                ......#...
                            """;

    @Test
    public void testPart1() {
        // Given
        String input = txt;

        // When
        String result = new Day06().part1(input);

        // Then
        assertEquals("41", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = txt;
        // When
        String result = new Day06().part2(input);

        // Then
        assertEquals("6", result);
    }
}
