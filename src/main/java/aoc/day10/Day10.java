package aoc.day10;

import java.util.HashSet;
import java.util.Optional;
import java.util.HashMap;

import aoc.Day;
import aoc.Utils;

public class Day10 implements Day {

    final int[][] directions = new int[][] {
        {1, 0},
        {0, 1},
        {-1, 0},
        {0, -1}
    };

    final int START = 0;

    @Override
    public String part1(String input) {
        var matrix = Utils.intMatrixFrom(input);
        var results = new HashMap<String, Integer>();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == START) {
                    var start = new int[] {r, c};
                    dfs(matrix, start, start, results, Optional.empty(), new HashSet<>());
                }
            }
        }

        return String.valueOf(results.keySet().size());
    }


    @Override
    public String part2(String input) {
        var matrix = Utils.intMatrixFrom(input);
        var results = new HashMap<String, Integer>();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == START) {
                    var start = new int[] {r, c};
                    dfs(matrix, start, start, results, Optional.empty(), new HashSet<>());
                }
            }
        }

        return String.valueOf(results.values().stream().reduce(0, Integer::sum));
    }

    private void dfs(int[][] matrix, int[] cell, int[] start, HashMap<String, Integer> count, Optional<Integer> lastValue, HashSet<String> visited) {
        if (Utils.isOutOfBounds(matrix, cell)) {
            return;
        }

        var value = matrix[cell[0]][cell[1]];

        var state = cell[0] + "," + cell[1];
        if (visited.contains(state)) {
            return;
        }

        if (!lastValue.isEmpty() && value - lastValue.get() != 1) {
            return;
        }

        visited.add(state);

        if (value == 9) {
            count.merge(state+start[0]+start[1], 1, Integer::sum);
            return;
        }

        lastValue = Optional.of(value);
        for (var dir : directions) {
            var r = cell[0] + dir[0];
            var c = cell[1] + dir[1];
            dfs(matrix, new int[] {r, c}, start, count, lastValue, new HashSet<>(visited));
        }

    }

}
