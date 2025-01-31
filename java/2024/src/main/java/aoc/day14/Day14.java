package aoc.day14;

import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

import aoc.Utils;
import aoc.common.Pair;


public class Day14 implements aoc.Day {

    /*
     * Part 2:
     * Run to get an output:
     * ./gradlew run --args="14 2" > gradlew_output.txt
     * 
     * There is a detailed explanation on how to solve this part programmatically:
     * https://www.youtube.com/watch?v=hhRC8XrXY1o 
     */

     int h = 103;
     int w = 101;

     String pattern = "p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)";

     Pattern r = Pattern.compile(pattern);

     public record Robot(int x, int y, int vx, int vy) {
     }

    @Override
    public String part1(String input) {
        int seconds = 100;

        var lines = Utils.splitLines(input);

        ArrayList<Robot> robots = new ArrayList<>();

        for(var line : lines) {
            var m = r.matcher(line);
            if (m.find()) {
                var x = Integer.parseInt(m.group(1));
                var y = Integer.parseInt(m.group(2));
                var vx = Integer.parseInt(m.group(3));
                var vy = Integer.parseInt(m.group(4));
                
                robots.add(new Robot(x, y, vx, vy));
            }
        }
        
        for(int j = 0; j < seconds; j++) {
            for (int i = 0; i < robots.size(); i++) {
                var r = robots.get(i);
                var nx = (r.x + r.vx + w) % w;
                var ny = (r.y + r.vy + h) % h;
                robots.set(i, new Robot(nx, ny, r.vx, r.vy));
            }
        }

        var i = 0;
        var ii = 0;
        var iii = 0;
        var iv = 0;
        for (var r : robots) {
            if (r.x < w / 2) {
                if (r.y < h / 2) i++; else if (r.y > h / 2) ii++;
            } else if (r.x > w / 2) {
                if (r.y < h / 2) iii++; else if (r.y > h / 2) iv++;
            }
        }
     
        return String.valueOf(i * ii * iii * iv);
    }

    @Override
    public String part2(String input) {

        var lines = Utils.splitLines(input);

        ArrayList<Robot> robots = new ArrayList<>();

        for(var line : lines) {
            var m = r.matcher(line);
            if (m.find()) {
                var x = Integer.parseInt(m.group(1));
                var y = Integer.parseInt(m.group(2));
                var vx = Integer.parseInt(m.group(3));
                var vy = Integer.parseInt(m.group(4));
                
                robots.add(new Robot(x, y, vx, vy));
            }
        }

        // stringify the state of the robots for the visualization
        var states = new HashSet<String>();
        
        int seconds = 1;
        while(seconds < 100000) {
            var state = state(robots);

            // if the state is already in the set, we have found a loop
            // this way we can minimize the initial output, after which we can analyze the output for the abnormal distributions to understand what we are looking for
            if (states.contains(state)) {
               break;
            }

            states.add(state);

            for (int i = 0; i < robots.size(); i++) {
                var r = robots.get(i);
                var nx = (r.x + r.vx + w) % w;
                var ny = (r.y + r.vy + h) % h;
                robots.set(i, new Robot(nx, ny, r.vx, r.vy));
            }

            // calculate the distribution of the robots in the quadrants
            var i = 0;
            var ii = 0;
            for (var r : robots) {
                if ((r.x + r.y) < (w + h) / 4) i++; // top left
                if (((w - r.x) + r.y) < (w + h) / 4) ii++; // top right
            }
            
            // the 50 is a heuristic to print only uneven distributions to minimize the output
            if (i + ii < 50) printIteration(robots, seconds); 

            seconds++;
        }

        return String.valueOf(seconds);
    }

    private void printIteration(ArrayList<Robot> robots, int seconds) {
        System.out.printf("\n\n--- %d ---\n\n", seconds);
        var points = robots.stream().map(v -> Pair.pair(v.x, v.y))
            .collect(Collectors.toSet());
            for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                System.out.print(points.contains(Pair.of(x, y)) ? "#" : ".");
            }
            System.out.println();
        }
    }

    public String state(List<Robot> bots) {
        return bots.stream()
        .map(bot -> bot.x + "," + bot.y)
        .collect(Collectors.joining(" "));
    }

}
