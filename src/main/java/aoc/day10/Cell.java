package aoc.day10;

import aoc.util.Coordinate;
import aoc.util.Direction;

import java.util.HashSet;
import java.util.Set;

public class Cell implements Comparable<Cell> {

    private World world;
    private Coordinate coordinate;
    private Integer value;
    private Set<Cell> reachableTops = new HashSet<>();
    private long nbPaths;

    public Cell(Coordinate c, Integer value, World w) {
        this.world = w;
        this.value = value;
        this.coordinate = c;
    }

    public Set<Cell> getReachableTops() {
        return reachableTops;
    }

    public void updateReachableTops() {
        for (Direction d : Direction.values()) {
            Cell neighbour = world.getCell(coordinate.getAdjacent(d));
            if (neighbour != null && neighbour.value == value + 1) {
                if (value == 8) {
                    reachableTops.add(neighbour);
                    nbPaths += 1L;
                } else {
                    reachableTops.addAll(neighbour.getReachableTops());
                    nbPaths += neighbour.getNbPaths();
                }
            }
        }
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Integer getValue() {
        return value;
    }

    public long getNbPaths() {
        return nbPaths;
    }

    public String toString() {
        return "[" + coordinate.x() + ", " + coordinate.y() + "]: " + value + "(" + nbPaths + ")";
    }

    @Override
    public int compareTo(Cell o) {
        return coordinate.compareTo(o.coordinate);
    }
}
