package aoc.day03;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {

    @Test
    public void testPart1() {
        // Given
        String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";

        // When
        String result = new Day03().part1(input);

        // Then
        assertEquals("161", result);
    }

    @Test
    public void testPart2() {
        // Given
        String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()";
        // When
        String result = new Day03().part2(input);

        // Then
        assertEquals("48", result);
    }
}
