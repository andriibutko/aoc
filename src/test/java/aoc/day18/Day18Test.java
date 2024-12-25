package aoc.day18;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day18Test {

    @Test
    public void testPart1() {
        // Given
        String input = """
                    5,4
                    4,2
                    4,5
                    3,0
                    2,1
                    6,3
                    2,4
                    1,5
                    0,6
                    3,3
                    2,6
                    5,1
                    1,2
                    5,5
                    2,5
                    6,5
                    1,4
                    0,4
                    6,4
                    1,1
                    6,1
                    1,0
                    0,5
                    1,6
                    2,0
                            """;

        // When
        String result = new Day18().part1(input);

        // Then
        assertEquals("22", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = """
                    5,4
                    4,2
                    4,5
                    3,0
                    2,1
                    6,3
                    2,4
                    1,5
                    0,6
                    3,3
                    2,6
                    5,1
                    1,2
                    5,5
                    2,5
                    6,5
                    1,4
                    0,4
                    6,4
                    1,1
                    6,1
                    1,0
                    0,5
                    1,6
                    2,0
                        """;
        // When
        String result = new Day18().part2(input);

        // Then
        assertEquals("6,1", result);
    }
}
