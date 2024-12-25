package aoc.day18;

import java.util.*;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.common.Pair;
import static aoc.common.Pair.pair;

public class Day18 implements aoc.Day {

    final int[][] DIRECTIONS = {
        {-1, 0}, // Up
        {1, 0},  // Down
        {0, -1}, // Left
        {0, 1}   // Right
    };
    
    final int GRID_SIZE = 71;

    @Override
    public String part1(String input) {
        var fallenBytes = Utils.splitLines(input).stream()
            .map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray())
            .collect(Collectors.toList());

        char[][] grid = new char[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            Arrays.fill(grid[i], '.');
        }

        int minSteps = Integer.MAX_VALUE;

        for (int i = 0; i < fallenBytes.size(); i++) {
            int x = fallenBytes.get(i)[0];
            int y = fallenBytes.get(i)[1];
            grid[y][x] = '#';
            
            if (i >= 1023) {
                int[] start = {0, 0}; 
                int[] end = {GRID_SIZE - 1, GRID_SIZE - 1};
                
                var steps = bfs(grid, start, end);

                if (steps > 0) minSteps = Math.min(minSteps, steps);
            }
        }

        
        return String.valueOf(minSteps);
    }

    @Override
    public String part2(String input) {
        var fallenBytes = Utils.splitLines(input).stream()
            .map(s -> Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray())
            .collect(Collectors.toList());

        char[][] grid = new char[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            Arrays.fill(grid[i], '.');
        }

        for (int i = 0; i < fallenBytes.size(); i++) {
            int x = fallenBytes.get(i)[0];
            int y = fallenBytes.get(i)[1];
            grid[y][x] = '#';
            
            if (i >= 1023) {
                int[] start = {0, 0}; 
                int[] end = {GRID_SIZE - 1, GRID_SIZE - 1};
                
                var steps = bfs(grid, start, end);

                if (steps == -1) return x+","+y;
            }
        }
        
        return "";
    }

    private int bfs(char[][] grid, int[] start, int[] end) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start[0], start[1], 0});
        
        Set<Pair> visited = new HashSet<>();
        visited.add(Pair.pair(start[0], start[1]));

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1], dist = current[2];
            
            if(x == end[0] && y == end[1]) {
                return dist;
            }

            for(int[] direction : DIRECTIONS) {
                var dx = x + direction[0];
                var dy = y + direction[1];

                if (dx >= 0 && dx < grid.length && dy >= 0 && dy < grid[0].length && grid[dx][dy] != '#' && !visited.contains(pair(dx, dy))) {
                    visited.add(pair(dx, dy));
                    queue.offer(new int[] { x + direction[0], y + direction[1], dist + 1});
                }
            }
        }
        return -1;
    }

}
