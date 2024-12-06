package aoc.day06;

import java.util.HashSet;

import aoc.Day;
import aoc.Utils;

public class Day06 implements Day {

    private static final int[] FORWARD_MOVE = {-1, 0};

    @Override
    public String part1(String input) {
        var matrix = Utils.matrixFrom(input);

        // find a start
        var start = findCell(matrix, '^');

        var visited = new HashSet<String>();

        var uniqueCellsCount = move(matrix, start, FORWARD_MOVE, visited);

        return String.valueOf(uniqueCellsCount);
    }

    @Override
    public String part2(String input) {
        var matrix = Utils.matrixFrom(input);

        var start = findCell(matrix, '^');
        var inFront = new int[]{start[0] + FORWARD_MOVE[0], start[1] + FORWARD_MOVE[1]};
        int count = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                var value = matrix[i][j];
                if (value == '^' || value == '#' || inFront[0] == i && inFront[1] == j) {
                    continue;
                }
                
                var mutatedMatrix = deepCopyMatrix(matrix);
                mutatedMatrix[i][j] = '#';

                if(findCycle(mutatedMatrix, start, FORWARD_MOVE, new HashSet<>())) {
                    count++;
                }
            }
        }

        return String.valueOf(count);
    }

    private static char[][] deepCopyMatrix(char[][] matrix) {
        char[][] copy = new char[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, matrix[i].length);
        }
        return copy;
    }

    private boolean findCycle(char[][] matrix, int[] cell, int[] direction, HashSet<String> visited) {
        int row = cell[0];
        int col = cell[1];
    
        while (!isOutOfBounds(matrix, row, col)) {
            String state = row + "," + col + "," + direction[0] + "," + direction[1];
            if (visited.contains(state)) {
                return true;
            }
    
            visited.add(state);
    
            int[] nextDirection = getNextDirection(matrix, row, col, direction);
            row += nextDirection[0];
            col += nextDirection[1];
            direction = nextDirection;
        }
    
        return false; // Guard leaves the grid
    }

    public int move(char[][] matrix, int[] cell, int[] direction, HashSet<String> visited) {
        int row = cell[0];
        int col = cell[1];

        if (isOutOfBounds(matrix, row, col)) {
            return visited.size();
        }

        visited.add(row + "," + col);
        
        var nextDirection = getNextDirection(matrix, row, col, direction);
        var nextCell = new int[]{row + nextDirection[0], col + nextDirection[1]};

        return move(matrix, nextCell, nextDirection, visited);
    }

    private int[] getNextDirection(char[][] matrix, int row, int col, int[] direction) {
        int nextRow = row + direction[0];
        int nextCol = col + direction[1];
    
        if (!isOutOfBounds(matrix, nextRow, nextCol) && matrix[nextRow][nextCol] == '#') {
            var newDir = rotatedClockwise90(direction);
            return getNextDirection(matrix, row, col, newDir);
        }
    
        return direction; // Continue forward otherwise
    }
    

    private static boolean isOutOfBounds(char[][] matrix, int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length;
    }

    public static int[] rotatedClockwise90(int[] direction) {
        return new int[]{direction[1], -direction[0]};
    }

    public static int[] findCell(char[][] matrix, char target) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == target) {
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalArgumentException("Target character not found in the matrix");
    }
}
