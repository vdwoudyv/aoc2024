package aoc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class CellWorld<T extends Cell> {

    protected final HashMap<Coordinate, T> cellMap;

    public CellWorld() {
        this.cellMap = new HashMap<>();
    }

    public void init() {
        cellMap.values().stream().sorted().forEach(Cell::init);
    }

    public void addCell(Coordinate c, T cell) {
        cellMap.put(c, cell);
    }

    public T getCell(Coordinate coordinate) {
        return cellMap.get(coordinate);
    }

    public List<T> getCells() {
        return new ArrayList<>(cellMap.values().stream().sorted().toList());
    }

    public int getWidth() {
        return cellMap.values().stream().mapToInt(c-> c.getCoordinate().x()).max().orElse(-1)+1;
    }

    public int getHeight() {
        return cellMap.values().stream().mapToInt(c-> c.getCoordinate().y()).max().orElse(-1)+1;
    }

    public void printOut(Function<T, String> f) {
        int currentRow = 0;
        for (T c: getCells()) {
            if (c.getCoordinate().y() > currentRow) {
                System.out.println();
                currentRow = c.getCoordinate().y();
            }
                System.out.print(f.apply(c));
        }
    }
}
