package aoc.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day15Test {


    final String in = """
                    ########
                    #..O.O.#
                    ##@.O..#
                    #...O..#
                    #.#.O..#
                    #...O..#
                    #......#
                    ########

                    <^^>>>vv<v>>v<<
                            """;

    final String in2 = """
                    #######
                    #...#.#
                    #.....#
                    #..OO@#
                    #..O..#
                    #.....#
                    #######

                    <vv<<^^<<^^
                    """;

    @Test
    public void testPart1() {
        // Given
        String input = in;

        // When
        String result = new Day15().part1(input);

        // Then
        assertEquals("2028", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = in2;
        String result = new Day15().part2(input);

        // Then
        assertEquals("105", result);
    }
}
