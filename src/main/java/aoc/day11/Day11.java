package aoc.day11;

import java.util.HashMap;
import java.util.stream.Stream;

import aoc.Day;
import aoc.common.*;;

public class Day11 implements Day {

    final HashMap<Pair<Long, Integer>, Long> memo = new HashMap<>();

    @Override
    public String part1(String input) {
        var stones = Stream.of(input.trim().split(" "))
                .mapToLong(Long::parseLong);

        var result = stones.map(s -> {
            return processStone(s, 25);
        }).sum();

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        var stones = Stream.of(input.trim().split(" "))
                .mapToLong(Long::parseLong);

        var result = stones.map(s -> {
            return processStone(s, 75);
        }).sum();

        return String.valueOf(result);
    }

    private long processStone(long stone, int blinksLeft) {
        if (blinksLeft == 0) {
            return 1;
        }

        var key = Pair.of(stone, blinksLeft);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        memo.put(key, blink(stone, blinksLeft));

        return memo.get(key);
    }

    private long blink(long stone, int blinksLeft) {
        if (stone != 0) {
            long length = stone == 0 ? 1 : (long) Math.log10(stone) + 1;
            if (length % 2 == 0) {
                var divisor = (long) Math.pow(10, length / 2);

                return processStone(stone / divisor, blinksLeft - 1) + processStone(stone % divisor, blinksLeft - 1);
            }
            return processStone(stone * 2024, blinksLeft - 1);
        }

        return processStone(1L, blinksLeft - 1);
    }

}
