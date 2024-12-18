package aoc.util;

public abstract class Cell implements Comparable<Cell> {

    protected Coordinate c;
    private final String content;
    private final CellWorld world;

    public Cell(Coordinate c, String content, CellWorld world) {
        this.c = c;
        this.content = content;
        this.world = world;
    }

    public Coordinate getCoordinate() {
        return c;
    }

    public String getContent() {
        return content;
    }

    public CellWorld getWorld() {
        return world;
    }

    public void init() {}


    @Override
    public int compareTo(Cell o) {
        return getCoordinate().compareTo(o.getCoordinate());
    }
}
