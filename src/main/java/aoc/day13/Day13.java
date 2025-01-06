package aoc.day13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day13 implements aoc.Day {

    record Location(int x, int y) {
    }

    /*
     * Button A: ax, ay. an - amount of minimum pushes
     * Button B: bx, by, bn - amount of minimum pushes
     * 
     * px, py - prize coordinates
     * 
     * ax * an + bx * bn = px (multiply by by) -> ax * an * by + bx * bn * by = px *
     * by
     * ay * an + by * bn = py (multiply by bx) -> ay * an * bx + by * bn * bx = py *
     * bx
     * 
     * bx * bn * by = px * by - ax * an * by
     * bx * bn * by = py * bx - ay * an * bx
     * 
     * px * by - ax * an * by = py * bx - ay * an * bx
     * ay * an * bx - ax * an * by = py * bx - px * by
     * 
     * an = (px * by - py * bx) / (ax * by - ay * bx)
     * bn = (px - ax * an) / bx
     * 
     */
    @Override
    public String part1(String input) {
        String[] parts = input.split("\n\n");

        int result = 0;

        for (var part : parts) {
            String[] lines = part.split(System.lineSeparator());

            Location a = parseCoordinates(lines[0]);
            Location b = parseCoordinates(lines[1]);
            Location prize = parseCoordinates(lines[2]);

            int px = prize.x;
            int py = prize.y;

            result += getCost(a, b, px, py);
        }

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        String[] parts = input.split("\n\n");

        long result = 0;
        long offset = 10000000000000L;

        for (var part : parts) {
            String[] lines = part.split(System.lineSeparator());

            Location a = parseCoordinates(lines[0]);
            Location b = parseCoordinates(lines[1]);
            Location prize = parseCoordinates(lines[2]);

            long px = prize.x + offset;
            long py = prize.y + offset;

            result += getCost(a, b, px, py);
        }

        return String.valueOf(result);
    }

    private long getCost(Location a, Location b, long px, long py) {
        long ax = a.x;
        long ay = a.y;
        long bx = b.x;
        long by = b.y;

        var detA = ax * by - ay * bx;
        long numA = px * by - py * bx;

        // check if the system has a solution
        if (detA == 0 && bx != 0 || numA % detA != 0) {
            return 0;
        }

        long an = numA / detA;
        long bn = (px - ax * an) / bx;

        return an * 3 + bn;
    }

    private static Location parseCoordinates(String input) {
        Pattern pattern = Pattern.compile("X[+=](\\d+), Y[+=](\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            return new Location(x, y);
        }

        throw new IllegalArgumentException("Invalid input: " + input);
    }

}
