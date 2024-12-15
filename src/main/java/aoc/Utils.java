package aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class Utils {
    
    public static List<String> splitLines(String input) {
        return Arrays.asList(input.split(System.lineSeparator()));
    }

    public static Stream<String> streamLines(String input) {
        return Stream.of(input.split(System.lineSeparator()));
    }

    public static char[][] matrixFrom(String input) {
        return Stream.of(input.split(System.lineSeparator()))
            .map(String::toCharArray).toArray(char[][]::new);
    }

    public static int[][] intMatrixFrom(String input) {
        return Stream.of(input.split(System.lineSeparator()))
        .map(line -> line.chars().map(Character::getNumericValue).toArray()).toArray(int[][]::new);
    }

    public static boolean isOutOfBounds(char[][] matrix, int x, int y) {
        return x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length;
    }

    public static boolean isOutOfBounds(int[][] matrix, int[] cell) {
        var r = cell[0];
        var c = cell[1];

        return r < 0 || r >= matrix.length || c < 0 || c >= matrix[0].length;
    }

}
