package aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import aoc.common.Location;


public class Utils {
    
    public static List<String> splitLines(String input) {
        return Arrays.asList(input.split(System.lineSeparator()));
    }

    public static Stream<String> streamLines(String input) {
        return Stream.of(input.split(System.lineSeparator()));
    }

    public static char[][] matrixFrom(String input) {
        return Stream.of(input.split(System.lineSeparator()))
            .map(String::trim)
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

    public static void printMatrix(char[][] matrix) {
        System.out.println(">> Printing matrix <<");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }   

    public static Location getLocation(char[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == c) {
                    return new Location(i, j);
                }
            }
        }
        throw new IllegalArgumentException("Character not found in matrix");
    }

}
