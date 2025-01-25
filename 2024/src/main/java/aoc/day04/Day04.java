package aoc.day04;

import java.util.function.Function;

import aoc.Day;
import aoc.Utils;

public class Day04 implements Day {

    final private static int[][] directions = {
        {0, 1}, // Right
        {0, -1}, // Left
        {1, 0}, // Down
        {-1, 0}, // Up
        {1, 1}, // Bottom-right diagonal
        {1, -1}, // Bottom-left diagonal
        {-1, 1}, // Top-right diagonal
        {-1, -1} // Top-left diagonal
    };

    @Override
    public String part1(String input) {
        var matrix = Utils.streamLines(input).map(String::toCharArray).toArray(char[][]::new);

        var result = countSequences(matrix, "XMAS");

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        var matrix = Utils.streamLines(input).map(String::toCharArray).toArray(char[][]::new);

        char[][] pattern1 = {
            {'M', '.', 'S'},
            {'.', 'A', '.'},
            {'M', '.', 'S'}
        };

        var result = countPatternsInMatrix(matrix, pattern1);

        return String.valueOf(result);
    }


    public int countSequences(char[][] matrix, String lookFor) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] dir : directions) {
                    if (searchFromCell(matrix, row, col, dir, lookFor)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public static int countPatternsInMatrix(char[][] grid, char[][] patterns) {
            int totalOccurrences = 0;

            char[][] currentPattern = patterns;
            for (int i = 0; i < 4; i++) {
                totalOccurrences += countPatternInMatrix(grid, currentPattern);
                currentPattern = rotate90(currentPattern);
            }

        return totalOccurrences;
    }

    public static int countPatternInMatrix(char[][] grid, char[][] pattern) {
        int rows = grid.length;
        int cols = grid[0].length;
        int pRows = pattern.length;
        int pCols = pattern[0].length;
        int count = 0;

        for (int i = 0; i <= rows - pRows; i++) {
            for (int j = 0; j <= cols - pCols; j++) {
                if (matchesPattern(grid, pattern, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean matchesPattern(char[][] grid, char[][] pattern, int startRow, int startCol) {
        int pRows = pattern.length;
        int pCols = pattern[0].length;

        for (int i = 0; i < pRows; i++) {
            for (int j = 0; j < pCols; j++) {
                if (grid[startRow + i][startCol + j] != pattern[i][j] && pattern[i][j] != '.') {
                    return false;
                }
            }
        }

        return true;
    }

    private static char[][] rotate90(char[][] pattern) {
        int pRows = pattern.length;
        int pCols = pattern[0].length;
        char[][] rotated = new char[pCols][pRows];

        for (int i = 0; i < pRows; i++) {
            for (int j = 0; j < pCols; j++) {
                rotated[j][pRows - 1 - i] = pattern[i][j];
            }
        }

        return rotated;
    }

    public int countSequences(char[][] matrix, String lookFor, Function<char[][], Boolean> check) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int count = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] dir : directions) {
                    if (searchFromCell(matrix, row, col, dir, lookFor)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private boolean searchFromCell(char[][] matrix, int row, int col, int[] dir, String lookFor) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < lookFor.length(); i++) {
            int newRow = row + i * dir[0];
            int newCol = col + i * dir[1];

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || matrix[newRow][newCol] != lookFor.charAt(i)) {
                return false;
            }
        }

        return true;
    }

}
