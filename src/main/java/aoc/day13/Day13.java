package aoc.day13;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.common.Pair;
import static aoc.common.Pair.pair;

public class Day13 implements aoc.Day {

    record Location(int x, int y) { }

    /*
     * Button A: ax, ay. an - amount of minimum pushes
     * Button B: bx, by, bn - amount of minimum pushes
     * 
     * px, py - prize coordinates
     * 
     * ax * an + bx * bn = px  (multiply by by) -> ax * an * by + bx * bn * by = px * by
     * ay * an + by * bn = py  (multiply by bx) -> ay * an * bx + by * bn * bx = py * bx
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

        for(var part : parts) {
            String[] lines = part.split(System.lineSeparator());

            Location a = parseCoordinates(lines[0]);
            Location b = parseCoordinates(lines[1]);
            Location prize = parseCoordinates(lines[2]);

            int ax = a.x;
            int ay = a.y;
            int bx = b.x;
            int by = b.y;
            int px = prize.x;
            int py = prize.y;

            double an = (px * by - py * bx) / (ax * by - ay * bx);
            double bn = (px - ax * an) / bx;

            if (an < 0 || bn < 0 || an > 100 || bn > 100) continue;

            if(an % 1 == 0 && bn % 1 == 0 ) {
                result += an * 3 + bn;
            }
        }

        return String.valueOf(result);
    }

    @Override
    public String part2(String input) {
        String[] parts = input.split("\n\n");

        long result = 0;
        long offset = 10000000000000L;

        for(var part : parts) {
            String[] lines = part.split(System.lineSeparator());

            Location a = parseCoordinates(lines[0]);
            Location b = parseCoordinates(lines[1]);
            Location prize = parseCoordinates(lines[2]);

            long ax = a.x;
            long ay = a.y;
            long bx = b.x;
            long by = b.y;
            long px = prize.x + offset;
            long py = prize.y + offset;

            var det = ax * by - ay * bx;
            if (det == 0) {
               continue; // if determinant is 0, then there is no solution
            }

            long an = by * px - bx * py;
            long bn = -ay * px + ax * py;

            if (an % det == 0 && bn % det == 0) {
                result += ((an * 3) + bn) / det;
            }
        }

        return String.valueOf(result);
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
