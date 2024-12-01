package aoc.day01;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day01 implements Day {

    @Override
    public String part1(String input) {
        List<String> lines = Utils.splitLines(input);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        lines.forEach(l -> {
            String[] parts = l.split("\\s+");

            left.add(Integer.valueOf(parts[0]));
            right.add(Integer.valueOf(parts[1]));
        });

        Collections.sort(left);
        Collections.sort(right);

        List<Integer> results = new ArrayList<>(left.size());
        for (int i = 0; i < left.size(); i++) {
            results.add(Math.abs(left.get(i) - right.get(i)));
        }

        int result = results.stream().reduce(0, (accumulator, combiner) -> accumulator + combiner);

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        List<String> lines = Utils.splitLines(input);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        lines.forEach(l -> {
            String[] parts = l.split("\\s+");

            left.add(Integer.valueOf(parts[0]));
            right.add(Integer.valueOf(parts[1]));
        });

        Map<Integer, Long> rightGrouped = right.stream()
                .collect(Collectors.groupingBy(v -> v, Collectors.counting()));

        var results = new ArrayList<Long>(left.size());
        left.forEach(i -> {
            if (rightGrouped.containsKey(i)) {
                var rightValue = rightGrouped.get(i);
                results.add(i * rightValue);
            }
        });

        var result = results.stream().reduce(0L, (accumulator, combiner) -> accumulator + combiner);

        return String.valueOf(result);
    }

}
