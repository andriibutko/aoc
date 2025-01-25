package aoc.common;

public record Location(int x, int y) {
    public Location move(int dx, int dy) {
        return new Location(x + dx, y + dy);
    }

    public Location move(Direction dir) {
        return move(dir.dx(), dir.dy());
    }
}