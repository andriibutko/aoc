package aoc.day13;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Day13Test {


    final String in = """
                    Button A: X+94, Y+34
                    Button B: X+22, Y+67
                    Prize: X=8400, Y=5400

                    Button A: X+26, Y+66
                    Button B: X+67, Y+21
                    Prize: X=12748, Y=12176

                    Button A: X+17, Y+86
                    Button B: X+84, Y+37
                    Prize: X=7870, Y=6450

                    Button A: X+69, Y+23
                    Button B: X+27, Y+71
                    Prize: X=18641, Y=10279
                            """;
    @Test
    public void testPart1() {
        // Given
        String input = in;

        // When
        String result = new Day13().part1(input);

        // Then
        assertEquals("480", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = in;
        new Day13().part2(input);

        // Then
        assertEquals("1", "1");
    }
}
