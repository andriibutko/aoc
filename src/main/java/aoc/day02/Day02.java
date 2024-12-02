package aoc.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import aoc.Day;
import aoc.Utils;

public class Day02 implements Day {

    @Override
    public String part1(String input) {
        var lines = parseInput(input).toList();
        
        int result = 0;

        for (List<Integer> line : lines) {
            var gradual = isDescendingOrAscending(line);
            if (gradual) {
                result++;
            }
        }

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        var lines = parseInput(input).toList();
        
        int result = 0;

        for (List<Integer> line : lines) {
            var gradual = isDescendingOrAscending(line);
            if (gradual) {
                result++;
            } else {
                for (int i = 0; i < line.size(); i++) {
                    var mutableLine = new ArrayList<>(line);
                    mutableLine.remove(i);
                    if (isDescendingOrAscending(mutableLine)) {
                        result++;
                        break;
                    }
                }
            }
        }

        return String.valueOf(result);
    }
    
    private Stream<List<Integer>> parseInput(String input) {
        return Utils.splitLines(input).stream()
                .map(line -> Arrays.asList(line.split("\\s+")))
                .map(list -> list.stream().map(Integer::parseInt).toList());
    }

private boolean isDescendingOrAscending(List<Integer> line) {
        boolean isDescending = true;
        boolean isAscending = true;

        for (int i = 0; i < line.size() - 1; i++) {
            if (line.get(i) - line.get(i + 1) > 0 && line.get(i) - line.get(i + 1) <= 3 && isDescending) {
                isAscending = false;
            } else if ((line.get(i) - line.get(i + 1) > 0 && line.get(i) - line.get(i + 1) <= 3 && isDescending)){
                
            }
            else if (line.get(i) - line.get(i + 1) < 0 && line.get(i) - line.get(i + 1) >= -3 && isAscending) {
                isDescending = false;
            } else {
                isDescending = false;
                isAscending = false;
            }
        }

        return isDescending || isAscending;
    }
}
