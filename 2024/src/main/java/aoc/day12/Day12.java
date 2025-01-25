package aoc.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.common.Pair;
import static aoc.common.Pair.pair;

public class Day12 implements aoc.Day {

    @Override
    public String part1(String input) {
        var matrix = Utils.matrixFrom(input);

        var gVisited = new HashSet<Pair<Integer, Integer>>();

        var perimeter = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                var cell = pair(i, j);
                if (gVisited.contains(cell)) {
                    continue;
                }
                var targetValue = matrix[i][j];

                var visited = new HashMap<Pair<Integer, Integer>, Integer>();

                dfs(matrix, cell, targetValue, visited);
                perimeter += visited.keySet().size() * visited.values().stream().reduce(0, Integer::sum);
                gVisited.addAll(visited.keySet());
            }
        }

        return String.valueOf(perimeter);
    }

    @Override
    public String part2(String input) {
        char[][] matrix = Utils.matrixFrom(input);
        long result = 0;
        Set<Pair<Integer, Integer>> counted = new HashSet<>();

        for (int y0 = 0; y0 < matrix.length; y0++) {
            for (int x0 = 0; x0 < matrix[0].length; x0++) {
                Pair<Integer, Integer> start = pair(x0, y0);
                if (counted.contains(start)) {
                    continue;
                }

                Set<Pair<Integer, Integer>> visited = new HashSet<>();
                char currentRegion = matrix[y0][x0];

                List<Integer>[] upRows = new ArrayList[matrix.length + 1];
                List<Integer>[] downRows = new ArrayList[matrix.length + 1];
                List<Integer>[] upCols = new ArrayList[matrix[0].length + 1];
                List<Integer>[] downCols = new ArrayList[matrix[0].length + 1];
                
                for (int i = 0; i <= matrix.length; i++) {
                    upRows[i] = new ArrayList<>();
                    downRows[i] = new ArrayList<>();
                }
                for (int i = 0; i <= matrix[0].length; i++) {
                    upCols[i] = new ArrayList<>();
                    downCols[i] = new ArrayList<>();
                }

                Function<Pair<Integer, Integer>, Boolean> dfs = new Function<>() {
                    @Override
                    public Boolean apply(Pair<Integer, Integer> cell) {
                        int x = cell.a();
                        int y = cell.b();

                        if (Utils.isOutOfBounds(matrix, x, y) || matrix[y][x] != currentRegion) return true;
                        if (visited.contains(cell)) return false;

                        visited.add(cell);

                        if (apply(pair(x - 1, y))) upCols[x].add(y);
                        if (apply(pair(x + 1, y))) downCols[x + 1].add(y);
                        if (apply(pair(x, y - 1))) upRows[y].add(x);
                        if (apply(pair(x, y + 1))) downRows[y + 1].add(x);

                        return false;
                    }
                };

                dfs.apply(start);

                long currentPrice = 0;
                for (List<Integer> list : merge(upCols, downCols, upRows, downRows)) {
                    if (list.isEmpty()) continue;
                    list.sort(Integer::compareTo);
                    currentPrice++;
                    for (int i = 1; i < list.size(); i++) {
                        if (!list.get(i - 1).equals(list.get(i) - 1)) {
                            currentPrice++;
                        }
                    }
                }

                result += visited.size() * currentPrice;
                counted.addAll(visited);
            }
        }

        return String.valueOf(result);
    }

    private boolean dfs(char[][] matrix, Pair<Integer, Integer> cell, char targetValue, HashMap<Pair<Integer, Integer>, Integer> visited) {
        var x = cell.getLeft();
        var y = cell.getRight();

        if (Utils.isOutOfBounds(matrix, x, y) || matrix[x][y] != targetValue) {
            return true;
        }

        if (visited.containsKey(cell)) return false;
        visited.put(cell, 0);

        if(dfs(matrix, pair((Integer) (x + 1), y), targetValue, visited)) visited.merge(pair(x, y), 1, Integer::sum);
        if(dfs(matrix, pair((Integer) (x - 1), y), targetValue, visited)) visited.merge(pair(x, y), 1, Integer::sum);
        if(dfs(matrix, pair(x, (Integer) (y + 1)), targetValue, visited)) visited.merge(pair(x, y), 1, Integer::sum);
        if(dfs(matrix, pair(x, (Integer) (y - 1)), targetValue, visited)) visited.merge(pair(x, y), 1, Integer::sum);

        return false;
    }

    public static List<List<Integer>> merge(List<Integer>[]... lists) {
        return Arrays.stream(lists) 
                     .flatMap(Arrays::stream) 
                     .collect(Collectors.toList()); 
    }
}
