package aoc.day10;

import aoc.util.Coordinate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World {

    private Map<Coordinate, Cell> cells = new HashMap<>();

    public World(List<List<String>> input) {
        for (int row = 0; row < input.size(); row++) {
            for (int column = 0; column < input.get(0).size(); column++) {
                Coordinate c = new Coordinate(column, row);
                int value = Integer.parseInt(input.get(row).get(column));
                cells.put(c, new Cell(c, value, this));
            }
        }
        for (int i = 8; i >= 0; i--) {
            int finalI = i;
            cells.values().stream().filter(c -> c.getValue() == finalI).forEach(Cell::updateReachableTops);

        }
    }

    public Cell getCell(Coordinate coord) {
        return cells.get(coord);
    }

    public Map<Coordinate, Cell> getCells() {
        return cells;
    }
}
