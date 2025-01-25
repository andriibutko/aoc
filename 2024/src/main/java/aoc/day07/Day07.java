package aoc.day07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import aoc.Day;
import aoc.Utils;

public class Day07 implements Day {

    @Override
    public String part1(String input) {
        var lines = Utils.streamLines(input);
        var results = new HashMap<String, Long>();
        var operators = List.of("+", "*");

        evaluateForAll(lines, operators, results);

        return String.valueOf(results.values().stream().reduce(Long::sum).get());
    }

    @Override
    public String part2(String input) {
        var lines = Utils.streamLines(input);
        var results = new HashMap<String, Long>();
        var operators = List.of("+", "*", "||");

        evaluateForAll(lines, operators, results);

        return String.valueOf(results.values().stream().reduce(Long::sum).get());
    }

    private void evaluateForAll(Stream<String> lines, List<String> operators, HashMap<String, Long> results) {
        lines.map(line -> line.split(":"))
                .forEach(parts -> {
                    var testValue = Long.valueOf(parts[0].trim());
                    var numbersRaw = parts[1].trim();
                    var numbers = Stream.of(numbersRaw.split(" "))
                            .mapToLong(tif -> {
                                return Long.valueOf(tif.trim());
                            })
                            .toArray();

                    var expressions = new ArrayList<String>();

                    buildAllPossibleExpressions(numbers, 1, String.valueOf(numbers[0]), operators, expressions);

                    for (String expression : expressions) {
                        if (eval(expression) == testValue) {
                            results.put(numbersRaw, testValue);
                        }
                    }
                });
    }

    private long eval(String expression) {
        var parts = expression.split("#");
        var numbers = Stream.of(parts)
                .filter(part -> !part.equals("+") && !part.equals("*") && !part.equals("||"))
                .mapToLong(Long::valueOf)
                .toArray();

        var operators = Stream.of(parts)
                .filter(part -> part.equals("+") || part.equals("*") || part.equals("||"))
                .toArray(String[]::new);

        var result = numbers[0];
        for (int i = 0; i < operators.length; i++) {
            switch (operators[i]) {
                case "+":
                    result += numbers[i + 1];
                    break;
                case "*":
                    result *= numbers[i + 1];
                    break;
                case "||":
                    var concataned = String.valueOf(result) + String.valueOf(numbers[i + 1]);
                    result = Long.parseLong(concataned);
                default:
                    break;
            }
        }

        return result;
    }

    private static void buildAllPossibleExpressions(long[] numbers, int index, String current, List<String> operators, List<String> expressions) {
        if (index >= numbers.length) {
            expressions.add(current);
            return;
        }

        for (String operator : operators) {
            buildAllPossibleExpressions(numbers, index + 1, String.join("#", current, operator, String.valueOf(numbers[index])), operators, expressions);
        }
    }

}
