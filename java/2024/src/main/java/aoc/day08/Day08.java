package aoc.day08;

import java.util.stream.Stream;
import java.util.*;

import aoc.Day;
import aoc.Utils;
import aoc.common.*;;

public class Day08 implements Day {

    record Location(int x, int y) {
    }

    @Override
    public String part1(String input) {
        var matrix = Utils.matrixFrom(input);

        var antenas = new HashMap<Character, ArrayList<Location>>();
        var antinodes = new HashSet<Pair<Integer, Integer>>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var freq = matrix[i][j];
                if (freq != '.') {
                    var coord = new Location(i, j);
                    antenas.putIfAbsent(freq, new ArrayList<Location>());
                    antenas.get(freq).add(coord);
                }
            }
        }

        for (var locations : antenas.values()) {

            for (int i = 0; i < locations.size(); i++) {
                for (int j = 0; j < locations.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    var a = locations.get(i);
                    var b = locations.get(j);
                    var dist = distance(a, b);

                    if (dist > 0) {
                        var x1 = a.x;
                        var y1 = a.y;
                        var x2 = b.x;
                        var y2 = b.y;

                        var dx1 = 2 * x2 - x1;
                        var dy1 = 2 * y2 - y1;

                        var dx2 = 2 * x1 - x2;
                        var dy2 = 2 * y1 - y2;

                        if (!Utils.isOutOfBounds(matrix, dx1, dy1)) {
                            if (matrix[dx1][dy1] == '.') {
                                matrix[dx1][dy1] = '#';
                            }
                            antinodes.add(Pair.of(dx1, dy1));
                        }

                        if (!Utils.isOutOfBounds(matrix, dx2, dy2)) {
                            if (matrix[dx2][dy2] == '.') {
                                matrix[dx2][dy2] = '#';
                            }
                            antinodes.add(Pair.of(dx2, dy2));
                        }
                    }
                }
            }
        }

        return String.valueOf(antinodes.size());
    }

    @Override
    public String part2(String input) {
        var matrix = Utils.matrixFrom(input);

        var antenas = new HashMap<Character, ArrayList<Location>>();
        var antinodes = new HashSet<Pair<Integer, Integer>>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                var freq = matrix[i][j];
                if (freq != '.') {
                    var coord = new Location(i, j);
                    antenas.putIfAbsent(freq, new ArrayList<Location>());
                    antenas.get(freq).add(coord);
                }
            }
        }

        for (var locations : antenas.values()) {

            for (int i = 0; i < locations.size(); i++) {
                for (int j = 0; j < locations.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    var a = locations.get(i);
                    var b = locations.get(j);
                    var dist = distance(a, b);

                    if (dist > 0) {
                        buildAntinodes(matrix, antinodes, a, b);
                        buildAntinodes(matrix, antinodes, b, a);
                    }
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println("ATRIX");

        return String.valueOf(antinodes.size());
    }

    private void buildAntinodes(char[][] matrix, HashSet<Pair<Integer, Integer>> antinodes, Location a, Location b) {
        var x1 = a.x;
        var y1 = a.y;

        var x2 = b.x;
        var y2 = b.y;

        var dx = x2 - x1;
        var dy = y2 - y1;

        var gcd = gcd(Math.abs(dx), Math.abs(dy));
        int stepX = dx / gcd;
        int stepY = dy / gcd;

        a = new Location(a.x + stepX, a.y + stepY);

        while (!Utils.isOutOfBounds(matrix, a.x, a.y)) {
            if (matrix[a.x][a.y] == '.') {
                matrix[a.x][a.y] = '#';
            }
            antinodes.add(Pair.of(a.x, a.y));

            a = new Location(a.x + stepX, a.y + stepY);
        }
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
      }

    private int distance(Location a, Location b) {
        return (int) Math.sqrt(Math.pow(Math.abs(a.x - b.x), 2) + Math.pow(Math.abs(a.y - b.y), 2));
    }

}
