package aoc.day25;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import aoc.Utils;
import aoc.common.Pair;
import static aoc.common.Pair.pair;

public class Day25 implements aoc.Day {

    @Override
    public String part1(String input) {
        Stream<char[][]> parts = Stream.of(input.split("\\n\\n")).map(Utils::matrixFrom);

        ArrayList<int[]> keys = new ArrayList<>();
        ArrayList<int[]> locks = new ArrayList<>();

        parts.forEach(matrix -> {
            int[] pin = new int[matrix[0].length];
            for (int col = 0; col < matrix[0].length; col++) { 
                int c = 0;
                for (int row = 0; row < matrix.length; row++) {
                    if (matrix[row][col] == '#') c++;
                }
                pin[col] = c;
            }

            if(matrix[0][0] == '#') {
                keys.add(pin);
            } else {
                locks.add(pin);
            }
        });   

        int matches = 0;

        for (int[] key : keys) {
            for (int[] lock : locks) {
                boolean matched = true;
                for (int i = 0; i < key.length; i++) {
                    if(key[i] + lock[i] > 7) {
                        matched = false;
                        break;
                    }
                }
                if(matched) matches++;
            }
        }

        return String.valueOf(matches);
    }

    @Override
    public String part2(String input) {
        
        return "";
    }

}
