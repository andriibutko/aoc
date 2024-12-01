package aoc.day01;

import aoc.Day;
import aoc.Utils;

import java.util.ArrayList;
import java.util.List;

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

        List<Integer> results = new ArrayList<Integer>(left.size());
        for (int i = 0; i < left.size(); i++) {
            results.add(Math.abs(left.get(i) - right.get(i)));
        }
    
        int result = results.stream().reduce(0, (accumulator, combiner) -> accumulator + combiner);

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        return input;
    }

}