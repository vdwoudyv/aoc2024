package aoc.day12;

import aoc.util.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Patch {

    private final List<FarmCell> cells;

    public Patch(FarmCell farmCell) {
        cells = new ArrayList<>();
        addCell(farmCell);
    }

    public void addCell(FarmCell toAdd) {
        if (toAdd.getPatch() == null) {
            cells.add(toAdd);
            toAdd.setPatch(this);
        } else {
            throw new IllegalArgumentException("Already in other patch");
        }
    }

    public void removeCell(FarmCell toDelete) {
        if (cells.contains(toDelete)) {
            toDelete.setPatch(null);
            this.cells.remove(toDelete);
        }
    }

    public int size() {
        return cells.size();
    }

    public List<FarmCell> getCells() {
        return cells;
    }

    public void merge(Patch other) {
        if (size() >= other.size()) {
            for (FarmCell c: new ArrayList<>(other.getCells())) {
                other.removeCell(c);
                this.addCell(c);
            }
        } else {
            other.merge(this);
        }
    }

    public long getCost() {
        long totalPerimeter = cells.stream().mapToLong(FarmCell::getPerimeter).sum();
        return totalPerimeter * cells.size();
    }

    public long getSideCostBasedOnSides() {
        return getNumberOfSides() * cells.size();
    }

    public long getNumberOfSides() {
        return getNbSidesFor((cell) -> cell.getCoordinate().y(), Direction.WEST, Direction.NORTH, Direction.SOUTH)
                + getNbSidesFor((cell) -> cell.getCoordinate().x(), Direction.NORTH, Direction.WEST, Direction.EAST);
    }

    private long getNbSidesFor(Function<FarmCell, Integer> coordinateSelector,
                               Direction start, Direction firstSide, Direction secondSide) {

        Map<Integer, List<FarmCell>> cellDirectionMap = cells.stream().collect(Collectors.groupingBy(coordinateSelector));
        List<Integer> iterationList = cellDirectionMap.keySet().stream().sorted().toList();
        int numberOfSides = 0;
        boolean continues;
        for (Integer integer : iterationList) {
            continues = false;
            // All cells in the column, sorted one by one.
            List<FarmCell> currentSlice = cellDirectionMap.get(integer).stream().sorted().toList();

            for (Direction edgeDirection : List.of(firstSide, secondSide)) {
                FarmCell previous = null;
                for (FarmCell currentCell : currentSlice) {
                    if (previous != null) {
                        Optional<FarmCell> cellNeighbour = currentCell.getNeighbour(start);
                        if (cellNeighbour.isPresent() && !cellNeighbour.get().equals(previous)) {
                            continues = false;
                        }
                    }
                    Optional<FarmCell> potentialEdgeNeighbour = currentCell.getNeighbour(edgeDirection);
                    if (potentialEdgeNeighbour.isEmpty() || !potentialEdgeNeighbour.get().getContent().equals(currentCell.getContent())) {
                        // There is a topperimeter
                        if (!continues) {
                            continues = true;
                            numberOfSides++;
                        }
                    } else {
                        if (continues) {
                            continues = false;
                        }
                    }
                    previous = currentCell;
                }
                continues = false;
            }
        }
        return numberOfSides;
    }
}
