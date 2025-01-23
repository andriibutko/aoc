package aoc.day15;

import java.util.*;

import aoc.Utils;
import aoc.common.*;

public class Day15 implements aoc.Day {


    private enum Direction {
        NORTH('^', -1, 0), EAST('>', 0, 1),
        SOUTH('v', 1, 0), WEST('<', 0, -1);

        public final char symbol;
        public final int dx;
        public final int dy;

        Direction(char symbol, int dx, int dy) {
            this.symbol = symbol;
            this.dx = dx;
            this.dy = dy;
        }

        public static Direction findBySymbol(int symbol) {
            return Arrays.stream(values()).filter(x -> x.symbol == symbol).findFirst().orElseThrow();
        }
    }

    char robotMark = '@';
    char boxMark = 'O';
    char lBoxMark = '[';
    char rBoxMark = ']';
    char wallMark = '#';
    char emptyMark = '.';

    Location robot = new Location(0, 0);

    @Override
    public String part1(String input) {
        var parts = input.split("\n\n");

        var grid = Utils.matrixFrom(parts[0]);

        var instructions = parts[1].lines()
            .flatMapToInt(CharSequence::chars)
            .toArray();

        robot = Utils.getLocation(grid, robotMark);

        for (var instruction : instructions) {
            moveRobot(grid, instruction);
        }

        return String.valueOf(calculateSafeness(grid, boxMark));
    }

    private void moveRobot(char[][] grid, int instruction) {
        Direction dir = Direction.findBySymbol(instruction);

        var next = robot.move(dir.dx, dir.dy);

        if (grid[next.x()][next.y()] == wallMark) return;

        Stack<Location> stack = new Stack<>();
        Location tmp = next;
        while(grid[tmp.x()][tmp.y()] == boxMark) {
            stack.add(tmp);
            tmp = tmp.move(dir.dx, dir.dy);
            if(grid[tmp.x()][tmp.y()] == wallMark) {
                stack.clear();
                break;
            }
        }

        while(!stack.isEmpty()) {
            var loc = stack.pop();
            grid[tmp.x()][tmp.y()] = boxMark;
            grid[loc.x()][loc.y()] = emptyMark;
            tmp = loc;
        }

        if(grid[next.x()][next.y()] == emptyMark) {
            grid[next.x()][next.y()] = robotMark;
            grid[robot.x()][robot.y()] = emptyMark;
            robot = next;
        }
    }

    @Override
    public String part2(String input) {
        var parts = input.split("\n\n");

        List<String> gridLines = new ArrayList<>(Utils.streamLines(parts[0]).toList());

        var instructions = parts[1].lines()
                .flatMapToInt(CharSequence::chars)
                .toArray();
        
        for (int i = 0; i < gridLines.size(); i++) {
            StringBuilder scaledLine = new StringBuilder();
            for (char c : gridLines.get(i).toCharArray()) {
                if (c == emptyMark) scaledLine.append("..");
                else if (c == wallMark) scaledLine.append("##");
                else if (c == boxMark) scaledLine.append("[]");
                else if (c == robotMark) scaledLine.append("@.");
            }
            gridLines.set(i, scaledLine.toString());
        }

        var grid = gridLines.stream().map(String::toCharArray).toArray(char[][]::new);

        robot = Utils.getLocation(grid, robotMark);

        for(var instruction : instructions) {
            move(grid, robot, Direction.findBySymbol(instruction));
        }

        return String.valueOf(calculateSafeness(grid, lBoxMark));
    }

    private void move(char[][] grid, Location curr, Direction direction) {
        List<Location> movedBlocks = new ArrayList<>();
        if (canMove(grid, curr.x(), curr.y(), direction, movedBlocks)) {
            movedBlocks.forEach(p -> {
                int newX = p.x() + direction.dx;
                int newY = p.y() + direction.dy;

                if (grid[p.x()][p.y()] == robotMark) {
                    robot = new Location(newX, newY);
                }
                grid[newX][newY] = grid[p.x()][p.y()];
                grid[p.x()][p.y()] = emptyMark;
            });
        }
    }

    private boolean canMove(char[][] grid, int x, int y, Direction direction, List<Location> movedBlocks) {
        if (movedBlocks.contains(new Location(x, y))) return true;

        int newX = x + direction.dx;
        int newY = y + direction.dy;

        if (Utils.isOutOfBounds(grid, newX, newY) || grid[newX][newY] == wallMark) return false;
        if (direction == Direction.WEST || direction == Direction.EAST) {
            if ((grid[newX][newY] == lBoxMark || grid[newX][newY] == rBoxMark) && !canMove(grid, newX, newY, direction, movedBlocks))
                return false;
        }
        else {
            if (grid[newX][newY] == lBoxMark && (!canMove(grid, newX, newY, direction, movedBlocks)
                || !canMove(grid, newX, newY + 1, direction, movedBlocks))) return false;
            if (grid[newX][newY] == rBoxMark && (!canMove(grid, newX, newY, direction, movedBlocks)
                || !canMove(grid, newX , newY - 1, direction, movedBlocks))) return false;
        }

        movedBlocks.add(new Location(x, y));
        return true;
    }

    private long calculateSafeness(char[][] grid, char mark) {
        long res = 0;
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[x].length; y++) {
                if (grid[x][y] == mark) {
                    res += 100L * x + y;
                }
            }
        }
     
        return res;
    }

}
