package aoc.day03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import aoc.Day;
import aoc.Utils;

public class Day03 implements Day {

    @Override
    public String part1(String input) {
        // find all cominations of 2 numbers
        // split them and multiply them
        // sum them up

                // Define the regex to match xmul(number, number)
        var sum = getSum(input);

        return String.valueOf(sum);
    }

    @Override
    public String part2(String input) {
        
        String doNext = "do()";
        String doNot = "don't()";

        var parts = input.split(doNot);

        var filtered = new StringBuilder(parts[0]);

        for(String part: parts) {
            if(part.contains(doNext)) {
                var doThis = part.substring(part.indexOf(doNext));
                filtered.append(doThis);
            }
        }
        
        var result = getSum(filtered.toString());

        return String.valueOf(result);
    }

    private int getSum(String input) {
        String regex = "mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        // Store the results
        List<int[]> results = new ArrayList<>();

        while (matcher.find()) {
            int first = Integer.parseInt(matcher.group(1)); // First number
            int second = Integer.parseInt(matcher.group(2)); // Second number
            results.add(new int[]{first, second});
        }

        var sum = results.stream().mapToInt(combiner -> combiner[0] * combiner[1]).sum();
        return sum;
    }

    
}
