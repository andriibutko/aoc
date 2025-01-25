package aoc.day16;

import java.util.*;

import aoc.Utils;
import aoc.common.*;

public class Day16 implements aoc.Day {

    @Override
    public String part1(String input) {
        return String.valueOf(calculateSafeness(grid, boxMark));
    }

    @Override
    public String part2(String input) {
      
        return String.valueOf(calculateSafeness(grid, lBoxMark));
    }

}
