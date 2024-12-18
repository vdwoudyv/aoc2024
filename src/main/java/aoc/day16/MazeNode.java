package aoc.day16;

import aoc.util.Coordinate;
import aoc.util.routing.Node;

public class MazeNode extends Node {

    private final Maze maze;
    private final Coordinate coordinate;
    private final boolean vertical;

    public MazeNode(String name, Coordinate c, Maze m, boolean isVertical) {
        super(name);
        this.coordinate = c;
        this.maze = m;
        this.vertical = isVertical;
    }

    public Maze getMaze() {
        return maze;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isVertical() {
        return vertical;
    }
}
