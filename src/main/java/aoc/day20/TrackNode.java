package aoc.day20;

import aoc.util.Coordinate;
import aoc.util.routing.Node;

public class TrackNode extends Node {

    private Coordinate coordinate;

    public TrackNode(Coordinate c) {
        super(c.toString());
        coordinate = c;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
